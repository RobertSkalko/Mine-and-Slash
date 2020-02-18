package com.robertx22.mine_and_slash.database.spells.synergies.ranger;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ranger.ImbueSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.AfterDamageContext;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ranger.HunterInstinctEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class RecoilAddHunterSynergy extends Synergy<AfterDamageContext> {

    @Override
    public String GUID() {
        return "recoil_add_hunter_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Gives Hunter Instinct if it hits a target"));

        list.addAll(HunterInstinctEffect.getInstance()
            .getEffectTooltip(info));

        return list;
    }

    @Override

    public BaseSpell spellAffected() {
        return ImbueSpell.getInstance();
    }

    @Override
    public void tryActivate(AfterDamageContext ctx) {
        if (ctx.dmg.isCriticalHit()) {
            PotionEffectUtils.reApplyToSelf(HunterInstinctEffect.getInstance(), ctx.caster);
        }
    }
}
