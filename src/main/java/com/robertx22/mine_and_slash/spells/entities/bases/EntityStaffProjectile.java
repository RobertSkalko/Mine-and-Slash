package com.robertx22.mine_and_slash.spells.entities.bases;

import com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics.StaffWeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemStaff;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.spells.bases.BaseSpellEffect;
import com.robertx22.mine_and_slash.spells.bases.DamageData;
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

public class EntityStaffProjectile extends EntityBaseProjectile {

    ItemStack staff;

    public EntityStaffProjectile(EntityType<? extends EntityStaffProjectile> type,
                                 World world) {
        super(type, world);
    }

    public EntityStaffProjectile(World worldIn) {
        super(EntityRegister.STAFFPROJECTILE, worldIn);

    }

    public EntityStaffProjectile(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.STAFFPROJECTILE, world);
    }

    public void SetReady(ItemStack staff) {
        this.staff = staff;
    }

    @Override
    public double radius() {
        return 0.5D;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

        LivingEntity entity = getEntityHit(result, 0.5D);

        if (!world.isRemote && entity != null && staff == null) {
            System.out.println("TEST");
        }

        if (entity != null && staff != null) {

            if (!world.isRemote) {
                try {

                    ItemStaff staffclass = (ItemStaff) staff.getItem();

                    UnitData sourcedata = Load.Unit(this.thrower);
                    UnitData targetdata = Load.Unit(entity);

                    staffclass.mechanic()
                            .Attack(this.getThrower(), entity, sourcedata, targetdata);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        } else {
            if (world.isRemote == false) {

                try {
                    Load.Unit(this.thrower)
                            .restoreEnergy(new StaffWeaponMechanic().GetEnergyCost() / 2);
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
            for (int i = 0; i < 5; i++) {

                this.world.addParticle(ParticleTypes.CRIT, true, this.posX + rand.nextFloat() * 0.2 - 0.1, this.posY + this
                        .getHeight() / 2 + rand.nextFloat() * 0.2 - 0.1, this.posZ + rand.nextFloat() * 0.2 - 0.1, 0, 0, 0);

            }
        }

        if (this.ticksExisted > 20) {
            this.remove();
        }
    }

    public void SpawnAndShoot(BaseSpellEffect effect, DamageData data,
                              LivingEntity caster) {

        this.ignoreEntity = caster;
        this.thrower = caster;

        SetReady(caster.getHeldItemMainhand());
        this.setPos(caster);
        shoot(caster, caster.rotationPitch, caster.rotationYaw, 0.0F, 1.5F, 1.0F);

        WorldUtils.spawnEntity(world, this);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.ENDER_PEARL);
    }
}