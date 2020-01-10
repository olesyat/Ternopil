package helsinki.assets;

import java.util.Date;


import helsinki.tablecodes.service.ServiceStatus;
import ua.com.fielden.platform.entity.AbstractPersistentEntity;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.DateOnly;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Developers
 *
 */
@KeyType(Asset.class)
@KeyTitle("Asset")
@CompanionObject(IAssetServiceStatus.class)
@MapEntityTo
public class AssetServiceStatus extends AbstractPersistentEntity<String> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(AssetServiceStatus.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    
    @IsProperty
    @MapTo
    private Asset key;
    
    @IsProperty
    @MapTo
    @Title(value = "Service Status", desc = "Stutus of this asset")
    private ServiceStatus serviceStatus;

    @IsProperty
    @MapTo
    @DateOnly
    @Title(value = "Creation Date", desc = "Date when this status was created")
    private Date creationDate;

    @Observable
    public AssetServiceStatus setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    

    
    
    @Observable
    public AssetServiceStatus setServiceStatus(final ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
        return this;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    

    
}
