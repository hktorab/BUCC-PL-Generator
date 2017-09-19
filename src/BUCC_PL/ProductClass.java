package BUCC_PL;

import javafx.beans.property.SimpleStringProperty;

public class ProductClass {

    protected   SimpleStringProperty cat,item,quantity,rate,cost;

    ProductClass(){

    }
    public ProductClass(String cat, String item, String quantity, String rate, String cost) {

        this.cat = new SimpleStringProperty( cat);
        this.item =new SimpleStringProperty(  item);
        this.quantity =new SimpleStringProperty(  quantity);
        this.rate = new SimpleStringProperty( rate);
        this.cost =new SimpleStringProperty(  cost);

    }

    public String getCat() {
        return cat.get();
    }


    public String getItem() {
        return item.get();
    }

    public String getQuantity() {
        return quantity.get();
    }

    public String getRate() {
        return rate.get();
    }

    public String getCost() {
        return cost.get();
    }



    public void setCat(String cat) {

        this.cat= new SimpleStringProperty(cat);
    }

    public void setItem(String item) {
        this.item=new SimpleStringProperty(item);
    }

    public void setQuantity(String quantity) {
        this.quantity=new SimpleStringProperty(quantity);
    }

    public void setRate(String rate) {
        this.rate=new SimpleStringProperty(rate);
    }

    public void setCost(String cost) {
        this.cost=new SimpleStringProperty(cost);
    }

    public String toString()
    {
        return String.format("%s %s", cat, item,quantity,rate,cost);
    }
}