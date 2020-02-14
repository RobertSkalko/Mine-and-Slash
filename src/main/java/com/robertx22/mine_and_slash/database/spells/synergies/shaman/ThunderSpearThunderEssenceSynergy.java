package com.robertx22.mine_and_slash.database.spells.synergies.shaman;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.ThunderspearSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.AfterDamageContext;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.shaman.ThunderEssenceEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class ThunderSpearThunderEssenceSynergy extends Synergy<AfterDamageContext> {

    @Override
    public String GUID() {
        return "thunder_spear_thunder_essence_synergy";
    }

    static int CHANCE = 50;

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(CHANCE + " percent chance for attacks give Thunder Essence"));

        list.addAll(ThunderEssenceEffect.INSTANCE.GetTooltipString(info));

        return list;
    }

    @Override
    public BaseSpell spellAffected() {
        return ThunderspearSpell.getInstance();
    }

    @Override
    public void tryActivate(AfterDamageContext ctx) {
        if (RandomUtils.roll(CHANCE)) {
            PotionEffectUtils.reApplyToSelf(ThunderEssenceEffect.INSTANCE, ctx.caster);
        }
    }
}
