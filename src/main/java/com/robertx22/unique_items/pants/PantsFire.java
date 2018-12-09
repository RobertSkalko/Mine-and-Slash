package com.robertx22.unique_items.pants;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.transfers.NatureToFireTransferFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.database.stat_mods.traits.conditionals.low_dodge.LowDodgeAddCritHitFlat;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniquePants;

public class PantsFire extends BaseUniquePants {

    public PantsFire() {

    }

    @Override
    public int Tier() {
	return 15;
    }

    @Override
    public String GUID() {
	return "pantsfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new LowDodgeAddCritHitFlat(), new HealthFlat(), new FireResistFlat(), new ArmorFlat(),
		new NatureToFireTransferFlat(), new CrippleLifeOnHitPercent());
    }

}
