package com.robertx22.crafting.bases;

import com.robertx22.saveclasses.gearitem.GearItemData;

public interface IRerollable {

	public abstract boolean IfRerollFully();

	public abstract boolean IfRerollNumbers();

	public abstract void RerollFully(GearItemData gear);

	public abstract void RerollNumbers(GearItemData gear);

	public abstract void SetRerollNumbers(boolean bool);

}
