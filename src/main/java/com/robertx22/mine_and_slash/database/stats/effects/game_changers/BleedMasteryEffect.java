package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.potion_effects.all.BleedPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;

public class BleedMasteryEffect extends BaseDamageEffect {

    public static final BleedMasteryEffect INSTANCE = new BleedMasteryEffect();

    @Override
    public int GetPriority() {
        return Priority.Last.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public DamageEffect modifyEffect(DamageEffect effect, StatData data, Stat stat) {
        PotionEffectUtils.apply(BleedPotion.INSTANCE, effect.source, effect.target);
        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect.getEffectType() == EffectData.EffectTypes.BASIC_ATTACK && effect.isDmgAllowed();
    }

}

