package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityBaseProjectile;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class RangerArrowEntity extends EntityBaseProjectile {

    public RangerArrowEntity(EntityType<? extends Entity> type, World world) {
        super(type, world);
    }

    public RangerArrowEntity(World worldIn) {
        super(EntityRegister.RANGER_ARROW, worldIn);
    }

    public RangerArrowEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.RANGER_ARROW, world);
    }

    @Override
    public double radius() {
        return 1;
    }

    @Override
    public void onTick() {

    }

    public void onHit(LivingEntity entity) {

        SpellDamageEffect dmg = this.getSetupSpellDamage(entity);

        dmg.Activate();

    }

    @Override
    protected void onImpact(RayTraceResult result) {
        LivingEntity entityHit = getEntityHit(result, 0.3D);

        if (entityHit != null) {
            if (world.isRemote) {
                SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_HURT, 1F, 0.9F);
            }

            onHit(entityHit);

        } else {
            if (world.isRemote) {
                SoundUtils.playSound(this, SoundEvents.BLOCK_STONE_HIT, 0.7F, 0.9F);
            }
        }

        this.remove();
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.AIR);
    }

    @Override
    public int durationInSeconds() {
        return 20;
    }
}
