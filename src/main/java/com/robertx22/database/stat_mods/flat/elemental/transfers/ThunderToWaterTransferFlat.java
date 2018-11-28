package com.robertx22.database.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stat_types.elementals.transfers.thunder_to.ThunderToWaterTransfer;
import com.robertx22.stats.Stat;

public class ThunderToWaterTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new ThunderToWaterTransfer();

    }

    @Override
    public String GUID() {
	return "ThunderToWaterTransferFlat";
    }

}
