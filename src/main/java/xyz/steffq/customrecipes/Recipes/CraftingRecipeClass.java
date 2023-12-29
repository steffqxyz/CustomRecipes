package xyz.steffq.customrecipes.Recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.steffq.customrecipes.CustomRecipes;
import xyz.steffq.customrecipes.files.Config;
import xyz.steffq.customrecipes.files.Parse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CraftingRecipeClass {


    private final Config config;

    private String material;
    private String name;
    private List<String> lore;
    private List<String> recipe;

    private Map<String, ShapedRecipe> customRecipies = new HashMap<>();

    public CraftingRecipeClass(Config config) {
        this.config = config;
        crateCraftinRecipe();
    }

    public void crateCraftinRecipe() {
        for (String key : config.options().getConfigurationSection("recipes").getKeys(false)) {
            ConfigurationSection recipes = config.options().getConfigurationSection("recipes." + key);

            material = recipes.getString("material");
            name = recipes.getString("name");
            lore = recipes.getStringList("lore");
            recipe = recipes.getStringList("recipe");

            ItemStack item = new ItemStack(Material.valueOf(material));
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(Parse.parseColor(name));
            meta.setLore(Parse.parseColors(lore));
            item.setItemMeta(meta);

            NamespacedKey namespacedKey = new NamespacedKey(CustomRecipes.getInstance(), "Recipes");

            ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, item);
            shapedRecipe.shape(recipe.get(0), recipe.get(1), recipe.get(2));

            ConfigurationSection itemsForRecipeSection = config.options().getConfigurationSection("items-for-recipe");

            if (itemsForRecipeSection != null) {
                for (String key1 : itemsForRecipeSection.getKeys(false)) {
                    ConfigurationSection itemsForRecipe = itemsForRecipeSection.getConfigurationSection(key1);

                    if (itemsForRecipe != null) {
                        String character = itemsForRecipe.getString("char");
                        String materialIFR = itemsForRecipe.getString("material");

                        shapedRecipe.setIngredient(character.charAt(0), Material.valueOf(materialIFR));
                    } else {
                        Bukkit.getLogger().warning("Configuration section 'items-for-recipe." + key1 + "' is missing or invalid.");
                    }
                }
            } else {
                Bukkit.getLogger().warning("Configuration section 'items-for-recipe' is missing or invalid.");
            }


            registerRecipies(key, shapedRecipe);

        }
    }

    private void registerRecipies(String recipeId, ShapedRecipe recipe) {
        customRecipies.put(recipeId, recipe);

        Bukkit.addRecipe(recipe);
    }

}
