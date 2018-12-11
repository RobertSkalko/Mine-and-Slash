package com.robertx22.database.stat_types.elementals.transfers.nature_to;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackFireDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackNatureDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.database.stat_types.elementals.transfers.BaseTransferMod;
import com.robertx22.stats.TransferMethod;

public class NatureToFireTransfer extends BaseTransferMod {

    @Override
    public String Guid() {
	return "Nature To Fire Transfer";
    }

    @Override
    public List<TransferMethod> Transfer() {
	return Arrays.asList(new TransferMethod(new SpellNatureDamage(), new SpellFireDamage()),
		new TransferMethod(new AttackNatureDamage(), new AttackFireDamage()));

    }

    @Override
    public String unlocString() {
	return "nature_fire_transfer";
    }
}
