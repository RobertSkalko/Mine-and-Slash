package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IGearPartTooltip;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

@Storable
public class UniqueStatsData implements IGearPartTooltip, IRerollable, IStatsContainer {

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
        this.uniqueGUID = gear.unique_id;
    }

    @Override
    public void RerollNumbers(GearItemData gear) {

        percents.clear();

        // wont ever have more than 10 unique stats.
        for (int i = 0; i < 10; i++) {
            percents.add(getMinMax(gear)
                .random());
        }

    }

    public ITextComponent getHeader() {
        return new StringTextComponent(Styles.YELLOW + "").appendSibling(Words.Unique_Stats
            .locName()
            .appendText(":"));
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info, GearItemData gear) {

        info.minmax = getMinMax(gear);

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        list.add(new SText(""));
        list.add(getHeader());

        GetAllStats().forEach(x -> list.addAll(x.GetTooltipString(info)));

        return list;

    }

    public IUnique getUnique() {
        return SlashRegistry.UniqueGears()
            .get(this.uniqueGUID);

    }

    @Override
    public Part getPart() {
        return Part.UNIQUE_STATS;
    }

    @Override
    public List<ExactStatData> GetAllStats() {

        List<ExactStatData> list = new ArrayList<>();

        int i = 0;
        for (StatModifier mod : SlashRegistry.UniqueGears()
            .get(uniqueGUID)
            .uniqueStats()) {
            list.add(mod.ToExactStat(percents.get(i)));
            i++;
        }
        return list;

    }
}
