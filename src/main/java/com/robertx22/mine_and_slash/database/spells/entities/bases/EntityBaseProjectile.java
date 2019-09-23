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
import net.minecraft.particles.ParticleTypes;
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

    Entity homindTarget = null;

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
     * Sets throwable heading based on an entity that's throwing it
     */
    public void shoot(Entity entityThrower, float rotationPitchIn, float rotationYawIn,
                      float pitchOffset, float velocity, float inaccuracy) {
        float f = -MathHelper.sin(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
        float f1 = -MathHelper.sin((rotationPitchIn + pitchOffset) * 0.017453292F);
        float f2 = MathHelper.cos(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
        this.shoot((double) f, (double) f1, (double) f2, velocity, inaccuracy);
        this.setMotion(getMotion().x + entityThrower.getMotion().x, getMotion().y, getMotion().z + entityThrower
                .getMotion().z);

        if (entityThrower instanceof LivingEntity) {
            this.thrower = (LivingEntity) entityThrower;
        }
        if (!entityThrower.onGround) {
            this.setMotion(this.getMotion().add(0, entityThrower.getMotion().y, 0));

        }
    }

    /**
     * Similar to setArrowHeading, it's point the throwable entity to a x, y, z
     * direction.
     */
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        float f = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / (double) f;
        y = y / (double) f;
        z = z / (double) f;
        x = x + this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        y = y + this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        z = z + this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        x = x * (double) velocity;
        y = y * (double) velocity;
        z = z * (double) velocity;

        this.setMotion(x, y, z);

        float f1 = MathHelper.sqrt(x * x + z * z);
        this.rotationYaw = (float) (MathHelper.atan2(x, z) * (180D / Math.PI));
        this.rotationPitch = (float) (MathHelper.atan2(y, (double) f1) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
        this.ticksInGround = 0;
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

        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        super.tick();

        if (this.throwableShake > 0) {
            --this.throwableShake;
        }

        if (this.inGround) {
            if (this.world.getBlockState(new BlockPos(this.xTile, this.yTile, this.zTile))
                    .getBlock() == this.inTile) {
                ++this.ticksInGround;

                if (this.ticksInGround == 1200) {
                    this.remove();
                }
                return;
            }

            this.inGround = false;

            this.setMotion(getMotion().x * this.rand.nextFloat() * 0.2F, getMotion().y * this.rand
                    .nextFloat() * 0.2F, getMotion().z * this.rand.nextFloat() * 0.2F);

            this.ticksInGround = 0;
            this.ticksInAir = 0;
        } else {
            ++this.ticksInAir;
        }

        this.posX += this.getMotion().x;
        this.posY += this.getMotion().y;
        this.posZ += this.getMotion().z;

        checkIfImpact();

        this.rotationYaw = (float) (MathHelper.atan2(this.getMotion().x, this.getMotion().z) * (180D / Math.PI));

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
            this.prevRotationPitch += 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
            this.prevRotationYaw -= 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
            this.prevRotationYaw += 360.0F;
        }

        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
        float f1 = 0.99F;
        float f2 = this.getGravityVelocity();

        if (world.isRemote && this.isInWater()) {
            for (int j = 0; j < 4; ++j) {
                this.world.addParticle(ParticleTypes.BUBBLE, true, this.posX - this.getMotion().x * 0.25D, this.posY - this
                        .getMotion().y * 0.25D, this.posZ - this.getMotion().z * 0.25D, this
                        .getMotion().x, this.getMotion().y, this.getMotion().z);
            }
            f1 = 0.8F;
        }

        this.setMotion(this.getMotion().x * f1, this.getMotion().y * f1, this.getMotion().z * f1);

        if (!this.hasNoGravity()) {
            this.setMotion(this.getMotion().subtract(new Vec3d(0D, (double) f2, 0D)));
        }

        this.setPosition(this.posX, this.posY, this.posZ);

        checkHoming();

    }

    public void checkIfImpact() {

        Vec3d motion = this.getMotion();
        Vec3d pos = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d posPlusMotion = pos.add(motion);

        RayTraceResult raytraceresult = this.world.rayTraceBlocks(new RayTraceContext(pos, posPlusMotion, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));
        if (raytraceresult.getType() != RayTraceResult.Type.MISS) {
            posPlusMotion = raytraceresult.getHitVec();
        }

        EntityRayTraceResult entityraytraceresult = ProjectileHelper.func_221271_a(this.world, this, pos, posPlusMotion, this
                .getBoundingBox()
                .expand(this.getMotion())
                .grow(1D), (e) -> {
            return !e.isSpectator() && e.canBeCollidedWith() && e instanceof LivingEntity && e != this.thrower && e != this.ignoreEntity;
        });

        if (entityraytraceresult != null) {
            raytraceresult = entityraytraceresult;
        }

        if (raytraceresult != null) {
            onHit(raytraceresult);
        }

    }

    protected void onHit(RayTraceResult raytraceResultIn) {
        RayTraceResult.Type raytraceresult$type = raytraceResultIn.getType();
        if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
            this.onImpact((EntityRayTraceResult) raytraceResultIn);
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
            blockstate.onProjectileCollision(this.world, blockstate, blockraytraceresult, this);
            this.remove();
        }

    }

    public void checkHoming() {

        if (this.getBuff().equals(SpellBuffType.Homing_Projectile)) {
            // homing

            if (!this.collided && !this.world.isRemote) {

                double seekingRange = 3.0d;

                if (homindTarget == null) {
                    List<LivingEntity> entities = Utilities.getEntitiesWithinRadius(seekingRange, this.posX, this.posY, this.posZ, this.world)
                            .stream()
                            .filter(x -> x.isAlive())
                            .collect(Collectors.toList());

                    for (Entity possibleTarget : entities) {
                        // Decides if current entity should be replaced.
                        if (homindTarget == null || this.getDistance(homindTarget) > this.getDistance(possibleTarget)) {
                            // Decides if new entity is a valid target.
                            if (Load.hasUnit(possibleTarget) && !possibleTarget.equals(this
                                    .getThrower())) {
                                homindTarget = possibleTarget;
                                this.setNoGravity(true);

                                this.setMotion(getMotion().x / 5, getMotion().y / 5, getMotion().z / 5);

                            }
                        }
                    }
                }

                if (homindTarget != null && Math.abs(this.getMotion().x) < 5 && Math.abs(this
                        .getMotion().y) < 5 && Math.abs(this.getMotion().z) < 5) {

                    this.addVelocity((homindTarget.posX - this.posX) / 30, (homindTarget.posY + homindTarget
                            .getHeight() / 2 - this.posY) / 30, (homindTarget.posZ - this.posZ) / 30);

                    // this.getMotion().y += (target.posY + target.height - this.posY) / 30;

                }
            }
        }
    }

    /**
     * Gets the amount of gravity to apply to the thrown entity with each tick.
     */
    protected float getGravityVelocity() {
        return 0.03F;
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