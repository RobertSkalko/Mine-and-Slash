package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityBaseProjectile;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergies;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.BeforeDamageContext;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterTargetContext;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Utilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class WhirlpoolEntity extends EntityBaseProjectile {

    public WhirlpoolEntity(EntityType<? extends Entity> type, World world) {
        super(type, world);
    }

    public WhirlpoolEntity(World worldIn) {
        super(EntityRegister.WHIRPOOL, worldIn);

    }

    @Override
    public int durationInSeconds() {
        return 10;
    }

    public WhirlpoolEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.WHIRPOOL, world);

    }

    @Override
    public double radius() {
        return 3.5F;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.HEART_OF_THE_SEA);
    }

    @Override
    public void tick() {

        int tickRate = 20;

        if (this.ticksExisted % tickRate == 0) {
            if (!world.isRemote) {

                List<LivingEntity> entities = Utilities.getEntitiesWithinRadius(radius(), this, LivingEntity.class);
                entities.removeIf(x -> x == getCaster());

                entities.forEach(x -> {

                    DamageEffect dmg = dealSpellDamageTo(x, new Options().knockbacks(false).activatesEffect(false));

                    if (Synergies.WHIRLPOOL_FROST_DMG.has(getCaster())) {
                        Synergies.WHIRLPOOL_FROST_DMG.tryActivate(new BeforeDamageContext(getCaster(), x, dmg));
                    }

                    dmg.Activate();

                    x.addPotionEffect(new EffectInstance(Effects.SLOWNESS, tickRate, 10));

                    if (Synergies.WHIRLPOOL_SHIVER.has(getCaster())) {
                        Synergies.WHIRLPOOL_SHIVER.tryActivate(new CasterTargetContext(getCaster(), x));
                    }

                    SoundUtils.playSound(this, SoundEvents.ENTITY_DROWNED_HURT_WATER, 1, 1);

                });
            } else {
                Vec3d p = this.getPositionVector();

                world.playSound(p.x, p.y, p.z, SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.BLOCKS, 1F, 1F,
                                false
                );

                /*
                this.player.playSound(SoundEvents.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 1.0F, 1.0F);
            } else {
                this.player.playSound(SoundEvents.BLOCK_BUBBLE_COLUMN_UPWARDS_INSIDE, 1.0F, 1.0F);


                 */

            }
        }

        if (this.inGround && world.isRemote) {

            float yUp = 0.05F;

            for (float rad = 1; rad < radius(); rad++) {

                yUp += 0.1F;

                for (int i = 0; i < 40; i++) {
                    Vec3d p = GeometryUtils.getRandomHorizontalPosInRadiusCircle(
                            getPositionVector().add(0, yUp, 0), rad);
                    ParticleUtils.spawn(ParticleRegister.BUBBLE, world, p);

                }
            }

            Vec3d p = this.getPositionVector();

            world.playSound(p.x, p.y, p.z, SoundEvents.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, SoundCategory.BLOCKS,
                            0.2F, 0.9F, false
            );

        }

        super.tick();

    }

}
