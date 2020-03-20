package com.robertx22.mine_and_slash.onevent.data_gen.providers;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.items.SimpleMatItem;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
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
            .key('#', ItemOre.ItemOres.get(IRarity.Uncommon))
            .key('i', Items.IRON_INGOT)
            .patternLine(" # ")
            .patternLine("#i#")
            .patternLine(" # ")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(10))
            .build(c);

        shaped(SimpleMatItem.GOLDEN_ORB)
            .key('#', ItemOre.ItemOres.get(IRarity.Rare))
            .key('i', Items.GOLD_INGOT)
            .patternLine(" # ")
            .patternLine("#i#")
            .patternLine(" # ")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(10))
            .build(c);

        shaped(SimpleMatItem.CRYSTALLIZED_ESSENCE)
            .key('#', ItemOre.ItemOres.get(IRarity.Epic))
            .key('i', Items.DIAMOND)
            .patternLine(" # ")
            .patternLine("#i#")
            .patternLine(" # ")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(10))
            .build(c);

        shaped(SimpleMatItem.MYTHIC_ESSENCE)
            .key('#', ItemOre.ItemOres.get(IRarity.Legendary))
            .key('b', Items.BOWL)
            .patternLine("###")
            .patternLine("###")
            .patternLine(" b ")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(10))
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