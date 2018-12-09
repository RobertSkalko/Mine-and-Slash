package com.robertx22.unique_items.pants;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.DodgeFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.transfers.FireToThunderTransferFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniquePants;

public class PantsThunder extends BaseUniquePants {

    public PantsThunder() {

    }

    @Override
    public int Tier() {
	return 6;
    }

    @Override
    public String GUID() {
	return "pantsthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new HealthFlat(), new SpellFireDamageFlat(), new DodgeFlat(), new FireResistFlat(),
		new FireToThunderTransferFlat(), new CrippleLifeOnHitPercent());
    }

}
