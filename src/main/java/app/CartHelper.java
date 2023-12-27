package app;

import java.sql.*;
import org.json.*;

import util.DBMgr;

public class CartHelper {
    private CartHelper() {
        
    }
    
    private static CartHelper ch;
    private Connection conn = null;
    private PreparedStatement pres = null;
    
    public static CartHelper getHelper() {
        /** Singleton檢查是否已經有ProductHelper物件，若無則new一個，若有則直接回傳 */
        if(ch == null) ch = new CartHelper();
        
        return ch;
    }
    
    public JSONObject getAll() {
        /** 新建一個 Product 物件之 m 變數，用於紀錄每一位查詢回之商品資料 */
    	Cart c = null;
        /** 用於儲存所有檢索回之商品，以JSONArray方式儲存 */
        JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT * FROM `tbl_cart`, `tbl_cart_food_connect`, `tbl_food` WHERE `tbl_cart`.`cart_id` = `tbl_cart_food_connect`.`cart_id_con` AND `tbl_cart_food_connect`.`food_id_con` = `tbl_food`.`food_id`";
            
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** 透過 while 迴圈移動pointer，取得每一筆回
             * 傳資料 */
            while(rs.next()) {
                /** 每執行一次迴圈表示有一筆資料 */
                row += 1;
                
                /** 將 ResultSet 之資料取出 */
                int id = rs.getInt("cart_id");
                int food_id = rs.getInt("food_id_con");
                int price = rs.getInt("food_price");
                int quantity = rs.getInt("food_num");
                int subtotal = rs.getInt("cart_total");
                
                /** 將每一筆商品資料產生一名新Product物件 */
                c = new Cart(id, food_id, price, quantity, subtotal);
                /** 取出該項商品之資料並封裝至 JSONsonArray 內 */
                jsa.put(c.getData());

            }
        } catch(SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
            /** 記錄錯誤次數並暫存至 Log */
            return null;
        } catch(Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
            /** 記錄錯誤次數並暫存至 Log */
            return null;
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }
        /** 紀錄程式結束執行時間 */
        long end_time = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end_time - start_time);
        
        /** 將SQL指令、花費時間、影響行數與所有會員資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;

    }
}