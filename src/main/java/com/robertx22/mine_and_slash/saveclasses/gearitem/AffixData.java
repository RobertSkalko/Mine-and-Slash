package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.*;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Storable
public abstract class AffixData implements IRerollable, IGearPartTooltip, IStatsContainer {

    @Store
    public Integer percent = 0;

    @Store
    public String baseAffix;

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info, GearItemData gear) {
        info.minmax = getMinMax(gear);

        BaseAffix affix = BaseAffix();

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        list.add(Styles.GRAYCOMP()
            .appendSibling(Words.Suffix.locName()
                .appendText(": ")
                .appendSibling(affix.locName())));

        GetAllStats(gear).forEach(x -> list.addAll(x.GetTooltipString(info)));

        return list;

    }

    @Override
    public IGearPart.Part getPart() {
        return IGearPart.Part.AFFIX;
    }

    @Override
    public void RerollNumbers(GearItemData gear) {
        percent = getMinMax(gear)
            .random();

    }

    public final BaseAffix BaseAffix() {
        return SlashRegistry.Affixes()
            .get(baseAffix);
    }

    @Override
    public List<ExactStatData> GetAllStats(GearItemData gear) {
        return this.BaseAffix()
            .StatMods()
            .stream()
            .map(x -> x.ToExactStat(percent))
            .collect(Collectors.toList());

    }
}
