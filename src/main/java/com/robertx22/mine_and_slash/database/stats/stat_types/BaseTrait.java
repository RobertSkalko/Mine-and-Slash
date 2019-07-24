package com.robertx22.mine_and_slash.database.stats.stat_types;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.major_arcana.INameSuffix;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import net.minecraft.client.gui.screen.Screen;
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
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public boolean IsShownOnTooltip() {
        return false;
    }

    @Override
    public void CalcVal(StatData data, EntityCap.UnitData Source) {

        if (data.Flat > 0) {
            data.Value = 1;
        } else {
            data.Value = 0;

        }

    }

    @OnlyIn(Dist.CLIENT)
    public ITextComponent TraitText(StatModData data) {
        StatMod mod = data.getStatMod();
        Stat basestat = mod.GetBaseStat();
        ITextComponent comp = Styles.GREENCOMP()
                .appendSibling(new StringTextComponent(" * ").appendSibling(basestat.locName()));

        if (basestat instanceof INameSuffix) {
            INameSuffix suffix = (INameSuffix) basestat;
            comp.appendText(TextFormatting.LIGHT_PURPLE + " * (");
            comp.appendSibling(suffix.locSuffix().appendText(") *"));
        }

        return comp;

    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public List<ITextComponent> getTooltipList(TooltipInfo info, StatModData data) {
        List<ITextComponent> list = new ArrayList<ITextComponent>();
        StatMod mod = data.getStatMod();
        Stat basestat = mod.GetBaseStat();
        ITextComponent text = new StringTextComponent("");

        text = TraitText(data);

        list.add(text);

        if (Screen.hasShiftDown()) {
            if (basestat instanceof Trait) {
                Trait trait = (Trait) basestat;

                for (StatModData motdata : trait.getStatsMods()) {
                    TooltipInfo newinfo = info.withLevel(info.unitdata.getLevel());
                    newinfo.minmax = new MinMax(trait.percent(), trait.percent());
                    list.addAll(motdata.GetTooltipString(newinfo));
                }
            }
        }

        if (Screen.hasAltDown()) {
            if (locDescForLangFile().isEmpty() == false) {
                list.add(new StringTextComponent(TextFormatting.BLUE + "[").appendSibling(this
                        .locDesc()).appendText("]"));
            }

        }

        return list;
    }
}
