package com.robertx22.mine_and_slash.mobs.entity;

import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityUtils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.*;

public class ZombieGriefAttackGoal extends ZombieAttackGoal {

    public ZombieGriefAttackGoal(ZombieEntity zombie, double p_i46803_2_, boolean p_i46803_4_) {
        super(zombie, p_i46803_2_, p_i46803_4_);
    }

    int breakTicks = 0;

    BlockPos blockPos;

    @Override
    public void tick() {

        super.tick();

        try {
            if (EntityUtils.isTryingButCantGetToPlayer(this.attacker)) {
                breakTicks++;

                World world = attacker.world;

                if (blockPos == null) {

                    blockPos = getBlockPosToGrief();
                }

                if (blockPos != null) {
                    Random random = this.attacker.getRNG();

                    if (breakTicks % 5 == 0) {
                        if (!world.isRemote) {
                            double d0 = 0.08D;
                            ((ServerWorld) world).spawnParticle(new ItemParticleData(ParticleTypes.ITEM, new ItemStack(Items.STONE)), (double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.7D, (double) blockPos.getZ() + 0.5D, 3, ((double) random.nextFloat() - 0.5D) * 0.08D, ((double) random.nextFloat() - 0.5D) * 0.08D, ((double) random.nextFloat() - 0.5D) * 0.08D, (double) 0.15F);
                        }
                        this.playBreakingSound(world, blockPos);
                    }
                    if (breakTicks > 40) {
                        if (!world.isRemote) {
                            for (int i = 0; i < 20; ++i) {
                                double d3 = random.nextGaussian() * 0.02D;
                                double d1 = random.nextGaussian() * 0.02D;
                                double d2 = random.nextGaussian() * 0.02D;
                                ((ServerWorld) world).spawnParticle(ParticleTypes.POOF, (double) blockPos.getX() + 0.5D, (double) blockPos.getY(), (double) blockPos.getZ() + 0.5D, 1, d3, d1, d2, (double) 0.15F);
                            }
                        }
                        this.playBrokenSound(world, blockPos);

                        world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 2);
                        blockPos = null;
                        this.breakTicks = 0;
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void playBreakingSound(IWorld worldIn, BlockPos pos) {
        worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ENTITY_ZOMBIE_DESTROY_EGG, SoundCategory.HOSTILE, 0.5F, 0.9F + 0.2F);
    }

    public void playBrokenSound(World worldIn, BlockPos pos) {
        worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ENTITY_TURTLE_EGG_BREAK, SoundCategory.BLOCKS, 0.7F, 0.9F + worldIn.rand.nextFloat() * 0.2F);
    }

    public BlockPos getBlockPosToGrief() {

        BlockPos targetPos = this.attacker.getAttackTarget()
            .getPosition();
        BlockPos currentPos = this.attacker.getPosition();

        BlockPos startPos = currentPos;

        BlockPos[] list = new BlockPos[0];

        if (targetPos.getY() > currentPos.getY()) {
            startPos = startPos.up();

            list = new BlockPos[]{
                startPos,
                startPos.up(),
                startPos.down(),
                startPos.west(),
                startPos.east(),
                startPos.north(),
                startPos.south(),
                };

        } else if (targetPos.getY() == currentPos.getY()) {

            list = new BlockPos[]{

                startPos.west(),
                startPos.east(),
                startPos.north(),
                startPos.south(),

                startPos.up().west(),
                startPos.up().east(),
                startPos.up().north(),
                startPos.up().south(),

                };

        } else {
            list = new BlockPos[]{
                startPos.down()
            };
        }

        List<BlockPos> toDestroy = new ArrayList<>();

        for (BlockPos blockpos : list) {
            if (attacker.world.getBlockState(blockpos)
                .getBlock()
                .getDefaultState()
                .isSolid()) {
                toDestroy.add(blockpos);
            }

        }

        toDestroy.removeIf(x -> x.getY() == 0); // don't destroy bedrock at y 0

        Optional<BlockPos> opt = toDestroy.stream()
            .min(Comparator.comparingInt(x -> (int) x.distanceSq(targetPos)));

        if (opt.isPresent()) {
            return opt.get();
        }

        return null;

    }

}
