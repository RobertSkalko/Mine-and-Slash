package com.robertx22.mine_and_slash.database.spells.synergies.ranger;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ranger.TripleShotSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterContext;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ranger.HunterInstinctEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class TripleShotConsumeHunterSynergy extends Synergy<CasterContext> {

    @Override
    public String GUID() {
        return "triple_shot_consume_hunter_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Consumes Hunter Instinct buff stacks to remove cooldown."));

        return list;
    }
    
    @Override

    public BaseSpell spellAffected() {
        return TripleShotSpell.getInstance();
    }

    public boolean canActivate(LivingEntity caster) {
        return PotionEffectUtils.getStacks(caster, HunterInstinctEffect.getInstance()) > 0;
    }

    @Override
    public void tryActivate(CasterContext ctx) {
        PotionEffectUtils.reduceStacks(ctx.caster, HunterInstinctEffect.getInstance(), 1);
    }
}