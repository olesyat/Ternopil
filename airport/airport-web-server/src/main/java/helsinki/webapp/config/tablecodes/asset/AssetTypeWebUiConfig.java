package helsinki.webapp.config.tablecodes.asset;

import static helsinki.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;
import static java.lang.String.format;
import static ua.com.fielden.platform.web.PrefDim.mkDim;

import java.util.Optional;

import com.google.inject.Injector;

import helsinki.common.LayoutComposer;
import helsinki.common.StandardActions;
import helsinki.main.menu.tablecodes.asset.MiAssetType;
import helsinki.organizational.BusinessUnit;
import helsinki.organizational.Organization;
import helsinki.organizational.Role;
import helsinki.tablecodes.asset.AssetClass;
import helsinki.tablecodes.asset.AssetType;
import helsinki.tablecodes.asset.producers.AssetTypeProducer;
import ua.com.fielden.platform.web.PrefDim.Unit;
import ua.com.fielden.platform.web.action.CentreConfigurationWebUiConfig.CentreConfigActions;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.centre.api.EntityCentreConfig;
import ua.com.fielden.platform.web.centre.api.actions.EntityActionConfig;
import ua.com.fielden.platform.web.centre.api.impl.EntityCentreBuilder;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;
/**
 * {@link AssetType} Web UI configuration.
 *
 * @author Developers
 *
 */
public class AssetTypeWebUiConfig {

    public final EntityCentre<AssetType> centre;
    public final EntityMaster<AssetType> master;

    public static AssetTypeWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new AssetTypeWebUiConfig(injector, builder);
    }

    private AssetTypeWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link AssetType}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<AssetType> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 2);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(AssetType.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(AssetType.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(AssetType.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(AssetType.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(AssetType.class, standardEditAction);

        final EntityCentreConfig<AssetType> ecc = EntityCentreBuilder.centreFor(AssetType.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("this").asMulti().autocompleter(AssetType.class).also()
                .addCrit("desc").asMulti().text().also()
                .addCrit("assetClass").asMulti().autocompleter(AssetClass.class).also()
                .addCrit("active").asMulti().bool()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("this").order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", AssetType.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp("desc").minWidth(100).also()
                .addProp("assetClass")
                    .width(150)
                    .withActionSupplier(builder.getOpenMasterAction(AssetClass.class)).also()
                .addProp("active").width(100)

                //.addProp("prop").minWidth(100).withActionSupplier(builder.getOpenMasterAction(Entity.class)).also()
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiAssetType.class, MiAssetType.class.getSimpleName(), ecc, injector, null);
    }

    /**
     * Creates entity master for {@link AssetType}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<AssetType> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(1,1,2, 4, 4, 4);

        final IMaster<AssetType> masterConfig = new SimpleMasterBuilder<AssetType>().forEntity(AssetType.class)
                .addProp("name").asSinglelineText().also()
                .addProp("desc").asMultilineText().also()
                .addProp("assetClass").asAutocompleter().also()
                .addProp("active").asCheckbox().also()
                .addProp("currOwnership.role").asAutocompleter().also()
                .addProp("currOwnership.org").asAutocompleter().also()
                .addProp("currOwnership.bu").asAutocompleter().also()
                .addProp("currOwnership.startDate").asDatePicker().also()
                .addProp("currOperator.role").asAutocompleter().also()
                .addProp("currOperator.org").asAutocompleter().also()
                .addProp("currOperator.bu").asAutocompleter().also()
                .addProp("currOperator.startDate").asDatePicker().also()
                .addProp("currManager.role").asAutocompleter().also()
                .addProp("currManager.org").asAutocompleter().also()
                .addProp("currManager.bu").asAutocompleter().also()
                .addProp("currManager.startDate").asDatePicker().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_THREE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(AssetType.class, AssetTypeProducer.class, masterConfig, injector);
    }
}
