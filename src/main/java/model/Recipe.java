package model;

import java.util.Objects;

public class Recipe {
    private String title;
    private int prepTime;
    private int cookTime;
    private String servings;
    private String ingredients;
    private String directions;
    private String postedBy;

    public Recipe(String title, int prepTime, int cookTime, String servings, String ingredients, String directions, String postedBy) {
        this.title = title;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.ingredients = ingredients;
        this.directions = directions;
        this.postedBy = postedBy;
    }
    //GETTERS

    public String getTitle() {
        return title;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public String getServings() {
        return servings;
    }

    public String getIngredients() {
        return ingredients;
    }
    public String getDirections() {
        return directions;
    }
    public String getPostedBy() {
        return postedBy;
    }

    //SETTERS
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }
    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }
    public void setServings(String servings) {
        this.servings = servings;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return prepTime == recipe.prepTime && cookTime == recipe.cookTime && title.equals(recipe.title) && servings.equals(recipe.servings) && ingredients.equals(recipe.ingredients) && directions.equals(recipe.directions) && postedBy.equals(recipe.postedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, prepTime, cookTime, servings, ingredients, directions, postedBy);
    }
}
