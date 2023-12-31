package controller;

import javax.servlet.http.*;
import javax.servlet.*;
import org.json.JSONObject;
import tools.JsonReader;
import java.io.IOException;
import app.*;

public class Order_Controller extends HttpServlet{

    private static final long serialVersionUID = 1L;
    private FoodHelper fh =  FoodHelper.getHelper();
	private OrderHelper oh =  OrderHelper.getHelper();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** 透過 JsonReader 類別將 Request 之 JSON 格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);

        /** 新建一個 JSONObject 用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();

        /** 透過 orderHelper 物件的 getByID() 方法自資料庫取回該筆訂單之資料，回傳之資料為 JSONObject 物件 */
        JSONObject data = oh.getAll();
        resp.put("status", "200");
        resp.put("message", "單筆訂單資料取得成功");
        resp.put("response", data);


        /** 透過 JsonReader 物件回傳到前端（以 JSONObject 方式） */
        jsr.response(resp, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** 透過 JsonReader 類別將 Request 之 JSON 格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();

        JSONObject resp = new JSONObject();
        if(jso.has("order_datetime") && jso.has("user_id")){
            String order_time = jso.getString("order_datetime");
            int user_id = jso.getInt("user_id");
            int total = jso.getInt("order_total");
            String captcha = jso.getString("order_captcha");
            int food_id = jso.getInt("food_id");
            int food_num = jso.getInt("food_num");

            JSONObject query = oh.addOrder(order_time, user_id, total, captcha, food_id, food_num);
            resp.put("status", "200");
            resp.put("message", "訂單資料新增成功");
            resp.put("response", query);
        }
        else{
            resp.put("status", "400");
            resp.put("message", "訂單資料新增失敗");
        }
        jsr.response(resp, response);
    }

}