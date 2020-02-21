package com.robertx22.mine_and_slash.blocks.beacon;

import com.robertx22.mine_and_slash.mmorpg.registers.common.TileEntityRegister;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.BeaconTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

public class CosmeticBeaconTile extends TileEntity implements ITickableTileEntity {

    public CosmeticBeaconTile() {
        super(TileEntityRegister.BEACON.get());

    }

    int lifeTicksRemaining = 60 * 20 * 2;

    List<BeaconTileEntity.BeamSegment> list = new ArrayList<>();

    public List<BeaconTileEntity.BeamSegment> getBeams() {
        if (list.isEmpty()) {
            float[] afloat = new float[]{
                1,
                0,
                1
            };
            list.add(new BeaconTileEntity.BeamSegment(afloat));
        }
        return list;
    }

    @Override
    public void tick() {
        this.lifeTicksRemaining--;

        if (lifeTicksRemaining < 1) {
            this.world.setBlockState(getPos(), Blocks.AIR.getDefaultState());
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);

        nbt.putInt("life", lifeTicksRemaining);

        return nbt;
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);

        this.lifeTicksRemaining = nbt.getInt("life");

    }
}
