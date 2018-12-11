package com.robertx22.database.stat_types.elementals.transfers.water_to;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.elementals.attack_damage.AttackFireDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.database.stat_types.elementals.transfers.BaseTransferMod;
import com.robertx22.stats.TransferMethod;

public class WaterToFireTransfer extends BaseTransferMod {

    @Override
    public String Guid() {
	return "Water To Fire Transfer";
    }

    @Override
    public List<TransferMethod> Transfer() {
	return Arrays.asList(new TransferMethod(new SpellWaterDamage(), new SpellFireDamage()),
		new TransferMethod(new AttackWaterDamage(), new AttackFireDamage()));

    }

    @Override
    public String unlocString() {
	return "water_fire_transfer";
    }
}
