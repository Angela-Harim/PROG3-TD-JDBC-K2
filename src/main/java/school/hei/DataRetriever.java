package main.java.school.hei;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DataRetriever {

    Dish findDishById(int id) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();

        try {
            PreparedStatement prepareStatement = connection.prepareStatement(
                    """
                    select dish.id as dish_id, dish.name as dish_name, dish.dish_type
                    from dish
                    where dish.id = ?
                    """
            );

            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                Dish dish = new Dish();
                dish.setId(resultSet.getInt("dish_id"));
                dish.setName(resultSet.getString("dish_name"));
                dish.setDishType(
                        DishTypeEnum.valueOf(resultSet.getString("dish_type"))
                );
                dish.setIngredients(findIngredientByDishId(id));
                return dish;
            }

            throw new RuntimeException("Dish not found " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    private List<Ingredient> findIngredientByDishId(Integer id) {
        throw new RuntimeException(
                "TODO : select ingredient.id, ingredient.name, ingredient.price, ingredient.category " +
                        "from ingredient " +
                        "where dish_id = ?"
        );
    }
}

