package com.robertx22.mine_and_slash.database.spells.synergies.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.ThornArmorSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterTargetContext;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornArmorEffect;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornsEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class ThornArmorThornsSynergy extends Synergy<CasterTargetContext> {

    @Override
    public String GUID() {
        return "thorn_armor_thorns_synergy";
    }

    static int CHANCE = 25;

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Attacking or being attacked has a  "));
        list.add(new StringTextComponent(CHANCE + "% of applying effect."));

        list.addAll(ThornsEffect.INSTANCE.GetTooltipString(info));

        return list;
    }

    @Override
    public BaseSpell spellAffected() {
        return ThornArmorSpell.getInstance();
    }

    @Override
    public void tryActivate(CasterTargetContext ctx) {

        if (RandomUtils.roll(CHANCE)) {
            if (PotionEffectUtils.has(ctx.caster, ThornArmorEffect.INSTANCE)) {
                PotionEffectUtils.apply(ThornsEffect.INSTANCE, ctx.caster, ctx.target);
            }
            if (PotionEffectUtils.has(ctx.target, ThornArmorEffect.INSTANCE)) {
                PotionEffectUtils.apply(ThornsEffect.INSTANCE, ctx.target, ctx.caster);
            }
        }
    }
}
