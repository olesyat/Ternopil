package helsinki.webapp.config.assets.errors;

import static helsinki.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;
import static java.lang.String.format;
import static ua.com.fielden.platform.web.PrefDim.mkDim;

import java.util.Optional;

import com.google.inject.Injector;

import helsinki.assets.Asset;
import helsinki.assets.errors.AssetErrorInteruptionReport;
import helsinki.common.LayoutComposer;
import helsinki.common.StandardActions;
import helsinki.main.menu.assets.errors.MiAssetErrorInteruptionReport;
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
 * {@link AssetErrorInteruptionReport} Web UI configuration.
 *
 * @author Developers
 *
 */
public class AssetErrorInteruptionReportWebUiConfig {

    public final EntityCentre<AssetErrorInteruptionReport> centre;
    public final EntityMaster<AssetErrorInteruptionReport> master;

    public static AssetErrorInteruptionReportWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new AssetErrorInteruptionReportWebUiConfig(injector, builder);
    }

    private AssetErrorInteruptionReportWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link AssetErrorInteruptionReport}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<AssetErrorInteruptionReport> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkGridForCentre(2, 2);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(AssetErrorInteruptionReport.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(AssetErrorInteruptionReport.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(AssetErrorInteruptionReport.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(AssetErrorInteruptionReport.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(AssetErrorInteruptionReport.class, standardEditAction);

        final EntityCentreConfig<AssetErrorInteruptionReport> ecc = EntityCentreBuilder.centreFor(AssetErrorInteruptionReport.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("asset").asMulti().autocompleter(Asset.class).also()
                .addCrit("regulatory").asMulti().bool().also()
                .addCrit("keyAsset").asMulti().bool().also()
                .addCrit("message").asSingle().text()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("this").order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", AssetErrorInteruptionReport.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp("desc").minWidth(100)
                //.addProp("prop").minWidth(100).withActionSupplier(builder.getOpenMasterAction(Entity.class)).also()
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiAssetErrorInteruptionReport.class, MiAssetErrorInteruptionReport.class.getSimpleName(), ecc, injector, null);
    }

    /**
     * Creates entity master for {@link AssetErrorInteruptionReport}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<AssetErrorInteruptionReport> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkGridForMasterFitWidth(2, 2);

        final IMaster<AssetErrorInteruptionReport> masterConfig = new SimpleMasterBuilder<AssetErrorInteruptionReport>().forEntity(AssetErrorInteruptionReport.class)
                .addProp("asset").asAutocompleter().also()
                .addProp("regulatory").asCheckbox().also()
                .addProp("keyAsset").asCheckbox().also()
                .addProp("message").asSinglelineText().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(AssetErrorInteruptionReport.class, masterConfig, injector);
    }
}