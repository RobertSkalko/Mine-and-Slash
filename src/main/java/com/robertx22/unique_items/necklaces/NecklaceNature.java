package com.robertx22.unique_items.necklaces;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellNatureDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.transfers.FireToNatureTransferFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stat_mods.percent.HealthPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueNecklace;

public class NecklaceNature extends BaseUniqueNecklace {

    public NecklaceNature() {

    }

    @Override
    public int Tier() {
	return 7;
    }

    @Override
    public String name() {
	return "Amulet of the Oak";
    }

    @Override
    public String GUID() {
	return "necklacenature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new SpellNatureDamageFlat(), new HealthFlat(), new HealthRegenFlat(),
		new FireToNatureTransferFlat(), new HealthPercent());
    }

    @Override
    public String description() {
	return "I seek strength only in nature.";
    }

}
