package com.robertx22.mine_and_slash.database.items.spells.aoe_bomb_proj;

import com.robertx22.mine_and_slash.database.items.spells.BaseSpellItem;

public abstract class BaseBombitem extends BaseSpellItem {

    public BaseBombitem() {
        super();

    }

    @Override
    public String locNameForLangFile() {
        return color + this.Spell().Element().dmgName + " Bomb";
    }
}