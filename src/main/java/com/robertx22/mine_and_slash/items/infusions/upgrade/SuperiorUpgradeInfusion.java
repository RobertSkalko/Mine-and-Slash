package com.robertx22.mine_and_slash.items.infusions.upgrade;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Arrays;
import java.util.List;

public class SuperiorUpgradeInfusion extends BaseUpgradeInfusion {

    public SuperiorUpgradeInfusion() {
        super(name);

    }

    private static final String name = "superior_upgrade_infusion";

    @ObjectHolder(Ref.MODID + ":" + name)
    public static final Item ITEM = null;

    @Override
    public int Tier() {
        return 9;
    }

    @Override
    public float critOnSuccessChance() {
        return 10F;
    }

    @Override
    public float bonusSuccessChance() {
        return 5F;
    }

    @Override
    public float majorFailureChance() {
        return 4F;
    }

    @Override
    public String GUID() {
        return name;
    }

    @Override
    public int rarity() {
        return 2;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Your understanding of luck deepens.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Superior Infusion Upgrade";
    }
}
