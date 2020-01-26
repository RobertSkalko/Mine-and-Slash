package com.robertx22.mine_and_slash.uncommon.utilityclasses;

public class StatUtils {

    public static float roundNumber(float n) {

        if (n < 10) {

        } else if (n < 100) {
            n = ((int) n / 2) * 2;
        } else if (n < 1000) {
            n = ((int) n / 50) * 50;
        } else if (n < 10000) {
            n = ((int) n / 500) * 500;
        } else if (n < 100000) {
            n = ((int) n / 1000) * 1000;
        } else {
            n = ((int) n / 5000) * 5000;
        }
        return n;

    }

}
