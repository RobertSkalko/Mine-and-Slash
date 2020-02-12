package com.robertx22.mine_and_slash.database.spells.synergies.shaman;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.ThunderDashSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.AfterDamageContext;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class ThunderDashEnergySynergy extends Synergy<AfterDamageContext> {

    @Override
    public String GUID() {
        return "thunder_dash_energy_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Restores energy for each mob hit."));

        list.addAll(CALC.GetTooltipString(info));

        return list;
    }

    public static SpellCalcData CALC = SpellCalcData.one(new ElementalSpellDamage(Elements.Thunder), 0.05F, 1);

    @Override
    public BaseSpell spellAffected() {
        return ThunderDashSpell.getInstance();
    }

    @Override
    public void tryActivate(AfterDamageContext ctx) {

        float energyrestored = CALC.getCalculatedValue(ctx.casterData);

        ResourcesData.Context ene = new ResourcesData.Context(ctx.casterData, ctx.caster, ResourcesData.Type.ENERGY,
                                                              energyrestored, ResourcesData.Use.RESTORE
        );

        Load.Unit(ctx.caster).getResources().modify(ene);
    }
}