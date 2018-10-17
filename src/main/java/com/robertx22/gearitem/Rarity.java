package com.robertx22.gearitem;

import com.robertx22.classes.IWeighted;

public abstract class Rarity implements IWeighted {

	public abstract String Name();

	public abstract int Rank();

	public abstract String Color();

	public abstract int Weight();

}
