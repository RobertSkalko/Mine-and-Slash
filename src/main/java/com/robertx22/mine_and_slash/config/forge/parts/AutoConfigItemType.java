package com.robertx22.mine_and_slash.config.forge.parts;

import com.robertx22.mine_and_slash.data_generation.compatible_items.CompatibleItem;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class AutoConfigItemType {

    public IntValue MAX_LEVEL;

    public DoubleValue POWER_REQ;

    public IntValue MIN_RARITY;
    public IntValue MAX_RARITY;

    public AutoConfigItemType(float req, ForgeConfigSpec.Builder builder, String type, int maxlvl, int minrar, int maxrar) {
        builder.push(type);

        MAX_LEVEL = builder.defineInRange("MAX_LEVEL", maxlvl, 0, Integer.MAX_VALUE);

        POWER_REQ = builder.defineInRange("POWER_REQ", req, 0F, 1F);

        MIN_RARITY = builder.defineInRange("MIN_RARITY", minrar, 0, IRarity.Mythic);
        MAX_RARITY = builder.defineInRange("MAX_RARITY", maxrar, 0, IRarity.Mythic);

        builder.pop();
    }

    public CompatibleItem getAutoCompatibleItem(Item item, GearItemSlot slot) {

        CompatibleItem comp = CompatibleItem.getDefaultAuto(item, slot);

        comp.max_rarity = MAX_RARITY.get();
        comp.min_rarity = MIN_RARITY.get();

        comp.max_level = MAX_LEVEL.get();

        return comp;

    }
}
