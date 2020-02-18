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

public class ImbueCritAddHunterSynergy extends Synergy<AfterDamageContext> {

    @Override
    public String GUID() {
        return "imbue_crit_add_hunter_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Gives Hunter Instinct on criticals."));

        list.addAll(HunterInstinctEffect.getInstance()
            .GetTooltipString(info));

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