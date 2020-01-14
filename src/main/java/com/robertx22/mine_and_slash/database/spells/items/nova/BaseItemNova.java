package com.robertx22.mine_and_slash.database.spells.items.nova;

import com.robertx22.mine_and_slash.database.spells.items.BaseSpellItem;

public abstract class BaseItemNova extends BaseSpellItem {

    public BaseItemNova() {
        super();

    }

    @Override
    public String locNameForLangFile() {
        return color + this.Spell().getElement().dmgName + " Nova";
    }
}