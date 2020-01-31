package com.robertx22.mine_and_slash.database.bosses.impl;

import com.robertx22.mine_and_slash.database.bosses.base.Boss;
import com.robertx22.mine_and_slash.database.bosses.base.BossData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class NecromancerBoss extends Boss {

    @Override
    public void onTick(LivingEntity en) {

    }

    @Override
    public ITextComponent getName(LivingEntity en) {
        return new StringTextComponent("Necromancer ").appendSibling(en.getDisplayName());
    }

    @Override
    public void onHealthTreshholdTriggered(LivingEntity en, BossData.HealthTreshhold treshhold) {

        spawnMinions(en);

    }

    @Override
    public String GUID() {
        return "necromancer";
    }

    public void spawnMinions(LivingEntity en) {

        BlockPos p = en.getPosition();

        for (int i = 0; i < 4; i++) {

            MobEntity spawned;

            if (en.world.rand.nextBoolean()) {
                spawned = EntityType.SKELETON.create(en.world);
            } else {
                spawned = EntityType.ZOMBIE.create(en.world);
            }

            spawnMinion(p, spawned, en.world);

        }
    }

    @Override
    public void applyStats(EntityCap.UnitData data) {

        super.applyStats(data);

    }

}
