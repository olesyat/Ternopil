package helsinki.webapp.config.assets;

import static helsinki.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;
import static java.lang.String.format;
import static ua.com.fielden.platform.web.PrefDim.mkDim;

import java.util.Optional;

import com.google.inject.Injector;

import helsinki.assets.Asset;
import helsinki.assets.AssetServiceStatus;
import helsinki.common.LayoutComposer;
import helsinki.common.StandardActions;
import helsinki.main.menu.assets.MiAssetServiceStatus;
import helsinki.tablecodes.service.ServiceStatus;
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
 * {@link AssetServiceStatus} Web UI configuration.
 *
 * @author Developers
 *
 */

public class AssetServiceStatusWebUiConfig {

    public final EntityCentre<AssetServiceStatus> centre;
    public final EntityMaster<AssetServiceStatus> master;

    public static AssetServiceStatusWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new AssetServiceStatusWebUiConfig(injector, builder);
    }

    private AssetServiceStatusWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link AssetServiceStatus}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<AssetServiceStatus> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkGridForCentre(1, 3);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(AssetServiceStatus.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(AssetServiceStatus.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(AssetServiceStatus.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(AssetServiceStatus.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(AssetServiceStatus.class, standardEditAction);

        final EntityCentreConfig<AssetServiceStatus> ecc = EntityCentreBuilder.centreFor(AssetServiceStatus.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("asset").asMulti().autocompleter(Asset.class).also()
                .addCrit("startDate").asRange().date().also()
                .addCrit("serviceStatus").asMulti().autocompleter(ServiceStatus.class)
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("asset").order(1).asc().minWidth(100)
                .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", AssetServiceStatus.ENTITY_TITLE))
                .withActionSupplier(builder.getOpenMasterAction(Asset.class)).also()
                .addProp("startDate").order(2).desc().width(150).also()
                .addProp("serviceStatus").order(3).asc().width(150)
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiAssetServiceStatus.class, MiAssetServiceStatus.class.getSimpleName(), ecc, injector, null);
    }

    /**
     * Creates entity master for {@link AssetServiceStatus}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<AssetServiceStatus> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkGridForMasterFitWidth(3, 1);

        final IMaster<AssetServiceStatus> masterConfig = new SimpleMasterBuilder<AssetServiceStatus>().forEntity(AssetServiceStatus.class)
                .addProp("asset").asAutocompleter().also()
                .addProp("startDate").asDatePicker().also()
                .addProp("serviceStatus").asAutocompleter().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(AssetServiceStatus.class, masterConfig, injector);
    }
}