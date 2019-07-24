package com.robertx22.mine_and_slash.config.mod_dmg_whitelist;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;

public class ModDmgWhitelistContainer {

    public static ModDmgWhitelistContainer INSTANCE = new ModDmgWhitelistContainer();

    public ModDmgWhitelistContainer() {
        this.modList.put("testmodid", new ModDmgWhitelist("testmodid", 0.5F));
        this.modList.put("testmodid2", new ModDmgWhitelist("testmodid2", 1F));
        // this.modList.put("minecraft", new ModDmgWhitelist("minecraft", 1F));

    }

    public HashMap<String, ModDmgWhitelist> modList = new HashMap<>();

    public static class ModDmgWhitelist {

        public ModDmgWhitelist(String id, float multi) {
            this.modid = id;
            this.dmgMultiplier = multi;
        }

        public String modid;
        public float dmgMultiplier = 1F;

    }

    public static ModDmgWhitelistContainer.ModDmgWhitelist getModDmgWhitelist(
            ItemStack stack) {

        if (stack.isEmpty()) {
            return null;
        }

        ResourceLocation loc = stack.getItem().getRegistryName();

        if (loc != null) {
            return ModDmgWhitelistContainer.INSTANCE.modList.get(loc.getNamespace());
        }

        return null;

    }

}
