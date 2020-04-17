package com.robertx22.mine_and_slash.database.spells.entities.trident;

import com.robertx22.mine_and_slash.database.spells.entities.bases.ISpellEntity;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.datasaving.EntitySpellDataSaving;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public abstract class BaseTridentEntity extends TridentEntity implements ISpellEntity {

    EntitySpellData syncedSpellData;

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
        return getSpellData().getCaster(world);
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
    public EntitySpellData getSpellData() {
        return syncedSpellData;
    }

    @Override
    public void setSpellData(EntitySpellData data) {
        this.syncedSpellData = data;
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

        if (world.isRemote) {
            if (this.ticksExisted > 2) {
                for (int i = 0; i < 10; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(posX, posY, posZ, 0.1F);
                    ParticleUtils.spawn(ParticleRegister.THUNDER, world, p);
                }
            }
        }

        super.tick();

        if (removed) {
            remove();
        }
    }

    public void onHit(LivingEntity en) {

        Entity shooter = this.getCaster();
        DamageEffect dmg = dealSpellDamageTo((LivingEntity) en, new Options().activatesEffect(false));
        dmg.Activate();
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult ray) {

        if (!world.isRemote) {

            Entity entity = ray.getEntity();
            if (entity instanceof LivingEntity) {
                Entity shooter = this.getCaster();

                if (entity == shooter) {
                    return;
                }
                this.setMotion(this.getMotion()
                    .mul(-0.01D, -0.1D, -0.01D)); // bounce back

                this.onHit((LivingEntity) entity);

                this.playSound(SoundEvents.ITEM_TRIDENT_HIT, 8F, 1.0F);

            }
        }
    }
}
