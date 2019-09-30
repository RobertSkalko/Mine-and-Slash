package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.database.IGUID;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TalentPoint implements IGUID {

    public TalentPoint(String guid) {
        this.guid = guid;
    }

    public List<TalentPoint> connections = new ArrayList<>();
    public TalentPointEffect effect;
    public ItemStack renderStack;
    private String guid;
    public int x;
    public int y;

    public PerkType getPerkType() {
        return this.effect.type;
    }

    public boolean isConnectedTo(TalentPoint talent) {
        for (TalentPoint con : connections) {
            if (con.GUID().equals(talent.GUID())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String GUID() {
        return guid;
    }

}
