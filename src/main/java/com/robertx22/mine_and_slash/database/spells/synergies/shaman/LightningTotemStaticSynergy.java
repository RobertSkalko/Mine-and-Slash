package com.robertx22.mine_and_slash.database.spells.synergies.shaman;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.LightningTotemSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.AfterDamageContext;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.shaman.StaticEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class LightningTotemStaticSynergy extends Synergy<AfterDamageContext> {

    @Override
    public String GUID() {
        return "lightning_totem_static_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Applies debuff"));

        list.addAll(StaticEffect.INSTANCE.GetTooltipString(info));

        return list;
    }

    @Override

    public BaseSpell spellAffected() {
        return LightningTotemSpell.getInstance();
    }

    @Override
    public void tryActivate(AfterDamageContext ctx) {
        PotionEffectUtils.apply(StaticEffect.INSTANCE, ctx.caster, ctx.target);
    }
}