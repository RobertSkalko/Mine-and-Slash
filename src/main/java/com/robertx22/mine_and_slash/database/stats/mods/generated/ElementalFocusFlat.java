package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalFocus;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

public class ElementalFocusFlat extends ElementalStatMod {

    public ElementalFocusFlat(Elements element) {
        super(element);
    }

    @Override
    public MapWrapper<Elements, ElementalFocus> getBaseStatMap() {
        return ElementalFocus.MAP;
    }

    @Override
    public float Min() {
        return 10;
    }

    @Override
    public float Max() {
        return 25;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalFocusFlat(element);
    }

}

