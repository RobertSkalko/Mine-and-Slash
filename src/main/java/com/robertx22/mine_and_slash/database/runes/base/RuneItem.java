package com.robertx22.mine_and_slash.database.runes.base;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import net.minecraft.item.Item;

import java.util.Locale;

public class RuneItem extends Item implements IAutoLocName {

    public int rarity;
    public String rune;

    public RuneItem(BaseRune rune) {

        super(new Properties().maxStackSize(1)
            .defaultMaxDamage(0));
        this.rarity = rune.rarity;
        this.rune = rune.GUID();

    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Runes;
    }

    @Override
    public String locNameLangFileGUID() {
        return getRegistryName()
            .toString();
    }

    @Override
    public String locNameForLangFile() {
        Rarity rar = Rarities.Runes.get(rarity);
        return rar.textFormatColor() + rune
            .toUpperCase(Locale.ROOT) + " - " + rar.locNameForLangFile() + " Rune";

    }

    @Override
    public String GUID() {
        return rune;
    }
}
