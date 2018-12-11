package com.robertx22.database.stat_types.elementals.transfers.fire_to;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackFireDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackNatureDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.database.stat_types.elementals.transfers.BaseTransferMod;
import com.robertx22.stats.TransferMethod;

public class FireToNatureTransfer extends BaseTransferMod {

    @Override
    public String Guid() {
	return "Fire To Nature Transfer";
    }

    @Override
    public List<TransferMethod> Transfer() {
	return Arrays.asList(new TransferMethod(new SpellFireDamage(), new SpellNatureDamage()),
		new TransferMethod(new AttackFireDamage(), new AttackNatureDamage()));

    }

    @Override
    public String unlocString() {
	return "fire_nature_transfer";
    }
}
