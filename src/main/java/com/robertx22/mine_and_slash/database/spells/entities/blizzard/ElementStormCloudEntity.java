package com.robertx22.mine_and_slash.database.spells.entities.blizzard;

import com.robertx22.mine_and_slash.database.spells.entities.bases.InvisibleEntity;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.particles.EleParticleData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class ElementStormCloudEntity extends InvisibleEntity {

    public ElementStormCloudEntity(World world) {
        super(EntityRegister.ELEMENT_STORM_CLOUD, world);
    }

    public ElementStormCloudEntity(EntityType type, World world) {
        super(type, world);
    }

    public ElementStormCloudEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.ELEMENT_STORM_CLOUD, world);
    }

    @Override
    public void tick() {
        try {

            float radius = 3;

            if (this.ticksExisted % 8 == 1) {
                this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0f, 1.0f);

                if (!this.world.isRemote) {

                    List<LivingEntity> targets = Utilities.getEntitiesWithinRadius(radius, 3, this, LivingEntity.class);

                    for (LivingEntity target : targets) {

                        if (this.isValidTarget(target)) {

                            if (this.getCaster() != null) {

                                this.getServerSpellData().effect.Activate(this.getServerSpellData().data, target);

                            }
                        }
                    }
                }
            }

            if (world.isRemote && ticksExisted % 2 == 0) {

                for (int i = 1; i < 12; i++) {
                    double speed = (rand.nextBoolean() ? 1 : -1) * 0.1 + 0.05 * rand.nextDouble();

                    float yRandom = (float) RandomUtils.RandomRange(1, 100) / 40F;

                    Vec3d p = GeometryUtils.getRandomHorizontalPosInRadiusCircle(posX, posY + 4 + yRandom, posZ, radius);

                    Minecraft.getInstance().world.addParticle(new EleParticleData(ParticleRegister.DRIP, getElement()), true, p.x, p.y, p.z, 0, 0, 0);

                }
            }

            super.tick();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}