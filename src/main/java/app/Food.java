package app;

import org.json.JSONObject;

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
}

