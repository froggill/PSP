package java.entity;

public class Order {
    private int id;
    private String name;
    private int quantity;
    private int user_id;

    public Order(int id, String name, int quantity, int user_id) {
        this.name = name;
        this.quantity = quantity;
        this.user_id = user_id;
        this.id = id;
    }

    public Order(int id, String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUser_id() {
        return user_id;
    }
}
