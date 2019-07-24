package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Storable
public class UniqueStatsData implements ITooltipList, IRerollable, IStatsContainer {

    public UniqueStatsData() {

    }

    @Store
    public String uniqueGUID;
    @Store
    public List<Integer> percents = new ArrayList<Integer>();

    public UniqueStatsData(String GUID) {
        this.uniqueGUID = GUID;
    }

    @Override
    public void RerollFully(GearItemData gear) {
        this.RerollNumbers(gear);
    }

    @Override
    public void RerollNumbers(GearItemData gear) {

        percents.clear();

        // wont ever have more than 10 unique stats.
        for (int i = 0; i < 10; i++) {
            percents.add(gear.getRarity().StatPercents().genPercent());
        }

    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        list.add(new StringTextComponent(Styles.YELLOW + "").appendSibling(Words.Unique_Stats
                .locName()
                .appendText(":")));

        for (LevelAndStats part : this.GetAllStats(info.level)) {

            for (StatModData data : part.mods) {
                list.addAll(data.GetTooltipString(info));
            }
        }

        return list;

    }

    public IUnique getUniqueItem() {

        return SlashRegistry.UniqueGears().get(this.uniqueGUID);

    }

    @Override
    public List<LevelAndStats> GetAllStats(int level) {

        IUnique unique = getUniqueItem();

        List<StatModData> list = new ArrayList<StatModData>();

        for (int i = 0; i < unique.uniqueStats().size(); i++) {
            StatMod mod = unique.uniqueStats().get(i);
            list.add(StatModData.Load(mod, percents.get(i)));
        }

        return Arrays.asList(new LevelAndStats(list, level));
    }

}
