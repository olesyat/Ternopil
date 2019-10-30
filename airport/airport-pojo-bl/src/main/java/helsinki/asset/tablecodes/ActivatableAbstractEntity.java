package helsinki.asset.tablecodes;

import ua.com.fielden.platform.entity.AbstractEntity;
import ua.com.fielden.platform.entity.AbstractPersistentEntity;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Readonly;
import ua.com.fielden.platform.entity.annotation.SkipEntityExistsValidation;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.security.user.User;

@SuppressWarnings("rawtypes")
public abstract class ActivatableAbstractEntity<K extends Comparable> extends AbstractEntity<K> {
    
    public static final String ACTIVE  = "active";
    public static final String RefCOUNT  = "refcount";
    
    @IsProperty
    protected static Integer refCount = 0;
    
    @IsProperty
    protected Boolean active;
    
    @Observable
    protected ActivatableAbstractEntity<K> setActive(final Boolean active) {
        if (active) {
            refCount++;
        }
        else {
            refCount--;
        }
        
        if (refCount <= 0) {
            refCount =0;
        }
        this.active = active;
        return this;
    }
    
    
    
}
