package com.robertx22.items.unique_items.necklaces;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellNatureDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.transfers.FireToNatureTransferFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stat_mods.percent.HealthPercent;
import com.robertx22.database.stat_mods.percent.much_less.CrippleDodgePercent;
import com.robertx22.database.stats.StatMod;
import com.robertx22.items.unique_items.bases.BaseUniqueNecklace;

import baubles.api.BaubleType;

public class NecklaceNature extends BaseUniqueNecklace {

    public NecklaceNature(BaubleType type) {
		super(type);
    }

    @Override
    public int Tier() {
	return 7;
    }

    @Override
    public String GUID() {
	return "necklacenature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new SpellNatureDamageFlat(), new HealthFlat(), new HealthRegenFlat(),
		new FireToNatureTransferFlat(), new FireResistFlat(), new HealthPercent(), new CrippleDodgePercent());
    }

}
