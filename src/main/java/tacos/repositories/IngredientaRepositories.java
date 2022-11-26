package tacos.repositories;

import tacos.constants.IngredientType;
import tacos.modells.Ingredient;

import java.sql.*;

public class IngredientaRepositories {
    String url="jdbc:postgresql://localhost:5432/taco-cloud";
    String username="postgres";
    String password="khumoyun9779";


    public Ingredient findOne(String id)  {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.prepareStatement(
                    "select ?, name, type from Ingredient");
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            Ingredient ingredient = null;
            if (resultSet.next()) {
                ingredient = new Ingredient(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        IngredientType.valueOf(resultSet.getString("type")));
            }
            return ingredient;
        } catch (SQLException e) {
            // ??? What should be done here ???
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {}
            }if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {}
            }
        }
        return null;
    }
}
