package com.robertx22.database.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stat_types.elementals.transfers.nature_to.NatureToFireTransfer;
import com.robertx22.stats.Stat;

public class NatureToFireTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new NatureToFireTransfer();
    }

    @Override
    public String GUID() {
	return "NatureToFireTransferFlat";
    }

}
