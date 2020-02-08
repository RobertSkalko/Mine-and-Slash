package com.robertx22.mine_and_slash.loot.blueprints.bases;

import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.requirements.GearRequestedFor;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.loot.blueprints.ItemBlueprint;
import com.robertx22.mine_and_slash.saveclasses.gearitem.SetData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

public class SetPart extends BlueprintPart<Set> {

    public SetPart(ItemBlueprint blueprint, GearRequestedFor request) {
        super(blueprint);

        this.chance = ((GearRarity) blueprint.rarity.get()).SetChance();
        this.request = request;
        this.canBeNull = true;
    }

    GearRequestedFor request;
    public float chance;

    public SetData getSetData() {

        if (get() == null) {
            return null;
        }

        SetData data = new SetData();
        data = data.generate(get());

        return data;

    }

    @Override
    protected Set generateIfNull() {
        if (RandomUtils.roll(chance)) {
            return SlashRegistry.Sets()
                    .getWrapped()
                    .errorIfNothingLeft(false)
                    .of(x -> x.requirements().satisfiesAllRequirements(request))
                    .random();
        } else {
            return null;
        }

    }
}


