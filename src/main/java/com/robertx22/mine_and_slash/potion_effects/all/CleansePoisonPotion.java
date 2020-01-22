package com.robertx22.mine_and_slash.potion_effects.all;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;

public class CleansePoisonPotion extends BasePotionEffect {

    public static final CleansePoisonPotion INSTANCE = new CleansePoisonPotion();

    private CleansePoisonPotion() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

    }

    @Override
    public String GUID() {
        return "cleanse_poison";
    }

    @Override
    public void onXTicks(LivingEntity entity, EffectInstance instance) {

        try {

            if (entity.world.isRemote) {
                ParticleUtils.spawnHealParticles(entity, 1);
            } else {

                if (entity.getActivePotionEffect(Effects.POISON) != null) {
                    entity.removePotionEffect(Effects.POISON);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int performEachXTicks() {
        return 20;
    }

    @Override
    public String locNameForLangFile() {
        return "Cleanse Poison";
    }
}