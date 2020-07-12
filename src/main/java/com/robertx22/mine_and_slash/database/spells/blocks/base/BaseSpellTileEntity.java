package com.robertx22.mine_and_slash.database.spells.blocks.base;

import com.robertx22.mine_and_slash.database.spells.entities.bases.ISpellEntity;
import com.robertx22.mine_and_slash.saveclasses.spells.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.datasaving.EntitySpellDataSaving;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public abstract class BaseSpellTileEntity extends TileEntity implements ISpellEntity, ITickableTileEntity {

    public EntitySpellData data = new EntitySpellData();

    public BaseSpellTileEntity(TileEntityType type) {
        super(type);
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);
        this.data = EntitySpellDataSaving.Load(nbt);
    }

    @Override
    public final void tick() {

        try {
            data.ticksExisted++;
        } catch (Exception e) {
            e.printStackTrace();
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
            return;
        }

        try {
            onTick();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (data.getRemainingLifeTicks() < 1 && data.ticksExisted > 5) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public abstract void onTick();

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);
        EntitySpellDataSaving.Save(nbt, data);
        return nbt;

    }

    @Override
    public EntitySpellData getSpellData() {
        return data;
    }

    @Override
    public void setSpellData(EntitySpellData data) {
        this.data = data;
    }

    @Override
    public void writeSpawnData(PacketBuffer buf) {
        CompoundNBT nbt = new CompoundNBT();
        write(nbt);
        buf.writeCompoundTag(nbt);
    }

    @Override
    public void readSpawnData(PacketBuffer buf) {
        CompoundNBT nbt = buf.readCompoundTag();
        this.read(nbt);
    }
}
