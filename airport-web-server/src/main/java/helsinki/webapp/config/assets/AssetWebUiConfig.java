package helsinki.webapp.config.assets;

import static java.lang.String.format;
import static helsinki.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import org.apache.tools.ant.Project;

import com.google.inject.Injector;

import helsinki.tablecodes.asset.AssetClass;
import helsinki.tablecodes.asset.AssetType;
import helsinki.assets.Asset;
import helsinki.common.LayoutComposer;
import helsinki.common.StandardActions;

import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.action.CentreConfigurationWebUiConfig.CentreConfigActions;
import ua.com.fielden.platform.web.centre.api.EntityCentreConfig;
import ua.com.fielden.platform.web.centre.api.actions.EntityActionConfig;
import ua.com.fielden.platform.web.centre.api.impl.EntityCentreBuilder;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import helsinki.main.menu.assets.MiAsset;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;
/**
 * {@link Asset} Web UI configuration.
 *
 * @author Developers
 *
 */
public class AssetWebUiConfig {

    public final EntityCentre<Asset> centre;
    public final EntityMaster<Asset> master;

    public static AssetWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new AssetWebUiConfig(injector, builder);
    }

    private AssetWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link Asset}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<Asset> createCentre(final Injector injector, final IWebUiBuilder builder) {

        final String layout = LayoutComposer.mkGridForCentre(3, 3);


        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(Asset.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Asset.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(Asset.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(Asset.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(Asset.class, standardEditAction);

        final EntityCentreConfig<Asset> ecc = EntityCentreBuilder.centreFor(Asset.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("this").asMulti().autocompleter(Asset.class).also()
                .addCrit("desc").asMulti().text().also()
                .addCrit("assetType").asMulti().autocompleter(AssetType.class).also()
                .addCrit("finDet.initCost").asRange().decimal().also()
                .addCrit("finDet.acquireDate").asRange().date().also()
                .addCrit("rate").asMulti().text().also()
                .addCrit("regulatory").asMulti().bool().also()
                .addCrit("keyService").asMulti().bool().also()
                .addCrit("active").asMulti().bool()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("this").order(1).asc().minWidth(25)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Asset.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp("desc").minWidth(50).also()
                .addProp("finDet.initCost").width(80).also()
                .addProp("finDet.acquireDate").width(150).also()
                .addProp("finDet.project").width(80).also()
                .addProp("regulatory").width(80).also()
                .addProp("keyService").width(80).also()
                .addProp("rate").width(80)
                //.addProp("prop").minWidth(100).withActionSupplier(builder.getOpenMasterAction(Entity.class)).also()
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiAsset.class, MiAsset.class.getSimpleName(), ecc, injector, null);
    }

    /**
     * Creates entity master for {@link Asset}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<Asset> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(1, 1, 2, 4, 4, 4, 4, 4, 4, 3);

        final IMaster<Asset> masterConfig = new SimpleMasterBuilder<Asset>().forEntity(Asset.class)
                .addProp("number").asSinglelineText().also()
                .addProp("desc").asMultilineText().also()
                .addProp("assetType").asAutocompleter().also()
                .addProp("active").asCheckbox().also()
                .addProp("assetType.currOwnership.role").asAutocompleter().also()
                .addProp("assetType.currOwnership.bu").asAutocompleter().also()
                .addProp("assetType.currOwnership.org").asAutocompleter().also()
                .addProp("assetType.currOwnership.startDate").asDatePicker().also()
                .addProp("currOwnership.role").asAutocompleter().also()
                .addProp("currOwnership.bu").asAutocompleter().also()
                .addProp("currOwnership.org").asAutocompleter().also()
                .addProp("currOwnership.startDate").asDatePicker().also()
                .addProp("assetType.currOperator.role").asAutocompleter().also()
                .addProp("assetType.currOperator.bu").asAutocompleter().also()
                .addProp("assetType.currOperator.org").asAutocompleter().also()
                .addProp("assetType.currOperator.startDate").asDatePicker().also()
                .addProp("currOperator.role").asAutocompleter().also()
                .addProp("currOperator.bu").asAutocompleter().also()
                .addProp("currOperator.org").asAutocompleter().also()
                .addProp("currOperator.startDate").asDatePicker().also()
                .addProp("assetType.currManager.role").asAutocompleter().also()
                .addProp("assetType.currManager.bu").asAutocompleter().also()
                .addProp("assetType.currManager.org").asAutocompleter().also()
                .addProp("assetType.currManager.startDate").asDatePicker().also()
                .addProp("currManager.role").asAutocompleter().also()
                .addProp("currManager.bu").asAutocompleter().also()
                .addProp("currManager.org").asAutocompleter().also()
                .addProp("currManager.startDate").asDatePicker().also()             
                .addProp("regulatory").asCheckbox().also()
                .addProp("keyService").asCheckbox().also()
                .addProp("rate").asSinglelineText().also()
//                .addProp("finDet.initCost").asMoney().also()
//                .addProp("finDet.acquireDate").asDatePicker().also()
                
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_THREE_COLUMN_MASTER_DIM_WIDTH, 520, Unit.PX))
                .done();
                
        return new EntityMaster<>(Asset.class, masterConfig, injector);
    }
}