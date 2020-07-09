package com.robertx22.mine_and_slash.database.requirements;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.data_generation.JsonUtils;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.requirements.bases.BaseRequirement;
import com.robertx22.mine_and_slash.database.requirements.bases.GearRequestedFor;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
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

    public static SlotRequirement Of(GearItemSlot.SlotFamily type) {
        return new SlotRequirement(SlashRegistry.GearTypes()
            .getFiltered(x -> x.slotTypeFamily() == type));

    }

    public static SlotRequirement Of(GearItemSlot.SlotTag tag) {
        return new SlotRequirement(SlashRegistry.GearTypes()
            .getFiltered(x -> x.getTags()
                .contains(tag)));

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
            .getFiltered(x -> x.slotTypeFamily()
                .equals(GearItemSlot.SlotFamily.Armor));
        if (copy.containsAll(armors)) {
            copy.removeIf(x -> x.slotTypeFamily()
                .equals(GearItemSlot.SlotFamily.Armor));
            comp.appendText(" ")
                .appendSibling(new SText("All Armors"));
        }

        List<GearItemSlot> weapons = SlashRegistry.GearTypes()
            .getFiltered(x -> x.slotTypeFamily()
                .equals(GearItemSlot.SlotFamily.Weapon));
        if (copy.containsAll(weapons)) {
            copy.removeIf(x -> x.slotTypeFamily()
                .equals(GearItemSlot.SlotFamily.Weapon));
            comp.appendText(" ")
                .appendSibling(new SText("All Weapons"));
        }

        List<GearItemSlot> jewerly = SlashRegistry.GearTypes()
            .getFiltered(x -> x.slotTypeFamily()
                .equals(GearItemSlot.SlotFamily.Jewelry));
        if (copy.containsAll(jewerly)) {
            copy.removeIf(x -> x.slotTypeFamily()
                .equals(GearItemSlot.SlotFamily.Jewelry));
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
