package com.robertx22.mine_and_slash.database.spells.spell_classes.rogue;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class BlinkStrikeSpell extends BaseSpell {

    private BlinkStrikeSpell() {
        this.castRequirements.add(REQUIRE_MELEE_WEAPON);
    }

    public static BlinkStrikeSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.ROGUE;
    }

    @Override
    public int getCooldownInSeconds() {
        return 45;
    }

    @Override
    public SpellType getSpellType() {
        return SpellType.Aoe_Damage_Nova;
    }

    @Override
    public String GUID() {
        return "blink_strike";
    }

    @Override
    public int getManaCost() {
        return 20;
    }

    @Override
    public int useTimeTicks() {
        return 5;
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.allAttackAndSpellDamages(0.75F, 1F, 8);
    }

    @Override
    public Elements getElement() {
        return Elements.Elemental;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();
        list.add(new StringTextComponent("Teleport behind enemy and attack: "));
        list.add(new StringTextComponent("Requires Melee weapon."));
        list.addAll(getCalculation()
            .GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.BlinkStrike;
    }

    @Override
    public boolean cast(LivingEntity caster, int ticksInUse) {

        LivingEntity target = EntityUtils.getEntityCasterIsLookingAt(caster);

        if (target != null) {
            Vec3d p = target.getPositionVector();
            p.add(p.subtract(caster.getPositionVector()));
            p.mul(0.1F, 0.1F, 0.1F);

            EntityUtils.setLoc(caster, p, target.rotationYaw, target.rotationPitch);

            int num = getCalculation().getCalculatedValue(Load.Unit(caster));

            DamageEffect dmg = new DamageEffect(null, caster, target, num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
            dmg.element = Elements.Elemental;
            dmg.Activate();

            return true;

        }
        return false;
    }

    @Override
    public boolean canCast(LivingEntity caster) {
        if (EntityUtils.getEntityCasterIsLookingAt(caster) != null) {
            return super.canCast(caster);
        } else {
            return false;
        }
    }

    private static class SingletonHolder {
        private static final BlinkStrikeSpell INSTANCE = new BlinkStrikeSpell();
    }
}
