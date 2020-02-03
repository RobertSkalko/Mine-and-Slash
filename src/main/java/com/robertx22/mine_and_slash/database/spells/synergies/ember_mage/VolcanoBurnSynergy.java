package com.robertx22.mine_and_slash.database.spells.synergies.ember_mage;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage.VolcanoSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterTargetContext;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.BurnEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class VolcanoBurnSynergy extends Synergy<CasterTargetContext> {

    @Override
    public String GUID() {
        return "volcano_burn_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Applies debuff"));

        list.addAll(BurnEffect.INSTANCE.GetTooltipString(info));

        return list;
    }

    @Override

    public BaseSpell spellAffected() {
        return VolcanoSpell.getInstance();
    }

    @Override
    public void tryActivate(CasterTargetContext ctx) {
        PotionEffectUtils.apply(BurnEffect.INSTANCE, ctx.caster, ctx.target);
    }
}