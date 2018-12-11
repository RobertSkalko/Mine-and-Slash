package com.robertx22.database.stat_types.elementals.transfers.thunder_to;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackFireDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.database.stat_types.elementals.transfers.BaseTransferMod;
import com.robertx22.stats.TransferMethod;

public class ThunderToFireTransfer extends BaseTransferMod {

    @Override
    public String Guid() {
	return "Thunder To Fire Transfer";
    }

    @Override
    public List<TransferMethod> Transfer() {
	return Arrays.asList(new TransferMethod(new SpellThunderDamage(), new SpellFireDamage()),
		new TransferMethod(new AttackThunderDamage(), new AttackFireDamage()));

    }

    @Override
    public String unlocString() {
	return "thunder_fire_transfer";
    }
}
