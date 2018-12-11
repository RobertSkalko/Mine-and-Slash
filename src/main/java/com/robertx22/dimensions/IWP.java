package com.robertx22.dimensions;

import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public interface IWP extends IWeighted {
    abstract String GUID();

    abstract String unlocString();

    public default String locName() {
	return CLOC.word(unlocString());
    }
}
