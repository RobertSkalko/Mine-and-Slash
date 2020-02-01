package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class MagicMissileEntity extends BaseElementalBoltEntity {

    public MagicMissileEntity(EntityType<? extends MagicMissileEntity> type, World world) {
        super(type, world);
    }

    public MagicMissileEntity(World worldIn) {

        super(EntityRegister.MAGIC_MISSILE, worldIn);

    }

    public MagicMissileEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.MAGIC_MISSILE, world);

    }

    @Override
    public Elements element() {
        return Elements.Elemental;
    }

    @Override
    public void onHit(LivingEntity entity) {
        dealSpellDamageTo(entity);

    }

    @Override
    public void tick() {
        super.tick();

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 3; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.15F);
                    //ParticleUtils.spawn(ParticleTypes.CRIT, world, p);
                }
            }
        }

    }

}