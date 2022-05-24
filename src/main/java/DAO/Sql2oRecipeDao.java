package DAO;

import model.Recipe;
import Database.DB;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;


public class Sql2oRecipeDao implements RecipeDao{

    //CREATE
    @Override
    public void add(Recipe recipe) {
        try (Connection connection = DB.sql2o.open()) {
            int id = (int) connection.createQuery("INSERT INTO recipes (title, prepTime, cookTime, servings, ingredients, directions, postedBy) VALUES (:title, :prepTime, :cookTime, :servings, :ingredients, :directions, :postedBy)", true)
                    .bind(recipe)
                    .executeUpdate()
                    .getKey();
            recipe.setId(id);
        } catch (Sql2oException exc) {
            System.out.println(exc.getMessage());
        }
    }

    //READ - LIST
    @Override
    public List<Recipe> getAll() {
        try (Connection connection = DB.sql2o.open()) {
            return connection.createQuery("SELECT * FROM recipes")
                    .executeAndFetch(Recipe.class);
        }
    }

    //READ - INDIVIDUAL
    @Override
    public Recipe findById(int id) {
        String sql = "SELECT * FROM recipes WHERE id = :id";
        try (Connection connection = DB.sql2o.open()) {
            return connection.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Recipe.class);
        }
    }

    //UPDATE
    @Override
    public void update(int id, Recipe recipe) {
        String sql = "UPDATE recipes SET (title, prepTime, cookTime, servings, ingredients, directions, postedBy) = (:title, :prepTime, :cookTime, :servings, :ingredients, :directions, :postedBy)";
        try (Connection connection = DB.sql2o.open()) {
            connection.createQuery(sql)
                    .bind(recipe)
                    .executeUpdate();
        }catch (Sql2oException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM departments WHERE id = :id ;";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    @Override
    public void clearAll() {
        String sql = "DELETE FROM departments;";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        }
    }


}
