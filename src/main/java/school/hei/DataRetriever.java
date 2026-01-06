package main.java.school.hei;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataRetriever {
    Dish findDishById(int id) {
        DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.getConnectioin();
        try {
                PreparedStatement prepareStatement = connection.prepareStatement(
                        sql """ select dish.id as dish_id, dish.name as dish_name, dish_type
                                from dish
                                where dish.id = ? 
                            """);
                prepareStatement.setInt(1, id);
                ResultSet resultSet = prepareStatement.executeQuery();
                if (resultSet.next()) {
                    Dish dish = new Dish();
                    dish.setIt(resultSet.getInt("dish_id"));
                    dish.setName(resultSet.getString("dish_name"));
                    dish.setDishType(DishTypeEnum.valueOf(resultSet.getString("dish_type")));
                    return dish;
                }

            dbConnection.closeConnection(connection);
        throw new RuntimeException( "Dish not found" + id);
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
