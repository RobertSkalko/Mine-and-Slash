package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.*;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import info.loenwind.autosave.annotations.Factory;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Storable
public class AffixData implements IRerollable, IGearPartTooltip, IStatsContainer {

    @Store
    public Integer percent = 0;

    @Store
    public String baseAffix;

    @Store
    public BaseAffix.Type affixType;

    @Store
    public boolean is_socket = false;

    public AffixData(BaseAffix.Type type) {
        this.affixType = type;
    }

    @Factory
    private AffixData() {
    }

    public boolean isSocketAndEmpty() {
        return this.is_socket && percent < 1;
    }

    public BaseAffix.Type getAffixType() {
        return affixType;
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info, GearItemData gear) {

        BaseAffix affix = BaseAffix();

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        GetAllStats(gear).forEach(x -> list.addAll(x.GetTooltipString(info)));

        return list;

    }

    public BaseAffix getAffix() {
        return SlashRegistry.Affixes()
            .get(this.baseAffix);
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

        if (this.is_socket) {
            return Arrays.asList();
        }

        return this.BaseAffix()
            .StatMods()
            .stream()
            .map(x -> x.ToExactStat(percent))
            .collect(Collectors.toList());

    }

    public void create(GearItemData gear, BaseAffix suffix) {
        baseAffix = suffix.GUID();
        RerollNumbers(gear);
    }

    @Override
    public void RerollFully(GearItemData gear) {

        if (gear.getAllAffixes()
            .stream()
            .filter(x -> x.is_socket)
            .count() < 3 && RandomUtils.roll(5)) {
            this.is_socket = true;
        } else {

            BaseAffix affix = SlashRegistry.Affixes()
                .getFilterWrapped(x -> x.type == getAffixType() && !gear.containsAffix(x))
                .random();

            this.create(gear, affix);
        }
    }
}
