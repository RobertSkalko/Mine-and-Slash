package com.robertx22.mine_and_slash.database.stats.types.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.resource.ResourceOnKill;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class PlusResourceOnKill extends Stat implements IStatEffects {

    public static PlusResourceOnKill HEALTH = new PlusResourceOnKill(Health.getInstance(), ResourceOnKill.HEALTH);
    public static PlusResourceOnKill MANA = new PlusResourceOnKill(Mana.getInstance(), ResourceOnKill.MANA);
    public static PlusResourceOnKill MAGIC_SHIELD = new PlusResourceOnKill(MagicShield.getInstance(), ResourceOnKill.MAGIC_SHIELD);

    Stat statRestored;
    ResourceOnKill effect;

    private PlusResourceOnKill(Stat statRestored, ResourceOnKill effect) {
        this.statRestored = statRestored;
        this.effect = effect;
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
        return "";
    }

    @Override
    public String locNameForLangFile() {
        return statRestored.locNameForLangFile() + " Restored on Kill";
    }

    @Override
    public String GUID() {
        return statRestored.GUID() + "_on_kill";
    }

    @Override
    public IStatEffect getEffect() {
        return effect;
    }
}
