package com.robertx22.mine_and_slash.vanilla_mc.potion_effects.bases;

import com.robertx22.mine_and_slash.vanilla_mc.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;

public class PotionEffectUtils {

    public static void reApplyToSelf(BasePotionEffect effect, LivingEntity caster) {
        apply(effect, caster, caster);
    }

    public static void applyToSelf(BasePotionEffect effect, LivingEntity caster) {
        apply(effect, caster, caster);
    }

    public static void apply(BasePotionEffect effect, LivingEntity caster, LivingEntity target) {

        if (caster.world.isRemote) {
            return;
        }

        int duration = effect.getDurationInTicks(caster);

        EntityCap.UnitData casterData = Load.Unit(caster);

        EffectInstance instance = target.getActivePotionEffect(effect);
        ExtraPotionData extraData;

        if (instance != null) {
            extraData = PotionDataSaving.getData(instance);
        } else {
            extraData = new ExtraPotionData();
        }

        if (extraData == null) {
            extraData = new ExtraPotionData();
        }

        if (extraData.getInitialDurationTicks() > 0) {
            duration = extraData.getInitialDurationTicks(); // if reapplied, apply existing duration
        }

        EffectInstance newInstance = new EffectInstance(effect, duration, extraData.getStacks(), false, false, true);

        // so it can recalc stats cus onpotion remoove/add
        if (instance == null) {

            extraData.casterID = caster.getUniqueID()
                .toString();
            extraData.setInitialDurationTicks(duration);

            PotionDataSaving.saveData(newInstance, extraData);

        } else {

            if (instance.getDuration() > duration) {
                duration = instance.getDuration();
            }

            extraData.casterID = caster.getUniqueID()
                .toString();
            extraData.setInitialDurationTicks(duration);
            extraData.addStacks(1, effect);

            PotionDataSaving.saveData(newInstance, extraData);

            target.removePotionEffect(effect); // HAVE TO REMOVE OR IT WONT ACTUALLY ADD CORRECTLY

        }
        target.addPotionEffect(newInstance);

        //target.sendMessage(new SText("You have " + getStacks(target, effect) + " " + effect.GUID() + " stacks "));

    }

    public static boolean reduceStacks(LivingEntity target, BasePotionEffect effect) {
        return reduceStacks(target, effect, 1);
    }

    public static boolean has(LivingEntity entity, BasePotionEffect effect) {
        return entity.getActivePotionEffect(effect) != null;
    }

    public static boolean reduceStacks(LivingEntity target, BasePotionEffect effect, int num) {

        EffectInstance instance = target.getActivePotionEffect(effect);

        if (instance != null) {
            ExtraPotionData extraData = PotionDataSaving.getData(instance);

            extraData.decreaseStacks(num, effect);

            if (extraData.getStacks() <= 0) {
                target.removePotionEffect(effect);
            } else {
                PotionDataSaving.saveData(instance, extraData);
            }
            return true;
        }

        return false;
    }

    public static int getStacks(LivingEntity en, BasePotionEffect effect) {
        EffectInstance instance = en.getActivePotionEffect(effect);

        if (instance != null) {
            ExtraPotionData extraData = PotionDataSaving.getData(instance);

            if (extraData != null) {
                return extraData.getStacks();
            }

        }
        return 0;

    }
}
