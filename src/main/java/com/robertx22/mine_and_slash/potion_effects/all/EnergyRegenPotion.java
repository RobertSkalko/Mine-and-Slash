package com.robertx22.mine_and_slash.potion_effects.all;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;

public class EnergyRegenPotion extends BasePotionEffect {

    public static final EnergyRegenPotion INSTANCE = new EnergyRegenPotion();

    private EnergyRegenPotion() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

    }

    @Override
    public String locNameForLangFile() {
        return "Regen Energy";
    }

    @Override
    public String GUID() {
        return "energy_regen";
    }

    @Override
    public void onXTicks(LivingEntity entity, EffectInstance instance) {

        try {

            if (entity.world.isRemote) {
                ParticleUtils.spawnEnergyRestoreParticles(entity, 5);
            } else {
                UnitData data = Load.Unit(entity);

                ResourcesData.Context energy = new ResourcesData.Context(
                        data, entity, ResourcesData.Type.ENERGY, instance.getAmplifier(), ResourcesData.Use.RESTORE);

                data.getResources().modify(energy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int performEachXTicks() {
        return 40;
    }

    @Override
    public void onEffectApplied(Entity applier, Entity caster, LivingEntity target, int amplifier) {

    }
}
