package com.robertx22.mine_and_slash.database.spells.blocks.thorn_bush;

import com.robertx22.mine_and_slash.database.spells.blocks.base.BaseSpellTileEntity;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergies;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterTargetContext;
import com.robertx22.mine_and_slash.mmorpg.registers.common.TileEntityRegister;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class ThornBushTileEntity extends BaseSpellTileEntity {

    public ThornBushTileEntity() {
        super(TileEntityRegister.THORN_BUSH.get());
    }

    @Override
    public void onTick() {

        float RADIUS = 1.5F;

        if (this.data.ticksExisted > durationInTicks() == false) {

            if (data.ticksExisted % 20 == 0) {

                LivingEntity caster = data.getCaster(world);

                if (caster == null) {
                    return;
                }

                EntityCap.UnitData data = Load.Unit(caster);

                ParticleEnum.sendToClients(
                    pos, world, new ParticlePacketData(pos, ParticleEnum.THORNS).radius(RADIUS)
                        .motion(new Vec3d(0, 0, 0))
                        .amount(25));

                List<LivingEntity> entities = EntityFinder.start(
                    caster, LivingEntity.class, new Vec3d(pos).add(0.5F, 0, 0.5F))
                    .radius(RADIUS)
                    .height(2)
                    .build();

                entities.forEach(target -> {
                    SpellDamageEffect dmg = getSetupSpellDamage(target);
                    dmg.Activate();

                    SoundUtils.playSound(target, SoundEvents.BLOCK_WET_GRASS_BREAK, 1, 1);

                    if (Synergies.THORN_BUSH_MAJOR_TORNS.has(caster)) {
                        Synergies.THORN_BUSH_MAJOR_TORNS.tryActivate(new CasterTargetContext(caster, target));
                    }
                });

            }
        }

    }

    public static int DURATION_SEC = 10;

    @Override
    public int durationInSeconds() {
        return DURATION_SEC;
    }

}

