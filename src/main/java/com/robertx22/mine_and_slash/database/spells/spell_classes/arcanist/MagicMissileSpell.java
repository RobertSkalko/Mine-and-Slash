package com.robertx22.mine_and_slash.database.spells.spell_classes.arcanist;

import com.robertx22.mine_and_slash.database.spells.entities.proj.MagicMissileEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellTooltips;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseProjectileSpell;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class MagicMissileSpell extends BaseProjectileSpell {

    private MagicMissileSpell() {
    }

    public static MagicMissileSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public AbstractArrowEntity newEntity(World world) {
        return new MagicMissileEntity(world);
    }

    @Override
    public SoundEvent getShootSound() {
        return SoundEvents.ENTITY_FIREWORK_ROCKET_SHOOT;
    }

    @Override
    public Elements getElement() {
        return Elements.Elemental;
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.ARCANIST;
    }

    @Override
    public int getCooldownInSeconds() {
        return 0;
    }

    @Override
    public String GUID() {
        return "magic_missile";
    }

    SpellCalcData calc = SpellCalcData.all(ElementalSpellDamage.MAP.MAP.values(), 0.1F, 10);

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.all(ElementalSpellDamage.MAP.MAP.values(), 0.1F, 2);
    }

    @Override
    public float getShootSpeed() {
        return 0.8F;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(SpellTooltips.singleTargetProjectile());
        list.add(new SText("Spawns 3 projectiles"));

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.Magic_Missile;
    }

    private static class SingletonHolder {
        private static final MagicMissileSpell INSTANCE = new MagicMissileSpell();
    }
}