package com.robertx22.mine_and_slash.database.bosses.impl;

import com.robertx22.mine_and_slash.database.bosses.base.BaseSummonerBoss;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class NecromancerBoss extends BaseSummonerBoss {

    private NecromancerBoss() {
    }

    public static NecromancerBoss getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public EntityType<?> getMinionType(LivingEntity en) {
        return en.world.rand.nextBoolean() ? EntityType.ZOMBIE : EntityType.SKELETON;
    }

    @Override
    public ITextComponent getName(LivingEntity en) {
        return new StringTextComponent("Necromancer ").appendSibling(en.getDisplayName());
    }

    @Override
    public IParticleData getParticle() {
        return ParticleTypes.WITCH;
    }

    @Override
    public String GUID() {
        return "necromancer";
    }

    private static class SingletonHolder {
        private static final NecromancerBoss INSTANCE = new NecromancerBoss();
    }
}
