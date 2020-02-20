package com.robertx22.mine_and_slash.database.unique_items;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.data_generation.JsonUtils;
import com.robertx22.mine_and_slash.data_generation.unique_gears.SerializableUniqueGear;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializable;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializedRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocDesc;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IGearSlotType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public interface IUnique extends ISpecificStatReq, IRarity, IGearSlotType, ITiered, IAutoLocName, IAutoLocDesc,
    ISerializedRegistryEntry<IUnique>, ISerializable<IUnique> {

    @Override
    default boolean isFromDatapack() {
        return true;
    }

    @Override
    default String datapackFolder() {
        return getGearSlot().GUID() + "/";
    }

    @Override
    default JsonObject toJson() {
        JsonObject json = getDefaultJson();

        JsonUtils.addStatMods(primaryStats(), json, "primary_stats");
        JsonUtils.addStatMods(uniqueStats(), json, "unique_stats");

        json.addProperty("can_get_set", canGetSet());
        json.add("requirements", this.getRequirements()
            .toJson());
        json.addProperty("gear_type", this.getGearSlot()
            .GUID());
        json.addProperty("item_id", this.getResourceLocForItem()
            .toString());

        return json;
    }

    @Override
    default IUnique fromJson(JsonObject json) {

        String guid = getGUIDFromJson(json);
        String name = getLangNameStringFromJson(json);
        String desc = getLangDescStringFromJson(json);
        int tier = getTierFromJson(json);
        int weight = getWeightFromJson(json);
        GearRarity rarity = Rarities.Gears.get(getRarityFromJson(json));

        ResourceLocation loc = new ResourceLocation(json.get("item_id")
            .getAsString());

        List<StatMod> primary = JsonUtils.getStatMods(json, "primary_stats");
        List<StatMod> unique = JsonUtils.getStatMods(json, "unique_stats");

        boolean canGetSet = json.get("can_get_set")
            .getAsBoolean();
        StatReq req = StatReq.nothing()
            .fromJson(json.get("requirements")
                .getAsJsonObject());
        String slot = json.get("gear_type")
            .getAsString();

        return new SerializableUniqueGear(primary, unique, tier, rarity, weight, canGetSet, req, guid, name, desc, slot, loc);

    }

    @Override
    public default int Weight() {
        return this.getRarity()
            .Weight();
    }

    List<StatMod> uniqueStats();

    List<StatMod> primaryStats();

    @Override
    public default int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public default Rarity getRarity() {
        return Rarities.Gears.get(getRarityRank());
    }

    default String getGeneratedResourceID() {
        return getGeneratedResourceFolderPath() + GUID();
    }

    default String getGeneratedResourceFolderPath() {
        return "uniques/" + getGearSlot().
            resourceID() + "/";
    }

    public default boolean canGetSet() {
        return false;
    }

    @Override
    public default AutoLocGroup locNameGroup() {
        return AutoLocGroup.Unique_Items;
    }

    @Override
    public default AutoLocGroup locDescGroup() {
        return AutoLocGroup.Unique_Items;
    }

    @Override
    public default SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.UNIQUE_GEAR;
    }

    default Item getItemForRegistration() {
        return getGearSlot().getBaseUniqueItem()
            .setRegistryName(getResourceLocForItem());
    }

    default ResourceLocation getResourceLocForItem() {
        return new ResourceLocation(Ref.MODID, getGeneratedResourceID());
    }

    default Item getUniqueItem() {
        return ForgeRegistries.ITEMS.getValue(getResourceLocForItem());
    }

    @Override
    default String locDescLangFileGUID() {
        return "item." + getResourceLocForItem()
            .toString() + ".desc";
    }

    @Override
    default String locNameLangFileGUID() {
        return "item." + getResourceLocForItem()
            .toString();
    }

}
