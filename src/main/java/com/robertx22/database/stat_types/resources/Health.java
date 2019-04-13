package com.robertx22.database.stat_types.resources;

import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.entity.EntityLivingBase;

public class Health extends Stat {
    public static String GUID = "Health";

    public Health() {
    }

    @Override
    public String unlocString() {
	return "health";
    }

    @Override
    public String Guid() {
	return GUID;
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

	return (int) (mult * unit.healthData().Value);

    }

}
