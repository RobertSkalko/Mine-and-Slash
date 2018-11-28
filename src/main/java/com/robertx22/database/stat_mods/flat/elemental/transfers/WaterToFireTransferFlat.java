package com.robertx22.database.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stat_types.elementals.transfers.water_to.WaterToFireTransfer;
import com.robertx22.stats.Stat;

public class WaterToFireTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new WaterToFireTransfer();
    }

    @Override
    public String GUID() {
	return "WaterToFireTransferFlat";
    }

}
