package com.robertx22.database.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stat_types.elementals.transfers.thunder_to.ThunderToNatureTransfer;
import com.robertx22.stats.Stat;

public class ThunderToNatureTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new ThunderToNatureTransfer();

    }

    @Override
    public String GUID() {
	return "ThunderToNatureTransferFlat";
    }

}
