package com.robertx22.mine_and_slash.uncommon.effectdatas;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import net.minecraft.entity.LivingEntity;

public class SpellStatsCalcEffect extends EffectData {
    public PreCalcSpellConfigs configs;

    public SpellStatsCalcEffect(PreCalcSpellConfigs configs, LivingEntity source, LivingEntity target) {
        super(source, target);

        this.configs = configs;
    }

    @Override
    protected void activate() {

    }
}
