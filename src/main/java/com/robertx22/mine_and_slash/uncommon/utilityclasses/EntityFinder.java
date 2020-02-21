package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.uncommon.capability.server_wide.TeamCap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EntityFinder {

    static boolean isTamed(LivingEntity x) {
        if (x instanceof TameableEntity) {
            TameableEntity tame = (TameableEntity) x;
            return tame.isTamed();
        }
        return false;
    }

    private static boolean isPlayer(Entity en) {
        return en instanceof PlayerEntity;
    }

    public enum SearchFor {

        ALLIES() {
            @Override
            public <T extends LivingEntity> List<T> getMatchingEntities(List<T> list, Setup setup) {
                return list.stream()
                    .filter(x -> {
                        if (setup.isCasterPlayer()) {
                            if (isPlayer(x)) {
                                if (x.world.isRemote) {
                                    return true;
                                } else {
                                    return setup.teams
                                        .isOnSameTeam((ServerPlayerEntity) setup.caster, (ServerPlayerEntity) x);
                                }
                            } else {
                                return isTamed(x);
                            }

                        } else {
                            return x instanceof PlayerEntity == false && !isTamed(x);
                        }
                    })
                    .collect(Collectors.toList());
            }

            @Override
            public boolean includesCaster() {
                return true;
            }
        },
        ENEMIES {
            @Override
            public <T extends LivingEntity> List<T> getMatchingEntities(List<T> list, Setup setup) {
                return list.stream()
                    .filter(x -> {
                            if (setup.isCasterPlayer()) {
                                if (isPlayer(x)) {
                                    if (x.world.isRemote) {
                                        return false;
                                    } else {
                                        return !setup.teams
                                            .isOnSameTeam((ServerPlayerEntity) setup.caster, (ServerPlayerEntity) x);
                                    }
                                } else {
                                    return !isTamed(x);
                                }
                            } else
                                return isPlayer(x);
                        }
                    )
                    .collect(Collectors.toList());
            }

            @Override
            public boolean includesCaster() {
                return false;
            }
        },
        ALL {
            @Override
            public <T extends LivingEntity> List<T> getMatchingEntities(List<T> list, Setup setup) {
                return list;
            }

            @Override
            public boolean includesCaster() {
                return true;
            }
        };

        public abstract <T extends LivingEntity> List<T> getMatchingEntities(List<T> list, Setup setup);

        public abstract boolean includesCaster();
    }

    public enum Finder {
        RADIUS() {
            @Override
            public <T extends Entity> List<T> getEntities(Setup setup) {

                double x = setup.pos.getX();
                double y = setup.pos.getY();
                double z = setup.pos.getZ();

                double hori = setup.horizontal;
                double verti = setup.vertical;

                AxisAlignedBB aabb = new AxisAlignedBB(x - hori, y - verti, z - hori, x + hori, y + verti, z + hori);

                //Utilities.spawnParticlesForTesting(aabb, setup.world); // TODO TEST

                List<T> entityList = setup.world.getEntitiesWithinAABB(setup.entityType, aabb);

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

    public static <T extends LivingEntity> Setup<T> start(LivingEntity caster, Class<T> entityType, Vec3d pos) {
        Setup<T> setup = new Setup<T>(caster, entityType, pos);
        return setup;

    }

    public static class Setup<T extends LivingEntity> {

        Class<T> entityType;
        Finder finder = Finder.RADIUS;
        SearchFor searchFor = SearchFor.ENEMIES;
        LivingEntity caster;
        boolean forceExcludeCaster = false;
        World world;
        Vec3d pos;
        double radius = 1;
        double horizontal = 1;
        double vertical = 1;

        TeamCap.ITeamData teams = TeamCap.getCapability();

        List<Predicate<T>> predicates = new ArrayList();

        boolean setRadius = false;

        double distanceToSearch = 10;

        public Setup(LivingEntity caster, Class<T> entityType, Vec3d pos) {
            this.entityType = entityType;
            this.caster = caster;
            this.world = caster.world;
            this.pos = pos;
        }

        public boolean isCasterPlayer() {
            return caster instanceof PlayerEntity;
        }

        public List<T> build() {

            Objects.requireNonNull(caster, "Caster can't be null");
            Objects.requireNonNull(caster, "Blockpos can't be null");
            Objects.requireNonNull(caster, "World can't be null");

            List<T> list = this.finder.getEntities(this);

            list.removeIf(x -> x == null);

            for (Predicate<T> predicate : predicates) {
                list.removeIf(y -> !predicate.test(y));
            }

            list = this.searchFor.getMatchingEntities(list, this);

            if (forceExcludeCaster || !searchFor.includesCaster()) {
                list.removeIf(x -> x == caster);
            }

            list.removeIf(x -> !x.isAlive());

            return list;

        }

        public Setup<T> addPredicate(Predicate<T> p) {
            this.predicates.add(p);
            return this;
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

        public Setup<T> height(double rad) {
            this.vertical = rad;

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
