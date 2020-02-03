package com.robertx22.mine_and_slash.database.spells.synergies.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.GeyserSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterAndSpellEntityContext;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class GeyserAttackSynergy extends Synergy<CasterAndSpellEntityContext> {

    @Override
    public String GUID() {
        return "geyser_attack_synergy";
    }

    public static SpellCalcData CALC = SpellCalcData.one(new ElementalSpellDamage(Elements.Water), 0.75F, 5);

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Attacks enemies inside."));

        list.addAll(CALC.GetTooltipString(info));

        return list;
    }

    @Override
    public BaseSpell spellAffected() {
        return GeyserSpell.getInstance();
    }

    @Override
    public void tryActivate(CasterAndSpellEntityContext ctx) {

        List<LivingEntity> entities = EntityFinder.start(
                ctx.caster, LivingEntity.class, ctx.spellEntity.getPositionVector()).radius(GeyserSpell.RADIUS).build();

        entities.forEach(x -> {

            int num = CALC.getCalculatedValue(ctx.casterData);

            SpellDamageEffect dmg = new SpellDamageEffect(ctx.caster, x, num, ctx.casterData, Load.Unit(x),
                                                          spellAffected()
            );

            dmg.Activate();

            SoundUtils.playSound(ctx.spellEntity, SoundEvents.ENTITY_DROWNED_HURT_WATER, 1, 1);

        });

    }
}