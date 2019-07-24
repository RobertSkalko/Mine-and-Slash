package com.robertx22.mine_and_slash.items.infusions.upgrade;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Arrays;
import java.util.List;

public class WondrousUpgradeInfusion extends BaseUpgradeInfusion {

    public WondrousUpgradeInfusion() {
        super(name);
    }

    private static final String name = "wondrous_upgrade_infusion";

    @ObjectHolder(Ref.MODID + ":" + name)
    public static final Item ITEM = null;

    @Override
    public int Tier() {
        return 12;
    }

    @Override
    public float critOnSuccessChance() {
        return 20F;
    }

    @Override
    public float bonusSuccessChance() {
        return 15F;
    }

    @Override
    public float majorFailureChance() {
        return 3F;
    }

    @Override
    public String GUID() {
        return name;
    }

    @Override
    public int rarity() {
        return 3;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Grasp Luck with your own hands.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Wondrous Infusion Upgrade";
    }
}
