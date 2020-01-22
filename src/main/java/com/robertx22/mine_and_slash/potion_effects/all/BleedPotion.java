package com.robertx22.mine_and_slash.potion_effects.all;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;

public class BleedPotion extends BasePotionEffect {

    public static final BleedPotion INSTANCE = new BleedPotion();

    private BleedPotion() {
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

    }

    @Override
    public String GUID() {
        return "bleed";
    }

    @Override
    public void onXTicks(LivingEntity entity, EffectInstance instance) {

        EntityCap.UnitData data = Load.Unit(entity);

        if (entity.world.isRemote) {
            ParticleUtils.spawnParticles(ParticleTypes.LAVA, entity, 5);
        } else {
            DamageEffect dmg = new DamageEffect(null, entity, entity, instance.getAmplifier(), data, data,
                                                EffectData.EffectTypes.DOT_DMG, WeaponTypes.None
            );
            dmg.removeKnockback();
            dmg.Activate();
        }

    }

    @Override
    public int performEachXTicks() {
        return 20;
    }

    @Override
    public String locNameForLangFile() {
        return "Bleed";
    }
}
