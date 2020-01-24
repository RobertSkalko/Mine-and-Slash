package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class FireballEntity extends BaseElementalBoltEntity {

    public FireballEntity(EntityType<? extends FireballEntity> type, World world) {
        super(type, world);
    }

    public FireballEntity(World worldIn) {

        super(EntityRegister.FIREBOLT, worldIn);

    }

    public FireballEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.FIREBOLT, world);

    }

    @Override
    public Elements element() {
        return Elements.Fire;
    }

    @Override
    public void tick() {
        super.tick();

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 3; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.15F);
                    ParticleUtils.spawn(ParticleTypes.FLAME, world, p);
                }
            }
        }

    }

}