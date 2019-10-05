package com.robertx22.mine_and_slash.potion_effects.all;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.SpellPotionBase;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;

public class BleedPotion extends SpellPotionBase {

    public static final BleedPotion INSTANCE = new BleedPotion();

    private BleedPotion() {
        // boolean isBadEffectIn, int liquidColorIn
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

    }

    @Override
    public String GUID() {
        return "bleed";
    }

    @Override
    public void doEffect(Entity applier, Entity caster, LivingEntity target,
                         int amplifier) {

    }

    @Override
    public void performEffectEverySetTime(LivingEntity entity, int amplifier) {

        EntityCap.UnitData data = Load.Unit(entity);

        if (entity.world.isRemote) {
            ParticleUtils.spawnParticles(ParticleTypes.LAVA, entity, 5);
        } else {
            DamageEffect dmg = new DamageEffect(null, entity, entity, amplifier, data, data, EffectData.EffectTypes.DOT_DMG, WeaponTypes.None);
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
