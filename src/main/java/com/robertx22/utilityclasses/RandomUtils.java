package com.robertx22.utilityclasses;

import java.util.Random;

import com.robertx22.constants.Chances;

public class RandomUtils {

    public static int rollArray(Chances.Chance[] chances) {

        int number = 0;

        // goes from least probable to most probable, only applies if its higher, that means if it rolled
        // mythic and rolls rare next, mythic will stay
        for (Chances.Chance chance : chances) {

            if (roll(chance.chance)) {
                if (chance.result > number) {
                    number = chance.result;
                }
            }

        }

        return number;
    }

    public static int rollArray(Chances.Chance[] chances, float settings, float bonusChance) {

        int number = 0;

        if (bonusChance < 1) {
            bonusChance = 1;
        }

        // goes from least probable to most probable, only applies if its higher, that means if it rolled
        // mythic and rolls rare next, mythic will stay
        for (Chances.Chance chance : chances) {

            if (roll(chance.chance * settings * bonusChance)) {
                if (chance.result > number) {
                    number = chance.result;
                }
            }

        }

        return number;
    }

    public static int rollArray(Chances.Chance[] chances, float settings) {

        int number = 0;

        // goes from least probable to most probable, only applies if its higher, that means if it rolled
        // mythic and rolls rare next, mythic will stay
        for (Chances.Chance chance : chances) {

            if (roll(chance.chance * settings)) {
                if (chance.result > number) {
                    number = chance.result;
                }
            }

        }

        return number;
    }

    public static boolean roll(int chance) {

        Random ran = new Random();

        double ranNum = ran.nextDouble() * 100;

        if (chance > ranNum) {
            return true;
        }

        return false;
    }

    public static boolean roll(float chance) {

        Random ran = new Random();

        double ranNum = ran.nextDouble() * 100;

        if (chance > ranNum) {
            return true;
        }

        return false;
    }

    public static boolean roll(double chance) {

        Random ran = new Random();

        double ranNum = ran.nextDouble() * 100;

        if (chance > ranNum) {
            return true;
        }

        return false;
    }

    public static int rollWhile(int chance, int min, int max) {

        // 0 = Magic, 1 = rare, ... epic, legendary, mythical

        boolean rolling = true;

        while (rolling) {

            if (min >= max) {
                rolling = false;
                break;
            }

            if (roll(chance)) {
                min++;
            }
            else {
                rolling = false;
                break;
            }

        }

        return min;
    }

}
