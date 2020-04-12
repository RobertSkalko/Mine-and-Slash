package com.robertx22.mine_and_slash.database.spells.synergies.ranger;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.hunting.RecoilShotSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ranger.HunterInstinctEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RecoilAddHunterSynergy extends OnDamageDoneSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Chance to give: " + HunterInstinctEffect.getInstance()
            .locNameForLangFile()));

        return list;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 1, 1);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.AMOUNT, 1, 3);
        c.setMaxLevel(8);
        return c;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return RecoilShotSpell.getInstance();
    }

    @Override
    public void tryActivate(SpellDamageEffect ctx) {

        float stacks = get(ctx.source, SC.AMOUNT);

        for (int i = 0; i < stacks; i++) {
            PotionEffectUtils.reApplyToSelf(HunterInstinctEffect.getInstance(), ctx.source);
        }
    }

    @Override
    public String locNameForLangFile() {
        return "Recoil of the hunter";
    }
}
