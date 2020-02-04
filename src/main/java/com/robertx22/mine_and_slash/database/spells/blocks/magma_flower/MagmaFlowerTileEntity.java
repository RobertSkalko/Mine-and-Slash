package com.robertx22.mine_and_slash.database.spells.blocks.magma_flower;

import com.robertx22.mine_and_slash.database.spells.blocks.base.BaseSpellTileEntity;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergies;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterAndSpellEntityContext;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterContext;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class MagmaFlowerTileEntity extends BaseSpellTileEntity {

    public float RADIUS = 1.5F;
    public float TICK_RATE = 30;

    public MagmaFlowerTileEntity() {
        super(BlockRegister.MAGMA_FLOWER_TILE);
    }

    @Override
    public void initSpellEntity() {

        LivingEntity caster = data.getCaster(world);

        if (Synergies.MAGMA_FLOWER_ENHANCED.has(caster)) {
            Synergies.MAGMA_FLOWER_ENHANCED.tryActivate(
                    new CasterAndSpellEntityContext<MagmaFlowerTileEntity>(caster, this));
        }

    }

    @Override
    public void onTick() {

        if (this.data.ticksExisted > durationInTicks() == false) {

            if (data.ticksExisted % TICK_RATE == 0) {

                LivingEntity caster = data.getCaster(world);
                EntityCap.UnitData data = Load.Unit(caster);

                ParticleEnum.sendToClients(
                        pos, world, new ParticlePacketData(pos, ParticleEnum.AOE).radius(RADIUS)
                                .motion(new Vec3d(0, 0, 0))
                                .type(ParticleTypes.FLAME)
                                .amount((int) (15 * RADIUS)));

                List<LivingEntity> entities = EntityFinder.start(
                        caster, LivingEntity.class, new Vec3d(getPos()).add(0.5F, 0, 0.5F)).radius(RADIUS).build();

                entities.forEach(x -> {
                    SpellDamageEffect dmg = getSetupSpellDamage(x);
                    dmg.Activate();
                    SoundUtils.playSound(x, SoundEvents.BLOCK_FIRE_EXTINGUISH, 1, 1);

                    if (Synergies.MAGMA_FLOWER_HEAL.has(caster)) {
                        Synergies.MAGMA_FLOWER_HEAL.tryActivate(new CasterContext(caster));
                    }

                });

            }
        }

    }

    public static int DURATION_SEC = 15;

    @Override
    public int durationInSeconds() {
        return DURATION_SEC;
    }

}
