package com.robertx22.database.stat_types.elementals.transfers.water_to;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.database.stat_types.elementals.transfers.BaseTransferMod;
import com.robertx22.stats.TransferMethod;

public class WaterToThunderTransfer extends BaseTransferMod {

    @Override
    public String Guid() {
	return "Water To Thunder Transfer";
    }

    @Override
    public List<TransferMethod> Transfer() {
	return Arrays.asList(new TransferMethod(new SpellWaterDamage(), new SpellThunderDamage()),
		new TransferMethod(new AttackWaterDamage(), new AttackThunderDamage()));

    }

    @Override
    public String unlocString() {
	return "water_thunder_transfer";
    }
}
