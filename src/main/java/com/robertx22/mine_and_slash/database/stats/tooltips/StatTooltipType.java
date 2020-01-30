package com.robertx22.mine_and_slash.database.stats.tooltips;

public enum StatTooltipType {

    NORMAL(new NormalStatTooltip()),
    PRIMARY_STATS(new PrimaryStatTooltip());

    StatTooltipType(IStatTooltipType impl) {
        this.impl = impl;
    }

    public IStatTooltipType impl;

}