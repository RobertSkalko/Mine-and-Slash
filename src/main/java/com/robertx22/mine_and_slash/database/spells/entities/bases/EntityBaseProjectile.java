package com.robertx22.mine_and_slash.database.spells.entities.bases;

import com.robertx22.mine_and_slash.database.spells.bases.BaseSpell.SpellType;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IBuffableSpell;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Utilities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class EntityBaseProjectile extends AbstractArrowEntity implements IProjectile, IMyRenderAsItem, IBuffableSpell, IShootableProjectile {

    Entity homingTarget = null;

    public float shootSpeed = 1.3F;

    private int xTile;
    private int yTile;
    private int zTile;
    private Block inTile;
    protected boolean inGround;
    public int throwableShake;
    /**
     * The entity that threw this throwable item.
     */
    protected LivingEntity thrower;
    private String throwerName;
    private int ticksInGround;
    private int ticksInAir;
    private int deathTime = 80;
    private int airProcTime;
    private boolean doGroundProc;

    public Entity ignoreEntity;

    public SpellBuffType buff = SpellBuffType.None;
    public SpellType spellType = SpellType.Self_Heal;

    public abstract double radius();

    @Override
    protected void registerData() {
        super.registerData();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void setBuffType(SpellType type) {
        this.spellType = type;
    }

    @Override
    public SpellType getBuffType() {
        return this.spellType;
    }

    @Override
    public void setBuff(SpellBuffType buff) {
        this.buff = buff;
    }

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

    @Override
    public SpellBuffType getBuff() {
        return buff;
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

    public void setDeathTime(int newVal) {
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

    }

    @Nullable
    public LivingEntity getEntityHit(RayTraceResult result, double radius) {

        EntityRayTraceResult enres = null;

        if (result instanceof EntityRayTraceResult) {
            enres = (EntityRayTraceResult) result;
        }

        if (enres != null && enres.getEntity() instanceof LivingEntity) {
            if (enres.getEntity() != this.getThrower()) {
                return (LivingEntity) enres.getEntity();
            }
        }

        List<LivingEntity> entities = Utilities.getEntitiesWithinRadius(radius, this, LivingEntity.class);

        if (entities.size() > 0) {

            LivingEntity closest = entities.get(0);

            for (LivingEntity en : entities) {
                if (en != closest) {
                    if (this.getDistance(en) < this.getDistance(closest)) {
                        closest = en;
                    }
                }
            }

            if (closest == this.getThrower()) {
                return null;
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

        checkHoming();

        if (this.ticksExisted >= this.getDeathTime()) {
            onExpireProc(this.getThrower());
            this.remove();
        }

    }

    @Nullable
    protected EntityRayTraceResult func_213866_a(Vec3d pos, Vec3d posPlusMotion) {

        return ProjectileHelper.func_221271_a(this.world, this, pos, posPlusMotion, this.getBoundingBox()
                .expand(this.getMotion())
                .grow(1D), (e) -> {
            return !e.isSpectator() && e.canBeCollidedWith() && e instanceof LivingEntity && e != this.thrower && e != this.ignoreEntity;
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

            Vec3d vec3d = blockraytraceresult.getHitVec()
                    .subtract(this.posX, this.posY, this.posZ);
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

    public void checkHoming() {

        if (this.getBuff().equals(SpellBuffType.Homing_Projectile)) {
            // homing

            if (!this.collided && !this.world.isRemote) {

                double seekingRange = 5.0d;

                if (homingTarget == null || !homingTarget.isAlive()) {
                    List<LivingEntity> entities = Utilities.getEntitiesWithinRadius(seekingRange, this.posX, this.posY, this.posZ, this.world)
                            .stream()
                            .filter(x -> x.isAlive())
                            .collect(Collectors.toList());

                    for (Entity possibleTarget : entities) {
                        // Decides if current entity should be replaced.
                        if (homingTarget == null || this.getDistance(homingTarget) > this.getDistance(possibleTarget)) {
                            // Decides if new entity is a valid target.
                            if (Load.hasUnit(possibleTarget) && !possibleTarget.equals(this
                                    .getThrower())) {
                                homingTarget = possibleTarget;
                                this.setNoGravity(true);

                                this.setMotion(getMotion().x / 5, getMotion().y / 5, getMotion().z / 5);

                            }
                        }
                    }
                }

                if (homingTarget != null && Math.abs(this.getMotion().x) < 5 && Math.abs(this
                        .getMotion().y) < 5 && Math.abs(this.getMotion().z) < 5) {

                    this.addVelocity((homingTarget.posX - this.posX) / 30, (homingTarget.posY + homingTarget
                            .getHeight() / 2 - this.posY) / 30, (homingTarget.posZ - this.posZ) / 30);

                    // this.getMotion().y += (target.posY + target.height - this.posY) / 30;

                }
            }
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
    public void writeAdditional(CompoundNBT compound) {

        compound.putInt("xTile", this.xTile);
        compound.putInt("yTile", this.yTile);
        compound.putInt("zTile", this.zTile);

        compound.putByte("shake", (byte) this.throwableShake);
        compound.putByte("inGround", (byte) (this.inGround ? 1 : 0));

        if ((this.throwerName == null || this.throwerName.isEmpty()) && this.thrower instanceof PlayerEntity) {
            this.throwerName = this.thrower.getName().toString();
        }

        compound.putString("ownerName", this.throwerName == null ? "" : this.throwerName);
        compound.putBoolean("doGroundProc", this.getDoExpireProc());
        compound.putInt("airProcTime", this.getAirProcTime());
        compound.putInt("deathTime", this.getDeathTime());
    }

    /**
     * (abstract) Protected helper method to read subclass entity dataInstance from NBT.
     */
    @Override
    public void readAdditional(CompoundNBT compound) {

        this.xTile = compound.getInt("xTile");
        this.yTile = compound.getInt("yTile");
        this.zTile = compound.getInt("zTile");

        this.throwableShake = compound.getByte("shake") & 255;
        this.inGround = compound.getByte("inGround") == 1;
        this.thrower = null;
        this.throwerName = compound.getString("ownerName");

        if (this.throwerName != null && this.throwerName.isEmpty()) {
            this.throwerName = null;
        }

        this.thrower = this.getThrower();

        this.setDoExpireProc(compound.getBoolean("doGroundProc"));
        this.setAirProcTime(compound.getInt("airProcTime"));
        this.setDeathTime(compound.getInt("deathTime"));
    }

    @Nullable
    public LivingEntity getThrower() {
        if (this.thrower == null && this.throwerName != null && !this.throwerName.isEmpty()) {

            if (((ServerWorld) this.world).getEntityByUuid(UUID.fromString(this.throwerName)) instanceof LivingEntity) {
                this.thrower = (LivingEntity) ((ServerWorld) this.world).getEntityByUuid(UUID
                        .fromString(this.throwerName));
            }

            if (this.thrower == null && this.world instanceof ServerWorld) {
                try {
                    Entity entity = ((ServerWorld) this.world).getEntityByUuid(UUID.fromString(this.throwerName));

                    if (entity instanceof LivingEntity) {
                        this.thrower = (LivingEntity) entity;
                    }
                } catch (Throwable var2) {
                    this.thrower = null;
                }
            }
        }

        return this.thrower;
    }

    protected void setPos(LivingEntity caster) {
        Vec3d look = caster.getLookVec();
        setPosition(caster.posX - look.x, caster.posY - look.y + 1.3, caster.posZ - look.z);
    }
}