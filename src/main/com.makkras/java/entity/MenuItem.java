package java.entity;

public class MenuItem {
    private int id;
    private String name;
    private int price;


    public MenuItem(int id, String name, int price) {
        this.name = name;
        this.price= price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
