package com.robertx22.database.status.effects;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.bonus.WaterSpellToAttackFlat;
import com.robertx22.database.status.effects.bases.BaseMobEleDMG;
import com.robertx22.saveclasses.gearitem.StatModData;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class MobWaterDMGSE extends BaseMobEleDMG {

	@Override
	public Item ItemModel() {
		return Items.WATER_BUCKET;
	}

	@Override
	public String GUID() {
		return "MobWaterDMGSE";
	}

	@Override
	public List<StatModData> Stats() {
		return Arrays.asList(StatModData.NewStatusEffect(this.percent, new WaterSpellToAttackFlat()));
	}

}
