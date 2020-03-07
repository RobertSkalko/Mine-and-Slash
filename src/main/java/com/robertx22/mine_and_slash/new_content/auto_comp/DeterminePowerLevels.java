package com.robertx22.mine_and_slash.new_content.auto_comp;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeterminePowerLevels {

    public static HashMap<GearItemSlot, List<PowerLevel>> MAP = new HashMap<>();
    public static HashMap<GearItemSlot, PowerLevel> STRONGEST = new HashMap<>();

    public static void setupHashMaps() {

        ForgeRegistries.ITEMS.forEach(item -> {
            try {
                if (item.getRegistryName() != null && !Ref.MODID.equals(item.getRegistryName()
                    .getNamespace())) {
                    SlashRegistry.GearTypes()
                        .getList()
                        .forEach(slot -> {
                            if (slot.isGearOfThisType(item)) {

                                if (!MAP.containsKey(slot)) {
                                    MAP.put(slot, new ArrayList<>());
                                }

                                PowerLevel current = new PowerLevel(item, slot);

                                MAP.get(slot)
                                    .add(current);

                                PowerLevel strongest = STRONGEST.getOrDefault(slot, new PowerLevel(item, slot));

                                if (current.isStrongerThan(strongest)) {
                                    strongest = current;
                                }

                                STRONGEST.put(slot, strongest);

                            }
                        });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        System.out.println(MAP.toString());

    }
}
