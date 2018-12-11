package com.robertx22.database.stat_types.elementals.transfers.nature_to;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackNatureDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.database.stat_types.elementals.transfers.BaseTransferMod;
import com.robertx22.stats.TransferMethod;

public class NatureToThunderTransfer extends BaseTransferMod {

    @Override
    public String Guid() {
	return "Nature To Thunder Transfer";
    }

    @Override
    public List<TransferMethod> Transfer() {
	return Arrays.asList(new TransferMethod(new SpellNatureDamage(), new SpellThunderDamage()),
		new TransferMethod(new AttackNatureDamage(), new AttackThunderDamage()));

    }

    @Override
    public String unlocString() {
	return "nature_thunder_transfer";
    }
}
