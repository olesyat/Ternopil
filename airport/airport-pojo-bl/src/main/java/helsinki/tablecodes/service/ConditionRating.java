package helsinki.tablecodes.service;

import helsinki.asset.tablecodes.AssetClass;
import helsinki.tablecodes.asset.AssetType;
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
public class ConditionRating extends AbstractPersistentEntity<String> {
    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(ConditionRating.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty
    @MapTo
    private AssetType assetType;
    
    public void SetAssetType(AssetType assetType) {
        this.assetType = assetType;
        ConditionalRatingTable.getInstance().allstatuses.add(this);
    }

    public AssetType getAssetClass() {
        return this.assetType; // returns key of the asset class when printed
    }
    
}
