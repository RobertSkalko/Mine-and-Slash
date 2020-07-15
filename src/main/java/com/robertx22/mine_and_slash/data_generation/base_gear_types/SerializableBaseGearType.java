package com.robertx22.mine_and_slash.data_generation.base_gear_types;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseGearType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.StatRequirement;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class SerializableBaseGearType extends BaseGearType {

    List<StatModifier> implicit_stats;
    List<StatModifier> base_stats;
    List<SlotTag> tags;
    String item_id;
    StatRequirement stat_req;
    EquipmentSlotType vanillaSlotType;
    String identifier;
    String lang_name_id;

    @Override
    public String locNameLangFileGUID() {
        return lang_name_id;
    }

    @Override
    public List<StatModifier> implicitStats() {
        return implicit_stats;
    }

    @Override
    public List<StatModifier> baseStats() {
        return base_stats;
    }

    @Override
    public List<SlotTag> getTags() {
        return tags;
    }

    @Override
    public Item getItem() {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(item_id));
    }

    @Override
    public StatRequirement getStatRequirements() {
        return stat_req;
    }

    @Override
    public EquipmentSlotType getVanillaSlotType() {
        return vanillaSlotType;
    }

    @Override
    public String locNameForLangFile() {
        return null;
    }

    @Override
    public String GUID() {
        return identifier;
    }
}
