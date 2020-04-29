package com.robertx22.mine_and_slash.database.runewords;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.data_generation.JsonUtils;
import com.robertx22.mine_and_slash.data_generation.runewords.SerializableRuneword;
import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runes.base.BaseUniqueRune;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializable;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializedRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.registry.empty_entries.EmptyRuneWord;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.List;
import java.util.stream.Collectors;

public abstract class RuneWord implements IGUID, IWeighted, IAutoLocName, ISerializedRegistryEntry<RuneWord>,
    ISerializable<RuneWord> {

    public static RuneWord EMPTY = EmptyRuneWord.getInstance();

    public abstract List<StatMod> mods();

    public abstract String GUID();

    @Override
    public String datapackFolder() {
        return runes().size() + "_runes/";
    }

    @Override
    public boolean isFromDatapack() {
        return true;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Runes.get(getRarityRank());
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.RUNEWORD;
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".word." + formattedGUID();
    }

    public abstract List<BaseRune> runes();

    public int size() {
        return runes().size();
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Rune_Words;
    }

    @Override
    public int Weight() {
        return this.getRarity()
            .Weight();
    }

    public String getRuneWordCombo() {

        String text = "";

        for (BaseRune item : runes()) {
            text += item.name()
                .toUpperCase();
        }
        return text;
    }

    @Override
    public boolean isRegistryEntryValid() {

        if (!checkStatModsValidity(this.mods())) {
            return false;
        }
        if (runes().stream()
            .filter(rune -> rune.isUnique())
            .count() > 1) {
            System.out.println(GUID() + " needs more than 1 unique rune, runewords should not be able to require " + "more than 1.!");
            return false;
        }
        if (runes().size() > 5) {
            System.out.println("Can't have more than 5 runes required in a runeword");
            return false;
        }

        return true;
    }

    public ITextComponent getRuneWordComboString() {

        ITextComponent comp = new StringTextComponent("");

        int current = 0;

        for (BaseRune item : runes()) {

            String runetext = item.name()
                .toUpperCase();

            if (current < runes().size() - 1) {
                runetext += " + ";
            }

            if (item instanceof BaseUniqueRune) {
                comp.appendText(TextFormatting.YELLOW + runetext);
            } else {
                comp.appendText(TextFormatting.GREEN + runetext);
            }

            current++;
        }

        return comp;
    }

    public boolean runesMatch(String word) {
        return this.getRuneWordCombo()
            .equals(word);
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = getDefaultJson();

        json.add(
            "runes",
            JsonUtils.stringListToJsonArray(runes().stream()
                .map(x -> x.GUID())
                .collect(Collectors.toList()))
        );

        JsonUtils.addStatMods(mods(), json, "mods");

        return json;
    }

    @Override
    public RuneWord fromJson(JsonObject json) {

        String guid = getGUIDFromJson(json);
        String lang = getLangNameStringFromJson(json);
        int weight = getWeightFromJson(json);
        int rarity = getRarityFromJson(json);

        List<String> runes = JsonUtils.jsonArrayToStringList(json.getAsJsonArray("runes"));

        List<StatMod> mods = JsonUtils.getStatMods(json, "mods");

        return new SerializableRuneword(rarity, weight, guid, mods, lang, runes);
    }
}
