package helsinki.assets;

import ua.com.fielden.platform.entity.AbstractEntity;
import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Readonly;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.DescRequired;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Asset Number")
@CompanionObject(IAsset.class)
@MapEntityTo
@DescTitle("Description")
@DisplayDescription
@DescRequired
public class Asset extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(Asset.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
// We assume that each concrete Asset has a possibility to report about its problem. 
//  However, we can implement Regulatory attribute as an user input while creating an Asset object.
    
    
    @IsProperty
    @MapTo
    @Title(value = "Regulatory", desc = "Indication of whether this asset is regulatory")
    private boolean regulatory;

    
    @IsProperty
    @MapTo
    @Title(value = "Key Asset", desc = "Indication of whether this asset is key asset")
    private boolean keyAsset;
    
        
    @IsProperty
    @MapTo
    @Title(value = "Number", desc = "A unique asset number, auto-generated.")
    @CompositeKeyMember(1)
    @Readonly
    private String number;
    
    @IsProperty
    @Title(value = "Fin Det", desc = "Financial details for this asset")
    private AssetFinDet finDet;
    
    @IsProperty
    @Title(value = "Days to expiration", desc = "Hom many days we can use that Asset")
    private int expDays;

    

    @IsProperty
    @Title(value = "Service Status", desc = "Service Status of this asset")
    private AssetServiceStatus serviceStatus;

    
    @Observable
    protected Asset setFinDet(final AssetFinDet finDet) {
        this.finDet = finDet;
        return this;
    }

    public AssetFinDet getFinDet() {
        return finDet;
    }
    
    
    public Asset setRegulatory(final Boolean regulatory) {
        this.regulatory = regulatory;
        return this;
    }
    
    public Boolean getRegulatory() {
        return regulatory;
    }
    
    public Asset setKeyAsset(final Boolean keyAsset) {
        this.keyAsset = keyAsset;
        return this;
    }
    
    public Boolean getKeyAsset() {
        return keyAsset;
    }
    
    public Asset setNumberOfExpirationDays(final int expDays) {
     this.expDays = expDays;
     return this;
    }
    
    public int getNumberOfExpirationDays() {
     return expDays;
    }
    
    public int getActiveDays() {
     // this function returns number of active days of Asset from DataBase
     // in the future we can implement it
     // int activeDays = returnActiveFromDb
     int activeDays = 10; //hardcoded
     return activeDays;
    }
    
    
    public float getUsageRate() {
     float expDays = getNumberOfExpirationDays();
     float sumActiveDays = getActiveDays();
     
     return sumActiveDays / expDays;
    }
    
    
    @IsProperty
    @MapTo
    @Title(value = "Usage rate", desc = "Desc")
    private float usageRate;

    public float getName() {
        return this.getUsageRate();
    }

    

    
    @Override
    @Observable
    public Asset setDesc(final String desc) {
        super.setDesc(desc);
        return this;
    }
    
    @Observable
    public Asset setServiceStatus(final AssetServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
        return this;
    }

    public AssetServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    @Observable
    public Asset setNumber(final String number) {
        this.number = number;
        return this;
    }

    public String getNumber() {
        return number;
    }

}
    