import DAO.Sql2oRecipeDao;
import model.Recipe;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.staticFileLocation;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        Sql2oRecipeDao sql2oRecipeDao = new Sql2oRecipeDao();


        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/recipe", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Recipe> recipes = sql2oRecipeDao.getAll();
            model.put("recipes",recipes);
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
            model.put("createdRecipe",createdRecipe);
            System.out.println(sql2oRecipeDao.getAll());
            return new ModelAndView(model,"index.hbs");
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
            sql2oRecipeDao.update(updateRecipe.getId(),updatedRecipe);
            model.put("updatedRecipe", updatedRecipe);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/card/:id/delete",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            int findRecipe =Integer.parseInt(request.params("id"));
            Recipe deleteRecipe = sql2oRecipeDao.findById(findRecipe);
            model.put("deleteRecipe",deleteRecipe);
            sql2oRecipeDao.deleteById(deleteRecipe.getId());
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());
    }

}
