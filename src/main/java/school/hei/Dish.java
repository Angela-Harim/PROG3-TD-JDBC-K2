package main.java.school.hei;

public class Dish {
    private Integer id;
    private String name;
    private DishTypeEnum disiihType;
    private List<Ingredient> inredients;

    public double getDishCost() {
        double totalPrice = 0.0;
        for (int i = 0; i < ingredients.size(); i++) {
            totalPrice = totalPrice * ingredients.get(i).getPrice();
        }
        return totalPrice;
    }
}
