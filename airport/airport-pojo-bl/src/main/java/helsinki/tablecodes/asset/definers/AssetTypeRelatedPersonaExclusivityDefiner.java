package helsinki.tablecodes.asset.definers;

import helsinki.tablecodes.asset.AssetTypeRelatedPersonas;
import ua.com.fielden.platform.entity.meta.IAfterChangeEventHandler;
import ua.com.fielden.platform.entity.meta.MetaProperty;

public class AssetTypeRelatedPersonaExclusivityDefiner implements IAfterChangeEventHandler<Object> {

    @Override
    public void handle(final MetaProperty<Object> prop, final Object value) {
       
        final AssetTypeRelatedPersonas ownership = prop.getEntity();
        
        final boolean allEmpty = ownership.getRole() == null && 
                ownership.getBu() == null && 
                ownership.getOrg() == null; 
        
        
        if (ownership.getRole() == null) {
            ownership.getProperty("role").setRequired(allEmpty);
        }
        if (ownership.getBu() == null) {
            ownership.getProperty("bu").setRequired(allEmpty);
        }
        
        if (ownership.getOrg() == null) {
            ownership.getProperty("org").setRequired(allEmpty);
        }
        
        if ("role".equals(prop.getName()) && value != null) {
                ownership.getProperty("bu").setRequired(false); 
                ownership.setBu(null); 
                ownership.getProperty("org").setRequired(false); 
                ownership.setOrg(null);
                ownership.getProperty("role").setRequired(true); 

            }
        
        else if ("bu".equals(prop.getName()) && value != null) {
                ownership.getProperty("role").setRequired(false);
                ownership.setRole(null); 
                ownership.getProperty("org").setRequired(false); 
                ownership.setOrg(null);
                ownership.getProperty("bu").setRequired(true); 

                        
        }
        else if ("org".equals(prop.getName()) && value != null) {        
                ownership.getProperty("role").setRequired(false);
                ownership.setRole(null); 
                ownership.getProperty("bu").setRequired(false); 
                ownership.setBu(null);
                ownership.getProperty("org").setRequired(true);     
        }
        
    }

}
