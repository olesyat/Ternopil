package helsinki.webapp.config.tablecodes.asset;

import static java.lang.String.format;
import static helsinki.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import helsinki.tablecodes.asset.AssetType;
import helsinki.tablecodes.asset.AssetTypeManager;
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
import helsinki.main.menu.tablecodes.asset.MiAssetTypeManager;
import helsinki.organizational.BusinessUnit;
import helsinki.organizational.Organization;
import helsinki.organizational.Role;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;
/**
 * {@link AssetTypeManager} Web UI configuration.
 *
 * @author Developers
 *
 */
public class AssetTypeManagerWebUiConfig {

    public final EntityCentre<AssetTypeManager> centre;
    public final EntityMaster<AssetTypeManager> master;

    public static AssetTypeManagerWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new AssetTypeManagerWebUiConfig(injector, builder);
    }

    private AssetTypeManagerWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link AssetTypeManager}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<AssetTypeManager> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 3);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(AssetTypeManager.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(AssetTypeManager.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(AssetTypeManager.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(AssetTypeManager.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(AssetTypeManager.class, standardEditAction);

        final EntityCentreConfig<AssetTypeManager> ecc = EntityCentreBuilder.centreFor(AssetTypeManager.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("assetType").asMulti().autocompleter(AssetType.class).also()
                .addCrit("startDate").asRange().date().also()
                .addCrit("role").asMulti().autocompleter(Role.class).also()
                .addCrit("bu").asMulti().autocompleter(BusinessUnit.class).also()
                .addCrit("org").asMulti().autocompleter(Organization.class).also()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("assetType").order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", AssetTypeManager.ENTITY_TITLE))
                    .withActionSupplier(builder.getOpenMasterAction(AssetType.class)).also()
                .addProp("desc").minWidth(100)
                //.addProp("prop").minWidth(100).withActionSupplier(builder.getOpenMasterAction(Entity.class)).also()
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiAssetTypeManager.class, MiAssetTypeManager.class.getSimpleName(), ecc, injector, null);
    }

    /**
     * Creates entity master for {@link AssetTypeManager}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<AssetTypeManager> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkGridForMasterFitWidth(1, 2);

        final IMaster<AssetTypeManager> masterConfig = new SimpleMasterBuilder<AssetTypeManager>().forEntity(AssetTypeManager.class)
                .addProp("assetType").asAutocompleter().also()
                .addProp("startDate").asDatePicker().also()
                .addProp("role").asAutocompleter().also()
                .addProp("bu").asAutocompleter().also()
                .addProp("org").asAutocompleter().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(AssetTypeManager.class, masterConfig, injector);
    }
}
