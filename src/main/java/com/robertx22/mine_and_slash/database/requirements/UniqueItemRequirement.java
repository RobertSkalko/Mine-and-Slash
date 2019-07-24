package com.robertx22.mine_and_slash.database.requirements;

public class UniqueItemRequirement extends BaseRequirement {

    @Override
    public boolean meetsRequierment(GearRequestedFor requested) {

        if (requested.gearData.isUnique() == false) {
            return false;
        }

        return true;

    }

}
