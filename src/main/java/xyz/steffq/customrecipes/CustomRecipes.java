package xyz.steffq.customrecipes;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.steffq.customrecipes.Recipes.CraftingRecipeClass;
import xyz.steffq.customrecipes.Recipes.FurnaceRecipeClass;
import xyz.steffq.customrecipes.files.Config;

public final class CustomRecipes extends JavaPlugin {


    @Override
    public void onEnable() {
        // Plugin startup logic

        Config config = new Config(this, "config");
        Config furnaceRecipe = new Config(this, "furnace-recipe");
        new CraftingRecipeClass(config);
        new FurnaceRecipeClass(furnaceRecipe);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CustomRecipes getInstance() {
        return getPlugin(CustomRecipes.class);
    }

}
