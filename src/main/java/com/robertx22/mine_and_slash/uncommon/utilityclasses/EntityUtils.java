package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.Vec3d;

import java.lang.reflect.Field;

public class EntityUtils {

    public static boolean isTryingButCantGetToPlayer(MobEntity mob) {
        if (mob.getAttackTarget() != null) {
            Path path = mob.getNavigator()
                .getPathToEntity(mob.getAttackTarget(), 0);
            if (path == null || !path.isFinished()) {
                return true;
            }
        }

        return false;
    }

    public static ItemStack getWeaponStackFromThrownEntity(Entity en) {

        for (Field field : en.getClass()
            .getFields()) {

            if (field.getType()
                .isAssignableFrom(ItemStack.class)) {
                try {
                    ItemStack stack = (ItemStack) field.get(en);
                    GearItemData gear = Gear.Load(stack);
                    if (gear != null) {
                        return stack;
                    }
                } catch (Exception e) {

                }

            }
        }

        try {
            for (EntityDataManager.DataEntry<?> entry : en.getDataManager()
                .getAll()) {
                if (entry.getValue() instanceof ItemStack) {
                    GearItemData gear = Gear.Load((ItemStack) entry.getValue());
                    if (gear != null) {
                        return (ItemStack) entry.getValue();
                    }
                }
            }
        } catch (Exception e) {
        }

        try {
            CompoundNBT nbt = en.serializeNBT();

            ItemStack stack = ItemStack.EMPTY;

            for (String key : nbt.keySet()) {
                if (stack == null || stack.isEmpty()) {
                    try {
                        if (nbt.get(key) instanceof CompoundNBT) {
                            ItemStack s = tryGetStackFromNbt(nbt.get(key));

                            if (!s.isEmpty() && Gear.has(s)) {
                                return s;
                            }

                        } else {

                            CompoundNBT nbt2 = (CompoundNBT) nbt.get(key);

                            for (String key2 : nbt2.keySet()) {
                                if (nbt.get(key) instanceof CompoundNBT) {
                                    ItemStack s2 = tryGetStackFromNbt(nbt2.get(key2));
                                    if (!s2.isEmpty() && Gear.has(s2)) {
                                        return s2;
                                    }

                                }
                            }

                        }
                    } catch (Exception e) {
                    }
                }

            }

            ItemStack tryWholeNbt = ItemStack.read(nbt);

            if (tryWholeNbt != null) {
                GearItemData gear = Gear.Load(tryWholeNbt);
                if (gear != null) {
                    return tryWholeNbt;
                }
            }

            if (stack == null) {
                stack = ItemStack.EMPTY;
            }

            return stack;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ItemStack.EMPTY;
    }

    private static ItemStack tryGetStackFromNbt(INBT nbt) {
        if (nbt instanceof CompoundNBT) {
            ItemStack s = ItemStack.read((CompoundNBT) nbt);
            if (s != null && !s.isEmpty()) {
                return s;

            }
        }

        return ItemStack.EMPTY;
    }

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
