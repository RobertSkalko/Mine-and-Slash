package com.robertx22.mine_and_slash.database.spells.synergies.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.ThornArmorSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterTargetContext;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.MajorThornsEffect;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornsEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class ThornBushMajorThornsSynergy extends Synergy<CasterTargetContext> {

    @Override
    public String GUID() {
        return "thorn_bush_major_thorns_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Turns Minor thorns into Major thorns"));

        list.addAll(MajorThornsEffect.INSTANCE.GetTooltipString(info));

        return list;
    }

    @Override
    public BaseSpell spellAffected() {
        return ThornArmorSpell.getInstance();
    }

    @Override
    public void tryActivate(CasterTargetContext ctx) {
        if (PotionEffectUtils.has(ctx.target, ThornsEffect.INSTANCE)) {

            PotionEffectUtils.reduceStacks(ctx.target, ThornsEffect.INSTANCE);

            PotionEffectUtils.apply(MajorThornsEffect.INSTANCE, ctx.caster, ctx.target);
        }
    }
}
