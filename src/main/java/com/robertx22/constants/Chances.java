package com.robertx22.constants;

public class Chances {

    public static class Chance {

        public double chance;
        public int result;

        public Chance(double chance, int result) {
            this.chance = chance;
            this.result = result;
        }

    }

    public static Chance[] MOB_SPAWN_RARITY = {
            new Chance(1F, 4),
            new Chance(3F, 3),
            new Chance(10F, 2),
            new Chance(30F, 1),
    };

    public static Chance[] GEAR_RARITY = {
            new Chance(0.2F, 4),
            new Chance(2F, 3),
            new Chance(10F, 2),
            new Chance(25F, 1),
    };

    public static Chance[] GEAR_AMOUNT = {
            new Chance(0.5F, 5),
            new Chance(2F, 4),
            new Chance(3F, 3),
            new Chance(5F, 2),
            new Chance(10F, 1),

    };

}

