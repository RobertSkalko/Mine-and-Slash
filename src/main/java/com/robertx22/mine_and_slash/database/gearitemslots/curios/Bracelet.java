package com.robertx22.mine_and_slash.database.gearitemslots.curios;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseCurio;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueBracelet;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemBracelet;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class Bracelet extends BaseCurio {
    public static GearItemSlot INSTANCE = new Bracelet();

    private Bracelet() {

    }

    @Override
    public Item getBaseUniqueItem() {
        return new BaseUniqueBracelet();
    }

    @Override
    public StatReq getRequirements() {
        return noReq;
    }

    @Override
    public String resourceID() {
        return "bracelet";
    }

    @Override
    public String GUID() {
        return "bracelet";
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return eleDmgs();
    }

    @Override
    public List<StatMod> getPossibleSecondaryStats() {
        return new ElementalResistFlat(Elements.Physical).allSingleElementVariations();
    }

    @Override
    public Item getDefaultItem() {
        return ItemBracelet.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ItemBracelet.Items;
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.Jewerly;
    }

    @Override
    public String locNameForLangFile() {
        return "Bracelet";
    }
}