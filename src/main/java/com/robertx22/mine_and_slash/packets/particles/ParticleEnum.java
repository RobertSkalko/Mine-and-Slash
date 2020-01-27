package com.robertx22.mine_and_slash.packets.particles;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.uncommon.enumclasses.RGB;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public enum ParticleEnum {

    THORNS() {
        @Override
        public void activate(ParticlePacketData data, World world, Entity entity) {
            Vec3d center = getCenter(data.pos);

            for (int i = 0; i < data.amount; i++) {
                Vec3d p = GeometryUtils.randomPos(center, world.rand);
                Vec3d m = GeometryUtils.randomMotion(center, world.rand);

                world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.BIRCH_LEAVES.getDefaultState()),
                                  p.x, p.y, p.z, m.x, m.y, m.z
                );
                world.addParticle(ParticleTypes.ITEM_SLIME, p.x, p.y, p.z, m.x, m.y, m.z);

            }
        }
    },

    PETRIFY() {
        @Override
        public void activate(ParticlePacketData data, World world, Entity entity) {
            Vec3d center = getCenter(data.pos);

            for (int i = 0; i < data.amount; i++) {
                Vec3d p = GeometryUtils.randomPos(center, world.rand);
                Vec3d m = GeometryUtils.randomMotion(center, world.rand);

                world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.STONE.getDefaultState()), p.x, p.y,
                                  p.z, m.x, m.y, m.z
                );
            }
        }
    },
    AOE() {
        @Override
        public void activate(ParticlePacketData data, World world, Entity entity) {
            Vec3d p = getCenter(data.pos);

            for (int i = 0; i < data.amount; i++) {
                Vec3d r = GeometryUtils.getRandomPosInRadiusCircle(p.x, p.y, p.z, data.radius);
                world.addParticle(data.getParticleType(), r.x, r.y, r.z, 1, 1, 1);
            }
        }
    },
    CIRCLE_REDSTONE() {
        @Override
        public void activate(ParticlePacketData data, World world, Entity entity) {
            Vec3d p = getCenter(data.pos);

            for (int i = 0; i < data.radius * 80; i++) {
                Vec3d r = GeometryUtils.getRandomPosInRadiusCircle(p.x, p.y, p.z, data.radius);
                this.spawnRedstone(world, data.color, r.x, r.y, r.z);
            }
        }
    },

    NOVA_REDSTONE() {
        @Override
        public void activate(ParticlePacketData data, World world, Entity entity) {

            Vec3d p = getCenter(data.pos);

            for (int i = 0; i < data.radius * 50; i++) {
                Vec3d r = GeometryUtils.getRandomHorizontalPosInRadiusCircle(p.x, p.y, p.z, data.radius);
                this.spawnRedstone(world, data.color, r.x, r.y, r.z);
            }
        }
    },
    BLAZING_INFERNO() {
        @Override
        public void activate(ParticlePacketData data, World world, Entity entity) {

            for (int i = 0; i < 150; i++) {
                Vec3d p = GeometryUtils.getRandomHorizontalPosInRadiusCircle(new Vec3d(data.pos), data.radius);
                world.addParticle(ParticleTypes.FLAME, p.x, p.y, p.z, 0, 0, 0);
                world.addParticle(ParticleTypes.SMOKE, p.x, p.y, p.z, 0, 0, 0);

            }
            SoundUtils.playSound(entity, SoundEvents.BLOCK_FIRE_EXTINGUISH, 1, 1);
        }
    };

    ParticleEnum() {

    }

    public static void sendToClients(Entity source, ParticlePacketData data) {
        if (source.world.isRemote) {
            data.type.activate(data, source.world, source);
        } else {
            MMORPG.sendToTracking(new ParticlePacket(data), source);
        }
    }

    public static void sendToClients(BlockPos pos, World world, ParticlePacketData data) {

        MMORPG.sendToTracking(new ParticlePacket(data), pos, world);

    }

    public Vec3d getCenter(BlockPos pos) {

        return new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
    }

    public void spawnRedstone(World world, RGB color, double xpos, double ypos, double zpos) {

        RedstoneParticleData data = new RedstoneParticleData(color.getR(), color.getG(), color.getB(), 1F);
        world.addParticle(data, true, xpos, ypos, zpos, 1, 1, 1);
    }

    public abstract void activate(ParticlePacketData data, World world, Entity entity);

}
