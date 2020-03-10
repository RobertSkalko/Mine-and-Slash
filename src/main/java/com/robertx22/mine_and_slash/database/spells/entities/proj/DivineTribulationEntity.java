package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityBaseProjectile;
import com.robertx22.mine_and_slash.database.spells.entities.bases.ISpellEntity;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class DivineTribulationEntity extends EntityBaseProjectile {

    public DivineTribulationEntity(EntityType<? extends Entity> type, World world) {
        super(type, world);
    }

    public DivineTribulationEntity(World worldIn) {
        super(EntityRegister.DIVINE_TRIBULATION, worldIn);

    }

    public DivineTribulationEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.DIVINE_TRIBULATION, world);

    }

    @Override
    public int durationInSeconds() {
        return 10;
    }

    @Override
    public void initSpellEntity() {
        this.setNoGravity(true);
        this.setDeathTime(100);

    }

    @Override
    public double radius() {
        return 3.5F;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.NETHER_STAR);
    }

    @Override
    public void onTick() {

        if (this.inGround) {
            this.remove();
            return;
        }

        int tickRate = 20;

        int ticksToRaise = 30;

        if (this.ticksExisted < ticksToRaise) {
            this.setMotion(getMotion().add(0, 0.003F, 0));
            // go up the sky
        } else {
            this.setMotion(0, 0, 0);
        }

        if (world.isRemote) {
            if (ticksExisted % 2 == 0) {
                ParticleEnum.sendToClients(
                    this, new ParticlePacketData(getPositionVector(), ParticleEnum.AOE).amount(5)
                        .type(ParticleTypes.CLOUD)
                        .motion(new Vec3d(0, 0, 0)));
            }
        }

        if (this.ticksExisted > ticksToRaise) {
            if (this.ticksExisted % tickRate == 0) {
                if (!world.isRemote) {
                    LivingEntity caster = getCaster();

                    if (caster == null) {
                        return;
                    }

                    List<LivingEntity> entities = EntityFinder.start(caster, LivingEntity.class, getPositionVector())
                        .radius(radius())
                        .height(4)
                        .build();

                    entities.forEach(x -> {

                        DamageEffect dmg = dealSpellDamageTo(x, new ISpellEntity.Options().knockbacks(false)
                            .activatesEffect(false));

                        dmg.Activate();

                        SpellUtils.summonLightningStrike(x);

                    });
                }
            }

        }

    }

}
