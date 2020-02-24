package com.robertx22.mine_and_slash.saveclasses.dungeon_dimension;

import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class DungeonData {

    @Store
    public MapItemData mapData = new MapItemData();
}
