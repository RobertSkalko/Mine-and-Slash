package com.robertx22.mine_and_slash.database.stats.types.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.name_regex.StatNameRegex;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;

public class ImmuneToEffectStat extends Stat {

    public static ImmuneToEffectStat POISON = new ImmuneToEffectStat(Effects.POISON, "poison", "Poison");
    public static ImmuneToEffectStat WITHER = new ImmuneToEffectStat(Effects.WITHER, "wither", "Wither");
    public static ImmuneToEffectStat SLOW = new ImmuneToEffectStat(Effects.SLOWNESS, "slow", "Slow");
    public static ImmuneToEffectStat HUNGER = new ImmuneToEffectStat(Effects.HUNGER, "hunger", "Hunger");

    Effect effect;
    String id;
    String name;

    public ImmuneToEffectStat(Effect effect, String id, String name) {
        this.effect = effect;
        this.id = id;
        this.name = name;
    }

    public void onPotionAdded(Effect effect, LivingEntity en) {
        if (effect.getRegistryName()
            .equals(this.effect.getRegistryName())) {
            if (en.isPotionActive(effect)) {
                en.removePotionEffect(effect);
            }
        }
    }

    @Override
    public StatNameRegex getStatNameRegex() {
        return StatNameRegex.JUST_NAME;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public String locDescForLangFile() {
        return "This effect can't apply to you.";
    }

    @Override
    public String locNameForLangFile() {
        return "Immune to " + name + " Effect";
    }

    @Override
    public String GUID() {
        return this.id + "_immunity";
    }
}
