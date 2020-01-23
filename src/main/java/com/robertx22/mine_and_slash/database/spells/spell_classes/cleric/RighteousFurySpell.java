package com.robertx22.mine_and_slash.database.spells.spell_classes.cleric;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpellHeal;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.cleric.RighteousFuryEffect;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.BlazingInfernoEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class RighteousFurySpell extends BaseSpellHeal {

    @Override
    public BaseSpell.SpellType getSpellType() {
        return SpellType.Self_Buff;
    }

    @Override
    public String GUID() {
        return "righteous_fury";
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.CLERIC;
    }

    @Override
    public int getCooldownInSeconds() {
        return 60;
    }

    @Override
    public int getManaCost() {
        return 50;
    }

    @Override
    public SpellCalcData getCalculation() {
        return BlazingInfernoEffect.CALC;
    }

    @Override
    public ITextComponent GetDescription() {
        return CLOC.tooltip("instant_heal");
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse) {

        try {
            World world = caster.world;

            if (!world.isRemote()) {

                PotionEffectUtils.applyToSelf(RighteousFuryEffect.INSTANCE, 20 * 45, caster);

                SoundUtils.playSoundAtPlayer(caster, SoundEvents.BLOCK_FIRE_EXTINGUISH, 1, 1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}