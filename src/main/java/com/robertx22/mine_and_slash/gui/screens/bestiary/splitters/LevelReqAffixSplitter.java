package com.robertx22.mine_and_slash.gui.screens.bestiary.splitters;

import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.gui.screens.bestiary.BestiaryEntry;
import com.robertx22.mine_and_slash.gui.screens.bestiary.groups.BestiaryGroup;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LevelReqAffixSplitter extends BaseSplitter<BaseAffix> {

    public LevelReqAffixSplitter(BestiaryGroup group) {
        super(group);
    }

    @Override
    public List<BestiaryEntry> split(int lvl) {

        List<BestiaryEntry> entries = new ArrayList<>();

        List<BaseAffix> affixes = group.getAll(lvl);

        Function<BaseAffix, LevelRequirement> byLevel = x -> x.getLevelRequirement();

        Map<LevelRequirement, List<BaseAffix>> collect = affixes.stream()
            .collect(Collectors.groupingBy(byLevel));

        collect.entrySet()
            .forEach(entry -> {

                entries.add(new BestiaryEntry.Splitter(CLOC.translate(entry.getKey()
                    .GetTooltipString(new TooltipInfo())
                    .get(0))));

                entry.getValue()
                    .forEach(r -> entries.add(new BestiaryEntry.Item(group.createStack(lvl, r), group)));

            });

        return entries;
    }
}
