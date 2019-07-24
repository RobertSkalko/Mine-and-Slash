package com.robertx22.mine_and_slash.database.requirements;

import java.util.ArrayList;
import java.util.List;

public class Requirements {

    List<BaseRequirement> requirements = new ArrayList<>();

    public Requirements(BaseRequirement req) {
        this.requirements.add(req);
    }

    public Requirements(BaseRequirement req1, BaseRequirement req2) {
        this.requirements.add(req1);
        this.requirements.add(req2);
    }

    public Requirements(BaseRequirement req1, BaseRequirement req2,
                        BaseRequirement req3) {
        this.requirements.add(req1);
        this.requirements.add(req2);
        this.requirements.add(req3);
    }

    public Requirements(List<BaseRequirement> reqs) {
        this.requirements.addAll(reqs);
    }

    public boolean satisfiesAllRequirements(GearRequestedFor requested) {

        for (BaseRequirement req : requirements) {
            if (req.meetsRequierment(requested) == false) {
                return false;
            }
        }

        return true;
    }

}
