package com.robertx22.mine_and_slash.database.spells.entities.bases.weapon_proj;

import com.robertx22.mine_and_slash.database.spells.bases.BaseSpellEffect;
import com.robertx22.mine_and_slash.database.spells.bases.DamageData;
import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityBaseProjectile;
import com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics.WandWeaponMechanic;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class EntityWandProjectile extends EntityBaseProjectile {

    ItemStack weapon;

    public EntityWandProjectile(EntityType<? extends EntityWandProjectile> type,
                                World world) {
        super(type, world);
    }

    public EntityWandProjectile(World worldIn) {
        super(EntityRegister.WANDPROJECTILE, worldIn);

    }

    public EntityWandProjectile(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.WANDPROJECTILE, world);
    }

    public void SetReady(ItemStack staff) {
        this.weapon = staff;
    }

    @Override
    public double radius() {
        return 0.5D;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

        LivingEntity entity = getEntityHit(result, 0.5D);

        if (entity != null && weapon != null) {

            if (!world.isRemote) {
                try {

                    LivingEntity caster = this.getThrower();
                    if (caster != null) {
                        UnitData sourcedata = Load.Unit(caster);
                        UnitData targetdata = Load.Unit(entity);

                        WandWeaponMechanic.INSTANCE.powerAttack(null, caster, entity, sourcedata, targetdata, this.charge);
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.remove();
        }

    }

    @Override
    public void tick() {

        super.tick();

        if (world.isRemote) {
            if (this.ticksExisted > 2) {
                for (int i = 0; i < 10; i++) {
                    this.world.addParticle(ParticleTypes.ENCHANTED_HIT, true, this.posX + rand
                            .nextFloat() * 0.2 - 0.1, this.posY + this.getHeight() / 2 + rand
                            .nextFloat() * 0.2 - 0.1, this.posZ + rand.nextFloat() * 0.2 - 0.1, 0, 0, 0);
                }
            }
        }

        if (this.ticksExisted > 20) {
            this.remove();
        }
    }

    public void SpawnAndShoot(LivingEntity caster) {
        SpawnAndShoot(caster, 2F);
    }

    public void SpawnAndShoot(LivingEntity caster, float vel) {

        this.ignoreEntity = caster;
        this.thrower = caster;

        SetReady(caster.getHeldItemMainhand());
        this.setPos(caster);
        shoot(caster, caster.rotationPitch, caster.rotationYaw, 0.0F, vel, 1.0F);

        WorldUtils.spawnEntity(world, this);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.NETHER_WART);
    }

    @Override
    public void SpawnAndShoot(BaseSpellEffect effect, DamageData data,
                              LivingEntity caster) {

    }
}
