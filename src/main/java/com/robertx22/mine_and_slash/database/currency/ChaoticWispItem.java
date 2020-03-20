package com.robertx22.mine_and_slash.database.currency;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.database.currency.base.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.GearEnumLocReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.item_types.GearReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.traits.major_arcana.BaseMajorArcana;
import com.robertx22.mine_and_slash.items.SimpleMatItem;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.ChaosStatsData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class ChaoticWispItem extends CurrencyItem implements ICurrencyItemEffect, IShapedRecipe {

    @Override
    public String GUID() {
        return "currency/whisp_of_chaos";
    }

    public ChaoticWispItem() {
        super(Ref.MODID + ":" + "currency/whisp_of_chaos");
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);
        StatMod mod = SlashRegistry.StatMods()
            .getFilterWrapped(x -> x.GetBaseStat() instanceof BaseMajorArcana)
            .random();
        gear.chaosStats = new ChaosStatsData();
        gear.chaosStats.create(gear, mod);
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearReq.INSTANCE, GearEnumLocReq.CAN_CHAOS_STATS, SimpleGearLocReq.NO_CHAOS_STATS);
    }

    @Override
    public int Tier() {
        return 15;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("High Expectations, strong disappointment.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Chaotic Wisp";
    }

    @Override
    public String locDescForLangFile() {
        return "Adds a Major Arcana Chaos Stat";
    }

    @Override
    public int instabilityAddAmount() {
        return 15;
    }

    @Override
    public float additionalBreakChance() {
        return 20;
    }

    @Override
    public float breakChanceMulti() {
        return 0.5F;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.CHAOTIC_WISP.get())
            .key('#', SimpleMatItem.MYTHIC_ESSENCE)
            .key('t', ModItems.CHAOS_ORB.get())
            .key('b', Items.ENDER_EYE)
            .key('o', ItemOre.ItemOres.get(IRarity.Uncommon))
            .patternLine("ooo")
            .patternLine("#t#")
            .patternLine("bbb")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(10));
    }

}
