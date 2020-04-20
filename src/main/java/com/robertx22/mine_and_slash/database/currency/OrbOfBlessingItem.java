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
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class OrbOfBlessingItem extends CurrencyItem implements ICurrencyItemEffect, IRenamed, IShapedRecipe {
    @Override
    public String GUID() {
        return "currency/number_reroll";
    }

    private static final String name = Ref.MODID + ":currency/number_reroll";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":number_reroll");
    }

    public OrbOfBlessingItem() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        for (IRerollable rel : gear.GetAllRerollable()) {
            rel.RerollNumbers(gear);
        }
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearReq.INSTANCE, GearEnumLocReq.REROLL_NUMBERS, SimpleGearLocReq.IS_NOT_UNIQUE);
    }

    @Override
    public int getTier() {
        return 0;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Rare;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Hopefully works better than the last time.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Orb Of Blessing";
    }

    @Override
    public String locDescForLangFile() {
        return "Re-rolls all numbers on a gear";
    }

    @Override
    public int instabilityAddAmount() {
        return 25;
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.ORB_OF_BLESSING.get())
            .key('#', SimpleMatItem.CRYSTALLIZED_ESSENCE)
            .key('t', ModItems.CRYSTAL_OF_LEGEND.get())
            .key('v', Items.COAL)
            .key('o', ItemOre.ItemOres.get(IRarity.Epic))
            .patternLine("v#v")
            .patternLine("vtv")
            .patternLine("ovo")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(10));
    }

}