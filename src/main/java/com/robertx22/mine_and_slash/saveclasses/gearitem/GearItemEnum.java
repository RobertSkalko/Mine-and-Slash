package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

import java.util.Arrays;

public enum GearItemEnum implements IWeighted {

    NORMAL() {
        @Override
        public int Weight() {
            return 1000;
        }

        @Override
        public boolean canGetSet(GearItemData data) {
            return true;
        }

        @Override
        public boolean canGetSecondaryStats(GearItemData data) {
            return true;
        }

        @Override
        public boolean canGetAffixes(GearItemData data) {
            return true;
        }

        @Override
        public boolean canRerollNumbers(GearItemData data) {
            return true;
        }

        @Override
        public boolean canGetInfusions(GearItemData data) {
            return true;
        }

        @Override
        public boolean canGetChaosStats(GearItemData data) {
            return true;
        }

        @Override
        public boolean canGetPrimaryStats(GearItemData data) {
            return true;
        }

    },

    RUNED() {
        @Override
        public int Weight() {
            return 250;
        }

        @Override
        public boolean canGetSet(GearItemData data) {
            return true;
        }

        @Override
        public boolean canGetAffixes(GearItemData data) {
            return false;
        }

        @Override
        public boolean canGetInfusions(GearItemData data) {
            return false;
        }

        @Override
        public boolean canRerollNumbers(GearItemData data) {
            return false;
        }

        @Override
        public boolean canGetSecondaryStats(GearItemData data) {
            return false;
        }

        @Override
        public boolean canGetChaosStats(GearItemData data) {
            return false;
        }

        @Override
        public boolean canGetPrimaryStats(GearItemData data) {
            return true;
        }
    },

    UNIQUE() {
        @Override
        public int Weight() {
            return 50;
        }

        @Override
        public boolean canGetSet(GearItemData data) {
            try {
                return data.uniqueStats.getUniqueItem().canGetSet();
            } catch (Exception e) {
            }

            return false;
        }

        @Override
        public boolean canGetPrimaryStats(GearItemData data) {
            return false;
        }

        @Override
        public boolean canRerollNumbers(GearItemData data) {
            return true;
        }

        @Override
        public boolean canGetChaosStats(GearItemData data) {
            return true;
        }

        @Override
        public boolean canGetAffixes(GearItemData data) {
            return true;
        }

        @Override
        public boolean canGetInfusions(GearItemData data) {
            return true;
        }

        @Override
        public boolean canGetSecondaryStats(GearItemData data) {
            return false;
        }
    };

    public abstract boolean canGetSet(GearItemData data);

    public abstract boolean canGetPrimaryStats(GearItemData data);

    public abstract boolean canRerollNumbers(GearItemData data);

    public abstract boolean canGetChaosStats(GearItemData data);

    public abstract boolean canGetAffixes(GearItemData data);

    public abstract boolean canGetInfusions(GearItemData data);

    public abstract boolean canGetSecondaryStats(GearItemData data);

    public static GearItemEnum random() {
        return RandomUtils.weightedRandom(Arrays.asList(GearItemEnum.values()));
    }

}
