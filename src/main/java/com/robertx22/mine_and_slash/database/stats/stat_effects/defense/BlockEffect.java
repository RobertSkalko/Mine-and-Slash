package com.robertx22.mine_and_slash.database.stats.stat_effects.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;

import java.util.UUID;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class BlockEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return Priority.Last.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof DamageEffect) {
                DamageEffect dmgeffect = (DamageEffect) Effect;

                DamageSource dmgsource = DamageSource.causeMobDamage(Effect.source);

                if (canBlockDamageSource(Effect.target, dmgsource)) {

                    float blockval = data.Value;

                    float afterblock = Effect.number - blockval;

                    if (afterblock < 0) {
                        dmgeffect.isFullyBlocked = true;
                    } else {
                        dmgeffect.isPartiallyBlocked = true;
                        applyKnockbackResist(Effect.target);
                    }

                    dmgeffect.number = afterblock;

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

    public static AttributeModifier MOD = new AttributeModifier(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"), Ref.MODID + "knockbackresist", 100, AttributeModifier.Operation.ADDITION);

    public static void applyKnockbackResist(LivingEntity entity) {

        if (entity.getAttribute(KNOCKBACK_RESISTANCE).hasModifier(MOD) == false) {
            entity.getAttribute(KNOCKBACK_RESISTANCE).applyModifier(MOD);
        }

    }

    public static void removeKnockbackResist(LivingEntity entity) {

        if (entity.getAttribute(KNOCKBACK_RESISTANCE).hasModifier(MOD)) {
            entity.getAttribute(KNOCKBACK_RESISTANCE).removeModifier(MOD);
        }

    }

    private boolean canBlockDamageSource(LivingEntity target,
                                         DamageSource damageSourceIn) {
        if (target.isActiveItemStackBlocking()) {
            Vec3d vec3d = damageSourceIn.getDamageLocation();

            if (vec3d != null) {
                Vec3d vec3d1 = target.getLook(1.0F);
                Vec3d vec3d2 = vec3d.subtractReverse(new Vec3d(target.posX, target.posY, target.posZ))
                        .normalize();
                vec3d2 = new Vec3d(vec3d2.x, 0.0D, vec3d2.z);

                if (vec3d2.dotProduct(vec3d1) < 0.0D) {
                    return true;
                }
            }
        }

        return false;
    }
}
