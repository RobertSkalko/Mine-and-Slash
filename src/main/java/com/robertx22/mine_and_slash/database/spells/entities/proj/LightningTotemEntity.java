package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityBaseProjectile;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergies;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.AfterDamageContext;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.shaman.StaticEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class LightningTotemEntity extends EntityBaseProjectile {

    public LightningTotemEntity(EntityType<? extends Entity> type, World world) {
        super(type, world);
    }

    public LightningTotemEntity(World worldIn) {
        super(EntityRegister.LIGHTNING_TOTEM, worldIn);

    }

    @Override
    public int durationInSeconds() {
        return 15;
    }

    public LightningTotemEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.LIGHTNING_TOTEM, world);

    }

    @Override
    public double radius() {
        return 1F;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.TOTEM_OF_UNDYING);
    }

    @Override
    public void tick() {

        int tickRate = 20;

        if (this.ticksExisted % tickRate == 0) {
            if (!world.isRemote) {

                List<LivingEntity> entities = EntityFinder.start(getCaster(), LivingEntity.class, getPosition())
                        .radius(radius())
                        .build();

                entities.forEach(x -> {

                    SpellDamageEffect dmg = dealSpellDamageTo(x, new Options().activatesEffect(false));

                    dmg.Activate();

                    if (Synergies.LIGHTNING_TOTEM_STATIC.has((PlayerEntity) getCaster())) {
                        Synergies.LIGHTNING_TOTEM_STATIC.tryActivate(new AfterDamageContext(getCaster(), x, dmg));
                    }

                    PotionEffectUtils.apply(StaticEffect.INSTANCE, getCaster(), x);

                    SoundUtils.playSound(this, SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, 1, 1);

                });
            } else {

                SoundUtils.playSound(this, SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, 1, 1);
                // TODO why isnt this being cast?

            }
        }

        if (this.inGround && world.isRemote) {

            for (int i = 0; i < 80; i++) {
                Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), (float) radius());
                ParticleUtils.spawn(ParticleRegister.THUNDER, world, p);

            }

        }

        super.tick();

    }

}
