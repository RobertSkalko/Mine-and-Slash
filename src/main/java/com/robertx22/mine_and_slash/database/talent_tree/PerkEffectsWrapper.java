package com.robertx22.mine_and_slash.database.talent_tree;

import java.util.HashMap;

public class PerkEffectsWrapper {

    private HashMap<PerkType, PerkEffect> map;

    public PerkEffectsWrapper(HashMap<PerkType, PerkEffect> map) {
        this.map = map;
    }

    public PerkEffect small() {
        return map.get(PerkType.SMALL);
    }

    public PerkEffect big() {
        return map.get(PerkType.BIG);
    }

    public PerkEffect major() {
        return map.get(PerkType.MAJOR);
    }

}
