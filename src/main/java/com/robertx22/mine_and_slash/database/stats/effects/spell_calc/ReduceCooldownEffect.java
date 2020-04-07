package com.robertx22.mine_and_slash.database.stats.effects.spell_calc;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseSpellCalcEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellStatsCalcEffect;

public class ReduceCooldownEffect extends BaseSpellCalcEffect {

    @Override
    public SpellStatsCalcEffect activate(SpellStatsCalcEffect effect, StatData data, Stat stat) {

        float multi = data.getReverseMultiplier();

        effect.configs.multiplyValueBy(SC.COOLDOWN_SECONDS, multi);
        effect.configs.multiplyValueBy(SC.COOLDOWN_TICKS, multi);

        return effect;
    }

}
