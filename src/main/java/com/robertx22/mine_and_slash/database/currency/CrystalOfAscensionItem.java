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
import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class CrystalOfAscensionItem extends CurrencyItem implements ICurrencyItemEffect, IRenamed, IShapedRecipe {
    @Override
    public String GUID() {
        return "currency/item_levelup";
    }

    private static final String name = Ref.MODID + ":currency/item_levelup";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":item_levelup");
    }

    public CrystalOfAscensionItem() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);
        gear.setLevel(gear.level + 1);
        gear.timesLeveledUp++;
        Gear.Save(stack, gear);

        return stack;
    }

    public static final int MAXIMUM_LEVEL_UPS = 10;

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearReq.INSTANCE, SimpleGearLocReq.LVLED_LESS_THAN_10_TIMES, SimpleGearLocReq.LEVEL_ISNT_HIGHER_THAN_MAX);
    }

    @Override
    public int Tier() {
        return 5;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Who said your sword can't level with you?");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Crystal Of Ascension";
    }

    @Override
    public String locDescForLangFile() {
        return "Increases item level";
    }

    @Override
    public int instabilityAddAmount() {
        return 5;
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.CRYSTAL_OF_ASCENSION.get())
            .key('#', SimpleMatItem.GOLDEN_ORB)
            .key('t', ModItems.ORB_OF_TRANSMUTATION.get())
            .key('b', Items.NETHER_WART)
            .key('o', ItemOre.ItemOres.get(IRarity.Uncommon))
            .patternLine("ooo")
            .patternLine("#t#")
            .patternLine(" b ")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(10));
    }

}