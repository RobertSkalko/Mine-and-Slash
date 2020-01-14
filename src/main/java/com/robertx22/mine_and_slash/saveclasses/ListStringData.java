package com.robertx22.mine_and_slash.saveclasses;

import info.loenwind.autosave.annotations.Storable;

import java.util.ArrayList;
import java.util.List;

@Storable
public class ListStringData {

    public List<String> list = new ArrayList<>();

    public ListStringData() {
    }

    public ListStringData(List<String> list) {
        this.list = list;
    }
}
