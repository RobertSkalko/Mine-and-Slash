package com.robertx22.crafting;

import com.robertx22.saveclasses.GearItemData;

public interface IRerollable {

	public abstract boolean IfRerollFully();

	public abstract boolean IfRerollNumbers();

	public abstract void RerollFully(GearItemData gear);

	public abstract void RerollNumbers(GearItemData gear);

}
