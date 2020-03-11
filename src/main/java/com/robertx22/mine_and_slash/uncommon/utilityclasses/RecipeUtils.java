package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.items.SimpleMatItem;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.new_content.trader.ISellPrice;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeUtils {

    private static HashMap<Item, Integer> ITEM_COMMONS = new HashMap<>();

    public static ItemStack getSalvageStack(Item item) {

        return ISellPrice.getHighestRarityStackFromCommons(getCommonOresNeededToCraftItem(item));

    }

    public static int getCommonOresNeededToCraftItem(Item item) {

        if (!ITEM_COMMONS.containsKey(item)) {

            int least = Integer.MAX_VALUE;

            boolean has = false;

            for (IRecipe r : getRecipesForThisItem(item)) {
                int commons = getCommonOresNeededToCraftItemUsingRecipe(r);

                if (commons < least) {
                    least = commons;
                    has = true;
                }

            }

            if (!has) {
                least = 0;
            }

            ITEM_COMMONS.put(item, least);
        }

        return ITEM_COMMONS.get(item);

    }

    public static int getCommonOresNeededToCraftItemUsingRecipe(IRecipe recipe, Item item) {

        float commons = getCommonOresNeededToCraftItemUsingRecipe(recipe);

        List<Item> matIngredients = new ArrayList<>();
        List<Ingredient> ingredients = recipe.getIngredients();

        ingredients.stream()
            .forEach(x -> {
                for (ItemStack stack : x.getMatchingStacks()) {
                    if (stack.getItem() instanceof SimpleMatItem) {
                        matIngredients.add(stack.getItem());
                    }
                }
            });

        for (Item m : matIngredients) {
            List<IRecipe> recipes = getRecipesForThisItem(m);

            if (recipes.size() == 1) {
                IRecipe matRecipe = recipes.get(0);
                commons += getCommonOresNeededToCraftItemUsingRecipe(matRecipe);
            } else {
                commons = 0;
            }

        }

        return (int) commons;

    }

    public static int getCommonOresNeededToCraftItemUsingRecipe(IRecipe recipe) {

        float commons = 0;

        for (GearRarity rar : Rarities.Gears.getNormalRarities()) {

            Item ore = ItemOre.ItemOres.get(rar.Rank());

            List<Ingredient> ingredients = recipe.getIngredients();

            List<Ingredient> oreIngredients;
            List<Item> matIngredients = new ArrayList<>();

            oreIngredients = ingredients.stream()
                .filter(x -> {
                    return x.test(new ItemStack(ore));
                })
                .collect(Collectors.toList());
            commons += ISellPrice.rarityOresToCommons(rar, oreIngredients.size());

        }
        return (int) commons;
    }

    public static List<IRecipe> getRecipesForThisItem(Item item) {

        List<IRecipe<?>> reg = MapManager.getServer()
            .getRecipeManager()
            .getRecipes()
            .stream()
            .filter(x -> x.getType()
                .equals(IRecipeType.CRAFTING))
            .collect(Collectors.toList());

        List<IRecipe> recipes = reg.stream()
            .filter(x -> x.getRecipeOutput()
                .getItem()
                .equals(item))
            .collect(Collectors.toList());

        return recipes;

    }
}
