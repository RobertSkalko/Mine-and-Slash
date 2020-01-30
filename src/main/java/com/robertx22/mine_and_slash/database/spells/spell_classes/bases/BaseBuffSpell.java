package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvent;

public abstract class BaseBuffSpell extends BaseSpell {

    public abstract SoundEvent getCastSound();

    public abstract BasePotionEffect getEffect();

    public void spawnParticles(LivingEntity caster) {

    }

    @Override
    public boolean cast(LivingEntity caster, int ticksInUse) {
        try {

            PotionEffectUtils.applyToSelf(getEffect(), caster);
            SoundUtils.playSound(caster, getCastSound(), 1, 1);
            spawnParticles(caster);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

