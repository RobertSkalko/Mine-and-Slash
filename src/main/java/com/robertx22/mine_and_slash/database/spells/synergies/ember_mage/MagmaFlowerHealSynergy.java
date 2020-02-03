package com.robertx22.mine_and_slash.database.spells.synergies.ember_mage;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage.MagmaFlowerSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterContext;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellHealEffect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class MagmaFlowerHealSynergy extends Synergy<CasterContext> {

    @Override
    public String GUID() {
        return "magma_flower_heal_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Heals the caster"));

        list.addAll(spellAffected().getCalculation().GetTooltipString(info));

        return list;
    }

    @Override

    public BaseSpell spellAffected() {
        return MagmaFlowerSpell.getInstance();
    }

    @Override
    public void tryActivate(CasterContext ctx) {
        EntityCap.UnitData data = Load.Unit(ctx.caster);

        BaseSpell spell = spellAffected();

        SpellHealEffect heal = new SpellHealEffect(
                new ResourcesData.Context(data, ctx.caster, ResourcesData.Type.HEALTH,
                                          spell.getCalculation().getCalculatedValue(data), ResourcesData.Use.RESTORE,
                                          spell
                ));

        heal.Activate();
    }
}
