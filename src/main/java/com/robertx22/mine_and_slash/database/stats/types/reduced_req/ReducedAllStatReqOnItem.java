package com.robertx22.mine_and_slash.database.stats.types.reduced_req;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatScaling;
import com.robertx22.mine_and_slash.database.stats.name_regex.StatNameRegex;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class ReducedAllStatReqOnItem extends Stat {

    public ReducedAllStatReqOnItem() {

    }

    @Override
    public StatNameRegex getStatNameRegex() {
        return StatNameRegex.REDUCED_REQ_BY_PECRENT;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    public float getModifiedRequirement(float req, ExactStatData data) {
        float multi = 1F - data.getAverageValue() / 100;
        return req * multi;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public Elements getElement() {
        return Elements.Physical;
    }

    @Override
    public String locDescForLangFile() {
        return "Alters requirements of this item.";
    }

    @Override
    public String locNameForLangFile() {
        return "Attribute Requirements";
    }

    @Override
    public String GUID() {
        return "alter_req_percent";
    }
}
