package com.robertx22.mine_and_slash.loot.blueprints.bases;

import com.robertx22.mine_and_slash.loot.blueprints.ItemBlueprint;

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

            try {
                throw new Exception("Do not use a blueprint instance to make more than 1 item!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public T get() {

        if (part == null) {
            part = generateIfNull();
        }

        if (part == null) {
            try {
                throw new Exception("Variable is somehow null even though it was supposed to be generated!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return part;
    }

}
