package com.robertx22.uncommon.utilityclasses;

import com.robertx22.db_lists.Rarities;

public interface IWeighted {

	public abstract int Weight();

	public static int CommonWeight = Rarities.Items.get(0).Weight();
	public static int UncommonWeight = Rarities.Items.get(1).Weight();
	public static int RareWeight = Rarities.Items.get(2).Weight();
	public static int EpicWeight = Rarities.Items.get(3).Weight();
	public static int LegendaryWeight = Rarities.Items.get(4).Weight();
	public static int MythicWeight = Rarities.Items.get(5).Weight();

}
