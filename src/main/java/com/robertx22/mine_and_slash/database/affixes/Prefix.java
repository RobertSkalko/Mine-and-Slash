package com.robertx22.mine_and_slash.database.affixes;

import com.robertx22.mine_and_slash.database.requirements.Requirements;

public abstract class Prefix extends BaseAffix {

    public Prefix(Requirements req) {
        super(req, Type.prefix);
    }

    @Override
    public String datapackFolder() {
        return "prefix/";
    }

    @Override
    public int Tier() {
        return 0;
    }
}
