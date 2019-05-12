package com.robertx22.stats;

import java.util.Arrays;
import java.util.List;

import com.robertx22.effectdatas.interfaces.WeaponTypes;
import com.robertx22.stats.StatEffects.offense.WeaponDamageEffect;
import com.robertx22.uncommon.enumclasses.Elements;

public abstract class WeaponDamageStat extends Stat implements IStatEffects {

    public abstract WeaponTypes weaponType();

    public WeaponDamageStat() {
	this.hasMinimumAmount = false;
    }

    @Override
    public boolean IsPercent() {
	return true;
    }

    @Override
    public boolean ScalesToLevel() {
	return false;
    }

    @Override
    public Elements Element() {
	return null;
    }

    @Override
    public List<IStatEffect> GetEffects() {
	return Arrays.asList(new WeaponDamageEffect());
    }

}
