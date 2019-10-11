package com.robertx22.mine_and_slash.database.gearitemslots.cloth;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseBoots;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldFlat;
import com.robertx22.mine_and_slash.items.gearitems.armor.cloth.ClothBootsItem;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ClothBoots extends BaseBoots {

    public static GearItemSlot INSTANCE = new ClothBoots();

    private ClothBoots() {

    }

    @Override
    public List<StatMod> PrimaryStats() {
        return Arrays.asList(new MagicShieldFlat());
    }

    @Override
    public List<Stat> statRequirements() {
        return clothRequirements();
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return this.clothArmorStats();
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ClothBootsItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Cloth Slippers";
    }

    @Override
    public String GUID() {
        return "Cloth_Boots";
    }
}
