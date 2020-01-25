package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergies;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.AfterDamageContext;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.BeforeDamageContext;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class FrostballEntity extends BaseElementalBoltEntity {

    public FrostballEntity(EntityType<? extends FrostballEntity> type, World world) {
        super(type, world);
    }

    public FrostballEntity(World worldIn) {

        super(EntityRegister.FROSTBOLT, worldIn);

    }

    public FrostballEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.FROSTBOLT, world);

    }

    @Override
    public Elements element() {
        return Elements.Water;
    }

    @Override
    public void onHit(LivingEntity entity) {

        SpellDamageEffect dmg = dealSpellDamageTo(entity, new Options().activatesEffect(false));

        if (Synergies.FROSTBALL_EXTRA_DMG.has((PlayerEntity) getCaster())) {
            Synergies.FROSTBALL_EXTRA_DMG.tryActivate(new BeforeDamageContext(getCaster(), entity, dmg));
        }

        dmg.Activate();

        if (Synergies.FROSTBALL_FROST_ESSENCE_GEN.has((PlayerEntity) getCaster())) {
            Synergies.FROSTBALL_FROST_ESSENCE_GEN.tryActivate(new AfterDamageContext(getCaster(), entity, dmg));
        }

    }

    @Override
    public void tick() {

        super.tick();

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 2; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.2F);
                    ParticleUtils.spawn(ParticleTypes.ITEM_SNOWBALL, world, p);
                }
            }

        }

    }

}