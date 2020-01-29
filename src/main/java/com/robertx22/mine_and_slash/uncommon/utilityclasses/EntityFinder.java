package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class EntityFinder {

    public enum SearchFor {

        ALLIES() {
            @Override
            public <T extends Entity> List<T> getMatchingEntities(List<T> list, Setup setup) {
                if (setup.caster instanceof PlayerEntity) {
                    list.removeIf(x -> x instanceof PlayerEntity == false);
                } else {
                    list.removeIf(x -> x instanceof PlayerEntity);
                }
                return list;
            }

            @Override
            public boolean includesCaster() {
                return true;
            }
        },
        ENEMIES {
            @Override
            public <T extends Entity> List<T> getMatchingEntities(List<T> list, Setup setup) {
                if (setup.caster instanceof PlayerEntity) {
                    list.removeIf(x -> x instanceof PlayerEntity);
                } else {
                    list.removeIf(x -> x instanceof PlayerEntity == false);
                }
                return list;
            }

            @Override
            public boolean includesCaster() {
                return false;
            }
        },
        ALL {
            @Override
            public <T extends Entity> List<T> getMatchingEntities(List<T> list, Setup setup) {
                return list;
            }

            @Override
            public boolean includesCaster() {
                return true;
            }
        };

        public abstract <T extends Entity> List<T> getMatchingEntities(List<T> list, Setup setup);

        public abstract boolean includesCaster();
    }

    public enum Finder {
        RADIUS() {
            @Override
            public <T extends Entity> List<T> getEntities(Setup setup) {

                float x = setup.pos.getX();
                float y = setup.pos.getY();
                float z = setup.pos.getZ();

                double radius = setup.radius;

                AxisAlignedBB aabb = new AxisAlignedBB(
                        x - radius, y - radius, z - radius, x + radius, y + radius, z + radius);
                List<T> entityList = setup.world.getEntitiesWithinAABB(setup.entityType, aabb);
                for (int i = 0; i < entityList.size(); i++) {
                    if (entityList.get(i).getPosition().manhattanDistance(new Vec3i(x, y, z)) > radius) {
                        entityList.remove(i);
                    }
                }
                return entityList;
            }
        },

        IN_FRONT {
            @Override
            public <T extends Entity> List<T> getEntities(Setup setup) {

                LivingEntity entity = setup.caster;

                double distance = setup.distanceToSearch;

                double horizontal = setup.horizontal;
                double vertical = setup.vertical;

                double x = entity.posX;
                double y = entity.posY;
                double z = entity.posZ;

                Vec3d l = Utilities.getEndOfLook(entity, distance);

                double minX = x < l.x ? x : l.x;
                double minY = y < l.y ? y : l.y;
                double minZ = z < l.z ? z : l.z;

                double maxX = x > l.x ? x : l.x;
                double maxY = y > l.y ? y : l.y;
                double maxZ = z > l.z ? z : l.z;

                AxisAlignedBB aabb = new AxisAlignedBB(minX - horizontal, minY - vertical, minZ - horizontal,
                                                       maxX + horizontal, maxY + vertical, maxZ + horizontal
                );

                List<T> entityList = entity.world.getEntitiesWithinAABB(setup.entityType, aabb);
                entityList.removeIf(e -> e == entity);

                return entityList;
            }
        };

        public abstract <T extends Entity> List<T> getEntities(Setup setup);

    }

    public static <T extends Entity> Setup<T> start(LivingEntity caster, Class<T> entityType, BlockPos pos) {
        Setup<T> setup = new Setup<T>(caster, entityType, pos);
        return setup;

    }

    public static class Setup<T extends Entity> {

        Class<T> entityType;
        Finder finder = Finder.RADIUS;
        SearchFor searchFor = SearchFor.ENEMIES;
        LivingEntity caster;
        boolean forceExcludeCaster = false;
        World world;
        BlockPos pos;
        double radius = 1;
        double horizontal = 1;
        double vertical = 1;

        double distanceToSearch = 10;

        public Setup(LivingEntity caster, Class<T> entityType, BlockPos pos) {
            this.entityType = entityType;
            this.caster = caster;
            this.world = caster.world;
            this.pos = pos;
        }

        public List<T> build() {

            Objects.requireNonNull(caster, "Caster can't be null");
            Objects.requireNonNull(caster, "Blockpos can't be null");
            Objects.requireNonNull(caster, "World can't be null");

            List<T> list = this.finder.getEntities(this);

            list = this.searchFor.getMatchingEntities(list, this);

            if (forceExcludeCaster || !searchFor.includesCaster()) {
                list.removeIf(x -> x == caster);
            }

            return list;

        }

        public Setup<T> finder(Finder f) {
            this.finder = f;
            return this;
        }

        public Setup<T> searchFor(SearchFor f) {
            this.searchFor = f;
            return this;
        }

        public Setup<T> distance(double distance) {
            this.distanceToSearch = distance;
            return this;
        }

        public Setup<T> radius(double rad) {
            this.radius = rad;
            this.horizontal = rad;
            this.vertical = rad;
            return this;
        }

        public Setup<T> excludeCaster(boolean bool) {
            this.forceExcludeCaster = bool;
            return this;
        }

    }
}
