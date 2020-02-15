package com.robertx22.mine_and_slash.database.affixes;

import com.robertx22.mine_and_slash.database.requirements.Requirements;

public abstract class Suffix extends BaseAffix {

    public Suffix(Requirements req) {
        super(req, Type.suffix);
    }

    @Override
    public String datapackFolder() {
        return "suffix/";
    }

    @Override
    public int Tier() {
        return 0;
    }
}
