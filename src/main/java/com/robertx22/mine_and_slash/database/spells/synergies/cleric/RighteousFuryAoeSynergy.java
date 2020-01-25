package com.robertx22.mine_and_slash.database.spells.synergies.cleric;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.cleric.RighteousFurySpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.DamageContext;
import com.robertx22.mine_and_slash.potion_effects.cleric.RighteousFuryEffect;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.BlazingInfernoEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Tooltip;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class RighteousFuryAoeSynergy extends Synergy<DamageContext> {

    @Override
    public String GUID() {
        return "righteous_fury_aoe_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(
                "At " + RighteousFuryEffect.INSTANCE.getMaxStacks() + " stacks, buff explodes in an area attack."));

        Tooltip.addEmpty(list);

        list.addAll(BlazingInfernoEffect.CALC.GetTooltipString(info));

        return list;
    }

    @Override

    public BaseSpell spellAffected() {
        return new RighteousFurySpell();
    }

    @Override
    public void tryActivate(DamageContext ctx) {

    }
}
