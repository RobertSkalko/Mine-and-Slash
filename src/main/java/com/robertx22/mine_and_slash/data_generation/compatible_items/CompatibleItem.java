package com.robertx22.mine_and_slash.data_generation.compatible_items;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.config.compatible_items.WeightedType;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.RunedGearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueGearBlueprint;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializable;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializedRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.GearItemEnum;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.List;

public class CompatibleItem implements ISerializable<CompatibleItem>, ISerializedRegistryEntry<CompatibleItem> {

    public static CompatibleItem EMPTY = new CompatibleItem();

    public String item_type = "sword";
    public String guid = "guid_for_this_entry";
    public String item_id = "item_id";

    public int unique_item_weight = 0;
    public int normal_item_weight = 80;
    public int runed_item_weight = 20;

    public int min_rarity = 0;
    public int max_rarity = 4;

    public boolean only_add_stats_if_loot_drop = false;
    public boolean add_to_loot_drops = true;
    public int loot_drop_weight = 1000;
    public boolean can_be_salvaged = false;

    public int level_variance = 0;
    public int min_level = 1;
    public int max_level = Integer.MAX_VALUE;

    public int if_unique_random_up_to_tier = 10;
    public String unique_id = "";

    public static CompatibleItem getDefaultAuto(Item item, GearItemSlot slot) {

        CompatibleItem comp = new CompatibleItem();
        comp.item_type = slot.GUID();
        comp.item_id = item.getRegistryName()
            .toString();
        comp.guid = slot.GUID() + ":" + comp.item_id;
        comp.level_variance = 3;
        comp.add_to_loot_drops = false;

        return comp;

    }

    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();

        json.addProperty("item_type", item_type);
        json.addProperty("item_id", item_id);
        json.addProperty("id", guid);

        JsonObject gearType = new JsonObject();
        gearType.addProperty("normal_item_weight", normal_item_weight);
        gearType.addProperty("runed_item_weight", runed_item_weight);
        gearType.addProperty("unique_item_weight", unique_item_weight);
        json.add("gear_type", gearType);

        JsonObject rarity = new JsonObject();
        rarity.addProperty("min_rarity", min_rarity);
        rarity.addProperty("max_rarity", max_rarity);
        json.add("rarity", rarity);

        JsonObject Misc = new JsonObject();
        Misc.addProperty("only_add_stats_if_loot_drop", only_add_stats_if_loot_drop);
        Misc.addProperty("add_to_loot_drops", add_to_loot_drops);
        Misc.addProperty("loot_drop_weight", loot_drop_weight);
        Misc.addProperty("can_be_salvaged", can_be_salvaged);
        json.add("misc", Misc);

        JsonObject level = new JsonObject();
        level.addProperty("min_level", min_level);
        level.addProperty("max_level", max_level);
        level.addProperty("level_variance", level_variance);
        json.add("level", level);

        JsonObject unique = new JsonObject();
        unique.addProperty("if_unique_random_up_to_tier", if_unique_random_up_to_tier);
        unique.addProperty("unique_id", unique_id);
        json.add("unique", unique);

        return json;
    }

    @Override
    public CompatibleItem fromJson(JsonObject json) {
        CompatibleItem obj = new CompatibleItem();

        obj.item_type = json.get("item_type")
            .getAsString();
        obj.item_id = json.get("item_id")
            .getAsString();
        obj.guid = getGUIDFromJson(json);

        JsonObject gearType = json.getAsJsonObject("gear_type");
        obj.normal_item_weight = gearType.get("normal_item_weight")
            .getAsInt();
        obj.runed_item_weight = gearType.get("runed_item_weight")
            .getAsInt();
        obj.unique_item_weight = gearType.get("unique_item_weight")
            .getAsInt();

        JsonObject rarity = json.getAsJsonObject("rarity");
        obj.min_rarity = rarity.get("min_rarity")
            .getAsInt();
        obj.max_rarity = rarity.get("max_rarity")
            .getAsInt();

        JsonObject misc = json.getAsJsonObject("misc");
        obj.only_add_stats_if_loot_drop = misc.get("only_add_stats_if_loot_drop")
            .getAsBoolean();
        obj.add_to_loot_drops = misc.get("add_to_loot_drops")
            .getAsBoolean();
        obj.loot_drop_weight = misc.get("loot_drop_weight")
            .getAsInt();
        obj.can_be_salvaged = misc.get("can_be_salvaged")
            .getAsBoolean();

        JsonObject level = json.getAsJsonObject("level");
        obj.min_level = level.get("min_level")
            .getAsInt();
        obj.max_level = level.get("max_level")
            .getAsInt();
        obj.level_variance = level.get("level_variance")
            .getAsInt();

        JsonObject unique = json.getAsJsonObject("unique");
        obj.if_unique_random_up_to_tier = unique.get("if_unique_random_up_to_tier")
            .getAsInt();
        obj.unique_id = unique.get("unique_id")
            .getAsString();

        return obj;
    }

    @Override
    public String datapackFolder() {
        return new ResourceLocation(item_id).getNamespace() + "/";
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.COMPATIBLE_ITEM;
    }

    @Override
    public boolean isFromDatapack() {
        return true;
    }

    public String getFileName() {
        return new ResourceLocation(item_id).getPath();
    }

    @Override
    public String GUID() {
        return guid;
    }

    public ItemStack create(ItemStack stack, int level) {

        level = this.getLevel(level);

        switch (getCreationType()) {
            case NORMAL:
                createNormal(stack, level);
                break;
            case UNIQUE:
                createUnique(stack, level);
                break;
            case RUNED:
                createRuned(stack, level);
                break;

        }

        return stack;

    }

    private GearItemEnum getCreationType() {
        WeightedType result = RandomUtils.weightedRandom(Arrays.asList(
            new WeightedType(normal_item_weight, GearItemEnum.NORMAL),
            new WeightedType(unique_item_weight, GearItemEnum.UNIQUE),
            new WeightedType(runed_item_weight, GearItemEnum.RUNED)
        ));

        return result.type;
    }

    private int getLevel(int playerlevel) {
        return MathHelper.clamp(playerlevel, min_level, max_level);
    }

    private ItemStack createNormal(ItemStack stack, int level) {

        GearBlueprint blueprint = new GearBlueprint(level);
        blueprint.gearItemSlot.set(this.item_type);
        blueprint.level.LevelRange = this.level_variance > 0;
        blueprint.level.LevelVariance = this.level_variance;
        blueprint.rarity.minRarity = this.min_rarity;
        blueprint.rarity.maxRarity = this.max_rarity;

        GearItemData gear = blueprint.createData();
        gear.isSalvagable = this.can_be_salvaged;
        gear.isNotFromMyMod = true;

        Gear.Save(stack, gear);

        return stack;

    }

    private ItemStack createUnique(ItemStack stack, int level) {

        UniqueGearBlueprint blueprint = null;

        if (SlashRegistry.UniqueGears()
            .isRegistered(unique_id)) {
            blueprint = new UniqueGearBlueprint(level, SlashRegistry.UniqueGears()
                .get(unique_id));
        } else {
            blueprint = new UniqueGearBlueprint(level, if_unique_random_up_to_tier);
        }

        blueprint.gearItemSlot.set(this.item_type);
        blueprint.level.LevelRange = this.level_variance > 0;
        blueprint.level.LevelVariance = this.level_variance;

        GearItemData gear = blueprint.createData();
        gear.isSalvagable = this.can_be_salvaged;
        gear.isNotFromMyMod = true;

        if (gear.uniqueGUID == null || !SlashRegistry.UniqueGears()
            .isRegistered(gear.uniqueGUID)) {
            return createNormal(stack, level);
        } else {
            Gear.Save(stack, gear);
        }

        return stack;

    }

    private ItemStack createRuned(ItemStack stack, int level) {

        RunedGearBlueprint blueprint = new RunedGearBlueprint(level);
        blueprint.gearItemSlot.set(this.item_type);
        blueprint.level.LevelRange = this.level_variance > 0;
        blueprint.level.LevelVariance = this.level_variance;
        blueprint.rarity.minRarity = this.min_rarity;
        blueprint.rarity.maxRarity = this.max_rarity;

        GearItemData gear = blueprint.createData();
        gear.isSalvagable = this.can_be_salvaged;
        gear.isNotFromMyMod = true;

        Gear.Save(stack, gear);

        return stack;

    }

    @Override
    public boolean isRegistryEntryValid() {

        if (!SlashRegistry.GearTypes()
            .isRegistered(this.item_type)) {
            System.out.println("Invalid gear slot: " + item_type);
            return false;
        }
        if (!unique_id.isEmpty()) {
            if (!SlashRegistry.UniqueGears()
                .isRegistered(unique_id)) {
                System.out.println("Invalid unique gear id: " + unique_id);
                return false;
            }
        }

        if (unique_id.isEmpty() && unique_item_weight > 0) {
            List<IUnique> possible = SlashRegistry.UniqueGears()
                .getFiltered(x -> x.getGearSlot()
                    .GUID()
                    .equals(item_type) && x.getTier() <= if_unique_random_up_to_tier);
            if (possible.isEmpty()) {
                System.out.println("There are no possible random uniques for item type of: " + item_type + " of unique tier of " + if_unique_random_up_to_tier + " or less.");
                System.out.println("This won't prevent the compatible item from functioning, but it means whenever it tries to generate as unique, it will turn to fallback normal item.");
                return true;
            }
        }

        return true;
    }

    //this is how the file should look and be separated into
    /*

        public String item_type = "Sword";


    class GearType {
            public int unique_item_weight = 0;
            public int normal_item_weight = 80;
            public int runed_item_weight = 20;
        }

    class Rarity {
        public int min_rarity = 0;
        public int max_rarity = 5;
    }

    class Misc {
        public boolean only_add_stats_if_loot_drop = false;
        public boolean add_to_loot_drops = true;
        public int loot_drop_weight = 1000;

        public boolean can_be_salvaged = false;
    }

    class Level {
        public int level_variance = 0;
        public int min_level = 1;
        public int max_level = 100;
    }

    class IfUnique {
        public int if_unique_random_up_to_tier = 10;
        public String unique_id = "";
    }

     */

}
