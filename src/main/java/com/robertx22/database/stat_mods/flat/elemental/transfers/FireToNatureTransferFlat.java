package com.robertx22.database.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stat_types.elementals.transfers.fire_to.FireToNatureTransfer;
import com.robertx22.stats.Stat;

public class FireToNatureTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new FireToNatureTransfer();
    }

    @Override
    public String GUID() {
	return "FireToNatureTransferFlat";
    }

}
