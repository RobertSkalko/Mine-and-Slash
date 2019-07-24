package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.world_gen.structures.bases.TemplatePiece;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.HashMap;

@Storable
public class StructuresData {

    @Store
    public HashMap<String, StructureData> map = new HashMap<>();

    public StructureData getData(TemplatePiece piece) {

        if (map.containsKey(piece.guid) == false) {

            map.put(piece.guid, new StructureData());

        }

        return map.get(piece.guid);

    }

}
