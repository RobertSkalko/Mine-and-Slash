package com.robertx22.mine_and_slash.database.stats.types;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.types.traits.major_arcana.INameSuffix;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips.TooltipStatInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTrait extends Stat {

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public boolean IsShownOnStatGui() {
        return false;
    }

    @Override
    public void CalcVal(StatData data, EntityCap.UnitData Source) {

        if (data.Flat > 0) {
            data.val = 1;
        } else {
            data.val = 0;

        }

    }

    @OnlyIn(Dist.CLIENT)
    public ITextComponent TraitText(TooltipStatInfo info) {
        Stat basestat = info.stat;
        ITextComponent comp = Styles.GREENCOMP()
                .appendSibling(new StringTextComponent(" " + SYMBOL + " ").appendSibling(basestat.locName()));

        if (basestat instanceof INameSuffix) {
            INameSuffix suffix = (INameSuffix) basestat;
            comp.appendText(TextFormatting.LIGHT_PURPLE + " " + MAJOR_ARCANA + "  (");
            comp.appendSibling(suffix.locSuffix().appendText(") " + MAJOR_ARCANA));
        }

        return comp;

    }

    public static String MAJOR_ARCANA = "\u25C8";

    public static String SYMBOL = "\u32A3";

    @OnlyIn(Dist.CLIENT)
    @Override
    public List<ITextComponent> getTooltipList(TooltipStatInfo info) {
        List<ITextComponent> list = new ArrayList<ITextComponent>();
        Stat basestat = info.stat;
        ITextComponent text = new StringTextComponent("");

        text = TraitText(info);

        list.add(text);

        if (info.useInDepthStats()) {
            if (basestat instanceof Trait) {
                Trait trait = (Trait) basestat;

                for (StatModData moddata : trait.getStatsMods()) {
                    TooltipInfo newinfo = info.tooltipInfo.withLevel(info.tooltipInfo.unitdata.getLevel());
                    newinfo.minmax = new MinMax(trait.percent(), trait.percent());
                    list.addAll(moddata.GetTooltipString(newinfo));
                }
            }
        }

        if (info.shouldShowDescriptions()) {
            if (locDescForLangFile().isEmpty() == false) {
                list.addAll(info.stat.getCutDescTooltip());
            }

        }

        return list;
    }
}
