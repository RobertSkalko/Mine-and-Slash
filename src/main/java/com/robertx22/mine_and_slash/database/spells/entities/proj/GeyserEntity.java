package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityBaseProjectile;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.GeyserSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergies;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterAndSpellEntityContext;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellHealEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class GeyserEntity extends EntityBaseProjectile {

    public GeyserEntity(EntityType<? extends Entity> type, World world) {
        super(type, world);
    }

    public GeyserEntity(World worldIn) {
        super(EntityRegister.GEYSER, worldIn);

    }

    @Override
    public int durationInSeconds() {
        return 8;
    }

    public GeyserEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.GEYSER, world);

    }

    @Override
    public double radius() {
        return 1.5F;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.AIR);
    }

    @Override
    public void tick() {

        if (!this.inGround) {
            this.addVelocity(0, -10, 0);
        } else {
            if (world.isRemote) {

                for (float i = 0; i < 5; i += 0.1F) {
                    for (int a = 0; a < 5 * radius(); a++) {
                        Vec3d p = GeometryUtils.getRandomHorizontalPosInRadiusCircle(
                                getPositionVector().add(0, i, 0), (float) radius());
                        ParticleUtils.spawn(ParticleRegister.BUBBLE, world, p);
                    }
                }

                Vec3d p = this.getPositionVector();
                world.playSound(p.x, p.y, p.z, SoundEvents.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, SoundCategory.BLOCKS,
                                0.2F, 0.9F, false
                );
            }

            int tickRate = 20;

            if (this.ticksExisted % tickRate == 0) {
                if (!world.isRemote) {
                    LivingEntity caster = getCaster();

                    if (caster == null) {
                        return;
                    }

                    if (Synergies.GEYSER_ATTACK.has(caster)) {
                        Synergies.GEYSER_ATTACK.tryActivate(
                                new CasterAndSpellEntityContext<GeyserEntity>(caster, this));
                    }

                    List<LivingEntity> allies = EntityFinder.start(caster, LivingEntity.class, getPositionVector())
                            .radius(GeyserSpell.RADIUS)
                            .searchFor(EntityFinder.SearchFor.ALLIES)
                            .build();

                    allies.forEach(x -> {

                        SpellHealEffect heal = healTarget(x);

                        SoundUtils.playSound(this, SoundEvents.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 1, 1);

                        heal.Activate();

                    });

                } else {
                    Vec3d p = this.getPositionVector();

                    world.playSound(p.x, p.y, p.z, SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.BLOCKS, 1F,
                                    1F,

                                    false
                    );

                }
            }
        }

        super.tick();

    }

}
