package com.robertx22.mine_and_slash.database.talent_tree.data;

import com.robertx22.mine_and_slash.database.stats.types.game_changers.BaseGameChangerTrait;
import com.robertx22.mine_and_slash.database.talent_tree.PerkEffectBuilder;
import com.robertx22.mine_and_slash.database.talent_tree.PerkType;
import com.robertx22.mine_and_slash.registry.SlashRegistry;

public class TraitPerkEffects {

    public static void create() {
        SlashRegistry.Stats()
                .getFiltered(x -> x instanceof BaseGameChangerTrait)
                .forEach(x -> create((BaseGameChangerTrait) x));

    }

    private static void create(BaseGameChangerTrait trait) {
        PerkEffectBuilder.trait(trait.GUID(), trait, PerkType.MAJOR).setGameChanger();
    }

}
