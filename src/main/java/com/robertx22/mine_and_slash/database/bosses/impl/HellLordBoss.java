package com.robertx22.mine_and_slash.database.bosses.impl;

import com.robertx22.mine_and_slash.database.bosses.base.BaseSummonerBoss;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class HellLordBoss extends BaseSummonerBoss {

    private HellLordBoss() {
    }

    public static HellLordBoss getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void onTick(LivingEntity en) {

    }

    @Override
    public EntityType<?> getMinionType(LivingEntity en) {
        return en.world.rand.nextBoolean() ? EntityType.MAGMA_CUBE : EntityType.BLAZE;
    }

    @Override
    public ITextComponent getName(LivingEntity en) {
        return new StringTextComponent("Hell Lord ").appendSibling(en.getDisplayName());
    }

    @Override
    public IParticleData getParticle() {
        return ParticleTypes.WITCH;
    }

    @Override
    public String GUID() {
        return "hell_lord";
    }

    private static class SingletonHolder {
        private static final HellLordBoss INSTANCE = new HellLordBoss();
    }
}
