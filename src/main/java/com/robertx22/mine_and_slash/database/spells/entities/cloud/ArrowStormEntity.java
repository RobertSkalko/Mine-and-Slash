package com.robertx22.mine_and_slash.database.spells.entities.cloud;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseCloudEntity;
import com.robertx22.mine_and_slash.database.spells.entities.proj.RangerArrowEntity;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class ArrowStormEntity extends BaseCloudEntity {

    public ArrowStormEntity(World world) {
        super(EntityRegister.ARROW_STORM, world);
    }

    public ArrowStormEntity(EntityType type, World world) {
        super(type, world);
    }

    public ArrowStormEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.ARROW_STORM, world);
    }

    @Override
    public void initSpellEntity() {

    }

    @Override
    public int durationInSeconds() {
        return 6;
    }

    @Override
    public void onHit(LivingEntity entity) {

    }

    @Override
    public void summonFallParticle(Vec3d p) {

    }

    public static int TICKS_PER_ARROW_SUMMON = 15;
    public static int ARROWS_PER_SUMMON = 8;

    @Override
    public void onTick() {

        super.onTick();

        if (this.ticksExisted % TICKS_PER_ARROW_SUMMON == 0) {
            LivingEntity caster = getCaster();

            if (!this.world.isRemote) {
                for (int i = 0; i < ARROWS_PER_SUMMON; i++) {
                    float yRandom = (float) RandomUtils.RandomRange(1, 100) / 40F;
                    float height = 4;
                    Vec3d p = GeometryUtils.getRandomHorizontalPosInRadiusCircle(
                        posX, posY + height + yRandom, posZ, radius());

                    RangerArrowEntity en = SpellUtils.getSpellEntity(new RangerArrowEntity(world), getSpellData().getSpell(), caster);
                    SpellUtils.setupProjectileForCasting(en, caster, 1.5F);
                    en.setMotion(new Vec3d(0, -1, 0));
                    en.setLocationAndAngles(p.x, p.y, p.z, 0, 0);
                    caster.world.addEntity(en);

                    SoundUtils.playSound(en, SoundEvents.ENTITY_ARROW_SHOOT, 1, 1);
                }
            }
        }

    }

    @Override
    public int ticksToHitMobs() {
        return 10000;
    }

    public float radius() {
        return 2.5F;
    }

}