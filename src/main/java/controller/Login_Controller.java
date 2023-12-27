package controller;

import javax.servlet.http.*;
import org.json.JSONObject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URLEncoder;

import tools.JsonReader;
import app.*;

public class Login_Controller extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        /** 取出經解析到JSONObject之Request參數 */
        String email = jso.getString("user_email");
        String password = jso.getString("user_password");
        
        /** 建立一個新的會員物件 */
        Member m = new Member(email, password);

        if(email.isEmpty() || password.isEmpty()) {
            /** 以字串組出JSON格式之資料 */
            String resp = "{\"status\": \'400\', \"message\": \'欄位不能有空值\', \'response\': \'\'}";
            /** 透過JsonReader物件回傳到前端（以字串方式） */
            jsr.response(resp, response);
        }
        else if(m.login(m)){

            JSONObject data = m.getDataByEmail(email);

            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "登入成功");
            resp.put("response", data);

            /** 建立一個新的 Cookie */
            String dataString = URLEncoder.encode(data.toString(), "UTF-8");
            Cookie loginCookie = new Cookie("user_data", dataString);
            loginCookie.setPath("/demo/");

            // 設定 cookie 的壽命為 30 分鐘
            loginCookie.setMaxAge(30*60);
            // 將 cookie 加入 response
            response.addCookie(loginCookie);
            
            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);
        }
        else{
            /** 以字串組出JSON格式之資料 */
            String resp = "{\"status\": \'400\', \"message\": \'登入失敗，帳號或密碼錯誤\', \'response\': \'\'}";
            /** 透過JsonReader物件回傳到前端（以字串方式） */
            jsr.response(resp, response);
        }
    }
}