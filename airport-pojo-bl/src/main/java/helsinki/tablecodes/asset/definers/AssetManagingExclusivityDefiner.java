package helsinki.tablecodes.asset.definers;

import helsinki.tablecodes.asset.AssetTypeManager;
//import helsinki.tablecodes.asset.AssetTypeOwnership;
import ua.com.fielden.platform.entity.meta.IAfterChangeEventHandler;
import ua.com.fielden.platform.entity.meta.MetaProperty;

public class AssetManagingExclusivityDefiner implements IAfterChangeEventHandler<Object>{

    @Override
    public void handle(MetaProperty<Object> prop, Object value) {
        final AssetTypeManager manager = prop.getEntity();
        
        final boolean allEmpty = manager.getRole() == null && 
                manager.getBu() == null && 
                manager.getOrg() == null; 
        
        
        if (manager.getRole() == null) {
            manager.getProperty("role").setRequired(allEmpty);
        }
        if (manager.getBu() == null) {
            manager.getProperty("bu").setRequired(allEmpty);
        }
        
        if (manager.getOrg() == null) {
            manager.getProperty("org").setRequired(allEmpty);
        }
        
        if ("role".equals(prop.getName()) && value != null) {
                manager.getProperty("bu").setRequired(false); 
                manager.setBu(null); 
                manager.getProperty("org").setRequired(false); 
                manager.setOrg(null);
                manager.getProperty("role").setRequired(true); 

            }
        else if (value != null) {
        
        if  ("bu".equals(prop.getName())) {
            manager.getProperty("role").setRequired(false);
            manager.setRole(null); 
            manager.getProperty("org").setRequired(false); 
            manager.setOrg(null);
            manager.getProperty("bu").setRequired(true); 

                        
        }
        if ("org".equals(prop.getName()) && value != null) {        
            manager.getProperty("role").setRequired(false);
            manager.setRole(null); 
            manager.getProperty("bu").setRequired(false); 
            manager.setBu(null);
            manager.getProperty("org").setRequired(true);     
        }
        
        }  
    }
}



