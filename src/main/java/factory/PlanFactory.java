package factory;

import entity.*;

public class PlanFactory {
    public Plan getPlan(String type) {

        if (type.equalsIgnoreCase("AudioPlan")) {
            return new AudioPlan();
        } else if (type.equalsIgnoreCase("DataPlan")) {
            return new DataPlan();
        }else if (type.equalsIgnoreCase("FullPlan")) {
            return new FullPlan();
        }else if (type.equalsIgnoreCase("PrepaidPlan")) {
            return new PrepaidPlan();
        }else{
            return null;
        }
    }
}
