package com.robertx22.database.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stat_types.elementals.transfers.nature_to.NatureToWaterTransfer;
import com.robertx22.stats.Stat;

public class NatureToWaterTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new NatureToWaterTransfer();
    }

    @Override
    public String GUID() {
	return "NatureToWaterTransferFlat";
    }

}
