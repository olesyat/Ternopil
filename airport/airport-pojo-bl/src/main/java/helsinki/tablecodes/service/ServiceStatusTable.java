package helsinki.tablecodes.service;

import java.util.ArrayList;

public class ServiceStatusTable {
    private static ServiceStatusTable single_instance = null; 
    
    public ArrayList<ServiceStatus> allstatuses;
    private ServiceStatusTable() {
        allstatuses = new ArrayList<ServiceStatus>();
    }
    public static ServiceStatusTable getInstance() {
        if (single_instance == null)
            single_instance = new ServiceStatusTable();
        return single_instance;
    }
    
}
