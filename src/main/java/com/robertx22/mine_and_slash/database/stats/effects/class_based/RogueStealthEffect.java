package com.robertx22.mine_and_slash.database.stats.effects.class_based;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.potion_effects.rogue.StealthEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import net.minecraft.potion.Effects;

public class RogueStealthEffect extends BaseDamageEffect {

    private RogueStealthEffect() {
    }

    public static RogueStealthEffect getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {
        effect.source.removePotionEffect(StealthEffect.getInstance());
        effect.source.removePotionEffect(Effects.INVISIBILITY);
        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return true;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public int GetPriority() {
        return Priority.Last.priority;
    }

    private static class SingletonHolder {
        private static final RogueStealthEffect INSTANCE = new RogueStealthEffect();
    }
}
