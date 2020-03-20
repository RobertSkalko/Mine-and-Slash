package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.items.SimpleMatItem;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RegisterItemUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ItemCapacitor extends Item implements IShapedRecipe {

    public static HashMap<Integer, Item> ITEMS = new HashMap<Integer, Item>();

    public ItemCapacitor(int rarity) {

        super(new Properties().group(CreativeTabs.MyModTab));

        this.rarity = rarity;

        RegisterItemUtils.RegisterItemName(this, "capacitor" + rarity);
    }

    int rarity;

    public List<Float> RepairValues = Arrays.asList(0.95F, 0.9F, 0.8F, 0.7F, 0.6F, 0.5F);

    public List<Float> bonusSalvageChance = Arrays.asList(1F, 2.5F, 5F, 10F, 15F, 25F);

    public List<Float> stationSpeedMulti = Arrays.asList(1F, 0.9F, 0.8F, 0.7F, 0.6F, 0.5F);

    public Float getSalvageBonusChance() {

        return bonusSalvageChance.get(rarity);

    }

    public Float GetFuelMultiplier() {

        return RepairValues.get(rarity);

    }

    public Float GetSpeedMultiplier() {

        return stationSpeedMulti.get(rarity);

    }

    @Override
    public ShapedRecipeBuilder getRecipe() {

        if (rarity == 0) {
            return shaped(ITEMS.get(rarity))
                .key('#', Items.IRON_INGOT)
                .key('t', ModItems.ORB_OF_TRANSMUTATION.get())
                .key('c', ItemOre.ItemOres.get(0))
                .patternLine("#c#")
                .patternLine("ctc")
                .patternLine("#c#")
                .addCriterion("player_level", new PlayerLevelTrigger.Instance(5));
        }
        if (rarity == 1) {
            return shaped(ITEMS.get(rarity))
                .key('#', SimpleMatItem.INFUSED_IRON)
                .key('t', ITEMS.get(rarity - 1))
                .key('c', ItemOre.ItemOres.get(rarity))
                .patternLine("#c#")
                .patternLine("ctc")
                .patternLine("#c#")
                .addCriterion("player_level", new PlayerLevelTrigger.Instance(5));
        }
        if (rarity == 2) {
            return shaped(ITEMS.get(rarity))
                .key('#', SimpleMatItem.GOLDEN_ORB)
                .key('t', ITEMS.get(rarity - 1))
                .key('c', ItemOre.ItemOres.get(rarity))
                .patternLine("#c#")
                .patternLine("ctc")
                .patternLine("#c#")
                .addCriterion("player_level", new PlayerLevelTrigger.Instance(5));
        }
        if (rarity == 3) {
            return shaped(ITEMS.get(rarity))
                .key('#', SimpleMatItem.CRYSTALLIZED_ESSENCE)
                .key('t', ITEMS.get(rarity - 1))
                .key('c', ItemOre.ItemOres.get(rarity))
                .patternLine("#c#")
                .patternLine("ctc")
                .patternLine("#c#")
                .addCriterion("player_level", new PlayerLevelTrigger.Instance(5));
        }
        if (rarity == 4) {
            return shaped(ITEMS.get(rarity))
                .key('#', SimpleMatItem.MYTHIC_ESSENCE)
                .key('t', ITEMS.get(rarity - 1))
                .key('c', ModItems.STONE_OF_HOPE.get())
                .patternLine("#c#")
                .patternLine("ctc")
                .patternLine("#c#")
                .addCriterion("player_level", new PlayerLevelTrigger.Instance(5));
        }

        return null;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
                               ITooltipFlag flagIn) {

        tooltip.add(CLOC.tooltip("capacitor"));

        tooltip.add(CLOC.tooltip("capacitor2")
            .appendText(": " + this.GetFuelMultiplier() + "x"));

        tooltip.add(Words.Bonus_Salvage_Chance.locName()
            .appendText(": " + this.getSalvageBonusChance() + "%"));

    }

}
