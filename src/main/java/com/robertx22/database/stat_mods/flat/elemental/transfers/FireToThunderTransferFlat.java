package com.robertx22.database.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stat_types.elementals.transfers.fire_to.FireToThunderTransfer;
import com.robertx22.stats.Stat;

public class FireToThunderTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new FireToThunderTransfer();
    }

    @Override
    public String GUID() {
	return "FireToThunderTransferFlat";
    }

}
