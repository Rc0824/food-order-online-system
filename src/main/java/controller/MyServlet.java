package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 獲取請求參數
        String param = request.getParameter("param");

        // 設定回應內容類型
        response.setContentType("json");
        // 獲取回應的寫入器
        PrintWriter out = response.getWriter();

        // 寫入回應內容
        out.println("<html><body>");
        out.println("<h1>" + param + "</h1>");
        out.println("</body></html>");

        // 關閉寫入器
        out.close();
    }
}