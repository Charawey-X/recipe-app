package DAO;

import Database.DB;
import org.junit.rules.ExternalResource;
import org.sql2o.Connection;

public class DatabaseRule extends ExternalResource {
    Connection con;
    public int id;

    @Override
    protected void before() {
        con = DB.sql2o.open();
    }

    @Override
    protected void after() {
        try (Connection con = DB.sql2o.open()) {
            String deleteRecipesQuery = "DELETE FROM recipes *;";
            con.createQuery(deleteRecipesQuery).executeUpdate();
            con.close();

        }
    }
}
