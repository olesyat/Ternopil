package helsinki.tablecodes.service;

import java.util.ArrayList;

public class ConditionalRatingTable {
    private static ConditionalRatingTable single_instance = null; 
    
    public ArrayList<ConditionRating> allstatuses;
    private ConditionalRatingTable() {
        allstatuses = new ArrayList<ConditionRating>();
    }
    public static ConditionalRatingTable getInstance() {
        if (single_instance == null)
            single_instance = new ConditionalRatingTable();
        return single_instance;
    }
    
}
