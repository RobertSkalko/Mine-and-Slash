package com.robertx22.mine_and_slash.database;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public interface IGUID {

    public String GUID();

    public default String formattedGUID() {
        return formatString(GUID());

    }

    public default String formatString(String str) {
        return getformattedString(str);
    }

    public static String getformattedString(String str) {
        if (isGUIDFormattedCorrectly(str)) {
            return str;
        } else {
            String newstring = StringUtils.join(
                    StringUtils.splitByCharacterTypeCamelCase(str.replaceAll("\\d+", "")), "_");

            newstring = str.replaceAll(" ", "_").toLowerCase(Locale.ROOT);

            return newstring.replaceAll("__", "_");
        }
    }

    default Item getFromForgeRegistry() {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(Ref.MODID, GUID()));
    }

    default boolean isGuidFormattedCorrectly() {
        return isGUIDFormattedCorrectly(GUID());
    }

    public static boolean isGUIDFormattedCorrectly(String id) {
        for (char c : id.toCharArray()) {
            if (!isValidPathCharacter(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidPathCharacter(char c) {
        return c >= '0' && c <= '9' || c >= 'a' && c <= 'z' || c == '_' || c == ':' || c == '/' || c == '.' || c == '-';
    }

}
