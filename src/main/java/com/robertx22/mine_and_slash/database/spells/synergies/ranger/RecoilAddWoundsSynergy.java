package com.robertx22.mine_and_slash.database.spells.synergies.ranger;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ranger.RecoilShotSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.AfterDamageContext;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ranger.WoundsEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class RecoilAddWoundsSynergy extends Synergy<AfterDamageContext> {

    @Override
    public String GUID() {
        return "recoil_add_wounds_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Applies Wounds to enemy"));

        list.addAll(WoundsEffect.getInstance()
            .GetTooltipString(info));

        return list;
    }

    @Override

    public BaseSpell spellAffected() {
        return RecoilShotSpell.getInstance();
    }

    @Override
    public void tryActivate(AfterDamageContext ctx) {
        PotionEffectUtils.apply(WoundsEffect.getInstance(), ctx.caster, ctx.target);
    }
}

