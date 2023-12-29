package xyz.steffq.customrecipes.Recipes;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.steffq.customrecipes.CustomRecipes;
import xyz.steffq.customrecipes.files.Config;
import xyz.steffq.customrecipes.files.Parse;

import java.util.List;

public class FurnaceRecipe {
    private String material;
    private String name;
    private List<String> lore;
    private List<String> recipe;
    private Config furnaceRecipe = CustomRecipes.getFurnaceRecipe();

    public void createFurnaceRecipe() {

        for (String key : furnaceRecipe.options().getConfigurationSection("recipes").getKeys(false)) {
            ConfigurationSection recipes = furnaceRecipe.options().getConfigurationSection("recipes." + key);

            material = recipes.getString("material");
            name = recipes.getString("name");
            lore = recipes.getStringList("lore");
            recipe = recipes.getStringList("recipe");

            ItemStack item = new ItemStack(Material.valueOf(material));
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(Parse.parseColor(name));
            meta.setLore(Parse.parseColors(lore));
            item.setItemMeta(meta);

        }
    }

}
