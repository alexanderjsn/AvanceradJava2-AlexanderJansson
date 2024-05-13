package org.example;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class JSONBlueprint {
    @SerializedName("A")
    private String orderDate;

    @SerializedName("B")
    private String Region;

    @SerializedName("C")
    private String Rep1;

    @SerializedName("D")
    private String Rep2;

    @SerializedName("E")
    private String Item;

    @SerializedName("F")
    private String Units;

    @SerializedName("G")
    private String UnitCost;

    @SerializedName("H")
    private String Total;

    public JSONBlueprint(String orderDate, String region, String rep1, String rep2, String item, String units, String unitCost, String total) {
        this.orderDate = orderDate;
        Region = region;
        Rep1 = rep1;
        Rep2 = rep2;
        Item = item;
        Units = units;
        UnitCost = unitCost;
        Total = total;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getRep1() {
        return Rep1;
    }

    public void setRep1(String rep1) {
        Rep1 = rep1;
    }

    public String getRep2() {
        return Rep2;
    }

    public void setRep2(String rep2) {
        Rep2 = rep2;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public String getUnits() {
        return Units;
    }

    public void setUnits(String units) {
        Units = units;
    }

    public String getUnitCost() {
        return UnitCost;
    }

    public void setUnitCost(String unitCost) {
        UnitCost = unitCost;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

}

