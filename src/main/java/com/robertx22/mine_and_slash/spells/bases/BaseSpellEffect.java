package com.robertx22.mine_and_slash.spells.bases;

import net.minecraft.entity.LivingEntity;

public abstract class BaseSpellEffect {

    public abstract String Name();

    public BaseSpellEffect() {

    }

    public abstract void Activate(DamageData dmgdata, LivingEntity target);

}
