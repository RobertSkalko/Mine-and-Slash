package com.robertx22.mine_and_slash.uncommon.enumclasses;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.util.ResourceLocation;

public enum SpellSchools {

    OCEAN_MYSTIC("ocean_mystic"),
    EMBER_MAGE("ember_mage"),
    DRUID("druid"),
    SHAMAN("shaman"),
    CLERIC("cleric");

    SpellSchools(String id) {
        this.id = id;
    }

    public String id;

    public ResourceLocation getGuiIconLocation() {
        return new ResourceLocation(Ref.MODID, "textures/gui/spells/schools/" + id + ".png");
    }

}
