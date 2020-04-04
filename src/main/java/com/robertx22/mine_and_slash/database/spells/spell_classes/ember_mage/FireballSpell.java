package com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage;

import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.FireballEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellTooltips;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseProjectileSpell;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class FireballSpell extends BaseProjectileSpell {

    private FireballSpell() {
        this.allowedAsRightClickOn = AllowedAsRightClickOn.MAGE_WEAPON;
    }

    public static FireballSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public AbstractArrowEntity newEntity(World world) {
        return new FireballEntity(world);
    }

    @Override
    public SoundEvent getCastSound() {
        return ModSounds.FIREBALL.get();
    }

    @Override
    public Elements getElement() {
        return Elements.Fire;
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.EMBER_MAGE;
    }

    @Override
    public float getShootSpeed() {
        return 0.5F;
    }

    @Override
    public int getCooldownInSeconds() {
        return 0;
    }

    @Override
    public int getCooldownInTicks() {
        return 10;
    }

    @Override
    public int useTimeTicks() {
        return 0;
    }

    @Override
    public String GUID() {
        return "fireball";
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.one(dmgStat(), 1F, 10);
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(SpellTooltips.singleTargetProjectile());

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.Fireball;
    }

    private static class SingletonHolder {
        private static final FireballSpell INSTANCE = new FireballSpell();
    }
}