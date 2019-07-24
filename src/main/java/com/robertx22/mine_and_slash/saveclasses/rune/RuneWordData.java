package com.robertx22.mine_and_slash.saveclasses.rune;

import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatsContainer;
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
public class RuneWordData implements IStatsContainer, ITooltipList {

    @Store
    public String name;

    @Store
    public int level;

    @Store
    public List<StatModData> Mods = new ArrayList<StatModData>();

    @Store
    public int rarity;

    @Override
    public List<LevelAndStats> GetAllStats(int level) {
        return Arrays.asList(new LevelAndStats(Mods, this.level));
    }

    public RuneWordData() {

    }

    public RuneWordData(RunesData data, RuneWord word) {

        level = data.getAverageLevel();

        int percent = data.getAveragePercents();

        name = word.GUID();

        rarity = data.getAverageRarity();

        Mods.clear();

        for (StatMod mod : word.mods()) {
            Mods.add(StatModData.Load(mod, percent));
        }

    }

    public RuneWord getRuneWord() {
        return SlashRegistry.RuneWords().get(name);
    }

    public RuneRarity getRarity() {
        return Rarities.Runes.get(rarity);
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList();

        RuneRarity rar = this.getRarity();

        RuneWord word = getRuneWord();

        list.add(new StringTextComponent(rar.Color() + "Rune Word: " + word.GUID()
                .toUpperCase()));
        // list.add("");

        for (StatModData mod : this.Mods) {
            list.addAll(mod.GetTooltipString(info));
        }

        return list;
    }

}
