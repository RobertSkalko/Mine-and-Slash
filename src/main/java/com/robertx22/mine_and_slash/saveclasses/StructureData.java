package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

@Storable
public class StructureData {

    @Store
    private int Y = -1;

    public int getY(IWorld iworld, BlockPos pos) {

        if (Y == -1) {
            Y = WorldUtils.getSurfaceCenterOfChunk(iworld, pos).getY();
        }

        return Y;
    }

}
