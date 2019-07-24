package com.robertx22.mine_and_slash.items.spells.projectile;

import com.robertx22.mine_and_slash.items.spells.BaseSpellItem;

public abstract class BaseBoltItem extends BaseSpellItem {

    public BaseBoltItem() {
        super();

    }

    @Override
    public String locNameForLangFile() {
        return color + this.Spell().Element().dmgName + " Bolt";
    }
}