package com.robertx22.saveclasses.gearitem.gear_bases;

import com.robertx22.uncommon.utilityclasses.IWeighted;

public abstract class Rarity implements IWeighted {

	public abstract String Name();

	public abstract int Rank();

	public abstract String Color();

	public abstract int Weight();

}
