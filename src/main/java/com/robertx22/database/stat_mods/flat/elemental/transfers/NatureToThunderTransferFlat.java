package com.robertx22.database.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stat_types.elementals.transfers.nature_to.NatureToThunderTransfer;
import com.robertx22.stats.Stat;

public class NatureToThunderTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new NatureToThunderTransfer();

    }

    @Override
    public String GUID() {
	return "NatureToThunderTransferFlat";
    }

}
