package main.java;

package school.hei;

import java.time.Instant;

public class StockMovement {
    private Integer id;
    private Ingredient ingredient;
    private double quantity;
    private String unit;
    private Instant datetime;

    public StockMovement(
            Integer id,
            Ingredient ingredient,
            double quantity,
            String unit,
            Instant datetime
    ) {
        this.id = id;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
        this.datetime = datetime;
    }

    public Integer getId() {
        return id;
    }

    public double getQuantity() {
        return quantity;
    }

    public Instant getDatetime() {
        return datetime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setDatetime(Instant datetime) {
        this.datetime = datetime;
    }
}

