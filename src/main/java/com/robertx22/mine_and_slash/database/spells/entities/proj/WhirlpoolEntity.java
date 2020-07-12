package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityBaseProjectile;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.saveclasses.spells.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
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
    public void onTick() {

        EntitySpellData sdata = getSpellData();
        int TICK_RATE = sdata.configs.get(SC.TICK_RATE)
            .intValue();
        int RADIUS = sdata.configs.get(SC.RADIUS)
            .intValue();

        if (this.inGround || this.ticksExisted > 30) {
            if (this.ticksExisted % TICK_RATE == 0) {
                if (!world.isRemote) {
                    LivingEntity caster = getCaster();

                    if (caster == null) {
                        return;
                    }

                    List<LivingEntity> entities = EntityFinder.start(caster, LivingEntity.class, getPositionVector())
                        .radius(RADIUS)
                        .build();

                    entities.forEach(x -> {

                        DamageEffect dmg = dealSpellDamageTo(x, new Options().knockbacks(false)
                            .activatesEffect(false));

                        dmg.Activate();

                        x.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 5, 5));

                        SoundUtils.playSound(this, SoundEvents.ENTITY_DROWNED_HURT_WATER, 1, 1);

                    });
                } else {
                    Vec3d p = this.getPositionVector();

                    world.playSound(p.x, p.y, p.z, SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.BLOCKS, 1F,
                        1F,

                        false
                    );

                }
            }

            if (this.inGround && world.isRemote) {

                float yUp = 0.05F;

                for (float rad = 1; rad < RADIUS; rad++) {

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
        }

    }

}
