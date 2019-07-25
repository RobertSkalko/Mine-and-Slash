package com.robertx22.mine_and_slash.database.item_modifications;

import com.robertx22.mine_and_slash.saveclasses.gearitem.ChaosStatsData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;

public class ChaosStatMod extends BaseGearMod {

    @Override
    public ITextComponent locName() {
        return Words.Chaos_Stats.locName();
    }

    @Override
    public boolean canModifyPRIVATE(ICommonDataItem data) {
        GearItemData gear = (GearItemData) data;
        return gear.chaosStats == null;
    }

    @Override
    public void modify(ICommonDataItem data) {
        GearItemData gear = (GearItemData) data;
        gear.chaosStats = new ChaosStatsData();
        gear.chaosStats.RerollFully(gear);
    }

    @Override
    public String GUID() {
        return "add_chaos_stats";
    }

}
