package com.robertx22.mine_and_slash.database.gearitemslots;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.misc.BonusExpFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemNecklace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Necklace extends GearItemSlot {

    @Override
    public String GUID() {
        return "Necklace";
    }

    @Override
    public List<StatMod> PrimaryStats() {
        return Arrays.asList(new HealthRegenFlat(), new BonusExpFlat());
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {

        return new ElementalResistFlat(Elements.Physical).allSingleElementVariations();

    }

    @Override
    public Item DefaultItem() {
        return ItemNecklace.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ItemNecklace.Items;
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.Jewerly;
    }

    @Override
    public String locNameForLangFile() {
        return "Necklace";
    }
}
