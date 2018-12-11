package com.robertx22.database.stat_types.elementals.transfers.nature_to;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackNatureDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.database.stat_types.elementals.transfers.BaseTransferMod;
import com.robertx22.stats.TransferMethod;

public class NatureToWaterTransfer extends BaseTransferMod {

    @Override
    public String Guid() {
	return "Nature To Water Transfer";
    }

    @Override
    public List<TransferMethod> Transfer() {
	return Arrays.asList(new TransferMethod(new SpellNatureDamage(), new SpellWaterDamage()),
		new TransferMethod(new AttackNatureDamage(), new AttackWaterDamage()));

    }

    @Override
    public String unlocString() {
	return "nature_water_transfer";
    }

}
