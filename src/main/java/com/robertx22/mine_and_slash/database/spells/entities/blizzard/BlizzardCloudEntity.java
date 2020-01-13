package com.robertx22.mine_and_slash.database.spells.entities.blizzard;

import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class BlizzardCloudEntity extends CloudEntity {
    public BlizzardCloudEntity(World world) {
        super(EntityRegister.FROST_BLIZZARD, world);
    }

    public BlizzardCloudEntity(EntityType type, World world) {
        super(type, world);
    }

    public BlizzardCloudEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.FROST_BLIZZARD, world);
    }

    @Override
    public void tick() {

        if (this.ticksExisted % 15 == 1) {
            this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0f, 1.0f);
        }

        super.tick();

        double radius = 3;

        if (!this.world.isRemote) {

            List<LivingEntity> targets = Utilities.getEntitiesWithinRadius(radius, this.posX, this.posY, this.posZ, this.world);

            for (LivingEntity target : targets) {

                if (this.isValidTarget(target)) {

                    if (this.getCaster() != null) {
                        try {
                            this.effect.Activate(this.data, target);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }

        if (world.isRemote && ticksExisted % 2 == 0) {

            // todo how to apply motion to particles
            for (int i = 1; i < 12; i++) {
                double speed = (rand.nextBoolean() ? 1 : -1) * 0.1 + 0.05 * rand.nextDouble();

                Elements.RGB color = Elements.Water.getRGBColor();

                double x = rand.nextFloat() * radius;
                double z = rand.nextFloat() * radius;

                Minecraft.getInstance().world.addParticle(ParticleRegister.ele_particle, true, posX + x, posY + 4, posZ + z, color
                        .getR(), color.getG(), color.getB());

            }
        }

    }

}