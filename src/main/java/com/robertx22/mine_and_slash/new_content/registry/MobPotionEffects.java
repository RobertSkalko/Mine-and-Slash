package com.robertx22.mine_and_slash.new_content.registry;

import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;

import java.util.ArrayList;
import java.util.List;

public class MobPotionEffects {

    private static List<Effect> all = new ArrayList<>();

    public static Effect getRandom() {

        if (all.isEmpty()) {
            all.add(Effects.SPEED);
            all.add(Effects.STRENGTH);
            all.add(Effects.REGENERATION);

        }

        return RandomUtils.randomFromList(all);

    }

}
