package helsinki.webapp.config.organizational;

import static java.lang.String.format;
import static helsinki.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import helsinki.organizational.BusinessUnit;
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
import helsinki.main.menu.organizational.MiBusinessUnit;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;
/**
 * {@link BusinessUnit} Web UI configuration.
 *
 * @author Developers
 *
 */
public class BusinessUnitWebUiConfig {

    public final EntityCentre<BusinessUnit> centre;
    public final EntityMaster<BusinessUnit> master;

    public static BusinessUnitWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new BusinessUnitWebUiConfig(injector, builder);
    }

    private BusinessUnitWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link BusinessUnit}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<BusinessUnit> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkGridForCentre(1, 1);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(BusinessUnit.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(BusinessUnit.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(BusinessUnit.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(BusinessUnit.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(BusinessUnit.class, standardEditAction);

        final EntityCentreConfig<BusinessUnit> ecc = EntityCentreBuilder.centreFor(BusinessUnit.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("name").asMulti().text()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("name").order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", BusinessUnit.ENTITY_TITLE))
                    .withAction(standardEditAction)
                    .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiBusinessUnit.class, MiBusinessUnit.class.getSimpleName(), ecc, injector, null);
    }

    /**
     * Creates entity master for {@link BusinessUnit}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<BusinessUnit> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkGridForMasterFitWidth(1, 1);

        final IMaster<BusinessUnit> masterConfig = new SimpleMasterBuilder<BusinessUnit>().forEntity(BusinessUnit.class)
                .addProp("name").asSinglelineText().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(BusinessUnit.class, masterConfig, injector);
    }
}