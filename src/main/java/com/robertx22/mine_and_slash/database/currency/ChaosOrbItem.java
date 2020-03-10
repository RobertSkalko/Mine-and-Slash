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
import com.robertx22.mine_and_slash.saveclasses.gearitem.ChaosStatsData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class ChaosOrbItem extends CurrencyItem implements ICurrencyItemEffect, IRenamed, IShapedRecipe {
    @Override
    public String GUID() {
        return "currency/chaos_orb";
    }

    public static final String ID = Ref.MODID + ":currency/chaos_orb";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":chaos_orb");
    }

    public ChaosOrbItem() {

        super(ID);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);
        gear.chaosStats = new ChaosStatsData();
        gear.chaosStats.RerollFully(gear);
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearReq.INSTANCE, GearEnumLocReq.CAN_CHAOS_STATS, SimpleGearLocReq.NO_CHAOS_STATS);
    }

    @Override
    public int Tier() {
        return 3;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Do not gamble what you are not willing to lose.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Chaos Orb";
    }

    @Override
    public String locDescForLangFile() {
        return "Permanently adds a Chaos stat";
    }

    @Override
    public int instabilityAddAmount() {
        return -25;
    }

    @Override
    public boolean activatesBreakRoll() {
        return false;
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.CHAOS_ORB.get())
            .key('#', SimpleMatItem.INFUSED_IRON)
            .key('t', ModItems.ORB_OF_TRANSMUTATION.get())
            .key('b', Items.NETHER_WART)
            .key('o', ItemOre.ItemOres.get(IRarity.Uncommon))
            .patternLine("ooo")
            .patternLine("#t#")
            .patternLine("bbb")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(10));
    }

}
