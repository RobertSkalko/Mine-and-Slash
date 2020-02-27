package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.Vec3d;

public class EntityUtils {
    public static LivingEntity getEntityCasterIsLookingAt(LivingEntity caster) {
        float d0 = 200;

        Vec3d vec3d = caster.getEyePosition(1);
        Vec3d vec3d1 = caster.getLook(1.0F);
        Vec3d vec3d2 = vec3d.add(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0);

        AxisAlignedBB axisalignedbb = caster.getBoundingBox()
            .expand(vec3d1.scale(d0))
            .grow(1, 1, 1);

        EntityRayTraceResult ray = ProjectileHelper.rayTraceEntities(caster.world, caster, vec3d, vec3d2, axisalignedbb, (en) -> {
            return !en.isSpectator() && en.canBeCollidedWith();
        }, 200);

        if (ray != null && ray.getEntity() instanceof LivingEntity) {
            return (LivingEntity) ray.getEntity();
        }

        return null;

    }

    public static void setLoc(LivingEntity entity, Vec3d p, float yaw, float pitch) {
        entity.setLocationAndAngles(p.x, p.y, p.z, yaw, pitch);
        entity.moveToBlockPosAndAngles(new BlockPos(p), yaw, pitch);
        entity.setPositionAndUpdate(p.x, p.y, p.z);
    }

}
