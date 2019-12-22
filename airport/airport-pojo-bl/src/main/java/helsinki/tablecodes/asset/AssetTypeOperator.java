package helsinki.tablecodes.asset;

import java.util.Date;

import helsinki.organizational.BusinessUnit;
import helsinki.organizational.Organization;
import helsinki.organizational.Role;
import helsinki.tablecodes.asset.definers.AssetTypeRelatedPersonaExclusivityDefiner;
import helsinki.tablecodes.asset.definers.AssetTypeOperatorExclusivityDefiner;
import ua.com.fielden.platform.entity.AbstractPersistentEntity;
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
@CompanionObject(IAssetTypeOperator.class)
@MapEntityTo

public class AssetTypeOperator extends AssetTypeRelatedPersonas {


    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(AssetTypeOperator.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @MapTo
    @Title(value = "Asset Type", desc = "Desc")
    @CompositeKeyMember(1)
    private AssetType assetType;

    

    @IsProperty
    @MapTo
    @Title(value = "Start Date", desc = "Start date of the operating")
    @DateOnly
    @CompositeKeyMember(2)
    private Date date;
    
    @IsProperty
    @MapTo
    @AfterChange(AssetTypeRelatedPersonaExclusivityDefiner.class)
    @Title(value = "Role", desc = "Role that operates assets of the specific asset Type")
    private Role role;

    
    @IsProperty
    @MapTo
    @AfterChange(AssetTypeRelatedPersonaExclusivityDefiner.class)
    @Title(value = "Business Unit", desc = "Business unit that operates assets of the specific asset Type")
    private BusinessUnit bu;

    
    @IsProperty
    @MapTo
    @AfterChange(AssetTypeRelatedPersonaExclusivityDefiner.class)
    @Title(value = "Organization", desc = "Organization that operates assets of the specific asset Type\"")
    private Organization org;


    @Observable
    @Override
    public AssetTypeOperator setDate(final Date date) {
        this.date = date;
        return this;
    }

    @Observable
    @Override
    public AssetTypeOperator setOrg(final Organization org) {
        this.org = org;
        return this;
    }


    @Override
    @Observable
    public AssetTypeOperator setBu(final BusinessUnit bu) {
        this.bu = bu;
        return this;
    }

    @Override
    @Observable
    public AssetTypeOperator setRole(final Role role) {
        this.role = role;
        return this;
    }

 
    @Override
    @Observable
    public AssetTypeOperator setAssetType(final AssetType assetType) {
        this.assetType = assetType;
        return this;
    }

}
