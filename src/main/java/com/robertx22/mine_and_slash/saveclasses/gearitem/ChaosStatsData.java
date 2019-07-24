package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ICreateSpecific;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import info.loenwind.autosave.annotations.Storable;
import net.minecraft.util.text.ITextComponent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Storable
public class ChaosStatsData extends StatGroupData implements ICreateSpecific<StatMod>, Serializable, ITooltipList, IRerollable {

    private static final long serialVersionUID = -8272316157157669116L;

    public ChaosStatsData() {

    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        list.add(Styles.REDCOMP()
                .appendSibling(Words.Chaos_Stats.locName().appendText(":")));

        for (LevelAndStats part : this.GetAllStats(info.unitdata.getLevel())) {
            for (StatModData data : part.mods) {
                list.addAll(data.GetTooltipString(info));
            }
        }

        return list;

    }

    @Override
    public void RerollFully(GearItemData gear) {

        this.Mods = new ArrayList<StatModData>();

        StatMod mod = RandomUtils.weightedRandom(gear.GetBaseGearType().ChaosStats());

        this.create(gear, mod);

    }

    @Override
    public void RerollNumbers(GearItemData gear) {

    }

    @Override
    public void create(GearItemData gear, StatMod mod) {

        this.Mods = new ArrayList<StatModData>();
        StatModData moddata = StatModData.NewRandom(gear.getRarity(), mod);
        this.Mods.add(moddata);

    }
}
