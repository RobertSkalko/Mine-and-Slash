package com.robertx22.mine_and_slash.onevent.entity.damage;

import com.robertx22.mine_and_slash.database.spells.bases.MyDamageSource;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import net.minecraft.util.DamageSource;

public class DmgSourceUtils {

    private static String MARKER = "dmgismineandconverted";

    public static boolean isMyDmgSource(DamageSource source) {
        return source instanceof MyDamageSource || source.getDamageType()
                .equals(DamageEffect.dmgSourceName) || source.damageType.contains(MARKER);
    }

    public static void markSourceAsMine(DamageSource source) {
        if (!(source instanceof MyDamageSource)) {
            if (!source.damageType.contains(MARKER)) {
                source.damageType = source.damageType + MARKER;
            }
        }
    }

    public static void removeSourceMarker(DamageSource source) {
        source.damageType = source.damageType.replaceAll(MARKER, "");

    }

}
