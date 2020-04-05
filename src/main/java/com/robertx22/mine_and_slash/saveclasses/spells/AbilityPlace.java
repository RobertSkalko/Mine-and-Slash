package com.robertx22.mine_and_slash.saveclasses.spells;

import com.google.common.base.Preconditions;

public class AbilityPlace {

    public int x;
    public int y;

    public static int MAX_X = 9;
    public static int MAX_Y = 9;

    public AbilityPlace(int x, int y) {

        Preconditions.checkArgument(x <= MAX_X && x >= 0);
        Preconditions.checkArgument(y <= MAX_Y && y >= 0);

        this.x = x;
        this.y = y;

    }

    public AbilityPlace upFrom(IAbility other) {
        return new AbilityPlace(other.getAbilityPlace().x, other.getAbilityPlace().y + 1);
    }

}
