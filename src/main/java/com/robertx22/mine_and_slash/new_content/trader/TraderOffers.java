package com.robertx22.mine_and_slash.new_content.trader;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.new_content.trader.offers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TraderOffers {

    public static List<TraderOffer> ALL = new ArrayList();

    static {

        ALL.add(new ChaosStatsOffer());
        ALL.add(new HighRarityOffer());
        ALL.add(new HighRarityRunedOffer());
        ALL.add(new MythicOffer());
        ALL.add(new UniqueOffer());
        ALL.add(new RunedJewerlyOffer());

        Arrays.stream(GearItemSlot.PlayStyle.values())
            .forEach(x -> {
                if (x != GearItemSlot.PlayStyle.NONE) {
                    ALL.add(new PlayStyleOffer(x));
                }
            });

    }

}