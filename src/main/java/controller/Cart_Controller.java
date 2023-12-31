package controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;

import app.*;
import tools.*;

@WebServlet("/api/cart")
public class Cart_Controller extends HttpServlet{
    private static final long serialVersionUID = 1L;

	private CartHelper ch = CartHelper.getHelper();

    public Cart_Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        /** 若直接透過前端AJAX之data以key=value之字串方式進行傳遞參數，可以直接由此方法取回資料 */
        String id_list = jsr.getParameter("id_list");

        JSONObject resp = new JSONObject();

        JSONObject query = ch.getAll();
        resp.put("status", "200");
        resp.put("message", "所有購物車資料取得成功");
        resp.put("response", query);

        jsr.response(resp, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        /** 取出經解析到JSONObject之Request參數 */
        JSONObject jso = jsr.getObject();

        JSONObject resp = new JSONObject();

        if(jso.has("food_name") && jso.has("food_quantity") && jso.has("user_id")){
            String name = jso.getString("food_name");
            int quantity = jso.getInt("food_quantity");
            int user_id = jso.getInt("user_id");
            int shop_user_id = jso.getInt("shop_user_id");

            // JSONObject data = fd.getDataByName(name);

            JSONObject query = ch.addCart(name, quantity, user_id, shop_user_id);
            resp.put("status", "200");
            resp.put("message", "購物車資料新增成功");
            resp.put("response", query);
        }else{
            resp.put("status", "400");
            resp.put("message", "購物車資料新增失敗");
        }

        jsr.response(resp, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();

        JSONObject resp = new JSONObject();

        if(jso.has("cart_id") && jso.has("food_name")){
            int cart_id = jso.getInt("cart_id");
            String food_name = jso.getString("food_name");
            int shop_user_id = jso.getInt("shop_user_id");

            JSONObject query = ch.deleteCart(cart_id, food_name, shop_user_id);
            resp.put("status", "200");
            resp.put("message", "購物車資料刪除成功");
            resp.put("response", query);
        }else{
            resp.put("status", "400");
            resp.put("message", "購物車資料刪除失敗");
        }

        jsr.response(resp, response);
    }

    
}
