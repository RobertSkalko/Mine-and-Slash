package com.robertx22.mine_and_slash.database.spells.synergies.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.WhirlpoolSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterTargetContext;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.ShiverEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class WhirlpoolShiverSynergy extends Synergy<CasterTargetContext> {

    @Override
    public String GUID() {
        return "whirlpool_shiver_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Applies Shiver"));

        list.addAll(ShiverEffect.INSTANCE.GetTooltipString(info));

        return list;
    }

    @Override

    public BaseSpell spellAffected() {
        return WhirlpoolSpell.getInstance();
    }

    @Override
    public void tryActivate(CasterTargetContext ctx) {

        PotionEffectUtils.apply(ShiverEffect.INSTANCE, ctx.caster, ctx.target);

    }
}