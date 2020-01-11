package helsinki.tablecodes.asset;


import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.cond;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.expr;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchAggregates;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchAll;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchAllAndInstrument;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchAllInclCalc;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchAllInclCalcAndInstrument;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchAndInstrument;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchIdOnly;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchKeyAndDescOnly;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchKeyAndDescOnlyAndInstrument;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchOnly;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetchOnlyAndInstrument;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.from;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.orderBy;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.select;
import static ua.com.fielden.platform.utils.EntityUtils.fetch;

import ua.com.fielden.platform.dao.QueryExecutionModel;
import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.annotation.Calculated;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.DescRequired;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Readonly;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.titles.PathTitle;
import ua.com.fielden.platform.entity.annotation.titles.Subtitles;
import ua.com.fielden.platform.entity.query.EntityAggregates;
import ua.com.fielden.platform.entity.query.fluent.fetch;
import ua.com.fielden.platform.entity.query.model.AggregatedResultQueryModel;
import ua.com.fielden.platform.entity.query.model.EntityResultQueryModel;
import ua.com.fielden.platform.entity.query.model.ExpressionModel;
import ua.com.fielden.platform.entity.query.model.OrderingModel;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Developers
 *
 **/


@KeyType(DynamicEntityKey.class)
@KeyTitle("Asset Type")
@CompanionObject(IAssetType.class)
@MapEntityTo
@DescTitle("Description")
@DisplayDescription
@DescRequired

public class AssetType extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(AssetType.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
  
    @IsProperty
    @MapTo
    @Title(value = "name", desc = "Asset Type name")
    @CompositeKeyMember(1)
    private String name;

    @IsProperty
    @MapTo
    @Title(value = "Asset Class", desc = "An asset class for this type.")
    private AssetClass assetClass;
    
    @IsProperty
    @Readonly
    @Calculated
    @Title(value = "Curr Manager", desc = "Current Manager for this type.")
    @Subtitles({@PathTitle(path="role", title="Manager Role"),
                @PathTitle(path="bu", title="Manager Business Unit"),
                @PathTitle(path="org", title="Manager Organization"),
                @PathTitle(path="startDate", title="Manager Start Date")})
    private AssetTypeManager currManager;
    
    private static final EntityResultQueryModel<AssetTypeManager> ManagerSubQuery = select(AssetTypeManager.class).where()
                                                                                .prop("assetType").eq().extProp("assetType").and()
                                                                                .prop("startDate").le().now().and()
                                                                                .prop("startDate").gt().extProp("startDate").model();
    
    protected static final ExpressionModel currManager_ = expr().model(select(AssetTypeManager.class)
                                                                .where().prop("assetType").eq().extProp("id").and()
                                                                .prop("startDate").le().now().and()
                                                                .notExists(ManagerSubQuery).model()).model();

    @IsProperty
    @Readonly
    @Calculated
    @Title(value = "Curr Ownership", desc = "Desc")
    @Subtitles({@PathTitle(path="role", title="Ownership Role"),
                @PathTitle(path="bu", title="Ownership Business Unit"),
                @PathTitle(path="org", title="Ownership Organization"),
                @PathTitle(path="startDate", title="Ownership Start Date")})
    private AssetTypeOwnership currOwnership;
    
    private static final EntityResultQueryModel<AssetTypeOwnership> ownershipSubQuery = select(AssetTypeOwnership.class).where()
                                                                                .prop("assetType").eq().extProp("assetType").and()
                                                                                .prop("startDate").le().now().and()
                                                                                .prop("startDate").gt().extProp("startDate").model();
            
    protected static final ExpressionModel currOwnership_ = expr().model(select(AssetTypeOwnership.class)
                                                            .where().prop("assetType").eq().extProp("id").and()
                                                            .prop("startDate").le().now().and()
                                                            .notExists(ownershipSubQuery).model()).model();


    
    @Observable
    protected AssetType setCurrManager(final AssetTypeManager currManager) {
        this.currManager = currManager;
        return this;
    }

    public AssetTypeManager getCurrManager() {
        return currManager;
    }
    
    @IsProperty
    @Readonly
    @Calculated
    @Title(value = "Curr Operator", desc = "Desc")
    @Subtitles({@PathTitle(path="role", title="Operator Role"),
                @PathTitle(path="bu", title="Operator Business Unit"),
                @PathTitle(path="org", title="Operator Organization"),
                @PathTitle(path="startDate", title="Operator Start Date")})
    private AssetTypeOperator currOperator;
    
    private static final EntityResultQueryModel<AssetTypeOperator> OperatorSubQuery= select(AssetTypeOperator.class).where()
                                                                                .prop("assetType").eq().extProp("assetType").and()
                                                                                .prop("startDate").le().now().and()
                                                                                .prop("startDate").gt().extProp("startDate").model();
            
    protected static final ExpressionModel currOperator_ = expr().model(select(AssetTypeOperator.class)
                                                            .where().prop("assetType").eq().extProp("id").and()
                                                            .prop("startDate").le().now().and()
                                                            .notExists(OperatorSubQuery).model()).model();
    


    @Observable
    public AssetType setAssetClass(final AssetClass assetClass) {
        this.assetClass = assetClass;
        return this;
    }

    public AssetClass getAssetClass() {
        return assetClass;
    }

    
    @Observable
    public AssetType setName(final String name) {
        this.name = name;
        return this;
    }
    

    public String getName() {
        return name;
    }
    
    @Override
    @Observable
    public AssetType setDesc(String desc) {
        super.setDesc(desc);
        return this;
    }
    
    @Override
    @Observable
    public AssetType setActive(boolean active) {
        super.setActive(active);
        return this;
    }
    
    @Observable
    protected AssetType setCurrOwnership(final AssetTypeOwnership currOwnership) {
        this.currOwnership = currOwnership;
        return this;
    }

    public AssetTypeOwnership getCurrOwnership() {
        return currOwnership;
    }
    
    @Observable
    protected AssetType setCurrOperator(final AssetTypeOperator currOperator) {
        this.currOperator = currOperator;
        return this;
    }

    public AssetTypeOperator getCurrOperator() {
        return currOperator;
    }
    

}