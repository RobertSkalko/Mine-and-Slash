package com.robertx22.saveclasses.gearitem.gear_bases;

import com.robertx22.uncommon.utilityclasses.IWeighted;

public interface Rarity extends IWeighted {

    String Name();

    int Rank();

    String Color();

    int Weight();

}
