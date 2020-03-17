package com.robertx22.mine_and_slash.database.currency;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.database.currency.base.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.item_types.GearReq;
import com.robertx22.mine_and_slash.items.SimpleMatItem;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class StoneOfCorruptionItem extends CurrencyItem implements ICurrencyItemEffect, IShapedRecipe {
    @Override
    public String GUID() {
        return "currency/cheap_gear_lvl";
    }

    public static final String ID = Ref.MODID + ":currency/cheap_gear_lvl";

    public StoneOfCorruptionItem() {

        super(ID);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);
        gear.setLevel(gear.getLevel() + 2);
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearReq.INSTANCE, SimpleGearLocReq.LEVEL_ISNT_HIGHER_THAN_MAX);
    }

    @Override
    public int Tier() {
        return 1;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Rare;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Impatience breeds failure.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Stone of Corruption";
    }

    @Override
    public String locDescForLangFile() {
        return "Increase Gears's lvl by 2";
    }

    @Override
    public int instabilityAddAmount() {
        return 250;
    }

    @Override
    public float additionalBreakChance() {
        return 20;
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.STONE_OF_CORRUPTION.get())
            .key('#', SimpleMatItem.INFUSED_IRON)
            .key('t', ModItems.ORB_OF_TRANSMUTATION.get())
            .key('b', Items.IRON_INGOT)
            .key('o', ItemOre.ItemOres.get(IRarity.Uncommon))
            .patternLine("ooo")
            .patternLine("#t#")
            .patternLine(" b ")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(10));
    }

}