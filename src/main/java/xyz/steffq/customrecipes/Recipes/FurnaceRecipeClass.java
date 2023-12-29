package xyz.steffq.customrecipes.Recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.steffq.customrecipes.CustomRecipes;
import xyz.steffq.customrecipes.files.Config;
import xyz.steffq.customrecipes.files.Parse;

import java.util.List;

public class FurnaceRecipeClass implements Listener {
    private String material;
    private String materialInput;
    private String name;
    private List<String> lore;
    private List<String> recipe;
    private int cookingTime;
    private final Config furnaceRecipe;

    public FurnaceRecipeClass(Config furnaceRecipe) {
        this.furnaceRecipe = furnaceRecipe;
        createFurnaceRecipe();
    }

    @EventHandler
    public void onSmelt(FurnaceSmeltEvent e) {

        ItemStack smeltedItem = e.getSource();
        ItemMeta itemMeta = smeltedItem.getItemMeta();


        for (String key : furnaceRecipe.options().getConfigurationSection("recipes-normal").getKeys(false)) {
            ConfigurationSection recipes = furnaceRecipe.options().getConfigurationSection("recipes-normal." + key);

            materialInput = recipes.getString("material-input");

            if (!(itemMeta.getPersistentDataContainer().has(NamespacedKey.minecraft(materialInput)))) {
                return;
            }


        }

    }

    public void createFurnaceRecipe() {

        for (String key : furnaceRecipe.options().getConfigurationSection("recipes-normal").getKeys(false)) {
            ConfigurationSection recipes = furnaceRecipe.options().getConfigurationSection("recipes-normal." + key);

            material = recipes.getString("material");
            name = recipes.getString("name");
            lore = recipes.getStringList("lore");
            recipe = recipes.getStringList("recipe");
            materialInput = recipes.getString("material-input");
            cookingTime = recipes.getInt("cooking-time");

            ItemStack item = new ItemStack(Material.valueOf(material));
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(Parse.parseColor(name));
            meta.setLore(Parse.parseColors(lore));
            item.setItemMeta(meta);


            FurnaceRecipe furnaceRecipe1 = new FurnaceRecipe(
                    new NamespacedKey(CustomRecipes.getInstance(), key), item, Material.valueOf(materialInput), 10f, cookingTime * 20);

            Bukkit.getServer().addRecipe(furnaceRecipe1);

            FurnaceRecipe furnaceRecipe2 = new FurnaceRecipe(
                    new NamespacedKey(CustomRecipes.getInstance(), key), item, Material.valueOf(materialInput), 10f, cookingTime * 20);

            Bukkit.getServer().addRecipe(furnaceRecipe2);

        }
    }


}
