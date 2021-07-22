package Model;

public class BabyNeeds {
    private String Item_name;
    private double Quantity;
    private String color;
    private double Size;
    private int item_Id;

    public BabyNeeds(String item_name, double quantity, String color, double size) {

        Item_name = item_name;
        Quantity = quantity;
        this.color = color;
        Size = size;
    }

    public BabyNeeds() {
    }

    public BabyNeeds( int item_Id,String item_name, double quantity, String color, double size) {
        Item_name = item_name;
        Quantity = quantity;
        this.color = color;
        Size = size;
        this.item_Id = item_Id;
    }

    public String getItem_name() {
        return Item_name;
    }

    public void setItem_name(String item_name) {
        Item_name = item_name;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getSize() {
        return Size;
    }

    public void setSize(double size) {
        Size = size;
    }

    public int getItem_Id() {
        return item_Id;
    }

    public void setItem_Id(int item_Id) {
        this.item_Id = item_Id;
    }
}
