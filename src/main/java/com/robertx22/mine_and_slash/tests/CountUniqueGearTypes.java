package com.robertx22.mine_and_slash.tests;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.registry.SlashRegistry;

public class CountUniqueGearTypes {

    public static void count() {

        System.out.println("[UNIQUES PER SLOT");

        for (GearItemSlot slot : SlashRegistry.GearTypes().getList()) {

            int amount = SlashRegistry.UniqueGears()
                    .getWrapped()
                    .ofSpecificGearType(slot.GUID()).list.size();

            System.out.println(slot.GUID() + " has " + amount + " uniques");

        }
        System.out.println("[UNIQUES PER SLOT END");

    }

}
