package com.robertx22.mine_and_slash.onevent.data_gen.providers;

import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.items.SimpleMatItem;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import net.minecraft.advancements.criterion.EnchantedItemTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class SlashRecipeProvider extends RecipeProvider {

    public SlashRecipeProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> c) {

        shaped(SimpleMatItem.INFUSED_IRON)
            .key('#', ModItems.MAGIC_ESSENCE.get())
            .key('i', Items.IRON_INGOT)
            .patternLine(" # ")
            .patternLine("#i#")
            .patternLine(" # ")
            .addCriterion("player_level", EnchantedItemTrigger.Instance.any())
            .build(c);

        shaped(SimpleMatItem.GOLDEN_ORB)
            .key('#', ModItems.RARE_MAGIC_ESSENCE.get())
            .key('i', Items.GOLD_INGOT)
            .patternLine(" # ")
            .patternLine("#i#")
            .patternLine(" # ")
            .addCriterion("player_level", EnchantedItemTrigger.Instance.any())
            .build(c);

        shaped(SimpleMatItem.CRYSTALLIZED_ESSENCE)
            .key('#', ModItems.RARE_MAGIC_ESSENCE.get())
            .key('i', Items.DIAMOND)
            .patternLine(" # ")
            .patternLine("#i#")
            .patternLine(" # ")
            .addCriterion("player_level", EnchantedItemTrigger.Instance.any())
            .build(c);

        shaped(SimpleMatItem.MYTHIC_ESSENCE)
            .key('#', ModItems.RARE_MAGIC_ESSENCE.get())
            .key('b', Items.BOWL)
            .patternLine("###")
            .patternLine("###")
            .patternLine(" b ")
            .addCriterion("player_level", EnchantedItemTrigger.Instance.any())
            .build(c);

        ForgeRegistries.ITEMS.forEach(x -> {
            if (x instanceof IShapedRecipe) {
                IShapedRecipe rec = (IShapedRecipe) x;
                rec.getRecipe()
                    .build(c);
            }
        });

    }

    private ShapedRecipeBuilder shaped(IItemProvider pro) {
        return ShapedRecipeBuilder.shapedRecipe(pro, 1);
    }
}