package com.robertx22.mine_and_slash.database.spells.synergies.divine;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.divine.PurifyingFiresSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class PurifyingFiresEnergizeSynergy extends OnDamageDoneSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Hits restore some energy to you."));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 0, 0);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 1, 4);
        c.setMaxLevel(8);
        return c;
    }

    @Override
    public void tryActivate(SpellDamageEffect ctx) {

        int num = this.getContext(ctx.source)
            .getConfigFor(this)
            .getCalc(Load.spells(ctx.source), this)
            .getCalculatedValue(ctx.sourceData, Load.spells(ctx.source), this);

        ResourcesData.Context ene = new ResourcesData.Context(ctx.sourceData, ctx.source, ResourcesData.Type.ENERGY,
            num,
            ResourcesData.Use.RESTORE
        );
        ctx.sourceData.getResources()
            .modify(ene);

    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public IAbility getRequiredAbility() {
        return PurifyingFiresSpell.getInstance();
    }

    @Override
    public String locNameForLangFile() {
        return "Energizing Hits";
    }

}

