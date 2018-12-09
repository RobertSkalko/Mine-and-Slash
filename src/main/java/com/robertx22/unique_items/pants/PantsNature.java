package com.robertx22.unique_items.pants;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.transfers.FireToNatureTransferFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.database.stat_mods.traits.conditionals.low_dodge.LowDodgeAddArmorFlat;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniquePants;

public class PantsNature extends BaseUniquePants {

    public PantsNature() {

    }

    @Override
    public int Tier() {
	return 14;
    }

    @Override
    public String GUID() {
	return "pantsnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new LowDodgeAddArmorFlat(), new HealthFlat(), new NatureResistFlat(),
		new FireToNatureTransferFlat(), new CrippleLifeOnHitPercent());
    }

}
