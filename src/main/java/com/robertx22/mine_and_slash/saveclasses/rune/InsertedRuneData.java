package com.robertx22.mine_and_slash.saveclasses.rune;

import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatGroupData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Storable
public class InsertedRuneData extends StatGroupData implements ITooltipList {

    public InsertedRuneData() {

    }

    public InsertedRuneData(int level, String name, List<StatModData> mods, int rarity) {
        this.level = level;
        this.rune = name;
        this.Mods = mods;
        this.rarity = rarity;

    }

    @Store
    public String usedForRuneWord = "";

    @Store
    public int level = 1;

    @Store
    public String rune;

    @Store
    public int rarity;

    public RuneRarity getRarity() {
        return Rarities.Runes.get(rarity);
    }

    @Override
    public List<LevelAndStats> GetAllStats(int level) {

        List<StatModData> list = new ArrayList<StatModData>(Mods);
        list.addAll(this.Mods);

        return Arrays.asList(new LevelAndStats(list, this.level));
    }

    public int getAveragePercents() {
        int per = 0;

        for (StatModData mod : Mods) {
            per += mod.getPercent();

        }

        per = per / Mods.size();

        return per;

    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList();

        for (StatModData mod : this.Mods) {
            list.addAll(mod.GetTooltipString(info.withLevel(this.level)));
        }

        List<ITextComponent> list2 = new ArrayList();

        RuneRarity rar = this.getRarity();

        for (ITextComponent s : list) {
            list2.add(new StringTextComponent(rar.Color() + rune.toUpperCase() + rar.Color() + ": [")
                    .appendSibling(s)
                    .appendText(rar.Color() + " ]"));
        }

        return list2;
    }

}
