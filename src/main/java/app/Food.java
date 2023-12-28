package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import util.DBMgr;

public class Food {

    /** id，會員編號 */
    private int id;
    /** id，會員編號 */
    private String name;
    /** id，會員編號 */
    private int price;
    /** id，會員編號 */
    private String photo;
    /** id，會員編號 */
	private String description;
    /** id，餐廳以及會員編號 */
    private int shop_user_id;
    private Connection conn = null;
    private PreparedStatement pres = null;


    public Food() {
        
    }

    /**
     * 實例化（Instantiates）一個新的（new）Product 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增產品時
     *
     * @param id 產品編號
     */
	public Food(int id) {
		this.id = id;
	}

    /**
     * 實例化（Instantiates）一個新的（new）Product 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增產品時
     *
     * @param name 產品名稱
     * @param price 產品價格
     * @param image 產品圖片
     */
	public Food(String name, int price, String photo) {
		this.name = name;
		this.price = price;
		this.photo = photo;
	}

    /**
     * 實例化（Instantiates）一個新的（new）Product 物件<br>
     * 採用多載（overload）方法進行，此建構子用於修改產品時
     *
     * @param id 產品編號
     * @param name 產品名稱
     * @param price 產品價格
     * @param image 產品圖片
     * @param describe 產品敘述
     */
	public Food(int id, String name, int price, String photo, String description, int shop_user_id) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.photo = photo;
		this.description = description;
        this.shop_user_id = shop_user_id;
	}

    public Food(String name, int price, String photo, String description, int shop_user_id) {
        this.name = name;
        this.price = price;
        this.photo = photo;
        this.description = description;
        this.shop_user_id = shop_user_id;
    }

    public Food(int id ,String name, int price, String photo, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.photo = photo;
        this.description = description;
    }

    /**
     * 取得產品編號
     *
     * @return int 回傳產品編號
     */
	public int getID() {
		return this.id;
	}

    /**
     * 取得產品名稱
     *
     * @return String 回傳產品名稱
     */
	public String getName() {
		return this.name;
	}

    /**
     * 取得產品價格
     *
     * @return int 回傳產品價格
     */
	public int getPrice() {
		return this.price;
	}

    /**
     * 取得產品圖片
     *
     * @return String 回傳產品圖片
     */
	public String getPhoto() {
		return this.photo;
	}

    /**
     * 取得產品敘述
     *
     * @return String 回傳產品敘述
     */
	public String getDescription() {
		return this.description;
	}

    public int getShopUserId() {
        return this.shop_user_id;
    }

    /**
     * 取得產品資訊
     *
     * @return JSONObject 回傳產品資訊
     */
	public JSONObject getData() {
        /** 透過JSONObject將該項產品所需之資料全部進行封裝*/
        JSONObject jso = new JSONObject();
        jso.put("food_id", getID());
        jso.put("food_name", getName());
        jso.put("food_price", getPrice());
        jso.put("food_photo", getPhoto());
        jso.put("food_description", getDescription());
        jso.put("shop_user_id", getShopUserId());

        return jso;
    }

    public JSONObject getDataByName(String name){
        JSONObject jso = new JSONObject();
        try {
            // 建立與資料庫的連接
            conn = DBMgr.getConnection();
            // 建立 SQL 查詢
            String sql = "SELECT * FROM tbl_food WHERE food_name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
    
            // 執行查詢並獲取結果
            ResultSet rs = pstmt.executeQuery();
    
            // 如果查詢結果不為空，將用戶資料儲存到 JSONObject
            if (rs.next()) {
                jso.put("food_id", rs.getInt("food_id"));
                jso.put("food_name", rs.getString("food_email"));
                jso.put("food_price", rs.getString("food_price"));
                jso.put("food_photo", rs.getString("food_photo"));
                jso.put("food_description", rs.getString("food_description"));
                jso.put("shop_user_id", rs.getTimestamp("shop_user_id"));
            }
    
            // 關閉連接
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jso;
    }
}

