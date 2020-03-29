package com.robertx22.mine_and_slash.database.stats.types.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.offense.CriticalHitEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class CriticalHit extends Stat implements IStatEffects {

    public static String GUID = "critical_hit";

    public static CriticalHit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Damage;
    }

    @Override
    public String locDescForLangFile() {
        return "Chance to multiply damage by critical damage";
    }

    @Override
    public String getIconPath() {
        return "crit_hit";
    }

    @Override
    public IStatEffect getEffect() {
        return new CriticalHitEffect();
    }

    private CriticalHit() {
        this.BaseFlat = 1;
        this.maximumValue = 100;
        this.minimumValue = 0;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public String locNameForLangFile() {
        return "Critical Hit Chance";
    }

    private static class SingletonHolder {
        private static final CriticalHit INSTANCE = new CriticalHit();
    }
}
