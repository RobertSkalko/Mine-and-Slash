package com.robertx22.mine_and_slash.database;

public interface IGUID {

    public String GUID();

    public default String formattedGUID() {
        return formatString(GUID());

    }

    public default String formatString(String str) {
        return str.toLowerCase()
                .replaceAll(" ", "_")
                .replaceAll("/", ".")
                .replaceAll(":", ".");
    }

}
