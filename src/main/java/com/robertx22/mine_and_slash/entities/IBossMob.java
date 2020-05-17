package com.robertx22.mine_and_slash.entities;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface IBossMob {

    static class BossData {
        public BossSpellCancelType cancel = BossSpellCancelType.CRITICAL;

        public BaseSpell spell;

        public int castTicks = 0;

        public int castTicksNeeded = 0;

        public int critsNeededToCancel = 3;

        public int ticksUntilNextSpellCast = 0;

        public int cantUseSpellsForTicks = 0;

        public boolean isCasting() {
            return spell != null && castTicks != castTicksNeeded;
        }

        public void cancel(LivingEntity boss) {

            cantUseSpellsForTicks = 120;
            spell = null;
            ParticleUtils.spawnParticles(ParticleTypes.SMOKE, boss, 15);
            SoundUtils.playSound(boss, SoundEvents.ENTITY_GENERIC_EXPLODE, 1, 1);

        }

        public void setupRandom(IBossMob boss) {

            cancel = RandomUtils.randomFromList(Arrays.asList(BossSpellCancelType.values()));

            spell = RandomUtils.randomFromList(boss.getBossSpells());

            castTicks = 0;

            critsNeededToCancel = 3;

            castTicksNeeded = (int) spell.getPreCalcConfig()
                .get(SC.CAST_TIME_TICKS)
                .get(1, spell) * 4;

            castTicksNeeded = MathHelper.clamp(castTicksNeeded, 100, 180);

        }

    }

    public BossData getBossData();

    public List<BaseSpell> getBossSpells();

    public enum BossSpellCancelType {
        CRITICAL, NOTHING
    }

    public static AttributeModifier SPEED = new AttributeModifier(
        UUID.fromString("38400500-8cf0-11bd-b23e-10b96e4ef00d"),
        Ref.MODID + SharedMonsterAttributes.MOVEMENT_SPEED.getName(), -10000,
        AttributeModifier.Operation.ADDITION
    );
    public static AttributeModifier FLY = new AttributeModifier(
        UUID.fromString("38400550-8cf0-11bd-b23e-10b96e4ef00d"),
        Ref.MODID + SharedMonsterAttributes.FLYING_SPEED.getName(), -11111,
        AttributeModifier.Operation.ADDITION
    );

    default void onCrit(LivingEntity boss) {
        if (getBossData().cancel == BossSpellCancelType.CRITICAL) {
            this.getBossData().critsNeededToCancel--;

            if (getBossData().critsNeededToCancel < 1) {
                getBossData().cancel(boss);
            }
        }
    }

    default void onBossTick(LivingEntity boss) {

        if (boss.world.isRemote) {
            return;
        }

        AbstractAttributeMap atris = boss.getAttributes();

        BossData data = ((IBossMob) boss).getBossData();

        data.cantUseSpellsForTicks--;

        if (data.cantUseSpellsForTicks > 0) {
            return;
        }

        data.ticksUntilNextSpellCast--;

        if (data.isCasting()) {

            data.castTicks++;

            if (data.castTicks == data.castTicksNeeded) {
                data.spell.cast(new SpellCastContext(boss, data.castTicks, data.spell));

                data.ticksUntilNextSpellCast = RandomUtils.RandomRange(100, 500);
            }

            if (data.cancel == BossSpellCancelType.CRITICAL) {

                ParticleEnum.sendToClients(boss, new ParticlePacketData(boss.getPositionVector(), ParticleEnum.AOE).radius(1)
                    .type(ParticleTypes.CRIT)
                    .amount(4)
                    .motion(new Vec3d(0, 0, 0)));

            }
            if (data.cancel == BossSpellCancelType.NOTHING) {
                ParticleUtils.spawnParticles(ParticleTypes.WITCH, boss, 5);
            }

            if (!atris
                .getAttributeInstance(SharedMonsterAttributes.MOVEMENT_SPEED)
                .hasModifier(SPEED)) {
                atris.getAttributeInstance(SharedMonsterAttributes.MOVEMENT_SPEED)
                    .applyModifier(SPEED);
            }

        } else {
            if (atris
                .getAttributeInstance(SharedMonsterAttributes.MOVEMENT_SPEED)
                .hasModifier(SPEED)) {
                atris.getAttributeInstance(SharedMonsterAttributes.MOVEMENT_SPEED)
                    .removeModifier(SPEED);
            }

            if (data.ticksUntilNextSpellCast < 1) {
                data.setupRandom((IBossMob) boss);
            }
        }

    }

}
