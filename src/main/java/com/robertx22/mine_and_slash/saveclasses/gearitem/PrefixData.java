package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.GearRequestedFor;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ICreateSpecific;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import info.loenwind.autosave.annotations.Storable;
import net.minecraft.util.text.ITextComponent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Storable
public class PrefixData extends AffixData implements ICreateSpecific<Prefix>, Serializable, ITooltipList, IRerollable {

    private static final long serialVersionUID = -110285627065158395L;

    public PrefixData() {

    }

    public PrefixData(GearItemData gear, String affixname, List<Integer> percents) {
        super();
        this.baseAffix = affixname;
        this.percents = percents;

    }

    @Override
    public void RerollFully(GearItemData gear) {

        Prefix prefix = SlashRegistry.Prefixes()
                .getWrapped()
                .allThatMeetRequirement(new GearRequestedFor(gear))
                .random();

        this.create(gear, prefix);

    }

    @Override
    public void RerollNumbers(GearItemData gear) {

        percents = new ArrayList<Integer>();

        for (StatMod mod : BaseAffix().StatMods()) {
            percents.add(gear.getRarity().StatPercents().genPercent());

        }

    }

    @Override
    public void create(GearItemData gear, Prefix prefix) {

        baseAffix = prefix.GUID();
        RerollNumbers(gear);
    }

    @Override
    public BaseAffix BaseAffix() {
        return SlashRegistry.Prefixes().get(baseAffix);
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        BaseAffix affix = BaseAffix();

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        list.add(Styles.GRAYCOMP()
                .appendSibling(Words.Prefix.locName()
                        .appendText(": ")
                        .appendSibling(affix.locName())));

        for (LevelAndStats part : this.GetAllStats(info.level)) {
            for (StatModData data : part.mods) {
                list.addAll(data.GetTooltipString(info));
            }
        }

        return list;

    }

}
