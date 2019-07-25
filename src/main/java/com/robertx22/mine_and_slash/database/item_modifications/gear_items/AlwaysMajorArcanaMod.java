package com.robertx22.mine_and_slash.database.item_modifications.gear_items;

import com.robertx22.mine_and_slash.database.item_modifications.bases.BaseGearMod;
import com.robertx22.mine_and_slash.database.item_modifications.bases.ItemModType;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.major_arcana.BaseMajorArcana;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.ChaosStatsData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;

public class AlwaysMajorArcanaMod extends BaseGearMod {

    @Override
    public ITextComponent locName() {
        return Words.AddMajorArcana.locName();
    }

    @Override
    public ItemModType getItemModType() {
        return ItemModType.CHAOS_STATS;
    }

    @Override
    public boolean canModifyPRIVATE(ICommonDataItem data) {
        GearItemData gear = (GearItemData) data;
        return gear.chaosStats == null;
    }

    @Override
    public void modifyGear(GearItemData gear) {
        StatMod mod = SlashRegistry.StatMods()
                .getFilterWrapped(x -> x.GetBaseStat() instanceof BaseMajorArcana)
                .random();

        gear.chaosStats = new ChaosStatsData();
        gear.chaosStats.create(gear, mod);

    }
  
    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public String GUID() {
        return "major_arcana_mod";
    }
}
