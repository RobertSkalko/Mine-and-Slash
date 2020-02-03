package com.robertx22.mine_and_slash.database.spells.synergies.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.FrostballSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.AfterDamageContext;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.FrostEssenceEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class FrostballFrostEssenceGenSynergy extends Synergy<AfterDamageContext> {

    @Override
    public String GUID() {
        return "frostball_frost_essence_gen_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Criticals give Frost Essence"));

        list.addAll(FrostEssenceEffect.INSTANCE.GetTooltipString(info));

        return list;
    }

    @Override
    public BaseSpell spellAffected() {
        return FrostballSpell.getInstance();
    }

    @Override
    public void tryActivate(AfterDamageContext ctx) {
        if (ctx.dmg.isCriticalHit()) {
            PotionEffectUtils.reApplyToSelf(FrostEssenceEffect.INSTANCE, ctx.caster);
        }
    }
}
