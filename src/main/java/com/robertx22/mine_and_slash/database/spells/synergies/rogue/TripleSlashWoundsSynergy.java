package com.robertx22.mine_and_slash.database.spells.synergies.rogue;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.rogue.TripleSlashSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.AfterDamageContext;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ranger.WoundsEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class TripleSlashWoundsSynergy extends Synergy<AfterDamageContext> {

    @Override
    public String GUID() {
        return "triple_slash_wounds_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Last hit applies Wounds"));
        list.addAll(WoundsEffect.getInstance()
            .GetTooltipString(info));

        return list;
    }

    @Override

    public BaseSpell spellAffected() {
        return TripleSlashSpell.getInstance();
    }

    @Override
    public void tryActivate(AfterDamageContext ctx) {
        PotionEffectUtils.apply(WoundsEffect.getInstance(), ctx.caster, ctx.target);
    }

}