package com.robertx22.mine_and_slash.potion_effects.all;

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

public class ManaRegenPotion extends BasePotionEffect {

    public static final ManaRegenPotion INSTANCE = new ManaRegenPotion();

    private ManaRegenPotion() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

    }

    @Override
    public String locNameForLangFile() {
        return "Regen Mana";
    }

    @Override
    public String GUID() {
        return "mana_regen";
    }

    @Override
    public void onXTicks(LivingEntity entity, EffectInstance instance) {

        try {

            if (entity.world.isRemote) {
                ParticleUtils.spawnManaRestoreParticles(entity, 5);
            } else {
                UnitData data = Load.Unit(entity);

                ResourcesData.Context cost = new ResourcesData.Context(
                        data, entity, ResourcesData.Type.MANA, instance.getAmplifier(), ResourcesData.Use.RESTORE);

                data.getResources().modify(cost);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int performEachXTicks() {
        return 40;
    }
}
