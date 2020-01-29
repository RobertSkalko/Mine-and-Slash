package com.robertx22.mine_and_slash.database.spells.entities.bases;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell.SpellType;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.datasaving.EntitySpellDataSaving;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class EntityBaseProjectile extends AbstractArrowEntity implements IProjectile, IMyRenderAsItem,
        ISpellEntity {

    EntitySpellData spellData;

    private int xTile;
    private int yTile;
    private int zTile;
    private Block inTile;
    protected boolean inGround;
    public int throwableShake;

    private int ticksInGround;
    private int ticksInAir;
    private int deathTime = 80;
    private int airProcTime;
    private boolean doGroundProc;

    public Entity ignoreEntity;

    public SpellType spellType = SpellType.Self_Heal;

    public abstract double radius();

    @Override
    protected ItemStack getArrowStack() {
        return ItemStack.EMPTY;
    }

    protected boolean onExpireProc(LivingEntity caster) {
        return true;
    }

    @Override
    public Iterable<ItemStack> getArmorInventoryList() {
        return new ArrayList<>();
    }

    @Override
    public void setItemStackToSlot(EquipmentSlotType slotIn, ItemStack stack) {

    }

    public boolean getDoExpireProc() {
        return this.doGroundProc;
    }

    public int getTicksInAir() {
        return this.ticksInAir;
    }

    public int getTicksInGround() {
        return this.ticksInGround;
    }

    public void setTicksInAir(int newVal) {
        this.ticksInAir = newVal;
    }

    public void setTicksInGround(int newVal) {
        this.ticksInGround = newVal;
    }

    public int getAirProcTime() {
        return this.airProcTime;
    }

    public void setAirProcTime(int newVal) {
        this.airProcTime = newVal;
    }

    public int getDeathTime() {
        return this.deathTime;
    }

    private void setDeathTime(int newVal) {
        this.deathTime = newVal;
    }

    public void setDoExpireProc(boolean newVal) {
        this.doGroundProc = newVal;
    }

    public EntityBaseProjectile(EntityType<? extends Entity> type, World worldIn) {
        super((EntityType<? extends AbstractArrowEntity>) type, worldIn);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;

        this.setDeathTime(this.getDefaultLifeInTicks());

    }

    @Nullable
    public LivingEntity getEntityHit(RayTraceResult result, double radius) {

        EntityRayTraceResult enres = null;

        if (result instanceof EntityRayTraceResult) {
            enres = (EntityRayTraceResult) result;
        }

        if (enres != null && enres.getEntity() instanceof LivingEntity) {
            if (enres.getEntity() != this.getCaster()) {
                return (LivingEntity) enres.getEntity();
            }
        }

        List<LivingEntity> entities = EntityFinder.start(getCaster(), LivingEntity.class, getPosition())
                .radius(radius)
                .build();

        if (entities.size() > 0) {

            LivingEntity closest = entities.get(0);

            for (LivingEntity en : entities) {
                if (en != closest) {
                    if (this.getDistance(en) < this.getDistance(closest)) {
                        closest = en;
                    }
                }
            }

            return closest;
        }

        return null;

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isInRangeToRenderDist(double distance) {
        double d0 = this.getBoundingBox().getAverageEdgeLength() * 4.0D;
        if (Double.isNaN(d0)) {
            d0 = 4.0D;
        }

        d0 = d0 * 64.0D;
        return distance < d0 * d0;
    }

    /**
     * Updates the entity motion clientside, called by packets from the server
     */
    @OnlyIn(Dist.CLIENT)
    public void setVelocity(double x, double y, double z) {
        this.setMotion(new Vec3d(x, y, z));

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt(x * x + z * z);
            this.rotationYaw = (float) (MathHelper.atan2(x, z) * (180D / Math.PI));
            this.rotationPitch = (float) (MathHelper.atan2(y, (double) f) * (180D / Math.PI));
            this.prevRotationYaw = this.rotationYaw;
            this.prevRotationPitch = this.rotationPitch;
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void tick() {
        super.tick();

        if (this.inGround) {
            ticksInGround++;
        } else {
            ticksInAir++;
        }

        if (this.ticksExisted >= this.getDeathTime()) {
            onExpireProc(this.getCaster());
            this.remove();
        }

    }

    @Override
    @Nullable
    protected EntityRayTraceResult rayTraceEntities(Vec3d pos, Vec3d posPlusMotion) {

        return ProjectileHelper.rayTraceEntities(
                this.world, this, pos, posPlusMotion, this.getBoundingBox().expand(this.getMotion()).grow(1D), (e) -> {
                    return !e.isSpectator() && e.canBeCollidedWith() && e instanceof LivingEntity && e != this.getCaster() && e != this.ignoreEntity;
                });

    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {

        RayTraceResult.Type raytraceresult$type = raytraceResultIn.getType();
        if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
            this.onImpact(raytraceResultIn);
            this.playSound(SoundEvents.ENTITY_SHULKER_BULLET_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

        } else if (raytraceresult$type == RayTraceResult.Type.BLOCK) {
            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceResultIn;
            BlockState blockstate = this.world.getBlockState(blockraytraceresult.getPos());

            Vec3d vec3d = blockraytraceresult.getHitVec().subtract(this.posX, this.posY, this.posZ);
            this.setMotion(vec3d);
            Vec3d vec3d1 = vec3d.normalize().scale((double) 0.05F);
            this.posX -= vec3d1.x;
            this.posY -= vec3d1.y;
            this.posZ -= vec3d1.z;
            this.inGround = true;

            this.onImpact(blockraytraceresult);

            blockstate.onProjectileCollision(this.world, blockstate, blockraytraceresult, this);

        }
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected abstract void onImpact(RayTraceResult result);

    /**
     * (abstract) Protected helper method to write subclass entity dataInstance to NBT.
     */

    @Override
    public void writeAdditional(CompoundNBT nbt) {

        nbt.putInt("xTile", this.xTile);
        nbt.putInt("yTile", this.yTile);
        nbt.putInt("zTile", this.zTile);

        nbt.putByte("shake", (byte) this.throwableShake);
        nbt.putByte("inGround", (byte) (this.inGround ? 1 : 0));

        nbt.putBoolean("doGroundProc", this.getDoExpireProc());
        nbt.putInt("airProcTime", this.getAirProcTime());
        nbt.putInt("deathTime", this.getDeathTime());

        EntitySpellDataSaving.Save(nbt, spellData);
    }

    /**
     * (abstract) Protected helper method to read subclass entity dataInstance from NBT.
     */
    @Override
    public void readAdditional(CompoundNBT nbt) {

        this.xTile = nbt.getInt("xTile");
        this.yTile = nbt.getInt("yTile");
        this.zTile = nbt.getInt("zTile");

        this.throwableShake = nbt.getByte("shake") & 255;
        this.inGround = nbt.getByte("inGround") == 1;

        this.setDoExpireProc(nbt.getBoolean("doGroundProc"));
        this.setAirProcTime(nbt.getInt("airProcTime"));
        this.setDeathTime(nbt.getInt("deathTime"));

        this.spellData = EntitySpellDataSaving.Load(nbt);
    }

    protected void setPos(LivingEntity caster) {
        Vec3d look = caster.getLookVec();
        setPosition(caster.posX - look.x, caster.posY - look.y + 1.3, caster.posZ - look.z);
    }

    ////////////////////////////////////////////////////////

    @Nullable
    public LivingEntity getCaster() {
        return this.getSpellData().getCaster(world);
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
        return spellData;
    }

    @Override
    public void setSpellData(EntitySpellData data) {
        this.spellData = data;
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