package com.robertx22.mine_and_slash.potion_effects.bases;

import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public interface IApplyStatPotion {

    default void applyStats(EntityCap.UnitData data, EffectInstance instance) {
        ExtraPotionData extraData = PotionDataSaving.getData(instance);
        getStatsAffected(data, extraData).forEach(x -> x.applyStats(data));
    }

    List<ExactStatData> getStatsAffected(EntityCap.UnitData data, ExtraPotionData extraData);

    default List<ITextComponent> getStatTooltip(TooltipInfo info, BasePotionEffect effect) {

        List<ITextComponent> list = new ArrayList<>();

        ExtraPotionData minStacks = new ExtraPotionData();
        minStacks.casterLvl = info.unitdata.getLevel();

        list.add(new StringTextComponent(TextFormatting.GREEN + "Affects stats: "));

        getStatsAffected(info.unitdata, minStacks).forEach(x -> list.addAll(x.GetTooltipString(info)));

        list.add(new StringTextComponent(
                TextFormatting.LIGHT_PURPLE + "Max Stacks: " + TextFormatting.DARK_PURPLE + effect.getMaxStacks()));

        return list;

    }

}
