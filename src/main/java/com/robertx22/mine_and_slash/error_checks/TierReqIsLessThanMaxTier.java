package com.robertx22.mine_and_slash.error_checks;

import com.robertx22.mine_and_slash.error_checks.base.IErrorCheck;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;

import java.util.ArrayList;
import java.util.List;

public class TierReqIsLessThanMaxTier implements IErrorCheck {

    @Override
    public void check() {

        List<ISlashRegistryEntry> list = new ArrayList<>();

        list.addAll(SlashRegistry.UniqueGears()
            .getList());
        list.addAll(SlashRegistry.Runes()
            .getList());

        int MAX = ITiered.getMaxTier();

        list.forEach(x -> {
            if (x.getTier() > MAX) {
                System.out.println(x.GUID() + " registry entry of type " + x.getSlashRegistryType()
                    .name() + " requires a tier that's above the current maximum: requires: " + x.getTier() + ", max: " + MAX);
                System.out.println("If you just wanted to disable an entry, a better way would be to set the weight to 0.");
            }
        });

    }
}
