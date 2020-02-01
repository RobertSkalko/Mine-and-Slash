package com.robertx22.mine_and_slash.database.bosses.impl;

import com.robertx22.mine_and_slash.database.bosses.base.BaseSummonerBoss;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ReplicatorBoss extends BaseSummonerBoss {

    private ReplicatorBoss() {
    }

    public static ReplicatorBoss getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void onTick(LivingEntity en) {

    }

    @Override
    public EntityType<?> getMinionType(LivingEntity en) {
        return en.getType();
    }

    @Override
    public ITextComponent getName(LivingEntity en) {
        return new StringTextComponent("Replicator ").appendSibling(en.getDisplayName());
    }

    @Override
    public IParticleData getParticle() {
        return ParticleTypes.WITCH;
    }

    @Override
    public String GUID() {
        return "replicator";
    }

    private static class SingletonHolder {
        private static final ReplicatorBoss INSTANCE = new ReplicatorBoss();
    }
}
