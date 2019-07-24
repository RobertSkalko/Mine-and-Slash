package com.robertx22.mine_and_slash.items.infusions.upgrade;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Arrays;
import java.util.List;

public class NormalUpgradeInfusion extends BaseUpgradeInfusion {

    private static final String name = "normal_upgrade_infusion";

    @ObjectHolder(Ref.MODID + ":" + name)
    public static final Item ITEM = null;

    public NormalUpgradeInfusion() {
        super(name);

    }

    @Override
    public int Tier() {
        return 7;
    }

    @Override
    public float critOnSuccessChance() {
        return 5F;
    }

    @Override
    public float bonusSuccessChance() {
        return 0;
    }

    @Override
    public float majorFailureChance() {
        return 5F;
    }

    @Override
    public String GUID() {
        return name;
    }

    @Override
    public int rarity() {
        return 1;
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Normal Infusion Upgrade";
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("You start to feel the essence of Luck.");
    }

}
