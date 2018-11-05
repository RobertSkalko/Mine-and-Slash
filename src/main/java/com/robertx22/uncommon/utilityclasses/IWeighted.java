package com.robertx22.uncommon.utilityclasses;

public interface IWeighted {

	public abstract int Weight();

	public static int CommonWeight = 30000;
	public static int NormalWeight = 10000;
	public static int UncommonWeight = 7500;
	public static int RareWeight = 2500;
	public static int VeryRareWeight = 500;

}
