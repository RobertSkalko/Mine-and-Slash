package com.robertx22.mine_and_slash.database.items.spells.nova;

import com.robertx22.mine_and_slash.database.items.spells.BaseSpellItem;

public abstract class BaseItemNova extends BaseSpellItem {

    public BaseItemNova() {
        super();

    }

    @Override
    public String locNameForLangFile() {
        return color + this.Spell().Element().dmgName + " Nova";
    }
}