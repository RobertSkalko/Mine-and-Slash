package com.robertx22.mine_and_slash.potion_effects.bases;

import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ClientOnly;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;

public class PotionEffectUtils {

    public static void reApplyToSelf(BasePotionEffect effect, LivingEntity caster) {
        apply(effect, -1, caster, caster);
    }

    public static void applyToSelf(BasePotionEffect effect, int duration, LivingEntity caster) {
        apply(effect, duration, caster, caster);
    }

    public static ExtraPotionData getDataForTooltips(BasePotionEffect effect) {
        LivingEntity caster = ClientOnly.getPlayer();

        ExtraPotionData data = new ExtraPotionData();
        data.casterID = caster.getUniqueID().toString();
        data.casterLvl = Load.Unit(caster).getLevel();

        return data;

    }

    public static void apply(BasePotionEffect effect, int duration, LivingEntity caster, LivingEntity target) {

        EntityCap.UnitData casterData = Load.Unit(caster);

        EffectInstance instance = target.getActivePotionEffect(effect);
        ExtraPotionData extraData;

        if (instance != null) {
            extraData = PotionDataSaving.getData(instance);
        } else {
            extraData = new ExtraPotionData();
        }

        if (extraData.getInitialDurationTicks() > 0) {
            duration = extraData.getInitialDurationTicks(); // if reapplied, apply existing duration
        }

        EffectInstance newInstance = new EffectInstance(effect, duration, 1, false, false, true);

        if (instance == null) {

            extraData.casterLvl = casterData.getLevel();
            extraData.casterID = caster.getUniqueID().toString();
            extraData.setInitialDurationTicks(duration);

            PotionDataSaving.saveData(newInstance, extraData);

            target.addPotionEffect(newInstance);
        } else {

            if (instance.getDuration() > duration) {
                duration = instance.getDuration();
            }

            extraData.casterLvl = casterData.getLevel();
            extraData.casterID = caster.getUniqueID().toString();
            extraData.setInitialDurationTicks(duration);
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
