package com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases;

import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface IStatModsContainer {

    static class LevelAndStats {

        public LevelAndStats(List<StatModData> mods, int level) {
            this.level = level;
            this.mods = mods;
        }

        public LevelAndStats(StatModData mod, int level) {
            this.level = level;
            this.mods = Arrays.asList(mod);
        }

        public int level = 1;

        public List<StatModData> mods = new ArrayList();

    }

    public List<LevelAndStats> GetAllStats(int level);
}
