package com.robertx22.database.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stat_types.elementals.transfers.water_to.WaterToThunderTransfer;
import com.robertx22.stats.Stat;

public class WaterToThunderTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new WaterToThunderTransfer();

    }

    @Override
    public String GUID() {
	return "WaterToThunderTransferFlat";
    }

}
