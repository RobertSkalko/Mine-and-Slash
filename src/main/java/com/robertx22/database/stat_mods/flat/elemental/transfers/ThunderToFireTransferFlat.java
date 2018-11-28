package com.robertx22.database.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stat_types.elementals.transfers.thunder_to.ThunderToFireTransfer;
import com.robertx22.stats.Stat;

public class ThunderToFireTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new ThunderToFireTransfer();

    }

    @Override
    public String GUID() {
	return "ThunderToFireTransferFlat";
    }

}
