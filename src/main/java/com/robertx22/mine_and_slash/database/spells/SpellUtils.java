package com.robertx22.mine_and_slash.database.spells;

import com.robertx22.mine_and_slash.database.spells.entities.bases.ISpellEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.EntityCalcSpellConfigs;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellHealEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.network.play.server.SSpawnGlobalEntityPacket;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.function.Function;

public class SpellUtils {

    public static void summonLightningStrike(Entity entity) {

        LightningBoltEntity lightningboltentity = new LightningBoltEntity(entity.world,
            (double) entity.getPosX() + 0.5D,
            (double) entity.getPosY(),
            (double) entity.getPosZ() + 0.5D, true
        );  //boolean true means it's only an effect!'

        addLightningBolt(((ServerWorld) entity.world), lightningboltentity);
        //((ServerWorld) entity.world).addLightningBolt(lightningboltentity);

    }

    public static void addLightningBolt(ServerWorld world, LightningBoltEntity entityIn) {
        world.getServer()
            .getPlayerList()
            .sendToAllNearExcept((PlayerEntity) null, entityIn.getPosX(), entityIn.getPosY(), entityIn.getPosZ(), 50, world.getDimension()
                .getType(), new SSpawnGlobalEntityPacket(entityIn));
    }

    public static void setupProjectileForCasting(AbstractArrowEntity projectile, LivingEntity caster, float speed) {
        Vec3d pos = caster.getPositionVector();

        ((Entity) projectile).setPosition(pos.x, caster.getPosY() + caster.getEyeHeight() - 0.1F, pos.z);

        projectile.shoot(caster, caster.rotationPitch, caster.rotationYaw, 0.0F, speed, 1F);

    }

    public static void castTripleProjectileInCone(EntityCalcSpellConfigs config, float apart, BaseSpell spell, Function<World, AbstractArrowEntity> projectile, LivingEntity caster, float speed) {
        World world = caster.world;

        for (int i = 0; i < 3; i++) {

            float f = 0;

            if (i == 0) {
                f = apart;
            }
            if (i == 2) {
                f = -apart;
            }
            f *= 10;

            AbstractArrowEntity en = (AbstractArrowEntity) SpellUtils.getSpellEntity(config, projectile.apply(world), spell, caster);
            SpellUtils.setupProjectileForCasting(en, caster, speed, caster.rotationPitch,
                caster.rotationYaw + f
            );
            caster.world.addEntity(en);

        }

    }

    public static void setupProjectileForCasting(AbstractArrowEntity projectile, LivingEntity caster, float speed,
                                                 float pitch, float yaw) {
        Vec3d pos = caster.getPositionVector();

        ((Entity) projectile).setPosition(pos.x, caster.getPosY() + caster.getEyeHeight() - 0.1F, pos.z);

        projectile.shoot(caster, pitch, yaw, 0.0F, speed, 1F);

    }

    public static <T extends Entity> T getSpellEntity(EntityCalcSpellConfigs config, T spellEntity,

                                                      BaseSpell spell,

                                                      LivingEntity caster

    ) {

        ISpellEntity se = (ISpellEntity) spellEntity;

        int lifeInTicks = se.getDefaultLifeInTicks();

        EntitySpellData syncData = new EntitySpellData(spell, caster, config);

        se.setSpellData(syncData);

        se.initSpellEntity();

        return spellEntity;

    }

    /*
    public static <T extends TameableEntity> T spawnSummon(T spellEntity,

                                                           BaseSpell spell,

                                                           LivingEntity caster) {

        T en = SpellUtils.getSpellEntity(spellEntity, spell, caster);
        if (caster instanceof PlayerEntity) {
            en.setTamedBy((PlayerEntity) caster);
        }
        caster.world.addEntity(en);

        return en;

    }


     */
    public static void heal(BaseSpell spell, LivingEntity en, float amount) {
        SpellHealEffect heal = new SpellHealEffect(
            new ResourcesData.Context(Load.Unit(en), en, ResourcesData.Type.HEALTH,
                amount, ResourcesData.Use.RESTORE,
                spell
            ));
        heal.Activate();
    }

    public static void healCaster(SpellCastContext ctx) {
        SpellHealEffect heal = new SpellHealEffect(
            new ResourcesData.Context(ctx.data, ctx.caster, ResourcesData.Type.HEALTH,
                ctx.getConfigFor(ctx.ability)
                    .getCalc(ctx.spellsCap, ctx.ability)
                    .getCalculatedValue(ctx.data, ctx.spellsCap, ctx.ability), ResourcesData.Use.RESTORE,
                ctx.spell
            ));
        heal.Activate();
    }

}
