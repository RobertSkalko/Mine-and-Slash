package com.robertx22.mine_and_slash.dimensions.blocks;

import com.robertx22.mine_and_slash.mmorpg.registers.common.TileEntityRegister;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TileMapPortal extends TileEntity {

    public TileMapPortal() {
        super(TileEntityRegister.MAP_PORTAL.get());
    }

    int ticks = 0;
    public String dungeonID = "";

    public void ontick() {
        ticks++;
    }

    public boolean readyToTeleport() {

        if (ticks > 70) {
            ticks = 0;
            return true;
        }
        return false;

    }

    @OnlyIn(Dist.CLIENT)
    public boolean shouldRenderFace(Direction face) {
        return face == Direction.UP;
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);

        ticks = nbt.getInt("ticks");
        dungeonID = nbt.getString("id");
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt); // The super call is required to save and load the tile loc

        nbt.putInt("ticks", ticks);
        nbt.putString("id", dungeonID);

        return nbt;
    }

}