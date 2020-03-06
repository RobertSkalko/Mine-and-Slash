package com.robertx22.mine_and_slash.database.currency;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.database.currency.base.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.GearEnumLocReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.item_types.GearReq;
import com.robertx22.mine_and_slash.items.SimpleMatItem;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.saveclasses.gearitem.SuffixData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class UnearthSuffixItem extends CurrencyItem implements ICurrencyItemEffect, IRenamed, IShapedRecipe {

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":add_suffix");
    }

    @Override
    public String GUID() {
        return "currency/add_suffix";
    }

    public static final String ID = Ref.MODID + ":currency/add_suffix";

    public UnearthSuffixItem() {

        super(ID);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        gear.suffix = new SuffixData();
        gear.suffix.RerollFully(gear);

        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearReq.INSTANCE, GearEnumLocReq.AFFIXES, SimpleGearLocReq.NO_SUFFIX);
    }

    @Override
    public int Tier() {
        return 10;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("And soar to the skies.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Unearth Suffix";
    }

    @Override
    public String locDescForLangFile() {
        return "Add a suffix";
    }

    @Override
    public int instabilityAddAmount() {
        return 25;
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.UNEARTH_SUFFIX.get())
            .key('#', SimpleMatItem.GOLDEN_ORB)
            .key('b', Items.BOWL)
            .key('c', ModItems.ORB_OF_BLESSING.get())
            .key('o', ItemOre.ItemOres.get(IRarity.Uncommon))
            .patternLine("ooo")
            .patternLine("#c#")
            .patternLine(" b ")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(10));
    }
}
