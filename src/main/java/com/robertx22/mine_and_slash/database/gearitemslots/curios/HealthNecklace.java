package com.robertx22.mine_and_slash.database.gearitemslots.curios;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseCurio;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.BonusExpFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldRegenFlat;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemNecklace;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HealthNecklace extends BaseCurio {
    public static GearItemSlot INSTANCE = new HealthNecklace();

    private HealthNecklace() {

    }

    @Override
    public List<StatModifier> ImplicitStats() {
        return Arrays.asList();
    }

    @Override
    public List<StatModifier> BaseStats() {
        return Arrays.asList();
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
        return Arrays.asList(
            new PosStats(new HealthRegenFlat()),
            new PosStats(new BonusExpFlat()),
            new PosStats(new MagicShieldRegenFlat())
        );
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
    public SlotFamily slotTypeFamily() {
        return SlotFamily.Jewelry;
    }

    @Override
    public List<SlotTag> getTags() {
        return Arrays.asList(SlotTag.Necklace);
    }

    @Override
    public String locNameForLangFile() {
        return "Life Necklace";
    }
}
