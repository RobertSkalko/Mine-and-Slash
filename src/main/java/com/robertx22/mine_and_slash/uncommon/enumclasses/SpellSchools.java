package com.robertx22.mine_and_slash.uncommon.enumclasses;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.*;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public enum SpellSchools {

    OCEAN_MYSTIC("ocean_mystic", Elements.Water.format, Words.OceanMystic),
    EMBER_MAGE("ember_mage", Elements.Fire.format, Words.EmberMage),
    DRUID("druid", Elements.Nature.format, Words.Druid),
    SHAMAN("shaman", Elements.Thunder.format, Words.Shaman),
    RANGER("ranger", TextFormatting.GREEN, Words.Ranger);

    public static int MAXIMUM_POINTS = 50;

    SpellSchools(String id, TextFormatting format, Words locName) {
        this.id = id;
        this.format = format;
        this.locName = locName;
    }

    public String id;
    public TextFormatting format;
    public Words locName;

    public List<ExactStatData> getStatsFor(int schoolLevel, EntityCap.UnitData data) { // TODO make it differ per school;
        List<ExactStatData> list = new ArrayList<>();

        // these will need a lot of fine tuning.

        list.add(new ExactStatData(25, Health.getInstance()));
        list.add(new ExactStatData(30, Mana.getInstance()));
        list.add(new ExactStatData(30, Energy.getInstance()));

        list.add(new ExactStatData(3, ManaRegen.getInstance()));
        list.add(new ExactStatData(3, EnergyRegen.getInstance()));
        list.add(new ExactStatData(3, HealthRegen.getInstance()));
        list.add(new ExactStatData(3, MagicShieldRegen.getInstance()));

        list.add(new ExactStatData(0.75F, PhysicalDamage.getInstance()));

        int level = getEffectiveLevel(schoolLevel);
        int lvl = MathHelper.clamp(level, 0, data.getLevel());

        list.forEach(x -> x.scaleToLvl(lvl)); // scale to this level, not player level

        return list;
    }

    public int getEffectiveLevel(PlayerSpellCap.ISpellsCap spells) {
        return getEffectiveLevel(spells.getAbilitiesData()
            .getSchoolPoints(this));

    }

    public static int LVL_TO_UNLOCK_2ND_SCHOOL = 10;

    public int getEffectiveLevel(int schoolLevel) {
        int level = (int) (ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get() * ((float) schoolLevel / (float) MAXIMUM_POINTS));

        return level;

    }

    public ResourceLocation getGuiIconLocation() {
        return new ResourceLocation(Ref.MODID, "textures/gui/spell_schools/schools/" + id + ".png");
    }

}
