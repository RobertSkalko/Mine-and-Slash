package com.robertx22.database.stat_types.elementals.transfers.thunder_to;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.database.stat_types.elementals.transfers.BaseTransferMod;
import com.robertx22.stats.TransferMethod;

public class ThunderToWaterTransfer extends BaseTransferMod {

    @Override
    public String Guid() {
	return "Thunder To Water Transfer";
    }

    @Override
    public List<TransferMethod> Transfer() {
	return Arrays.asList(new TransferMethod(new SpellThunderDamage(), new SpellWaterDamage()),
		new TransferMethod(new AttackThunderDamage(), new AttackWaterDamage()));

    }

    @Override
    public String unlocString() {
	return "thunder_water_transfer";
    }
}
