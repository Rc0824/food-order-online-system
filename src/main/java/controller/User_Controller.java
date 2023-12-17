package controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import javax.servlet.ServletException;
import java.io.IOException;
import tools.JsonReader;
import app.*;


public class User_Controller extends HttpServlet {

    Admin admin = new Admin();

    @Override
    // public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //     response.getWriter().write("Hello from User_Controller!");
    // }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        /** 若直接透過前端AJAX之data以key=value之字串方式進行傳遞參數，可以直接由此方法取回資料 */
        String id = jsr.getParameter("user_id");
        
        /** 判斷該字串是否存在，若存在代表要取回個別會員之資料，否則代表要取回全部資料庫內會員之資料 */
        if (id.isEmpty()) {
            /** 透過MemberHelper物件之getAll()方法取回所有會員之資料，回傳之資料為JSONObject物件 */
            JSONObject query = admin.getAll();
            
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "所有資料取得成功");
            resp.put("response", query);
    
            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);
        }
        else {
            /** 透過MemberHelper物件的getByID()方法自資料庫取回該名會員之資料，回傳之資料為JSONObject物件 */
            JSONObject query = admin.getAll();
            
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "會員資料取得成功");
            resp.put("response", query);
    
            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);
        };
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST request here
    }

    public void doPut( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */

    }
}