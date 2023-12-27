package app;

public class Cart {
    
    private int id;
    private int total;
    private int food_id;

    public Cart(int id, int total, int food_id) {
        this.id = id;
        this.total = total;
        this.food_id = food_id;
    }

    public int getId() {
        return id;
    }

    public int getTotal() {
        return total;
    }

    public int getFood_id() {
        return food_id;
    }

}
