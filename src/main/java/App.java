import DAO.Sql2oRecipeDao;
import model.Recipe;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/recipe_app", "x", "230620");
        Sql2oRecipeDao sql2oRecipeDao = new Sql2oRecipeDao();


        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/recipe", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "recipe.hbs");
        }, new HandlebarsTemplateEngine());

        get("/form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/recipe", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String title = request.queryParams("title");
            int prepTime = Integer.parseInt(request.queryParams("prepTime"));
            int cookTime = Integer.parseInt(request.queryParams("cookTime"));
            int servings = Integer.parseInt(request.queryParams("servings"));
            String ingredients = request.queryParams("ingredients ");
            String directions = request.queryParams("directions");
            String postedBy = request.queryParams("postedBy");

            Recipe createdRecipe = new Recipe(title, prepTime, cookTime, servings, ingredients, directions, postedBy);
            sql2oRecipeDao.add(createdRecipe);
            System.out.println(sql2oRecipeDao.getAll());
            response.redirect("/recipe");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/recipe/:id/update",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            int findRecipe = Integer.parseInt(request.params("id"));
            Recipe updateRecipe=sql2oRecipeDao.findById(findRecipe);
            model.put("updateRecipe", updateRecipe);
            return new ModelAndView(model,"form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/recipe/:id/update",(request, response) -> {
            Map<String,Object> model =new HashMap<>();
            int findRecipe=Integer.parseInt(request.params("id"));
            Recipe updateRecipe =sql2oRecipeDao.findById(findRecipe);
            String title = request.queryParams("title");
            int prepTime = Integer.parseInt(request.queryParams("prepTime"));
            int cookTime = Integer.parseInt(request.queryParams("cookTime"));
            int servings = Integer.parseInt(request.queryParams("servings"));
            String ingredients = request.queryParams("ingredients ");
            String directions = request.queryParams("directions");
            String postedBy = request.queryParams("postedBy");
            Recipe updatedRecipe= new Recipe(title, prepTime, cookTime, servings, ingredients, directions, postedBy);
            sql2oRecipeDao.update(updatedRecipe.getId(),updatedRecipe);
            model.put("updateRecipe", updateRecipe);
            return new ModelAndView(model, "recipe.hbs");
        }, new HandlebarsTemplateEngine());

        get("/recipe/:id/delete",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            int findRecipe =Integer.parseInt(request.params("id"));
            Recipe deleteRecipe = sql2oRecipeDao.findById(findRecipe);
            model.put("deleteRecipe",deleteRecipe);
            sql2oRecipeDao.deleteById(findRecipe);
            return new ModelAndView(model,"recipe.hbs");
        }, new HandlebarsTemplateEngine());
    }

}
