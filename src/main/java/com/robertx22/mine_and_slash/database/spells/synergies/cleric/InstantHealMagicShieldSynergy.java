package com.robertx22.mine_and_slash.database.spells.synergies.cleric;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.cleric.InstantHealSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.BeforeHealContext;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class InstantHealMagicShieldSynergy extends Synergy<BeforeHealContext> {

    @Override
    public String GUID() {
        return "instant_heal_magic_shield_synergy";
    }

    public static SpellCalcData CALC = SpellCalcData.base(0.75F, 5);

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Heals magic shield too"));

        list.addAll(CALC.GetTooltipString(info));

        return list;
    }

    @Override
    public BaseSpell spellAffected() {
        return InstantHealSpell.getInstance();
    }

    @Override
    public void tryActivate(BeforeHealContext ctx) {

        ResourcesData.Context heal = new ResourcesData.Context(ctx.casterData, ctx.caster,
            ResourcesData.Type.MAGIC_SHIELD,
            CALC.getCalculatedValue(ctx.casterData),
            ResourcesData.Use.RESTORE, spellAffected()
        );

        Load.Unit(ctx.target)
            .modifyResource(heal);
    }
}