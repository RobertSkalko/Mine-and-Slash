package com.robertx22.mine_and_slash.loot.blueprints.bases;

import com.robertx22.mine_and_slash.loot.blueprints.ItemBlueprint;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;

public abstract class BlueprintPart<T> {

    private T part;

    ItemBlueprint blueprint;

    public BlueprintPart(ItemBlueprint blueprint) {
        this.blueprint = blueprint;
    }

    protected abstract T generateIfNull();

    public void set(T t) {

        if (part == null) {
            part = t;
        } else {
            MMORPG.devToolsErrorLog("Do not override an already set and created part!");
        }
    }

    public T get() {

        if (part == null) {
            part = generateIfNull();
        }

        if (part == null) {
            MMORPG.devToolsErrorLog("Do not use a blueprint instance to make more than 1 item!");
        }

        return part;
    }

}
