package helsinki.tablecodes.asset;

import java.util.Date;
import helsinki.organizational.BusinessUnit;
import helsinki.organizational.Organization;
import helsinki.organizational.Role;
import helsinki.tablecodes.asset.definers.AssetTypeRelatedPersonaExclusivityDefiner;
import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.DateOnly;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.mutator.AfterChange;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;


@KeyType(DynamicEntityKey.class)
@KeyTitle("Key")
@CompanionObject(IAssetTypeManager.class)
@MapEntityTo
public class AssetTypeManager extends AssetTypeRelatedPersonas {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(AssetTypeManager.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @MapTo
    @Title(value = "Asset Type", desc = "Desc")
    @CompositeKeyMember(1)
    private AssetType assetType;

    

    @IsProperty
    @MapTo
    @Title(value = "Start Date", desc = "Start date of the management")
    @DateOnly
    @CompositeKeyMember(2)
    private Date date;
    
    @IsProperty
    @MapTo
    @AfterChange(AssetTypeRelatedPersonaExclusivityDefiner.class)
    @Title(value = "Role", desc = "Role that manages assets of the specific asset Type")
    private Role role;

    
    @IsProperty
    @MapTo
    @AfterChange(AssetTypeRelatedPersonaExclusivityDefiner.class)
    @Title(value = "Business Unit", desc = "Business unit that managess assets of the specific asset Type")
    private BusinessUnit bu;

    
    @IsProperty
    @MapTo
    @AfterChange(AssetTypeRelatedPersonaExclusivityDefiner.class)
    @Title(value = "Organization", desc = "Organization that manages assets of the specific asset Type")
    private Organization org;


    @Observable
    @Override
    public AssetTypeManager setDate(final Date date) {
        this.date = date;
        return this;
    }

    @Observable
    @Override
    public AssetTypeManager setOrg(final Organization org) {
        this.org = org;
        return this;
    }


    @Override
    @Observable
    public AssetTypeManager setBu(final BusinessUnit bu) {
        this.bu = bu;
        return this;
    }

    @Override
    @Observable
    public AssetTypeManager setRole(final Role role) {
        this.role = role;
        return this;
    }

 
    @Override
    @Observable
    public AssetTypeManager setAssetType(final AssetType assetType) {
        this.assetType = assetType;
        return this;
    }

}
