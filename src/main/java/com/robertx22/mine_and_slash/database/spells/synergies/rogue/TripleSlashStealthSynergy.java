package com.robertx22.mine_and_slash.database.spells.synergies.rogue;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.rogue.StealthSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterContext;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class TripleSlashStealthSynergy extends Synergy<CasterContext> {

    @Override
    public String GUID() {
        return "triple_slash_stealth_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Last hit refreshes stealth cooldown by 10 seconds."));

        return list;
    }

    @Override
    public BaseSpell spellAffected() {
        return StealthSpell.getInstance();
    }

    @Override
    public void tryActivate(CasterContext ctx) {
        if (ctx.caster instanceof PlayerEntity) {
            Load.spells((PlayerEntity) ctx.caster)
                .getSpellData()
                .getDataBySpell(StealthSpell.getInstance())
                .tickCooldown(10 * 20);
        }
    }

}