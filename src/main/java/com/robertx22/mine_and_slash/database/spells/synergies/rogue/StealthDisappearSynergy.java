package com.robertx22.mine_and_slash.database.spells.synergies.rogue;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.rogue.StealthSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterContext;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class StealthDisappearSynergy extends Synergy<CasterContext> {

    @Override
    public String GUID() {
        return "stealth_disappear_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Clears you as target from mobs nearby"));
        list.add(new StringTextComponent("This allows stealth to be useful while in combat."));

        return list;
    }

    @Override

    public BaseSpell spellAffected() {
        return StealthSpell.getInstance();
    }

    @Override
    public void tryActivate(CasterContext ctx) {
        removeTargetingFromNearbyMobs(ctx.caster);
    }

    public static void removeTargetingFromNearbyMobs(LivingEntity caster) {
        EntityFinder.start(caster, MobEntity.class, caster.getPositionVector())
            .radius(20)
            .build()
            .forEach(x -> {
                if (x.getAttackTarget()
                    == caster) {
                    x.setAttackTarget(null);
                }
            });
    }
}