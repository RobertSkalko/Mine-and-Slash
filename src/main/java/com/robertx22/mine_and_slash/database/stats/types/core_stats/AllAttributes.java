package com.robertx22.mine_and_slash.database.stats.types.core_stats;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.IPreCoreStat;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

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
    public Elements getElement() {
        return null;
    }

    @Override
    public void addToCoreStats(EntityCap.UnitData unitdata, StatData data) {

        for (StatMod statmod : this.coreStatsThatBenefit()) {
            unitdata.getUnit()
                .getCreateStat(statmod.GetBaseStat()
                    .GUID()).Flat += data.val;
        }

    }

    @Override
    public List<StatMod> coreStatsThatBenefit() {
        return new CoreStatFlat(Dexterity.INSTANCE).generateAllPossibleStatVariations();
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.CORE_STAT;
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



