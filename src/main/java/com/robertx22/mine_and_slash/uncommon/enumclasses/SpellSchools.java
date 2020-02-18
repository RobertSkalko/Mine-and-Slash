package com.robertx22.mine_and_slash.uncommon.enumclasses;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public enum SpellSchools {

    ARCANIST("arcanist", Elements.Elemental.format, Words.Arcanist),
    OCEAN_MYSTIC("ocean_mystic", Elements.Water.format, Words.OceanMystic),
    EMBER_MAGE("ember_mage", Elements.Fire.format, Words.EmberMage),
    DRUID("druid", Elements.Nature.format, Words.Druid),
    SHAMAN("shaman", Elements.Thunder.format, Words.Shaman),
    RANGER("ranger", TextFormatting.GREEN, Words.Ranger),
    CLERIC("cleric", TextFormatting.WHITE, Words.Cleric);

    SpellSchools(String id, TextFormatting format, Words locName) {
        this.id = id;
        this.format = format;
        this.locName = locName;
    }

    public String id;
    public TextFormatting format;
    public Words locName;

    public ResourceLocation getGuiIconLocation() {
        return new ResourceLocation(Ref.MODID, "textures/gui/spells/schools/" + id + ".png");
    }

}
