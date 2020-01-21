package com.robertx22.mine_and_slash.database.talent_tree;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;

public abstract class BasePerkEffect implements ITooltipList {

    protected boolean isGameChanger = false;
    public boolean isStart = false;

    public abstract void render(int x, int y);

    public abstract PerkType getPerkType();

    public boolean isGameChanger() {
        return isGameChanger;
    }

}



