package com.robertx22.mine_and_slash.professions.blocks.bases;

import com.robertx22.mine_and_slash.blocks.bases.BaseInventoryBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class ProfessionBlock extends BaseInventoryBlock {

    public static final BooleanProperty ENABLED = BlockStateProperties.ENABLED;

    public ProfessionBlock() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(5F).notSolid());
        this.setDefaultState(this.stateContainer.getBaseState().with(ENABLED, true));

    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ENABLED);
        super.fillStateContainer(builder);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player,
                                             Hand hand, BlockRayTraceResult ray) {
        if (world.isRemote) {
            return ActionResultType.CONSUME;
        }

        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof ProfessionTile) {
            ProfessionTile prof = (ProfessionTile) tile;

            if (prof.canPlayerOpen((ServerPlayerEntity) player)) {

                prof.onOpenByPlayer((ServerPlayerEntity) player);

                NetworkHooks.openGui((ServerPlayerEntity) player, prof, extraData -> {
                    extraData.writeBlockPos(tile.getPos());

                });
            } else {
                player.sendMessage(new StringTextComponent(
                        TextFormatting.RED + "You can't use someone else's Profession Station."));
            }
        }

        return ActionResultType.SUCCESS;
    }

}