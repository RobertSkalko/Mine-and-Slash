package com.robertx22.mine_and_slash.database.stats;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.data_generation.statmods.SerializableStatMod;
import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializable;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializedRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.registry.empty_entries.EmptyStatMod;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class StatMod implements IWeighted, IRarity, IGUID, ISerializedRegistryEntry<StatMod>,
    ISerializable<StatMod>, ITooltipList {

    public static EmptyStatMod EMPTY = EmptyStatMod.getInstance();

    public Size size = Size.NORMAL;
    //protected String afterPrefix = "";

    public enum Size {
        TRIPLE_LESS("crippled_", -3F),
        DOUBLE_LESS("double_less_", -2F),
        ONE_LESS("much_less_", -1F),
        HALF_LESS("less_", -0.5F),
        QUARTER("tiny_", 0.25F),
        HALF("very_low_", 0.5F),
        LOW("low_", 0.75F),
        NORMAL("", 1),
        HALF_MORE("high_", 1.5F),
        DOUBLE("very_high_", 2),
        TRIPLE("major_", 3);

        public String prefix;
        public float multi;

        Size(String prefix, float multi) {
            this.prefix = prefix;
            this.multi = multi;
        }
    }

    public static List<StatMod> ofSize(List<StatMod> mod, Size size) {
        return mod.stream()
            .map(x -> x.size(size))
            .collect(Collectors.toList());
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new SText(TextFormatting.GREEN + "").appendSibling(GetBaseStat()
            .locName())
            .appendText(": " + getMin() + " / " + getMax()));

        return list;
    }

    public StatMod size(Size size) {
        String newGUID = getGUIDFor(GetBaseStat(), size, getModType()); // cus serializablestatmod takes a guid field
        return new SerializableStatMod(GetBaseStat().GUID(), Min(), Max(), getModType(), newGUID, size, minSecond(), maxSecond());
    }

    public String getGUIDFor(Stat stat, Size size, StatModTypes type) {
        Preconditions.checkNotNull(stat, this);
        Preconditions.checkNotNull(size, this);
        Preconditions.checkNotNull(type, this);
        return size.prefix + stat.GUID() + "_" + type.id;
    }

    @Override
    public String GUID() {
        return getGUIDFor(GetBaseStat(), size, getModType());
    }

    public List<StatMod> getAllSizeVariations() {
        List<StatMod> list = new ArrayList<>();
        Arrays.stream(Size.values())
            .forEach(x -> list.add(size(x)));
        return list;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.STATMOD;
    }

    @Override
    public int Weight() {
        return this.getRarity()
            .Weight();
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Gears.get(getRarityRank());

    }

    @Override
    public int getRarityRank() {
        return 1;
    }

    public abstract Stat GetBaseStat();

    public abstract float Min();

    public abstract float Max();

    public float minSecond() {
        return Min();
    }

    public float maxSecond() {
        return Max();
    }

    public boolean usesNumberRanges() {
        return getModType()
            .equals(StatModTypes.Flat) && Min() != minSecond() && Max() != maxSecond();
    }

    public final float getMin() {
        return Min() * size.multi;
    }

    public final float getMax() {
        return Max() * size.multi;
    }

    public final float getMinSecond() {
        return minSecond() * size.multi;
    }

    public final float getMaxSecond() {
        return maxSecond() * size.multi;
    }

    public abstract StatModTypes getModType();

    @Override
    public JsonObject toJson() {

        JsonObject json = new JsonObject();

        json.addProperty("min", Min());
        json.addProperty("max", Max());

        json.addProperty("min_second", minSecond());
        json.addProperty("max_second", maxSecond());

        json.addProperty("stat", GetBaseStat().GUID());
        json.addProperty("type", getModType().name());
        json.addProperty("guid", GUID());
        json.addProperty("size", size.name());

        return json;
    }

    @Override
    public StatMod fromJson(JsonObject json) {

        float min = json.get("min")
            .getAsFloat();
        float max = json.get("max")
            .getAsFloat();

        float min2 = json.get("min_second")
            .getAsFloat();
        float max2 = json.get("max_second")
            .getAsFloat();

        String stat = json.get("stat")
            .getAsString();
        String guid = json.get("guid")
            .getAsString();
        Size size = Size.valueOf(json.get("size")
            .getAsString());

        StatModTypes type = StatModTypes.valueOf(json.get("type")
            .getAsString());

        return new SerializableStatMod(stat, min, max, type, guid, size, min2, max2);
    }

}