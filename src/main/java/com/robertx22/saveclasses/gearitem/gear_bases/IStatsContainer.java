package com.robertx22.saveclasses.gearitem.gear_bases;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.saveclasses.gearitem.StatModData;

public interface IStatsContainer {

    static class LevelAndStats {

	public LevelAndStats(List<StatModData> mods, int level) {
	    this.level = level;
	    this.mods = mods;
	}

	public int level = 1;
	public List<StatModData> mods = new ArrayList();

    }

    public List<LevelAndStats> GetAllStats(int level);
}
