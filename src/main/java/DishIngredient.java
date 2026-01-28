package main.java;

import java.util.Objects;

public class DishIngredient {
    private Integer id;
    private double quantity_required;
    private String unit;

    public DishIngredient(Integer id, double quantity_required, String unit) {
        this.id = id;
        this.quantity_required = quantity_required;
        this.unit = unit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getQuantity_required() {
        return quantity_required;
    }

    public void setQuantity_required(double quantity_required) {
        this.quantity_required = quantity_required;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DishIngredient that = (DishIngredient) o;
        return Double.compare(quantity_required, that.quantity_required) == 0 && Objects.equals(id, that.id) && Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity_required, unit);
    }

    @Override
    public String toString() {
        return "DishIngredient{" +
                "id=" + id +
                ", quantity_required=" + quantity_required +
                ", unit='" + unit + '\'' +
                '}';
    }
}
