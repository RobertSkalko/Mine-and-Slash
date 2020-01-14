package com.robertx22.mine_and_slash.database.spells.items.aoe_bomb_proj;

import com.robertx22.mine_and_slash.database.spells.items.BaseSpellItem;

public abstract class BaseBombitem extends BaseSpellItem {

    public BaseBombitem() {
        super();

    }

    @Override
    public String locNameForLangFile() {
        return color + this.Spell().getElement().dmgName + " Bomb";
    }
}