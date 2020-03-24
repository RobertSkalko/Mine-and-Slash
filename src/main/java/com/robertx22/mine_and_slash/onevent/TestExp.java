package com.robertx22.mine_and_slash.onevent;

public class TestExp {
    public static int equateXp(double lvl) {
        return (int) Math.floor(lvl + 340 * Math.pow(2, lvl / 9));
    }

    public static int levelToExp(int level, boolean old) {
        if (old) {
            return level * level * level * 10;
        }

        double xp = 0;

        for (int i = 1; i < level; i++)
            xp += equateXp(i);

        return (int) Math.floor(xp / 4);
    }

    public static void test() {

        int totalOld = 0;
        for (int i = 0; i < 100; i++) {
            totalOld += levelToExp(i, false);
        }
        int totalnew = 0;
        for (int i = 0; i < 60; i++) {
            totalnew += levelToExp(i, true);
        }

        System.out.println(" old exp for lvl 100: " + totalOld);
        System.out.println(" new exp for lvl 60: " + totalnew);
    }
}
