package com.robertx22.mine_and_slash.database.stats.types.class_based;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.effects.class_based.RogueStealthEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.ArrayList;
import java.util.List;

public class RogueStealth extends Trait implements IStatEffects {
    private RogueStealth() {
    }

    public static RogueStealth getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public Elements getElement() {
        return Elements.Elemental;
    }

    @Override
    public String locDescForLangFile() {
        return "Stealth is removed on first damage done.";
    }

    @Override
    public String locNameForLangFile() {
        return "Stealth";
    }

    @Override
    public String GUID() {
        return "rogue_stealth";
    }

    @Override
    public IStatEffect getEffect() {
        return RogueStealthEffect.getInstance();
    }

    @Override
    public List<StatMod> getStats() {
        return new ArrayList<>();
    }

    private static class SingletonHolder {
        private static final RogueStealth INSTANCE = new RogueStealth();
    }
}
