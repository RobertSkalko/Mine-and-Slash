package com.robertx22.mine_and_slash.database.spells.synergies.shaman;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.ThunderDashSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ThunderDashEnergySynergy extends OnDamageDoneSynergy {

    @Override
    public String GUID() {
        return "thunder_dash_energy_synergy";
    }

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Restores energy for each mob hit."));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {

    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 1, 6);
        c.setMaxLevel(6);
        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return AbilityPlace.upFrom(ThunderDashSpell.getInstance());
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return ThunderDashSpell.getInstance();
    }

    @Override
    public void tryActivate(SpellDamageEffect ctx) {

        float energyrestored = getCalcVal(ctx.source);

        ResourcesData.Context ene = new ResourcesData.Context(ctx.sourceData, ctx.source, ResourcesData.Type.ENERGY,
            energyrestored, ResourcesData.Use.RESTORE
        );

        ctx.sourceData
            .getResources()
            .modify(ene);
    }
}