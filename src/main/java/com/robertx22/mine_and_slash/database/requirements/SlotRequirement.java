package com.robertx22.mine_and_slash.database.requirements;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.data_generation.JsonUtils;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseBoots;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseChest;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseHelmet;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BasePants;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Bracelet;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Charm;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Necklace;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Ring;
import com.robertx22.mine_and_slash.database.gearitemslots.offhand.Shield;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Staff;
import com.robertx22.mine_and_slash.database.requirements.bases.BaseRequirement;
import com.robertx22.mine_and_slash.database.requirements.bases.GearRequestedFor;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SlotRequirement extends BaseRequirement<SlotRequirement> {

    public List<GearItemSlot> slots = new ArrayList<>();

    public SlotRequirement() {

    }

    private SlotRequirement(GearItemSlot slot) {
        this.slots.add(slot);
    }

    private SlotRequirement(List<GearItemSlot> slots) {
        this.slots.addAll(slots);
    }

    @Override
    public boolean meetsRequierment(GearRequestedFor requested) {

        for (GearItemSlot slot : slots) {
            if (requested.forSlot.getClass()
                .isAssignableFrom(slot.getClass())) {
                return true;
            }
        }
        return false;

    }

    public static SlotRequirement ChestAndShield() {
        SlotRequirement req = new SlotRequirement(SlotRequirement.chest().slots);
        req.slots.add(Shield.INSTANCE);
        return req;

    }

    public static SlotRequirement helmetCharmAndNecklace() {
        SlotRequirement req = new SlotRequirement(SlotRequirement.helmet().slots);
        req.slots.add(Necklace.INSTANCE);
        req.slots.add(Charm.INSTANCE);

        return req;

    }

    public static SlotRequirement ring() {
        return new SlotRequirement(Ring.INSTANCE);
    }

    public static SlotRequirement allExceptWeapon() {
        return new SlotRequirement(allSlotsExceptWeapon());
    }

    public static SlotRequirement slots(GearItemSlot... slots) {
        return new SlotRequirement(Arrays.asList(slots));
    }

    public static SlotRequirement necklace() {
        return new SlotRequirement(Necklace.INSTANCE);
    }

    public static SlotRequirement charm() {
        return new SlotRequirement(Charm.INSTANCE);
    }

    public static SlotRequirement shield() {
        return new SlotRequirement(Shield.INSTANCE);
    }

    public static SlotRequirement staff() {
        return new SlotRequirement(Staff.INSTANCE);
    }

    public static SlotRequirement bracelet() {
        return new SlotRequirement(Bracelet.INSTANCE);
    }

    public static SlotRequirement chest() {
        return new SlotRequirement(SlashRegistry.GearTypes()
            .getAll()
            .values()
            .stream()
            .filter(x -> x instanceof BaseChest)
            .collect(Collectors.toList()));
    }

    public static SlotRequirement pants() {
        return new SlotRequirement(SlashRegistry.GearTypes()
            .getAll()
            .values()
            .stream()
            .filter(x -> x instanceof BasePants)
            .collect(Collectors.toList()));
    }

    public static SlotRequirement boots() {
        return new SlotRequirement(SlashRegistry.GearTypes()
            .getAll()
            .values()
            .stream()
            .filter(x -> x instanceof BaseBoots)
            .collect(Collectors.toList()));
    }

    public static SlotRequirement helmet() {
        return new SlotRequirement(SlashRegistry.GearTypes()
            .getAll()
            .values()
            .stream()
            .filter(x -> x instanceof BaseHelmet)
            .collect(Collectors.toList()));
    }

    public static SlotRequirement weapons() {
        return new SlotRequirement(weaponSlots());
    }

    public static SlotRequirement armors() {
        return new SlotRequirement(armorSlots());

    }

    public static SlotRequirement armorsOnlyNoOffHand() {
        return new SlotRequirement(armorSlotsNoOffHand());
    }

    public static SlotRequirement jewerly() {
        return new SlotRequirement(jewerlySlots());
    }

    static List<GearItemSlot> weaponSlots() {

        return SlashRegistry.GearTypes()
            .getAll()
            .values()
            .stream()
            .filter(x -> x.slotType()
                .equals(GearItemSlot.GearSlotType.Weapon))
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
            .filter(x -> x.slotType()
                .equals(GearItemSlot.GearSlotType.Armor))
            .collect(Collectors.toList());

    }

    static List<GearItemSlot> allSlotsExceptWeapon() {

        return SlashRegistry.GearTypes()
            .getAll()
            .values()
            .stream()
            .filter(x -> !x.slotType()
                .equals(GearItemSlot.GearSlotType.Weapon))
            .collect(Collectors.toList());

    }

    static List<GearItemSlot> jewerlySlots() {

        return SlashRegistry.GearTypes()
            .getAll()
            .values()
            .stream()
            .filter(x -> x.slotType()
                .equals(GearItemSlot.GearSlotType.Jewerly))
            .collect(Collectors.toList());

    }

    @Override
    public String getJsonID() {
        return "slot_req";
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.add(
            "slots",
            JsonUtils.stringListToJsonArray(slots.stream()
                .map(x -> x.GUID())
                .collect(Collectors.toList()))
        );
        return json;
    }

    @Override
    public SlotRequirement fromJson(JsonObject json) {

        try {
            SlotRequirement newobj = new SlotRequirement();

            JsonArray array = json.getAsJsonArray("slots");

            newobj.slots = JsonUtils.jsonArrayToStringList(array)
                .stream()
                .map(x -> SlashRegistry.GearTypes()
                    .get(x))
                .collect(Collectors.toList());

            return newobj;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new SText(TextFormatting.GREEN + "Allowed on: "));

        List<GearItemSlot> copy = new ArrayList<>(this.slots);

        ITextComponent comp = new SText(TextFormatting.RED + "");

        List<GearItemSlot> armors = SlashRegistry.GearTypes()
            .getFiltered(x -> x.slotType()
                .equals(GearItemSlot.GearSlotType.Armor));
        if (copy.containsAll(armors)) {
            copy.removeIf(x -> x.slotType()
                .equals(GearItemSlot.GearSlotType.Armor));
            comp.appendText(" ")
                .appendSibling(new SText("All Armors"));
        }

        List<GearItemSlot> weapons = SlashRegistry.GearTypes()
            .getFiltered(x -> x.slotType()
                .equals(GearItemSlot.GearSlotType.Weapon));
        if (copy.containsAll(weapons)) {
            copy.removeIf(x -> x.slotType()
                .equals(GearItemSlot.GearSlotType.Weapon));
            comp.appendText(" ")
                .appendSibling(new SText("All Weapons"));
        }

        List<GearItemSlot> jewerly = SlashRegistry.GearTypes()
            .getFiltered(x -> x.slotType()
                .equals(GearItemSlot.GearSlotType.Jewerly));
        if (copy.containsAll(jewerly)) {
            copy.removeIf(x -> x.slotType()
                .equals(GearItemSlot.GearSlotType.Jewerly));
            comp.appendText(" ")
                .appendSibling(new SText("All Jewerly"));
        }
        copy.forEach(x -> {
            comp.appendText(" ")
                .appendSibling(x.locName());

        });

        list.add(comp);

        return list;
    }
}
