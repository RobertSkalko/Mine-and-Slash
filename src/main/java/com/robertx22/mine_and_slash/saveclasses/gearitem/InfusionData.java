package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Storable
public class InfusionData extends StatGroupData implements ITooltipList {

    public InfusionData() {

    }

    static int maxLevel = 10;

    @Store
    public int currentLevel = 1;

    public boolean isEmpty() {
        return this.Mods.size() == 0;
    }

    public void success() {
        level();

    }

    public void fail() {
        decrease();

    }

    public void majorFail() {
        decrease();
        decrease();

    }

    public void majorSuccess() {
        level();
        level();

    }

    public void level() {
        if (currentLevel < maxLevel) {
            currentLevel++;
        }

    }

    public void decrease() {
        if (currentLevel > 0) {
            currentLevel--;
        }
    }

    @Override
    public List<LevelAndStats> GetAllStats(int level) {

        List<StatModData> list = new ArrayList<StatModData>(Mods);
        for (StatModData mod : list) {
            mod.setPercent(mod.getPercent() + bonusModPercent());
        }

        return Arrays.asList(new LevelAndStats(list, level));
    }

    public int bonusModPercent() {
        return currentLevel * 10;
    }

    public boolean canUpgrade() {
        return this.currentLevel < this.maxLevel;
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        if (isEmpty() == false) {

            list.add(Styles.LIGHT_PURPLECOMP()
                    .appendSibling(new StringTextComponent("+" + this.currentLevel + " ").appendSibling(Words.Infusion
                            .locName()
                            .appendText(": "))));

            for (LevelAndStats part : this.GetAllStats(info.level)) {
                for (StatModData data : part.mods) {

                    list.addAll(data.GetTooltipString(info));
                }
            }

        }

        return list;

    }

    public float getChance() {

        if (chancePerLevel.containsKey(currentLevel)) {
            return chancePerLevel.get(currentLevel);
        }

        return 1F;

    }

    public static HashMap<Integer, Float> chancePerLevel = new HashMap<Integer, Float>() {
        {
            {
                put(1, 100F);
                put(2, 98F);
                put(3, 95F);
                put(4, 90F);
                put(5, 80F);
                put(6, 70F);
                put(7, 60F);
                put(8, 50F);
                put(9, 25F);
                put(10, 10F);

            }
        }
    };
}
