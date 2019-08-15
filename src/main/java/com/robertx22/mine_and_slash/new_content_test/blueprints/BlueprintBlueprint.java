package com.robertx22.mine_and_slash.new_content_test.blueprints;

import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.loot.blueprints.ItemBlueprint;
import com.robertx22.mine_and_slash.new_content_test.blueprints.requests.BlueprintDataItemRequest;
import com.robertx22.mine_and_slash.new_content_test.blueprints.requests.BlueprintSimpleItemRequest;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

import java.util.ArrayList;
import java.util.List;

public class BlueprintBlueprint extends ItemBlueprint {

    public int tier = 0;

    public BlueprintBlueprint(int level, int tier) {
        super(level);
        this.tier = tier;
    }

    @Override
    public RaritiesContainer<? extends Rarity> getRarityContainer() {
        return Rarities.Items;
    }

    public void generateRequests(BlueprintItemData data) {

        List<BlueprintDataItemRequest> datas = new ArrayList<>();
        List<BlueprintSimpleItemRequest> simples = new ArrayList<>();

        ItemRarity rar = Rarities.Items.get(this.getRarityRank());

        BlueprintDataItemRequest request = new BlueprintDataItemRequest();
        request.random(rar);
        datas.add(request);

        // use new registry randomlist here instead
        // add currneices to registry

        for (int i = 0; i < 4; i++) {

            BlueprintSimpleItemRequest simple = new BlueprintSimpleItemRequest();
            simple.random(rar);
            simples.add(simple);
        }

        data.simpleItemRequests = simples;
        data.dataRequests = datas;
    }

}
