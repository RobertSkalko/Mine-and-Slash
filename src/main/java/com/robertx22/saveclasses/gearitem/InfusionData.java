package com.robertx22.saveclasses.gearitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.SLOC;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.EntityLivingBase;

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

    public void success(EntityLivingBase player) {
	level();
	player.sendMessage(SLOC.chat("infusion_sucess"));
    }

    public void fail(EntityLivingBase player) {
	decrease();
	player.sendMessage(SLOC.chat("infusion_fail"));
    }

    public void majorFail(EntityLivingBase player) {
	decrease();
	decrease();

	player.sendMessage(SLOC.chat("infusion_major_fail"));
    }

    public void majorSuccess(EntityLivingBase player) {
	level();
	level();

	player.sendMessage(SLOC.chat("infusion_major_sucess"));
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
    public List<StatModData> GetAllStats(int level) {

	List<StatModData> list = new ArrayList<StatModData>(Mods);
	for (StatModData mod : list) {
	    mod.percent += bonusModPercent();
	}

	return list;
    }

    public int bonusModPercent() {
	return currentLevel * 10;
    }

    @Override
    public List<String> GetTooltipString(GearItemData gear) {

	List<String> list = new ArrayList<String>();

	if (isEmpty() == false) {

	    list.add(CLOC.word("infusion") + ": ");

	    for (StatModData data : this.GetAllStats(gear.level)) {
		list.addAll(data.GetTooltipString(gear.GetRarity().StatPercents(), gear.level, true));
	    }
	}

	return list;

    }

    public static HashMap<Integer, Float> All = new HashMap<Integer, Float>() {
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
