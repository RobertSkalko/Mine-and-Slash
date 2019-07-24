package com.robertx22.mine_and_slash.dimensions.blocks;

import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TileMapPortal extends TileEntity {

    public String id;

    public TileMapPortal(DimensionType type) {
        super(BlockRegister.TILE_PORTAL_BLOCK);
        this.id = MapManager.getResourceLocation(type).toString();
    }

    public TileMapPortal() {
        super(BlockRegister.TILE_PORTAL_BLOCK);
    }

    int ticks = 0;

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

        id = nbt.getString("dim_Id_string");
        ticks = nbt.getInt("ticks");
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt); // The super call is required to save and load the tile loc

        nbt.putString("dim_Id_string", id);
        nbt.putInt("ticks", ticks);

        return nbt;
    }

}