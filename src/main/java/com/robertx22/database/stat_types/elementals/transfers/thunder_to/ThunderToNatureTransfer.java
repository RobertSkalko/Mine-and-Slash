package com.robertx22.database.stat_types.elementals.transfers.thunder_to;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackNatureDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.database.stat_types.elementals.transfers.BaseTransferMod;
import com.robertx22.stats.TransferMethod;

public class ThunderToNatureTransfer extends BaseTransferMod {

    @Override
    public String Guid() {
	return "Thunder To Nature Transfer";
    }

    @Override
    public List<TransferMethod> Transfer() {
	return Arrays.asList(new TransferMethod(new SpellThunderDamage(), new SpellNatureDamage()),
		new TransferMethod(new AttackThunderDamage(), new AttackNatureDamage()));

    }

    @Override
    public String unlocString() {
	return "thunder_nature_transfer";
    }
}
