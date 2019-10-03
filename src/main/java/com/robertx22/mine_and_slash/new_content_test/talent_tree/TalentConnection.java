package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import java.util.Objects;

public class TalentConnection {

    public TalentConnection(Allocation status, TalentPoint one, TalentPoint two) {
        this.allocationStatus = status;
        this.one = one;
        this.two = two;

    }

    public TalentPoint one, two;
    public Allocation allocationStatus = Allocation.CANT_ALLOCATE;

    public enum Allocation {
        CAN_ALLOCATE,
        CANT_ALLOCATE,
        ALLOCATED
    }

    private String getCombinedGuid() {
        return Objects.hash(one.GUID()) > Objects.hash(two.GUID()) ? one.GUID() + two.GUID() : two
                .GUID() + one.GUID();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TalentConnection)) {
            return false;
        }
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCombinedGuid());
    }

}
