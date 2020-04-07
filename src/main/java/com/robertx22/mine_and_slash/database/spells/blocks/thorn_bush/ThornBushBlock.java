package com.robertx22.mine_and_slash.database.spells.blocks.thorn_bush;

import com.robertx22.mine_and_slash.database.spells.blocks.base.BaseSpellBlock;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class ThornBushBlock extends BaseSpellBlock {
    public static final String ID = Ref.MODID + ":thorn_bush";

    public ThornBushBlock() {
        super();

    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {

        return new ThornBushTileEntity();

    }
}
