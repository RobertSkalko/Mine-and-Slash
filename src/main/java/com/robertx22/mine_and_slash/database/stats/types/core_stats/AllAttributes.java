package com.robertx22.mine_and_slash.database.stats.types.core_stats;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.*;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.StatUtils;

import java.util.Arrays;
import java.util.List;

public class AllAttributes extends Stat implements IPreCoreStat {

    @Override
    public StatGroup statGroup() {
        return StatGroup.CoreStat;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public boolean ScalesToLevel() {
        return true;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public void addToCoreStats(EntityCap.UnitData unitdata, StatData data) {

        for (StatMod statmod : this.coreStatsThatBenefit()) {
            unitdata.getUnit().getStat(statmod.GetBaseStat().GUID()).Flat += data.val;
        }

    }

    @Override
    public List<StatMod> coreStatsThatBenefit() {
        return Arrays.asList(
                new StrengthFlat(), new StaminaFlat(), new IntelligenceFlat(), new WisdomFlat(), new DexterityFlat(),
                new VitalityFlat()
        );
    }

    @Override
    public float calculateScalingStatGrowth(float stat, int lvl) {
        return StatUtils.calculateBaseStatScalingStatGrowth(stat, lvl);
    }

    @Override
    public String locDescForLangFile() {
        return "Adds to all core stats like VIT, DEX, STR, INT, WIS, STA";
    }

    @Override
    public String locNameForLangFile() {
        return "All Attributes";
    }

    @Override
    public String GUID() {
        return "all_attributes";
    }
}



