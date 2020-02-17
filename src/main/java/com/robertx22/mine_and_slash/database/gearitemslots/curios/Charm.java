package com.robertx22.mine_and_slash.database.gearitemslots.curios;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueCharm;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Stamina;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemCharm;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Charm extends GearItemSlot {
    public static GearItemSlot INSTANCE = new Charm();

    private Charm() {

    }

    @Override
    public StatReq getRequirements() {
        return noReq;
    }

    @Override
    public Item getBaseUniqueItem() {
        return new BaseUniqueCharm();
    }

    @Override
    public String resourceID() {
        return "charm";
    }

    @Override
    public String GUID() {
        return "charm";
    }

    static float multi = 2;

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        List<PosStats> list = new ArrayList<>();
        new ElementalPeneFlat(Elements.Nature).allSingleElementVariations()
            .stream()
            .forEach(x -> list.add(new PosStats((StatMod) x)));
        return list;

    }

    @Override
    public boolean isGearOfThisType(Item item) {
        return false;
    }

    @Override
    public List<StatMod> getPossibleSecondaryStats() {
        return new CoreStatFlat(Stamina.INSTANCE).generateAllPossibleStatVariations();
    }

    @Override
    public Item getDefaultItem() {
        return ItemCharm.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ItemCharm.Items;
    }

    @Override
    public int Weight() {
        return 1200;
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.Jewerly;
    }

    @Override
    public String locNameForLangFile() {
        return "Charm";
    }
}
