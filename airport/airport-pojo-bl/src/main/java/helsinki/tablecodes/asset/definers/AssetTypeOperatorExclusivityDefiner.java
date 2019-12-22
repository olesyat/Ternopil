package helsinki.tablecodes.asset.definers;

import helsinki.tablecodes.asset.AssetTypeOperator;
import ua.com.fielden.platform.entity.meta.IAfterChangeEventHandler;
import ua.com.fielden.platform.entity.meta.MetaProperty;

public class AssetTypeOperatorExclusivityDefiner implements IAfterChangeEventHandler<Object> {

    @Override
    public void handle(final MetaProperty<Object> prop, final Object value) {
       
        final AssetTypeOperator operator = prop.getEntity();
        
        final boolean allEmpty = operator.getRole() == null && 
                operator.getBu() == null && 
                operator.getOrg() == null; 
        
        
        if (operator.getRole() == null) {
            operator.getProperty("role").setRequired(allEmpty);
        }
        if (operator.getBu() == null) {
            operator.getProperty("bu").setRequired(allEmpty);
        }
        
        if (operator.getOrg() == null) {
            operator.getProperty("org").setRequired(allEmpty);
        }
        
        if ("role".equals(prop.getName()) && value != null) {
            operator.getProperty("bu").setRequired(false); 
            operator.setBu(null); 
            operator.getProperty("org").setRequired(false); 
            operator.setOrg(null);
            operator.getProperty("role").setRequired(true); 

            }
        
        else if ("bu".equals(prop.getName()) && value != null) {
            operator.getProperty("role").setRequired(false);
            operator.setRole(null); 
            operator.getProperty("org").setRequired(false); 
            operator.setOrg(null);
            operator.getProperty("bu").setRequired(true); 

                        
        }
        else if ("org".equals(prop.getName()) && value != null) {        
            operator.getProperty("role").setRequired(false);
            operator.setRole(null); 
            operator.getProperty("bu").setRequired(false); 
            operator.setBu(null);
            operator.getProperty("org").setRequired(true);     
        }
        
    }

}
