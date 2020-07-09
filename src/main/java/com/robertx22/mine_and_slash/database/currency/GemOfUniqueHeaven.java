package com.robertx22.mine_and_slash.database.currency;

import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.database.currency.base.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.database.rarities.gears.UniqueGear;
import com.robertx22.mine_and_slash.items.SimpleMatItem;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueGearBlueprint;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocMultiLore;
import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class GemOfUniqueHeaven extends CurrencyItem implements ICurrencyItemEffect, IRenamed, IAutoLocMultiLore,
    IShapedRecipe {

    private static final String GUID = Ref.MODID + ":currency/create_new_unique";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":create_new_unique");
    }

    @Override
    public String GUID() {
        return "currency/create_new_unique";
    }

    public GemOfUniqueHeaven() {

        super(GUID);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        UniqueGearBlueprint gearPrint = new UniqueGearBlueprint(
            gear.uniqueStats.getUnique()
                .getTier(), false);
        gearPrint.rarity.setSpecificRarity(UniqueGear.getInstance()
            .Rank());

        ItemStack newstack = gearPrint.createStack();

        GearItemData newgear = gearPrint.createData();

        int tries = 0; // in case there's only 1 unique in a tier
        while (newgear.gear_type.equals(gear.gear_type) && tries < 10) {
            newgear = gearPrint.createData();
            newstack = gearPrint.createStack();
            tries++;
        }

        gear.WriteOverDataThatShouldStay(newgear);

        return newstack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(SimpleGearLocReq.IS_UNIQUE);
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Don't want it? Transform it!");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Gem Of Unique Heaven";
    }

    @Override
    public String locDescForLangFile() {
        return "Transform unique (same tier)";
    }

    @Override
    public int instabilityAddAmount() {
        return 0;
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.GEM_OF_UNIQUE_HEAVEN.get())
            .key('#', SimpleMatItem.MYTHIC_ESSENCE)
            .key('t', ModItems.STONE_OF_HOPE.get())
            .key('v', Items.GOLD_INGOT)
            .key('o', ModItems.RARE_MAGIC_ESSENCE.get())
            .patternLine("ooo")
            .patternLine("#t#")
            .patternLine("vvv")
            .addCriterion("player_level", trigger());
    }

}