package com.robertx22.mine_and_slash.database.stats.tooltips;

public enum StatTooltipType {

    NORMAL(new NormalStatTooltipUtils()),
    PRIMARY_STATS(new PrimaryStatTooltipUtils());

    StatTooltipType(IStatTooltipType impl) {
        this.impl = impl;
    }

    public IStatTooltipType impl;

}