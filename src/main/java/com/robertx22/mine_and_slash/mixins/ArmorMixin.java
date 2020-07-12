package com.robertx22.mine_and_slash.mixins;

import com.robertx22.mine_and_slash.onevent.entity.damage.LivingHurtUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class ArmorMixin {

    @Inject(
        method = "net.minecraft.entity.LivingEntity.applyArmorCalculations(Lnet/minecraft/util/DamageSource;F)F",
        at = @At("HEAD"),
        cancellable = true
    )
    public void onArmorReduction(DamageSource source, float damage, CallbackInfoReturnable<Float> ci) {
        System.out.println(source.damageType + " class:" + source.getClass()
            .toString());

        if (LivingHurtUtils.isEnviromentalDmg(source)) {
            System.out.println("Not changing enviromental");
            return;
        } else {
            if (!source.isUnblockable()) {
                System.out.println("Returning dmg value before it's affected by armor calculation.");

                LivingEntity en = (LivingEntity) (Object) this;

                LivingHurtUtils.damageArmorItems(en);

                ci.setReturnValue(damage);
            }
        }

    }
}
