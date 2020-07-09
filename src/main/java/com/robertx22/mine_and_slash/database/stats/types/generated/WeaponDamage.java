package com.robertx22.mine_and_slash.database.stats.types.generated;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.stats.ILocalStat;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.offense.ElementalAttackDamageEffect;
import com.robertx22.mine_and_slash.database.stats.types.ElementalStat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

import java.util.List;

public class WeaponDamage extends ElementalStat implements IStatEffects, ILocalStat {
    public static MapWrapper<Elements, WeaponDamage> MAP = new MapWrapper();

    @Override
    public List<Stat> generateAllPossibleStatVariations() {
        List<Stat> list = super.generateAllPossibleStatVariations();
        list.forEach(x -> MAP.put(x.getElement(), (WeaponDamage) x));
        return list;
    }

    @Override
    public Stat.StatGroup statGroup() {
        return Stat.StatGroup.EleAttackDamage;
    }

    @Override
    public boolean UsesSecondValue() {
        return true;
    }

    public WeaponDamage(Elements element) {
        super(element);
    }

    @Override
    public Stat newGeneratedInstance(Elements element) {
        return new WeaponDamage(element);
    }

    @Override
    public String getIconPath() {
        return "ele_atk_dmg/" + element.guidName;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public IStatEffect getEffect() {
        return new ElementalAttackDamageEffect();
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "ele_atk_dmg";
    }

    @Override
    public boolean IsNativeToGearType(GearItemSlot slot) {
        return slot.slotTypeFamily() == GearItemSlot.SlotFamily.Weapon;
    }

    @Override
    public String locNameForLangFile() {
        if (element.equals(Elements.Elemental)) {
            return getElement().name() + "Attack Damage";
        } else {
            return getElement().name() + " Damage";
        }
    }

    @Override
    public String locDescForLangFile() {
        return "Adds x element damage on weapon hit";
    }

    @Override
    public String GUID() {
        return this.getElement().guidName + "_weapon_damage";
    }

}
