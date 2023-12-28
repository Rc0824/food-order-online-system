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
        // Handle POST request here
    }

}