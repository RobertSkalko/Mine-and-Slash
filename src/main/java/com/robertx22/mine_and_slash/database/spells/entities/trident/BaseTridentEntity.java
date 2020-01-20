package com.robertx22.mine_and_slash.database.spells.entities.trident;

import com.robertx22.mine_and_slash.database.spells.entities.bases.ISpellEntity;
import com.robertx22.mine_and_slash.database.spells.entities.bases.ServerEntitySpellData;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.datasaving.EntitySpellDataSaving;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Utilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BaseTridentEntity extends TridentEntity implements ISpellEntity {

    EntitySpellData syncedSpellData;
    ServerEntitySpellData serverSpellData;

    public BaseTridentEntity(EntityType<? extends TridentEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
        this.syncedSpellData = EntitySpellDataSaving.Load(nbt);
    }

    @Override
    public void writeAdditional(CompoundNBT nbt) {
        EntitySpellDataSaving.Save(nbt, syncedSpellData);
    }

    @Nullable
    public LivingEntity getCaster() {
        return Utilities.getLivingEntityByUUID(world, getCasterUUID());
    }

    @Override
    protected void registerData() {
        super.registerData();
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

    @Override
    public void tick() {

        if (this.timeInGround > 50) {
            this.removed = true;
        }

        super.tick();

        if (removed) {
            remove();
        }
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult ray) {

        Entity entity = ray.getEntity();
        Entity shooter = this.getCaster();

        if (entity == shooter) {
            return;
        }

        SoundEvent soundevent = SoundEvents.ITEM_TRIDENT_HIT;

        this.setMotion(this.getMotion().mul(-0.01D, -0.1D, -0.01D)); // bounce back

        this.playSound(soundevent, 8F, 1.0F);

    }
}
