package com.robertx22.mine_and_slash.mixins;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.onevent.entity.damage.LivingHurtUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(
        method = "applyArmorCalculations(Lnet/minecraft/util/DamageSource;F)F",
        at = @At("HEAD"),
        cancellable = true
    )
    public void onArmorReduction(DamageSource source, float damage, CallbackInfoReturnable<Float> ci) {
        MMORPG.mixinLog(source.damageType + " class:" + source.getClass()
            .toString());

        if (LivingHurtUtils.isEnviromentalDmg(source)) {
            MMORPG.mixinLog("Not changing enviromental dmg");
            return;
        } else {
            if (!source.isUnblockable()) {
                MMORPG.mixinLog("Returning dmg value before it's affected by armor calculation.");

                LivingEntity en = (LivingEntity) (Object) this;

                LivingHurtUtils.damageArmorItems(en);

                ci.setReturnValue(damage);
            } else {
                MMORPG.mixinLog("Is unblockable");
            }
        }

    }
}
