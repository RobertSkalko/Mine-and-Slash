package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.Comparator;
import java.util.Optional;

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
    public void onTick() {

        if (!world.isRemote) {
            goToEnemy();
        }

    }

    Entity en;

    public void goToEnemy() {

        LivingEntity caster = getCaster();

        if (en == null) {

            Optional<LivingEntity> opt = EntityFinder.start(caster, LivingEntity.class, this.getPositionVector())
                    .radius(15)
                    .build()
                    .stream()
                    .min(Comparator.comparingDouble(caster::getDistanceSq));

            if (opt.isPresent()) {
                en = opt.get();
            }

        }

        if (en != null) {

            this.setNoGravity(true);

            float maxDistance = caster.getDistance(en);
            float currentDistance = this.getDistance(en);

            float multi = (currentDistance / maxDistance);

            float divide = 20 * multi;

            this.setVelocity((en.posX - this.posX) / divide, (en.posY + en.getHeight() / 2 - this.posY) / divide,
                             (en.posZ - this.posZ) / divide
            );

        } else {
            this.setNoGravity(false);

        }

    }

}