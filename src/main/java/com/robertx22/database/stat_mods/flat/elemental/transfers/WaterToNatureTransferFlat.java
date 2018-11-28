package com.robertx22.database.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stat_types.elementals.transfers.water_to.WaterToNatureTransfer;
import com.robertx22.stats.Stat;

public class WaterToNatureTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new WaterToNatureTransfer();

    }

    @Override
    public String GUID() {
	return "WaterToNatureTransferFlat";
    }

}
