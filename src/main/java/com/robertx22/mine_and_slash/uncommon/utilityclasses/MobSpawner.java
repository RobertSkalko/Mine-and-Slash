package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.onevent.entity.OnMobSpawn;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MobSpawner {

    EntityType entityType;
    public Rarity rarity;
    World world;
    BlockPos pos;
    public boolean addPotion = false;

    public MobSpawner(EntityType entityType, World world, BlockPos pos) {
        this.entityType = entityType;
        this.world = world;
        this.pos = pos;
    }

    public LivingEntity spawn() {

        LivingEntity en = (LivingEntity) entityType.create(world);

        if (rarity == null) {
            rarity = Rarities.Mobs.random();
        }

        Vec3d vec = new Vec3d(pos);
        vec = vec.add(0.5F, 0, 0.5F);

        if (en instanceof MobEntity) {
            ((MobEntity) en).onInitialSpawn(world, world.getDifficultyForLocation(pos), SpawnReason.REINFORCEMENT, null, null);
        }

        en.setPosition(vec.getX(), vec.getY(), vec.getZ());

        OnMobSpawn.setupNewMobOnSpawn(en);

        world.addEntity(en);

        return en;

    }

}
