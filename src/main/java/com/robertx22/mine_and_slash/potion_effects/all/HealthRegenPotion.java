package com.robertx22.mine_and_slash.potion_effects.all;

import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.RegenerateSpell;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.BasePotionEffect;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;

public class HealthRegenPotion extends BasePotionEffect {

    public static final HealthRegenPotion INSTANCE = new HealthRegenPotion();

    private HealthRegenPotion() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

    }

    @Override
    public String GUID() {
        return "self_regen";
    }

    @Override
    public void onXTicks(LivingEntity entity, EffectInstance instance) {

        try {

            if (entity.world.isRemote) {
                ParticleUtils.spawnHealParticles(entity, 3);
            } else {
                UnitData data = Load.Unit(entity);

                ResourcesData.Context hp = new ResourcesData.Context(data, entity, ResourcesData.Type.HEALTH,
                                                                     instance.getAmplifier(), ResourcesData.Use.RESTORE,
                                                                     new RegenerateSpell()
                );

                data.modifyResource(hp);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int performEachXTicks() {
        return 30;
    }

    @Override
    public String locNameForLangFile() {
        return "Regenerate";
    }
}
