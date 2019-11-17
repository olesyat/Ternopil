package helsinki.tablecodes.service;

import helsinki.asset.tablecodes.AssetClass;
import ua.com.fielden.platform.entity.AbstractPersistentEntity;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

@KeyType(String.class)
@KeyTitle("Name")
@DescTitle("Description")
public class ServiceStatus extends AbstractPersistentEntity<String> {
    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(ServiceStatus.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty
    @MapTo
    private AssetClass assetClass;
    
    public void SetAssetClass(AssetClass assetClass) {
        this.assetClass = assetClass;
        ServiceStatusTable.getInstance().allstatuses.add(this);
    }
    
    public AssetClass getAssetClass() {
        return this.assetClass; // returns key of the asset class when printed
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
