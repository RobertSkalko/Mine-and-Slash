package com.robertx22.mine_and_slash.database.stats.types.core_stats;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatScaling;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.BaseCoreStat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.IPreCoreStat;
import com.robertx22.mine_and_slash.saveclasses.unit.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class AllAttributes extends Stat implements IPreCoreStat {

    private AllAttributes() {
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.LINEAR;
    }

    public static AllAttributes getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.CoreStat;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public void addToCoreStats(EntityCap.UnitData unitdata, StatData data) {
        coreStatsThatBenefit().forEach(x -> x.getMods(data, unitdata.getLevel())
            .forEach(m -> m.applyStats(unitdata)));
    }

    public List<BaseCoreStat> coreStatsThatBenefit() {
        return Arrays.asList(Dexterity.INSTANCE, Intelligence.INSTANCE, Strength.INSTANCE);
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

    private static class SingletonHolder {
        private static final AllAttributes INSTANCE = new AllAttributes();
    }
}



