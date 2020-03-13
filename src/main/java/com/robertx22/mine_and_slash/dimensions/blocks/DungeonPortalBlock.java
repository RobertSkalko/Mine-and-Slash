package com.robertx22.mine_and_slash.dimensions.blocks;

import com.google.common.cache.LoadingCache;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModBlocks;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class DungeonPortalBlock extends Block {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final VoxelShape X_AABB = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    public DungeonPortalBlock() {
        super(Block.Properties.create(Material.PORTAL, MaterialColor.BLACK)
            .doesNotBlockMovement()
            .lightValue(11)
            .tickRandomly()
            .hardnessAndResistance(-1F)
            .noDrops());
        this.setDefaultState(this.stateContainer.getBaseState()
            .with(AXIS, Direction.Axis.X));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch ((Direction.Axis) state.get(AXIS)) {
            case Z:
                return Z_AABB;
            case X:
            default:
                return X_AABB;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState()
            .with(AXIS, context.getPlacementHorizontalFacing()
                .rotateYCCW()
                .getAxis());
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        Direction.Axis direction$axis = facing.getAxis();
        Direction.Axis direction$axis1 = stateIn.get(AXIS);
        boolean flag = direction$axis1 != direction$axis && direction$axis.isHorizontal();
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity en) {

        try {
            if (!en.isPassenger() && !en.isBeingRidden() && en.isNonBoss()) {
                if (!worldIn.isRemote) {
                    if (en instanceof PlayerEntity) {
                        PlayerEntity player = (PlayerEntity) en;

                        if (WorldUtils.isMapWorldClass(en.world)) {
                            Load.playerMapData(player)
                                .teleportPlayerBack(player);
                        } else {

                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(100) == 0) {
            worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int i = 0; i < 4; ++i) {
            double d0 = (double) pos.getX() + (double) rand.nextFloat();
            double d1 = (double) pos.getY() + (double) rand.nextFloat();
            double d2 = (double) pos.getZ() + (double) rand.nextFloat();
            double d3 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
            int j = rand.nextInt(2) * 2 - 1;
            if (worldIn.getBlockState(pos.west())
                .getBlock() != this && worldIn.getBlockState(pos.east())
                .getBlock() != this) {
                d0 = (double) pos.getX() + 0.5D + 0.25D * (double) j;
                d3 = (double) (rand.nextFloat() * 2.0F * (float) j);
            } else {
                d2 = (double) pos.getZ() + 0.5D + 0.25D * (double) j;
                d5 = (double) (rand.nextFloat() * 2.0F * (float) j);
            }

            worldIn.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
        }

    }

    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        switch (rot) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch ((Direction.Axis) state.get(AXIS)) {
                    case Z:
                        return state.with(AXIS, Direction.Axis.X);
                    case X:
                        return state.with(AXIS, Direction.Axis.Z);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    public static BlockPattern.PatternHelper createPatternHelper(IWorld p_181089_0_, BlockPos worldIn) {
        Direction.Axis direction$axis = Direction.Axis.Z;
        DungeonPortalBlock.Size size = new DungeonPortalBlock.Size(p_181089_0_, worldIn, Direction.Axis.X);
        LoadingCache<BlockPos, CachedBlockInfo> loadingcache = BlockPattern.createLoadingCache(p_181089_0_, true);
        if (!size.isValid()) {
            direction$axis = Direction.Axis.X;
            size = new DungeonPortalBlock.Size(p_181089_0_, worldIn, Direction.Axis.Z);
        }

        if (!size.isValid()) {
            return new BlockPattern.PatternHelper(worldIn, Direction.NORTH, Direction.UP, loadingcache, 1, 1, 1);
        } else {
            int[] aint = new int[Direction.AxisDirection.values().length];
            Direction direction = size.rightDir.rotateYCCW();
            BlockPos blockpos = size.bottomLeft.up(size.getHeight() - 1);

            for (Direction.AxisDirection dir : Direction.AxisDirection.values()) {
                BlockPattern.PatternHelper helper = new BlockPattern.PatternHelper(direction.getAxisDirection() == dir ? blockpos : blockpos.offset(size.rightDir, size.getWidth() - 1), Direction.getFacingFromAxis(dir, direction$axis), Direction.UP, loadingcache, size.getWidth(), size.getHeight(), 1);

                for (int i = 0; i < size.getWidth(); ++i) {
                    for (int j = 0; j < size.getHeight(); ++j) {
                        CachedBlockInfo cachedblockinfo = helper.translateOffset(i, j, 1);
                        if (!cachedblockinfo.getBlockState()
                            .isAir()) {
                            ++aint[dir.ordinal()];
                        }
                    }
                }
            }

            Direction.AxisDirection dir1 = Direction.AxisDirection.POSITIVE;

            for (Direction.AxisDirection dir2 : Direction.AxisDirection.values()) {
                if (aint[dir2.ordinal()] < aint[dir1.ordinal()]) {
                    dir1 = dir2;
                }
            }

            return new BlockPattern.PatternHelper(direction.getAxisDirection() == dir1 ? blockpos : blockpos.offset(size.rightDir, size.getWidth() - 1), Direction.getFacingFromAxis(dir1, direction$axis), Direction.UP, loadingcache, size.getWidth(), size.getHeight(), 1);
        }
    }

    public static class Size {
        private final IWorld world;
        private final Direction.Axis axis;
        private final Direction rightDir;
        private final Direction leftDir;
        private int portalBlockCount;
        @Nullable
        private BlockPos bottomLeft;
        private int height;
        private int width;

        public Size(IWorld worldIn, BlockPos pos, Direction.Axis axisIn) {
            this.world = worldIn;
            this.axis = axisIn;
            if (axisIn == Direction.Axis.X) {
                this.leftDir = Direction.EAST;
                this.rightDir = Direction.WEST;
            } else {
                this.leftDir = Direction.NORTH;
                this.rightDir = Direction.SOUTH;
            }

            for (BlockPos blockpos = pos; pos.getY() > blockpos.getY() - 21 && pos.getY() > 0 && this.func_196900_a(worldIn.getBlockState(pos.down())); pos = pos.down()) {
                ;
            }

            int i = this.getDistanceUntilEdge(pos, this.leftDir) - 1;
            if (i >= 0) {
                this.bottomLeft = pos.offset(this.leftDir, i);
                this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
                if (this.width < 2 || this.width > 21) {
                    this.bottomLeft = null;
                    this.width = 0;
                }
            }

            if (this.bottomLeft != null) {
                this.height = this.calculatePortalHeight();
            }

        }

        protected int getDistanceUntilEdge(BlockPos pos, Direction directionIn) {
            int i;
            for (i = 0; i < 22; ++i) {
                BlockPos blockpos = pos.offset(directionIn, i);
                if (!this.func_196900_a(this.world.getBlockState(blockpos)) || !isMyPortalFrame(this.world, blockpos.down())) {
                    break;
                }
            }

            BlockPos framePos = pos.offset(directionIn, i);
            return
                isMyPortalFrame(this.world, framePos) ? i : 0;
        }

        public int getHeight() {
            return this.height;
        }

        public int getWidth() {
            return this.width;
        }

        protected int calculatePortalHeight() {
            label56:
            for (this.height = 0; this.height < 21; ++this.height) {
                for (int i = 0; i < this.width; ++i) {
                    BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i)
                        .up(this.height);
                    BlockState blockstate = this.world.getBlockState(blockpos);
                    if (!this.func_196900_a(blockstate)) {
                        break label56;
                    }

                    Block block = blockstate.getBlock();
                    if (block == ModBlocks.DUNGEON_PORTAL.get()) {
                        ++this.portalBlockCount;
                    }

                    if (i == 0) {
                        BlockPos framePos = blockpos.offset(this.leftDir);
                        if (!
                            isMyPortalFrame(this.world, framePos)) {
                            break label56;
                        }
                    } else if (i == this.width - 1) {
                        BlockPos framePos = blockpos.offset(this.rightDir);
                        if (!
                            isMyPortalFrame(this.world, framePos)) {
                            break label56;
                        }
                    }
                }
            }

            for (int j = 0; j < this.width; ++j) {
                BlockPos framePos = this.bottomLeft.offset(this.rightDir, j)
                    .up(this.height);
                if (!isMyPortalFrame(this.world, framePos)) {
                    this.height = 0;
                    break;
                }
            }

            if (this.height <= 21 && this.height >= 3) {
                return this.height;
            } else {
                this.bottomLeft = null;
                this.width = 0;
                this.height = 0;
                return 0;
            }
        }

        boolean isMyPortalFrame(IWorldReader world, BlockPos pos) {
            return world.getBlockState(pos)
                .getBlock() == ModBlocks.DUNGEON_PORTAL.get();
        }

        protected boolean func_196900_a(BlockState pos) {
            Block block = pos.getBlock();
            return pos.isAir() || block == Blocks.FIRE || block == ModBlocks.DUNGEON_PORTAL.get();
        }

        public boolean isValid() {
            return this.bottomLeft != null && this.width >= 1 && this.width <= 21 && this.height >= 3 && this.height <= 21;
        }

        public void placePortalBlocks() {
            for (int i = 0; i < this.width; ++i) {
                BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);

                for (int j = 0; j < this.height; ++j) {
                    this.world.setBlockState(blockpos.up(j), ModBlocks.DUNGEON_PORTAL.get()
                        .getDefaultState()
                        .with(DungeonPortalBlock.AXIS, this.axis), 18);
                }
            }

        }

        private boolean func_196899_f() {
            return this.portalBlockCount >= this.width * this.height;
        }

        public boolean func_208508_f() {
            return this.isValid() && this.func_196899_f();
        }
    }
}