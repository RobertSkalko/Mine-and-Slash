package com.robertx22.mine_and_slash.potion_effects.all;

import com.robertx22.mine_and_slash.uncommon.effectdatas.HealData;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.SpellPotionBase;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;

public class AoeRegenPotion extends SpellPotionBase {

    public static final AoeRegenPotion INSTANCE = new AoeRegenPotion();

    private AoeRegenPotion() {
        // boolean isBadEffectIn, int liquidColorIn
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

    }

    @Override
    public String GUID() {
        return "aoe_regen";
    }

    @Override
    public void doEffect(Entity applier, Entity caster, LivingEntity target,
                         int amplifier) {

    }

    @Override
    public void performEffectEverySetTime(LivingEntity entity, int amplifier) {

        UnitData data = Load.Unit(entity);

        for (LivingEntity en : this.getEntitiesAround(entity, 3F)) {

            if (en.world.isRemote) {
                ParticleUtils.spawnHealParticles(en, 3);
            } else {

                int healed = (int) data.getUnit().healthData().Value / 50;

                HealData healData = new HealData(entity, data, en, Load.Unit(en), healed);

                Load.Unit(en).heal(healData);

            }
        }

    }

    @Override
    public int performEachXTicks() {
        return 40;
    }

    @Override
    public String locNameForLangFile() {
        return "Aoe Regen";
    }
}
