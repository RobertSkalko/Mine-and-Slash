package com.robertx22.mine_and_slash.database.bosses.base;

import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.mine_and_slash.uncommon.capability.BossCap;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public abstract class Boss implements ISlashRegistryEntry<Boss>, IApplyableStats {

    public abstract void onTick(LivingEntity en);

    public abstract ITextComponent getName(LivingEntity en);

    public void onSpawn(LivingEntity en) {

    }

    public void onDeath(LivingEntity en) {

    }

    public abstract void onHealthTreshholdTriggered(LivingEntity en, BossData.HealthTreshhold treshhold);

    public void spawnMinion(BlockPos p, MobEntity minion, World world) {
        minion.setPosition(p.getX(), p.getY(), p.getZ());
        minion.getCapability(BossCap.Data).ifPresent(x -> x.setIsBoss(false));

        minion.onInitialSpawn(
                world, world.getDifficultyForLocation(p), SpawnReason.REINFORCEMENT, null, (CompoundNBT) null);

        world.addEntity(minion);

    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.BOSS;
    }

    @Override
    public void applyStats(EntityCap.UnitData data) {
        data.getUnit().getCreateStat(Health.INSTANCE).Multi += 500;
    }
}

