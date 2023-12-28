package app;

import java.sql.Timestamp;
import org.json.JSONObject;
import java.util.*;

public class Order {
    
    private int id;
    private Timestamp order_time;
    private int total;
    private String captcha;
    private Food food;
    private FoodHelper fh = FoodHelper.getHelper();
    private User user ;
    private UserHelper uh = UserHelper.getHelper();


    public Order(int user_id, int id, Timestamp order_time, int total, String captcha, int food_id) {
        getUserFromDB(user_id);
        this.id = id;
        this.order_time = order_time;
        this.total = total;
        this.captcha = captcha;
        getFoodFromDB(food_id);
    }

    public Order(int id, Timestamp order_time, int total, String captcha) {
        this.id = id;
        this.order_time = order_time;
        this.total = total;
        this.captcha = captcha;
    }

    public void getFoodFromDB(int food_id) {
        String id = String.valueOf(food_id);
        this.food = fh.getByID(id);
    }

    public void getUserFromDB(int user_id) {
        String id = String.valueOf(user_id);
        this.user = uh.getByID(id);
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

    public Food getFood() {
        return food;
    }

    public User getUser() {
        return user;
    }

    public JSONObject getOrderInfo() {
        JSONObject jso = new JSONObject();
        jso.put("user_id", getUser().getId());
        jso.put("order_id", getId());
        jso.put("order_time", getOrder_time());
        jso.put("order_total", getTotal());
        jso.put("order_captcha", getCaptcha());
        jso.put("food", getFood().getData());
        return jso;
    }


}
