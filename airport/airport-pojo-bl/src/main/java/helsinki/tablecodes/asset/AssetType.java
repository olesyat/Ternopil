package helsinki.tablecodes.asset;

import helsinki.asset.tablecodes.AssetClass;
import helsinki.asset.tablecodes.IAssetClass;
import ua.com.fielden.platform.entity.AbstractPersistentEntity;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;


@KeyType(String.class)
@KeyTitle("Name")
@DescTitle("Description")
public class AssetType extends AbstractPersistentEntity<String> {
    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(AssetType.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @MapTo
    private AssetClass assetClass;
    
    public void SetAssetClass(AssetClass assetClass) {
        this.assetClass = assetClass;
    }
    
    public AssetClass getAssetClass() {
        return this.assetClass; // returns key of the asset class when printed
    }
   
//testing
//    public static void main(String[] args) {
//        AssetType test1 = new AssetType();
//      
//        AssetClass TEST1 = new AssetClass();
//        TEST1.setKey("lalala");
//        AssetClass TEST2 = new AssetClass();
//        
//        test1.SetAssetClass(TEST1);
//        test1.SetAssetClass(TEST2);
//        System.out.println(test1.getAssetClass());
//        
//    }
}
