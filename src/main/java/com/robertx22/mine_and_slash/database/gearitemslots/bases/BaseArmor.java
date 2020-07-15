package com.robertx22.mine_and_slash.database.gearitemslots.bases;

public abstract class BaseArmor extends BaseGearType {

    @Override
    public final int Weight() {
        return super.Weight() / 3;
    }

}
