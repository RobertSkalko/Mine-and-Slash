package com.robertx22.mine_and_slash.database.spells.spell_tree;

import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.talent_tree.BasePerkEffect;
import com.robertx22.mine_and_slash.database.talent_tree.PerkType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class SynergyPerkEffect extends BasePerkEffect {

    public Synergy synergy;

    public SynergyPerkEffect(Synergy synergy) {
        this.synergy = synergy;
    }

    @Override
    public void render(int x, int y) {

    }

    @Override
    public PerkType getPerkType() {
        return null;
    }

    public SynergyPerkEffect setGameChanger() {
        this.isGameChanger = true;
        return this;
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        if (synergy != null) {
            list.addAll(synergy.GetTooltipString(info));
        }

        return list;
    }
}
