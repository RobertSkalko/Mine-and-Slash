package com.robertx22.mine_and_slash.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.robertx22.mine_and_slash.config.compatible_items.ConfigItem;
import com.robertx22.mine_and_slash.config.compatible_items.ConfigItems;
import com.robertx22.mine_and_slash.config.compatible_items.ConfigItemsSerialization;
import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SerializationUtils;
import net.minecraft.item.Item;

public class MineAndSlashAPI {

    public static void addCompatibleItem(String itemID, ConfigItem item) {
        item.registryName = itemID;
        SlashRegistry.CompatibleItems().register(item);
    }

    public static void generateCompatibleItemsFile(ConfigItems items, String fileName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(items);
        SerializationUtils.makeFileAndDirAndWrite(new ConfigItemsSerialization().folder(), fileName, json);
    }

    public static void addAffix(BaseAffix affix) {
        if (affix instanceof Prefix) {
            SlashRegistry.Prefixes().register((Prefix) affix);
        } else if (affix instanceof Suffix) {
            SlashRegistry.Suffixes().register((Suffix) affix);
        } else {
            throw new Error("Affix must be derived from the Prefix or the Suffix class!");
        }
    }

    public static void addMapAffix(BaseMapAffix affix) {
        SlashRegistry.MapAffixes().register(affix);
    }

    public static void addRuneWord(RuneWord word) {
        SlashRegistry.RuneWords().register(word);
    }

    public static void addSet(Set set) {
        SlashRegistry.Sets().register(set);
    }

    public static void addSpell(BaseSpell spell) {
        SlashRegistry.Spells().register(spell);

    }

    public static void addMobEffect(BaseStatusEffect effect) {
        SlashRegistry.StatusEffects().register(effect);
    }

    public static void addGearItemType(GearItemSlot type) {
        SlashRegistry.GearTypes().register(type);
    }

    public static void addRune(BaseRuneItem rune) {
        SlashRegistry.Runes().register(rune);
    }

    public static <T extends Item & IUnique> void addUnique(T unique) {
        SlashRegistry.UniqueGears().register(unique);
    }

}
