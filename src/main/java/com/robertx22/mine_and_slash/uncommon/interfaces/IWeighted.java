package com.robertx22.mine_and_slash.uncommon.interfaces;

public interface IWeighted {

    public abstract int Weight();

    public static int TestWeight = 9999999; // THIS CAN'T BE IN USE WHILE DEPLOYING TO PUBLIC

    public static int Never = 0;
    /*
    public static int CommonWeight = Rarities.Items.get(0).Weight();
    public static int UncommonWeight = Rarities.Items.get(1).Weight();
    public static int RareWeight = Rarities.Items.get(2).Weight();
    public static int EpicWeight = Rarities.Items.get(3).Weight();
    public static int LegendaryWeight = Rarities.Items.get(4).Weight();
    public static int MythicWeight = Rarities.Items.get(5).Weight();

    public static int VeryCommonWeight = CommonWeight * 3;

     */

    public static int UniqueSetSuperCommonWeight = 100555;

}
