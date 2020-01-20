package com.robertx22.mine_and_slash.database.spells.entities.bases;

import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.datasaving.EntitySpellDataSaving;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Utilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public abstract class BaseInvisibleEntity extends Entity implements IMyRenderAsItem, ISpellEntity {

    EntitySpellData syncedSpellData;
    ServerEntitySpellData serverSpellData;

    public BaseInvisibleEntity(EntityType type, World world) {
        super(type, world);

        this.setNoGravity(true);
        this.setMotion(new Vec3d(0, 0, 0));
        this.setInvulnerable(true);
    }

    @Override
    public void tick() {

        super.tick();

        if (this.ticksExisted > getLifeInTicks() && getLifeInTicks() != -1) {
            this.remove();

        }
    }

    @Override
    public ItemStack getItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected void readAdditional(CompoundNBT nbt) {
        this.syncedSpellData = EntitySpellDataSaving.Load(nbt);
    }

    @Override
    protected void writeAdditional(CompoundNBT nbt) {
        EntitySpellDataSaving.Save(nbt, syncedSpellData);
    }

    @Nullable
    public LivingEntity getCaster() {
        return Utilities.getLivingEntityByUUID(world, getCasterUUID());
    }

    @Override
    protected void registerData() {

    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public boolean isValidTarget(Entity target) {
        return true;
    }

    @Override
    public boolean canRenderOnFire() {
        return false;
    }

    @Override
    public boolean isPushedByWater() {
        return false;
    }

    @Override
    public EntitySpellData getSyncedSpellData() {
        return syncedSpellData;
    }

    @Override
    public ServerEntitySpellData getServerSpellData() {
        return serverSpellData;
    }

    @Override
    public void setSyncedSpellData(EntitySpellData data) {
        this.syncedSpellData = data;
    }

    @Override
    public void setServerSpellData(ServerEntitySpellData data) {
        this.serverSpellData = data;
    }

    @Override
    public void writeSpawnData(PacketBuffer buf) {
        CompoundNBT nbt = new CompoundNBT();
        writeAdditional(nbt);
        buf.writeCompoundTag(nbt);
    }

    @Override
    public void readSpawnData(PacketBuffer buf) {
        CompoundNBT nbt = buf.readCompoundTag();
        this.readAdditional(nbt);
    }

}