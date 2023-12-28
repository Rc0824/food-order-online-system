package controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;

import app.*;
import tools.*;


public class Food_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FoodHelper fh =  FoodHelper.getHelper();

    public Food_Controller() {
        super();
        // TODO Auto-generated constructor stub
    }


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        /** 若直接透過前端AJAX之data以key=value之字串方式進行傳遞參數，可以直接由此方法取回資料 */
        String id_list = jsr.getParameter("id_list");

        JSONObject resp = new JSONObject();


        JSONObject query = fh.getAll();
        resp.put("status", "200");
        resp.put("message", "所有商品資料取得成功");
        resp.put("response", query);

        jsr.response(resp, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();

        String name = jso.getString("food_name");
        int price = jso.getInt("food_price");
        String photo = jso.getString("food_photo");
        String description = jso.getString("food_description");
        int shop_user_id = jso.getInt("shop_user_id");

        Food f = new Food(name, price, photo, description, shop_user_id);

        if(name.isEmpty() || price == 0 ) {
            String resp = "{\"status\": \'400\', \"message\": \'名稱或價錢不能有空值\', \'response\': \'\'}";
            /** 透過JsonReader物件回傳到前端（以字串方式） */
            jsr.response(resp, response);
        } else if(!fh.checkDuplicate(f)) {

            JSONObject data = fh.create(f);
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "成功! 新增餐點");
            resp.put("response", data);  
            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);

        }
        else{
            String resp = "{\"status\": \'400\', \"message\": \'餐點名稱重複\', \'response\': \'\'}";
            /** 透過JsonReader物件回傳到前端（以字串方式） */
            jsr.response(resp, response);
        }
        
	}

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        /** 取出經解析到JSONObject之Request參數 */
        int id = jso.getInt("food_id");
        String name = jso.getString("food_name");
        int price = jso.getInt("food_price");
        String photo = jso.getString("food_photo");
        String description = jso.getString("food_description");

        /** 透過傳入之參數，新建一個以這些參數之會員Member物件 */
        Food f = new Food(id, name, price, photo, description);
        
        /** 透過Member物件的update()方法至資料庫更新該名會員資料，回傳之資料為JSONObject物件 */
        JSONObject data = new JSONObject(); // Initialize the data variable
        if(id != 0) {
            data = fh.update(f);
        }
        /** 透過Member物件的update()方法至資料庫更新該名會員資料，回傳之資料為JSONObject物件 */
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "成功更新會員資料!");
        resp.put("response", data);
        
        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
        
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        /** 取出經解析到JSONObject之Request參數 */
        int id = jso.getInt("food_id");
        
        /** 透過MemberHelper物件的deleteByID()方法至資料庫刪除該名會員，回傳之資料為JSONObject物件 */
        JSONObject query = fh.deleteByID(id);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "會員移除成功！");
        resp.put("response", query);

        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }


}
