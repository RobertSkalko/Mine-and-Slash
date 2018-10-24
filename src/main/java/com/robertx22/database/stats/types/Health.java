package com.robertx22.database.stats.types;

import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.entity.EntityLivingBase;

public class Health extends Stat {
	public Health() {
		this.StatMinimum = 20;
	}

	@Override
	public String Name() {
		return "Health";
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
	}

	@Override
	public Elements Element() {
		return null;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

	public int CurrentValue(EntityLivingBase entity, Unit unit) {

		float mult = entity.getHealth() / entity.getMaxHealth();

		return (int) (mult * unit.health().Value);

	}

}
