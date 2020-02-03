package com.robertx22.mine_and_slash.database.spells.synergies.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.BlizzardSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.BeforeDamageContext;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.FrostEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class BlizzardFrostSynergy extends Synergy<BeforeDamageContext> {

    @Override
    public String GUID() {
        return "blizzard_frost_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Applies Frost"));

        list.addAll(FrostEffect.INSTANCE.GetTooltipString(info));

        return list;
    }

    @Override

    public BaseSpell spellAffected() {
        return BlizzardSpell.getInstance();
    }

    @Override
    public void tryActivate(BeforeDamageContext ctx) {

        PotionEffectUtils.apply(FrostEffect.INSTANCE, ctx.caster, ctx.target);

    }
}