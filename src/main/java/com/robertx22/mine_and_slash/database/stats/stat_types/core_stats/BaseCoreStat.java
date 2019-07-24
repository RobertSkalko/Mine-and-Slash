package com.robertx22.mine_and_slash.database.stats.stat_types.core_stats;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.math.MathHelper;

public abstract class BaseCoreStat extends Stat implements ICoreStat {

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
    public Elements Element() {
        return null;
    }

    @Override
    public float amountToReach100Percent() {
        return 10;
    }

    @Override
    public void addToOtherStats(EntityCap.UnitData unitdata, StatData data) {

        float percent = data.Value / (this.amountToReach100Percent() * unitdata.getLevel()) * 100;

        percent = MathHelper.clamp(percent, 0, 10000);

        for (StatMod statmod : this.statsThatBenefit()) {
            StatModData.Load(statmod, (int) percent).useOnPlayer(unitdata);
        }

    }

}


