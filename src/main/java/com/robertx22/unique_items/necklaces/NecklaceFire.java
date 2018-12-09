package com.robertx22.unique_items.necklaces;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.bonus.FireSpellToAttackFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.transfers.WaterToFireTransferFlat;
import com.robertx22.database.stat_mods.flat.less.LessHealthRegenFlat;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueNecklace;

public class NecklaceFire extends BaseUniqueNecklace {

    public NecklaceFire() {

    }

    @Override
    public int Tier() {
	return 10;
    }

    @Override
    public String GUID() {
	return "necklacefire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new SpellFireDamageFlat(), new FireSpellToAttackFlat(), new WaterToFireTransferFlat(),
		new WaterResistFlat(), new LessHealthRegenFlat());

    }

}
