package com.robertx22.mine_and_slash.database.data.currency.loc_reqs;

import net.minecraft.util.text.ITextComponent;

public abstract class BaseLocRequirement {

    public abstract ITextComponent getText();

    public abstract boolean isAllowed(LocReqContext context);

    public boolean isNotAllowed(LocReqContext context) {
        return this.isAllowed(context) == false;
    }

}
