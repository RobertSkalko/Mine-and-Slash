package com.robertx22.mine_and_slash.database.spells.blocks.holy_flower;

import com.robertx22.mine_and_slash.database.spells.blocks.base.BaseSpellBlock;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class HolyFlowerBlock extends BaseSpellBlock {
    public static final String ID = Ref.MODID + ":holy_flower";

    public HolyFlowerBlock() {
        super();

    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new HolyFlowerTileEntity();
    }
}
