package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.generated.WeaponDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IGearPartTooltip;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.NumberUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Storable
public class BaseStatsData implements IGearPartTooltip, IRerollable, IStatsContainer {

    @Store
    public List<Integer> percents = new ArrayList<Integer>();

    @Store
    public String gear_type = "";

    @Override
    public void RerollFully(GearItemData gear) {

        percents = new ArrayList<>();
        this.gear_type = gear.gear_type;

        gear.GetBaseGearType()
            .BaseStats()
            .forEach(x -> percents.add(getMinMax(gear).random()));

    }

    @Override
    public void RerollNumbers(GearItemData gear) {
        RerollFully(gear);
    }

    static TextFormatting NUMBER_COLOR = TextFormatting.BLUE;
    static TextFormatting TEXT_COLOR = TextFormatting.GRAY;

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info, GearItemData gear) {

        List<ExactStatData> all = GetAllStats(gear);

        List<ITextComponent> list = new ArrayList<ITextComponent>();
        list.add(new StringTextComponent(" "));

        ITextComponent physdmg = null;
        ITextComponent eledmg = null;
        ITextComponent critchance = null;

        String eleDmgs = "";

        for (ExactStatData exactStatData : all) {
            Stat stat = exactStatData.getStat();

            String perc = stat.IsPercent() || !exactStatData.getType()
                .isFlat() ? "%" : "";

            if (stat instanceof WeaponDamage) {
                if (stat.getElement() == Elements.Physical) {
                    physdmg = new SText(TEXT_COLOR + CLOC.translate(stat.locName()) + ": " + NUMBER_COLOR +
                        NumberUtils.format(exactStatData.getFirstValue()) + "-" + NumberUtils.format(exactStatData.getSecondValue()));

                } else {

                    String dot = ",";
                    if (eleDmgs.length() == 0) {
                        dot = "";
                    }
                    eleDmgs += dot + " " + stat.getElement().format +
                        NumberUtils.format(exactStatData.getFirstValue()) + "-" + NumberUtils.format(exactStatData.getSecondValue());
                }
            } else {

                ITextComponent comp = new SText(TEXT_COLOR + CLOC.translate(stat.locName()) + ": " + NUMBER_COLOR + NumberUtils.format(exactStatData.getFirstValue()) + perc);

                if (stat instanceof CriticalHit) {
                    critchance = comp;
                } else {
                    list.add(comp);
                }

            }
        }
        if (eleDmgs.length() > 0) {
            eledmg = new SText(TEXT_COLOR + "Elemental Damage:" + eleDmgs);
        }

        if (physdmg != null) {
            list.add(physdmg);
        }
        if (eledmg != null) {
            list.add(eledmg);
        }
        if (critchance != null) {
            list.add(critchance);
        }

        return list;

    }

    @Override
    public Part getPart() {
        return Part.PRIMARY_STATS;
    }

    @Override
    public boolean isBaseStats() {
        return true;
    }

    @Override
    public List<ExactStatData> GetAllStats(GearItemData gear) {

        List<ExactStatData> local = new ArrayList<>();
        List<ExactStatData> all = gear.GetAllStats(false, true);

        int i = 0;

        for (StatModifier mod : SlashRegistry.GearTypes()
            .get(gear_type)
            .BaseStats()) {
            local.add(mod.ToExactStat(percents.get(i)));
            i++;
        }

        // add up flats first
        all.forEach(x -> {

            if (x.shouldBeAddedToLocalStats(gear) && x.getType()
                .isFlat()) {

                Optional<ExactStatData> opt = local.stream()
                    .filter(t -> t.getStat() == x.getStat())
                    .findFirst();

                if (opt.isPresent()) {
                    opt.get()
                        .add(x);
                } else {
                    local.add(x);
                }
            }
        });

        // now increase all flats by local increases
        all.stream()
            .filter(x ->
                x.shouldBeAddedToLocalStats(gear) && x.getType()
                    == StatModTypes.LOCAL_INCREASE)
            .forEach(s -> {

                ExactStatData flatLocal = local.stream()
                    .filter(x -> x.getStat()
                        .GUID()
                        .equals(s.getStat()
                            .GUID()))
                    .findFirst()
                    .get();

                flatLocal.percentIncrease += s.getFirstValue();

            });

        local.forEach(x -> x.increaseByAddedPercent());

        return local;

    }
}
