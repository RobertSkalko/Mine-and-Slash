package com.robertx22.database.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stat_types.elementals.transfers.fire_to.FireToWaterTransfer;
import com.robertx22.stats.Stat;

public class FireToWaterTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new FireToWaterTransfer();
    }

    @Override
    public String GUID() {
	return "FireToWaterTransferFlat";
    }

}
