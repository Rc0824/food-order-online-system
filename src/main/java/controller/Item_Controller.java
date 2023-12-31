package controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;

import app.*;
import tools.*;

@WebServlet("/api/item")

public class Item_Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CartHelper ch = CartHelper.getHelper();

    public Item_Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        /** 取出經解析到JSONObject之Request參數 */
        JSONObject jso = jsr.getObject();

        JSONObject resp = new JSONObject();

        JSONObject query = ch.deleteCartAll();
        resp.put("status", "200");
        resp.put("message", "購物車資料刪除成功");
        resp.put("response", query);

        jsr.response(resp, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    
}
