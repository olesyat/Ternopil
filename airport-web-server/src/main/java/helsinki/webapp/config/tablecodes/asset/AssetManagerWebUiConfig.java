package helsinki.webapp.config.tablecodes.asset;

import static helsinki.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;
import static java.lang.String.format;
import static ua.com.fielden.platform.web.PrefDim.mkDim;

import java.util.Optional;

import com.google.inject.Injector;

import helsinki.assets.Asset;
import helsinki.common.LayoutComposer;
import helsinki.common.StandardActions;
import helsinki.main.menu.tablecodes.asset.MiAssetManager;
import helsinki.organizational.BusinessUnit;
import helsinki.organizational.Organization;
import helsinki.organizational.Role;
import helsinki.tablecodes.asset.AssetManager;
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
 * {@link AssetManager} Web UI configuration.
 *
 * @author Developers
 *
 */
public class AssetManagerWebUiConfig {

    public final EntityCentre<AssetManager> centre;
    public final EntityMaster<AssetManager> master;

    public static AssetManagerWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new AssetManagerWebUiConfig(injector, builder);
    }

    private AssetManagerWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link AssetManager}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<AssetManager> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 3);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(AssetManager.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(AssetManager.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(AssetManager.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(AssetManager.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(AssetManager.class, standardEditAction);

        final EntityCentreConfig<AssetManager> ecc = EntityCentreBuilder.centreFor(AssetManager.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("asset").asMulti().autocompleter(Asset.class).also()
                .addCrit("startDate").asRange().date().also()
                .addCrit("role").asMulti().autocompleter(Role.class).also()
                .addCrit("bu").asMulti().autocompleter(BusinessUnit.class).also()
                .addCrit("org").asMulti().autocompleter(Organization.class)
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("asset").order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", AssetManager.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp("startDate").order(2).desc().width(150).also()
                .addProp("role").minWidth(100).also()
                .addProp("bu").minWidth(100).also()
                .addProp("org").minWidth(100)
                //.addProp("prop").minWidth(100).withActionSupplier(builder.getOpenMasterAction(Entity.class)).also()
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiAssetManager.class, MiAssetManager.class.getSimpleName(), ecc, injector, null);
    }

    /**
     * Creates entity master for {@link AssetManager}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<AssetManager> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(4, 1);

        final IMaster<AssetManager> masterConfig = new SimpleMasterBuilder<AssetManager>().forEntity(AssetManager.class)
                .addProp("asset").asAutocompleter().also()
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

        return new EntityMaster<>(AssetManager.class, masterConfig, injector);
    }
}