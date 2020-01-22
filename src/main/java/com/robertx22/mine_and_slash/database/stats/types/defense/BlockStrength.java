package com.robertx22.mine_and_slash.database.stats.types.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.defense.BlockEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class BlockStrength extends Stat implements IStatEffects {

    public static final BlockStrength INSTANCE = new BlockStrength();

    @Override
    public StatGroup statGroup() {
        return StatGroup.Defenses;
    }

    @Override
    public String getIcon() {
        return "\u56DE";
    }

    @Override
    public String getIconPath() {
        return "block";
    }

    @Override
    public String locDescForLangFile() {
        return "Blocks part of DMG when blocking, if all damage is blocked, attack is canceled";
    }

    public static String GUID = "BlockStrength";

    public BlockStrength() {
        this.minimumValue = 0;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public boolean ScalesToLevel() {
        return true;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public String locNameForLangFile() {
        return "Block Strength";
    }

    @Override
    public IStatEffect getEffect() {
        return new BlockEffect();
    }
}
