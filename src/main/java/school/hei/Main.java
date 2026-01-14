package school.hei;

import java.util.List;

public class Main {
   // public static void main(String[] args) {
   //    DataRetriever dataRetriever = new DataRetriever();
   //     Dish dish = dataRetriever.findDishById(1);
   //   System.out.println(dish);
   //}

    public static void main(String[] args) {
        Ingredient rice = new Ingredient(
                1,
                "Rice",
                CategoryEnum.ANIMAL,
                2.5,
                null,
                3.0
        );

        Dish dish = new Dish(
                1,
                "Riz nature",
                DishTypeEnum.MAIN,
                List.of(rice)
        );

        System.out.println(dish.getDishCost());
    }

}