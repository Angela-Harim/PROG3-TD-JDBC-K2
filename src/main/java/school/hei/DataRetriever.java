package school.hei;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                Dish dish = new Dish(
                dish.setId(resultSet.getInt("dish_id")),
                dish.setName(resultSet.getString("dish_name")),
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

    List<Ingredient> createIngredients(List<Ingredient> newIngredients) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();

        try {
            connection.setAutoCommit(false);

            for (Ingredient ingredient : newIngredients) {
                PreparedStatement checkStmt = connection.prepareStatement(
                        "SELECT id FROM ingredient WHERE name = ?"
                );
                checkStmt.setString(1, ingredient.getName());
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    throw new RuntimeException(
                            "Ingredient already exists: " + ingredient.getName()
                    );
                }

                PreparedStatement insertStmt = connection.prepareStatement(
                        """
                        insert into ingredient (name, price, category)
                        values (?, ?, ?)
                        """
                );
                insertStmt.setString(1, ingredient.getName());
                insertStmt.setDouble(2, ingredient.getPrice());
                insertStmt.setString(3, ingredient.getCategory().name());
                insertStmt.executeUpdate();
            }

            connection.commit();
            return newIngredients;

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ignored) {}
            throw new RuntimeException(e);
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    void saveDish(Dish dish) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();

        try {
            connection.setAutoCommit(false);

            PreparedStatement checkStmt = connection.prepareStatement(
                    "select id from dish where id = ?"
            );
            checkStmt.setInt(1, dish.getId());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                PreparedStatement updateStmt = connection.prepareStatement(
                        "update dish set name = ?, dish_type = ? where id = ?"
                );
                updateStmt.setString(1, dish.getName());
                updateStmt.setString(2, dish.getDishType().name());
                updateStmt.setInt(3, dish.getId());
                updateStmt.executeUpdate();
            } else {
                PreparedStatement insertStmt = connection.prepareStatement(
                        """
                        insert into dish (id, name, dish_type)
                        values (?, ?, ?)
                        """
                );
                insertStmt.setInt(1, dish.getId());
                insertStmt.setString(2, dish.getName());
                insertStmt.setString(3, dish.getDishType().name());
                insertStmt.executeUpdate();
            }

            PreparedStatement deleteLink = connection.prepareStatement(
                    "delete from dish_ingredient where dish_id = ?"
            );
            deleteLink.setInt(1, dish.getId());
            deleteLink.executeUpdate();

            for (Ingredient ingredient : dish.getIngredients()) {
                PreparedStatement linkStmt = connection.prepareStatement(
                        """
                        insert into dish_ingredient (dish_id, ingredient_id)
                        values (?, ?)
                        """
                );
                linkStmt.setInt(1, dish.getId());
                linkStmt.setInt(2, ingredient.getId());
                linkStmt.executeUpdate();
            }

            connection.commit();

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ignored) {}
            throw new RuntimeException(e);
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    List<Dish> findDishesByIngredientName(String ingredientName) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();
        List<Dish> dishes = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    """
                    select d.id
                    from dish d
                    join dish_ingredient di on di.dish_id = d.id
                    join ingredient i on i.id = di.ingredient_id
                    where i.name like ?
                    """
            );
            stmt.setString(1, "%" + ingredientName + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                dishes.add(findDishById(rs.getInt("id")));
            }

            return dishes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

}

