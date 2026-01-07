package main.java.school.hei;

import java.util.Objects;

public class Dish {
    private Integer id;
    private String name;
    private DishTypeEnum dishType;
    private List<Ingredient> inredients;

    public Dish(Integer id, String name, DishTypeEnum dishType, List<Ingredient> inredients) {
        this.id = id;
        this.name = name;
        this.dishType = dishType;
        this.inredients = inredients;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishTypeEnum getDishType() {
        return dishType;
    }

    public void setDishType(DishTypeEnum dishType) {
        this.dishType = dishType;
    }

    public List<Ingredient> getInredients() {
        return inredients;
    }

    public void setInredients(List<Ingredient> inredients) {
        this.inredients = inredients;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(id, dish.id) && Objects.equals(name, dish.name) && dishType == dish.dishType && Objects.equals(inredients, dish.inredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dishType, inredients);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishType=" + dishType +
                ", inredients=" + inredients +
                '}';
    }

    public double getDishCost() {
        double totalPrice = 0.0;
        for (int i = 0; i < ingredients.size(); i++) {
            totalPrice = totalPrice * ingredients.get(i).getPrice();
        }
        return totalPrice;
    }
}
