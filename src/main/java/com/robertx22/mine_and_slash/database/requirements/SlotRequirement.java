package com.robertx22.mine_and_slash.database.requirements;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SlotRequirement extends BaseRequirement {

    public List<String> slotsGUIDS = new ArrayList<>();

    public SlotRequirement(GearItemSlot slot) {
        this.slotsGUIDS.add(slot.GUID());
    }

    public SlotRequirement(List<GearItemSlot> slots) {

        for (GearItemSlot slot : slots) {
            this.slotsGUIDS.add(slot.GUID());
        }

    }

    @Override
    public boolean meetsRequierment(GearRequestedFor requested) {

        if (slotsGUIDS.contains(requested.forSlot.GUID()) == false) {
            return false;
        }

        return true;

    }

    public static SlotRequirement weaponsOnly() {
        return new SlotRequirement(weaponSlots());
    }

    public static SlotRequirement armorsOnly() {
        return new SlotRequirement(armorSlots());
    }

    public static SlotRequirement armorsOnlyNoOffHand() {
        return new SlotRequirement(armorSlotsNoOffHand());
    }

    public static SlotRequirement jewerlyOnly() {
        return new SlotRequirement(jewerlySlots());
    }

    static List<GearItemSlot> weaponSlots() {

        return SlashRegistry.GearTypes()
                .getAll()
                .values()
                .stream()
                .filter(x -> x.slotType().equals(GearItemSlot.GearSlotType.Weapon))
                .collect(Collectors.toList());

    }

    static List<GearItemSlot> armorSlots() {

        return SlashRegistry.GearTypes()
                .getAll()
                .values()
                .stream()
                .filter(x -> x.slotType()
                        .equals(GearItemSlot.GearSlotType.Armor) || x.slotType()
                        .equals(GearItemSlot.GearSlotType.OffHand)) // tenp i dont have enough affixes for off hands
                .collect(Collectors.toList());

    }

    static List<GearItemSlot> armorSlotsNoOffHand() {

        return SlashRegistry.GearTypes()
                .getAll()
                .values()
                .stream()
                .filter(x -> x.slotType().equals(GearItemSlot.GearSlotType.Armor))
                .collect(Collectors.toList());

    }

    static List<GearItemSlot> jewerlySlots() {

        return SlashRegistry.GearTypes()
                .getAll()
                .values()
                .stream()
                .filter(x -> x.slotType().equals(GearItemSlot.GearSlotType.Jewerly))
                .collect(Collectors.toList());

    }

}
