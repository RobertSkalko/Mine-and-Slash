package com.robertx22.mine_and_slash.database.spells;

import com.robertx22.mine_and_slash.database.spells.entities.bases.ISpellEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;

public class SpellUtils {

    public static void summonLightningStrike(Entity entity) {

        LightningBoltEntity lightningboltentity = new LightningBoltEntity(entity.world, (double) entity.getX() + 0.5D,
                                                                          (double) entity.getY(),
                                                                          (double) entity.getZ() + 0.5D, true
        );  //boolean true means it's only an effect!'

        ((ServerWorld) entity.world).addLightningBolt(lightningboltentity);

    }

    public static void setupProjectileForCasting(AbstractArrowEntity projectile, LivingEntity caster, float speed) {
        Vec3d pos = caster.getPositionVector();

        ((Entity) projectile).setPosition(pos.x, caster.getEyeY() - 0.1F, pos.z);

        projectile.shoot(caster, caster.rotationPitch, caster.rotationYaw, 0.0F, speed, 1F);

    }

    public static <T extends Entity> T getEntity(T spellEntity, LivingEntity caster) {
        return getSpellEntity(spellEntity, null, caster);
    }

    public static <T extends Entity> T getSpellEntity(T spellEntity,

                                                      BaseSpell spell,

                                                      LivingEntity caster

    ) {

        ISpellEntity se = (ISpellEntity) spellEntity;

        int lifeInTicks = se.getDefaultLifeInTicks();

        EntitySpellData syncData = new EntitySpellData(spell, caster, lifeInTicks);

        se.setSpellData(syncData);

        se.initSpellEntity();

        return spellEntity;

    }

}
