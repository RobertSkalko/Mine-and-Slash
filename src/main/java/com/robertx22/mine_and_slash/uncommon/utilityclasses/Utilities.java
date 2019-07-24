package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.List;

public final class Utilities {

    /**
     * Shorthand for
     * {@link Utilities#getEntitiesWithinRadius(double, double, double, double, World, Class)}
     * with EntityLivingBase as the entity type. This is by far the most common use
     * for that method, which is why this shorthand exists.
     *
     * @param radius The search radius
     * @param x      The x coordinate to search around
     * @param y      The y coordinate to search around
     * @param z      The z coordinate to search around
     * @param world  The world to search in
     */
    public static List<LivingEntity> getEntitiesWithinRadius(double radius, double x,
                                                             double y, double z,
                                                             World world) {
        return getEntitiesWithinRadius(radius, x, y, z, world, LivingEntity.class);
    }

    /**
     * Returns all entities of the specified type within the specified radius of the
     * given coordinates. This is different to using a raw AABB because a raw AABB
     * will search in a cube volume rather than a sphere. Note that this does not
     * exclude any entities; if any specific entities are to be excluded this must
     * be checked when iterating through the list.
     *
     * @param radius     The search radius
     * @param x          The x coordinate to search around
     * @param y          The y coordinate to search around
     * @param z          The z coordinate to search around
     * @param world      The world to search in
     * @param entityType The class of entity to search for; pass in Entity.class for
     *                   all entities
     * @see {@link Utilities#getEntitiesWithinRadius(double, double, double, double, World)}
     */
    public static <T extends Entity> List<T> getEntitiesWithinRadius(double radius,
                                                                     double x, double y,
                                                                     double z,
                                                                     World world,
                                                                     Class<T> entityType) {
        AxisAlignedBB aabb = new AxisAlignedBB(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius);
        List<T> entityList = world.getEntitiesWithinAABB(entityType, aabb);
        for (int i = 0; i < entityList.size(); i++) {
            if (entityList.get(i)
                    .getPosition()
                    .manhattanDistance(new Vec3i(x, y, z)) > radius) {
                entityList.remove(i);
            }
        }
        return entityList;
    }

    public static <T extends Entity> List<T> getEntitiesWithinRadius(double horizontal,
                                                                     double vertical,
                                                                     Entity entity,
                                                                     Class<T> entityType) {
        double x = entity.posX;
        double y = entity.posY;
        double z = entity.posZ;
        AxisAlignedBB aabb = new AxisAlignedBB(x - horizontal, y - vertical, z - horizontal, x + horizontal, y + vertical, z + horizontal);
        List<T> entityList = entity.world.getEntitiesWithinAABB(entityType, aabb);

        return entityList;
    }

    public static <T extends Entity> List<T> getEntitiesWithinRadius(double radius,
                                                                     Entity entity,
                                                                     Class<T> entityType) {
        double x = entity.posX;
        double y = entity.posY;
        double z = entity.posZ;
        AxisAlignedBB aabb = new AxisAlignedBB(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius);
        List<T> entityList = entity.world.getEntitiesWithinAABB(entityType, aabb);
        for (int i = 0; i < entityList.size(); i++) {
            if (entityList.get(i).getDistance(entity) > radius) {
                entityList.remove(i);
            }
        }
        return entityList;
    }

    public static double getPlayerEyesPos(LivingEntity player) {
        return player.getBoundingBox().minY + player.getEyeHeight();
    }

}
