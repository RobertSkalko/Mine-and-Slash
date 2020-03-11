package com.robertx22.mine_and_slash.database.gearitemslots.curios;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseCurio;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.BonusExpFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueNecklace;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemNecklace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Necklace extends BaseCurio {
    public static GearItemSlot INSTANCE = new Necklace();

    private Necklace() {

    }

    @Override
    public StatReq getRequirements() {
        return noReq;
    }

    @Override
    public Item getBaseUniqueItem() {
        return new BaseUniqueNecklace();
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.NONE;
    }

    @Override
    public String resourceID() {
        return "necklace";
    }

    @Override
    public String GUID() {
        return "necklace";
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return Arrays.asList(new PosStats(new HealthRegenFlat()), new PosStats(new BonusExpFlat()),
            new PosStats(new MagicShieldRegenFlat())
        );
    }

    @Override
    public List<StatMod> getPossibleSecondaryStats() {

        return new ElementalResistFlat(Elements.Physical).allSingleElementVariations();

    }

    @Override
    public Item getDefaultItem() {
        return ItemNecklace.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
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
