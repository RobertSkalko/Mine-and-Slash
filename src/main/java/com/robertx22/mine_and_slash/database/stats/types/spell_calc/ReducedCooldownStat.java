package com.robertx22.mine_and_slash.database.stats.types.spell_calc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.spell_calc.ReduceCooldownEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class ReducedCooldownStat extends Stat implements IStatEffects {

    private ReducedCooldownStat() {
        this.maximumValue = 75;
    }

    public static ReducedCooldownStat getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public String locDescForLangFile() {
        return "Reduces spell cooldown.";
    }

    @Override
    public String locNameForLangFile() {
        return "Cooldown Reduction";
    }

    @Override
    public String GUID() {
        return "cdr";
    }

    @Override
    public IStatEffect getEffect() {
        return new ReduceCooldownEffect();
    }

    private static class SingletonHolder {
        private static final ReducedCooldownStat INSTANCE = new ReducedCooldownStat();
    }
}
