package app;

import org.json.JSONObject;

public class Cart {
    
    private int id;
    private Food food;
    private int quantity;
    private int price;
    private int subtotal;
    private FoodHelper fh = FoodHelper.getHelper();

    public Cart(Food food, int quantity) {
        this.food = food;
        this.quantity = quantity;
        this.price = food.getPrice();
        this.subtotal = this.price * this.quantity;
    }

    public Cart(int cart_id, int food_id, int price, int quantity, int subtotal) {
        this.id = cart_id;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = this.price * this.quantity;
        getFoodFromDB(food_id);
    }

    public void getFoodFromDB(int food_id) {
        String id = String.valueOf(food_id);
        this.food = fh.getByID(id);
    }

    public int getId() {
        return id;
    }

    public Food getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JSONObject getData() {
        JSONObject jso = new JSONObject();
        jso.put("cart_id", getId());
        jso.put("food", getFood().getName());
        jso.put("price", getPrice());
        jso.put("quantity", getQuantity());
        jso.put("subtotal", getSubtotal());
        return jso;
    }

}
