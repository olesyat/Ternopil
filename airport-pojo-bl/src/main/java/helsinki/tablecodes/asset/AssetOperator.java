package helsinki.tablecodes.asset;

import helsinki.assets.Asset;
import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
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
@KeyType(DynamicEntityKey.class)
@KeyTitle("Key")
@CompanionObject(IAssetOperator.class)
@MapEntityTo
public class AssetOperator extends AbstractOperator {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(AssetOperator.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty
    @MapTo
    @Title(value = "Asset", desc = "Asset referenced in this operatorship")
    @CompositeKeyMember(1)
    private Asset asset;

    @Observable
    public AssetOperator setAsset(final Asset asset) {
        this.asset = asset;
        return this;
    }

    public Asset getAsset() {
        return asset;
    }

}