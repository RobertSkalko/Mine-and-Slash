package com.robertx22.mine_and_slash.database.bosses.base;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;

public abstract class Boss {

    public abstract void onTick(LivingEntity en);

    public abstract ITextComponent getName(LivingEntity en);

    public void onSpawn(LivingEntity en) {

    }

    public void onDeath(LivingEntity en) {

    }

}

