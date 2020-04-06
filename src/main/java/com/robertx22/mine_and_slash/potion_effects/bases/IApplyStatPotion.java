package com.robertx22.mine_and_slash.potion_effects.bases;

import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.potion_effects.bases.data.PotionStat;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface IApplyStatPotion {

    default void applyStats(EntityCap.UnitData data, PlayerSpellCap.ISpellsCap cap, EffectInstance instance) {
        ExtraPotionData extraData = PotionDataSaving.getData(instance);

        if (extraData != null) {
            getStatsAffected((BasePotionEffect) instance.getPotion(), data, cap, extraData).forEach(x -> x.applyStats(data));
        }

    }

    default List<ExactStatData> getStatsAffected(BasePotionEffect effect, EntityCap.UnitData data, PlayerSpellCap.ISpellsCap cap, ExtraPotionData extraData) {
        return getPotionStats().stream()
            .map(x -> x.getExactStat(data, cap, extraData, effect))
            .collect(Collectors.toList());
    }

    List<PotionStat> getPotionStats();

    default List<ITextComponent> getStatTooltip(TooltipInfo info, BasePotionEffect effect) {

        List<ITextComponent> list = new ArrayList<>();

        ExtraPotionData minStacks = new ExtraPotionData();
        minStacks.casterLvl = info.unitdata.getLevel();

        list.add(new StringTextComponent(TextFormatting.GREEN + "Affects stats: "));

        getStatsAffected(effect, info.unitdata, Load.spells(info.player), minStacks).forEach(x -> list.addAll(x.GetTooltipString(info)));

        return list;

    }

}
