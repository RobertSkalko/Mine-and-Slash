package com.robertx22.mine_and_slash.database.spells.synergies.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.FrostballSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.AfterDamageContext;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.ColdEssenceEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class FrostballFrostEssenceGenSynergy extends Synergy<AfterDamageContext> {

    @Override
    public String GUID() {
        return "frostball_frost_essence_gen_synergy";
    }

    static int CHANCE = 50;

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(CHANCE + " percent chance for attacks give Frost Essence"));

        list.addAll(ColdEssenceEffect.INSTANCE.GetTooltipString(info));

        return list;
    }

    @Override
    public BaseSpell spellAffected() {
        return FrostballSpell.getInstance();
    }

    @Override
    public void tryActivate(AfterDamageContext ctx) {
        if (RandomUtils.roll(CHANCE)) {
            PotionEffectUtils.reApplyToSelf(ColdEssenceEffect.INSTANCE, ctx.caster);
        }
    }
}
