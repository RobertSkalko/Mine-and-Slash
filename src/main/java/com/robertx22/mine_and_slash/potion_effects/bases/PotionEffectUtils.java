package com.robertx22.mine_and_slash.potion_effects.bases;

import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;

public class PotionEffectUtils {

    public static void applyToSelf(BasePotionEffect effect, int duration, LivingEntity caster) {
        apply(effect, duration, caster, caster);
    }

    public static void apply(BasePotionEffect effect, int duration, LivingEntity caster, LivingEntity target) {

        EntityCap.UnitData casterData = Load.Unit(caster);

        EffectInstance instance = target.getActivePotionEffect(effect);

        EffectInstance newInstance = new EffectInstance(effect, duration, 1, false, false, true);

        if (instance == null) {

            ExtraPotionData extraData = new ExtraPotionData();
            extraData.casterLvl = casterData.getLevel();

            PotionDataSaving.saveData(newInstance, extraData);

            target.addPotionEffect(newInstance);
        } else {

            if (instance.getDuration() > duration) {
                duration = instance.getDuration();
            }

            ExtraPotionData extraData = PotionDataSaving.getData(instance);
            extraData.casterLvl = casterData.getLevel();
            extraData.addStacks(1, effect);

            PotionDataSaving.saveData(newInstance, extraData);

            target.removePotionEffect(effect); // HAVE TO REMOVE OR IT WONT ACTUALLY ADD CORRECTLY
            target.addPotionEffect(newInstance);

            net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(
                    new net.minecraftforge.event.entity.living.PotionEvent.PotionAddedEvent(target,
                                                                                            target.getActivePotionEffect(
                                                                                                    effect), instance
                    ));

        }

    }

}
