package helsinki.tablecodes.asset.definers;

import java.util.Set;

import helsinki.tablecodes.asset.AssetManager;
import helsinki.tablecodes.asset.AssetTypeManager;
import ua.com.fielden.platform.entity.AbstractEntity;
//import helsinki.tablecodes.asset.AssetTypeOwnership;
import ua.com.fielden.platform.entity.meta.IAfterChangeEventHandler;
import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.error.Result;
import ua.com.fielden.platform.utils.CollectionUtil;

public class AssetTypeManagerExclusivityDefiner implements IAfterChangeEventHandler<Object> {
    
    private final static Set<String> managementPropNames = CollectionUtil.setOf("role", "bu", "org");

    @Override
    public void handle(final MetaProperty<Object> property, final Object value) {
        
        if (!(property.getEntity() instanceof AssetManager) && !(property.getEntity() instanceof AssetTypeManager)) {
            throw Result.failure("Stringly entities of type AssetOwnership or AssetTypeOwnership are expected");
        }
       final AbstractEntity<?> management = property.getEntity();
       final boolean allEmpty = management.get("role") == null && management.get("bu") == null && management.get("org") == null;
        
       managementPropNames.stream()
               .map(name -> management.getProperty(name))
               .filter(p -> p.getValue() == null)
               .forEach(p -> p.setRequired(allEmpty));
           
       if (value != null) {
           managementPropNames.stream()
                    .filter(name -> !name.equalsIgnoreCase(property.getName()))
                    .map(name -> management.getProperty(name))
                    .forEach(p -> {p.setRequired(false);p.setValue(null);});
           property.setRequired(true);
       }
   }
}