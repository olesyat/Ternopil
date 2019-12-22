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

/**
 * Master entity object.
 *
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Key")
@CompanionObject(IAssetTypeOwnership.class)
@MapEntityTo
public class AssetTypeOwnership extends AssetTypeRelatedPersonas {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(AssetTypeOwnership.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @MapTo
    @Title(value = "Asset Type", desc = "Desc")
    @CompositeKeyMember(1)
    private AssetType assetType;

    

    @IsProperty
    @MapTo
    @Title(value = "Start Date", desc = "Start date of the ownership")
    @DateOnly
    @CompositeKeyMember(2)
    private Date date;
    
    @IsProperty
    @MapTo
    @AfterChange(AssetTypeRelatedPersonaExclusivityDefiner.class)
    @Title(value = "Role", desc = "Role that owns assets of the specific asset Type")
    private Role role;

    
    @IsProperty
    @MapTo
    @AfterChange(AssetTypeRelatedPersonaExclusivityDefiner.class)
    @Title(value = "Business Unit", desc = "Business unit that owns assets of the specific asset Type")
    private BusinessUnit bu;

    
    @IsProperty
    @MapTo
    @AfterChange(AssetTypeRelatedPersonaExclusivityDefiner.class)
    @Title(value = "Organization", desc = "Organization that owns assets of the specific asset Type\"")
    private Organization org;


    @Observable
    @Override
    public AssetTypeOwnership setDate(final Date date) {
        this.date = date;
        return this;
    }

    @Observable
    @Override
    public AssetTypeOwnership setOrg(final Organization org) {
        this.org = org;
        return this;
    }


    @Override
    @Observable
    public AssetTypeOwnership setBu(final BusinessUnit bu) {
        this.bu = bu;
        return this;
    }

    @Override
    @Observable
    public AssetTypeOwnership setRole(final Role role) {
        this.role = role;
        return this;
    }

    
    @Override
    @Observable
    public AssetTypeOwnership setAssetType(final AssetType assetType) {
        this.assetType = assetType;
        return this;
    }

}