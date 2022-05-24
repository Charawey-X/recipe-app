package DAO;

import Database.DB;
import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {

    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/recipe_app_test", "x", "230620");
    Connection connection;
    public int id;

    @Override
    protected void before() {
        connection = sql2o.open();
    }

    @Override
    protected void after() {
        try (Connection connection = sql2o.open()) {
            String deleteRecipesQuery = "DELETE FROM recipes;";
            connection.createQuery(deleteRecipesQuery).executeUpdate();
        }
        connection.close();
    }
}
