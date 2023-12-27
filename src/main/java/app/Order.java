package app;

import java.sql.Timestamp;
import org.json.JSONObject;
import java.util.*;

public class Order {
    
    private int id;
    private Timestamp order_time;
    private int total;
    private String captcha;
    private ArrayList<Cart> orderlist;

    public Order(int id, Timestamp order_time, int total, String captcha) {
        this.id = id;
        this.order_time = order_time;
        this.total = total;
        this.captcha = captcha;
    }

    public int getId() {
        return id;
    }

    public Timestamp getOrder_time() {
        return order_time;
    }

    public int getTotal() {
        return total;
    }

    public String getCaptcha() {
        return captcha;
    }

    public ArrayList<Cart> getOrderList() {
        return orderlist;
    }

    public JSONObject getOrderInfo() {
        JSONObject jso = new JSONObject();
        jso.put("id", getId());
        jso.put("order_time", getOrder_time());
        jso.put("total", getTotal());
        jso.put("captcha", getCaptcha());
        return jso;
    }


}
