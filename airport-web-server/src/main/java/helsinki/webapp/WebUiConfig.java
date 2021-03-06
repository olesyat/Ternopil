package helsinki.webapp;

import org.apache.commons.lang.StringUtils;

import helsinki.assets.Asset;
import helsinki.assets.AssetFinDet;
import helsinki.assets.AssetServiceStatus;
import helsinki.config.personnel.PersonWebUiConfig;
import helsinki.organizational.BusinessUnit;
import helsinki.organizational.Organization;
import helsinki.organizational.Role;
import helsinki.projects.Project;
import helsinki.tablecodes.asset.AssetClass;
import helsinki.tablecodes.asset.AssetManager;
import helsinki.tablecodes.asset.AssetOperator;
import helsinki.tablecodes.asset.AssetOwnership;
import helsinki.tablecodes.asset.AssetType;
import helsinki.tablecodes.asset.AssetTypeManager;
import helsinki.tablecodes.asset.AssetTypeOperator;
import helsinki.tablecodes.asset.AssetTypeOwnership;
import helsinki.tablecodes.service.ConditionRating;
import helsinki.tablecodes.service.ServiceStatus;
import helsinki.webapp.config.assets.AssetFinDetWebUiConfig;
import helsinki.webapp.config.assets.AssetServiceStatusWebUiConfig;
import helsinki.webapp.config.assets.AssetWebUiConfig;
import helsinki.webapp.config.organizational.BusinessUnitWebUiConfig;
import helsinki.webapp.config.organizational.OrganizationWebUiConfig;
import helsinki.webapp.config.organizational.RoleWebUiConfig;
import helsinki.webapp.config.projects.ProjectWebUiConfig;
import helsinki.webapp.config.tablecodes.asset.AssetClassWebUiConfig;
import helsinki.webapp.config.tablecodes.asset.AssetManagerWebUiConfig;
import helsinki.webapp.config.tablecodes.asset.AssetOperatorWebUiConfig;
import helsinki.webapp.config.tablecodes.asset.AssetOwnershipWebUiConfig;
import helsinki.webapp.config.tablecodes.asset.AssetTypeManagerWebUiConfig;
import helsinki.webapp.config.tablecodes.asset.AssetTypeOperatorWebUiConfig;
import helsinki.webapp.config.tablecodes.asset.AssetTypeOwnershipWebUiConfig;
import helsinki.webapp.config.tablecodes.asset.AssetTypeWebUiConfig;
import helsinki.webapp.config.tablecodes.service.ConditionRatingWebUiConfig;
import helsinki.webapp.config.tablecodes.service.ServiceStatusWebUiConfig;
import ua.com.fielden.platform.basic.config.Workflows;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.resources.webui.AbstractWebUiConfig;
import ua.com.fielden.platform.web.resources.webui.UserRoleWebUiConfig;
import ua.com.fielden.platform.web.resources.webui.UserWebUiConfig;

/**
 * App-specific {@link IWebApp} implementation.
 *
 * @author Generated
 *
 */
public class WebUiConfig extends AbstractWebUiConfig {

    private final String domainName;
    private final String path;
    private final int port;

    public WebUiConfig(final String domainName, final int port, final Workflows workflow, final String path) {
        super("Ternopil Airport Asset Management (development)", workflow, new String[] { "helsinki/" });
        if (StringUtils.isEmpty(domainName) || StringUtils.isEmpty(path)) {
            throw new IllegalArgumentException("Both the domain name and application binding path should be specified.");
        }
        this.domainName = domainName;
        this.port = port;
        this.path = path;
    }


    @Override
    public String getDomainName() {
        return domainName;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public int getPort() {
        return port;
    }

    /**
     * Configures the {@link WebUiConfig} with custom centres and masters.
     */
    @Override
    public void initConfiguration() {
        super.initConfiguration();

        final IWebUiBuilder builder = configApp();
        builder.setDateFormat("DD/MM/YYYY").setTimeFormat("HH:mm").setTimeWithMillisFormat("HH:mm:ss")
        // Minimum tablet width defines the boundary below which mobile layout takes place.
        // For example for Xiaomi Redmi 4x has official screen size of 1280x640,
        // still its viewport sizes is twice lesser: 640 in landscape orientation and 360 in portrait orientation.
        // When calculating reasonable transition tablet->mobile we need to consider "real" viewport sizes instead of physical pixel sizes (http://viewportsizes.com).
        .setMinTabletWidth(600);

        // Personnel
        final PersonWebUiConfig personWebUiConfig = PersonWebUiConfig.register(injector(), builder);
        final UserWebUiConfig userWebUiConfig = new UserWebUiConfig(injector());
        final UserRoleWebUiConfig userRoleWebUiConfig = new UserRoleWebUiConfig(injector());
        
        // Asset table codes
        final AssetClassWebUiConfig assetClassWebUiConfig = AssetClassWebUiConfig.register(injector(), builder);
        final AssetTypeWebUiConfig assetTypeWebUiConfig = AssetTypeWebUiConfig.register(injector(), builder);
        final AssetTypeOwnershipWebUiConfig assetTypeOwnershipWebUiConfig = AssetTypeOwnershipWebUiConfig.register(injector(), builder);
        final AssetTypeOperatorWebUiConfig assetTypeOperatorshipWebUiConfig = AssetTypeOperatorWebUiConfig.register(injector(), builder);
        final AssetTypeManagerWebUiConfig assetTypeManagementWebUiConfig = AssetTypeManagerWebUiConfig.register(injector(), builder);
      
        // Service Status table codes
        final ServiceStatusWebUiConfig serviceStatusWebUiConfig = ServiceStatusWebUiConfig.register(injector(), builder);
        final ConditionRatingWebUiConfig conditionRatingWebUiConfig = ConditionRatingWebUiConfig.register(injector(), builder);
      
        // Asset instance
        final AssetWebUiConfig assetWebUiConfig = AssetWebUiConfig.register(injector(), builder);
        final AssetFinDetWebUiConfig assetFinDetWebUiConfig = AssetFinDetWebUiConfig.register(injector(), builder);
        final AssetOwnershipWebUiConfig assetOwnershipWebUiConfig = AssetOwnershipWebUiConfig.register(injector(), builder);
        final AssetManagerWebUiConfig assetManagementWebUiConfig = AssetManagerWebUiConfig.register(injector(), builder);
        final AssetOperatorWebUiConfig assetOperatorshipWebUiConfig = AssetOperatorWebUiConfig.register(injector(), builder);

        
        // Organizational
        final RoleWebUiConfig roleWebUiConfig = RoleWebUiConfig.register(injector(), builder);
        final BusinessUnitWebUiConfig buWebUiConfig = BusinessUnitWebUiConfig.register(injector(), builder);
        final OrganizationWebUiConfig orgWebUiConfig = OrganizationWebUiConfig.register(injector(), builder);
        final AssetServiceStatusWebUiConfig assetServiceStatusWebUiConfig = AssetServiceStatusWebUiConfig.register(injector(), builder);
       
        
        // Project related UI
        final ProjectWebUiConfig projectWebUiConfig = ProjectWebUiConfig.register(injector(), builder);


        
        
        
        
        // Configure application web resources such as masters and centres
        configApp()
        .addMaster(userWebUiConfig.master)
        .addMaster(userWebUiConfig.rolesUpdater)
        .addMaster(userRoleWebUiConfig.master)
        .addMaster(userRoleWebUiConfig.tokensUpdater)
        // user/personnel module
        .addCentre(userWebUiConfig.centre)
        .addCentre(userRoleWebUiConfig.centre);

        // Configure application menu
        configDesktopMainMenu().
        addModule("Asset Acquisition").
        description("Asset acquisition module").
        icon("mainMenu:equipment").
        detailIcon("mainMenu:equipment").
        bgColor("#6a89cc").
        captionBgColor("#4a69bd").menu()
            .addMenuItem(Asset.ENTITY_TITLE).description(String.format("%s Centre", Asset.ENTITY_TITLE)).centre(assetWebUiConfig.centre).done()
            .addMenuItem(AssetFinDet.ENTITY_TITLE).description(String.format("%s Centre", AssetFinDet.ENTITY_TITLE)).centre(assetFinDetWebUiConfig.centre).done()
            .addMenuItem(AssetOwnership.ENTITY_TITLE).description(String.format("%s Centre", AssetOwnership.ENTITY_TITLE)).centre(assetOwnershipWebUiConfig.centre).done()
            .addMenuItem(AssetManager.ENTITY_TITLE).description(String.format("%s Centre", AssetManager.ENTITY_TITLE)).centre(assetManagementWebUiConfig.centre).done()
            .addMenuItem(AssetOperator.ENTITY_TITLE).description(String.format("%s Centre", AssetOperator.ENTITY_TITLE)).centre(assetOperatorshipWebUiConfig.centre).done()
            .addMenuItem(Project.ENTITY_TITLE).description(String.format("%s Centre", Project.ENTITY_TITLE)).centre(projectWebUiConfig.centre).done()
            .done().done().
        addModule("Users / Personnel").
            description("Provides functionality for managing application security and personnel data.").
            icon("mainMenu:help").
            detailIcon("anotherMainMenu:about").
            bgColor("#f8c291").
            captionBgColor("#e55039").menu()
            .addMenuItem("Personnel").description("Personnel related data")
                .addMenuItem("Personnel").description("Personnel Centre").centre(personWebUiConfig.centre).done()
            .done()
            .addMenuItem("Users").description("Users related data")
                .addMenuItem("Users").description("User centre").centre(userWebUiConfig.centre).done()
                .addMenuItem("User Roles").description("User roles centre").centre(userRoleWebUiConfig.centre).done()
            .done().
        done().done().
        addModule("Table Codes").
            description("Table Codes Description").
            icon("mainMenu:tablecodes").
            detailIcon("mainMenu:tablecodes").
            bgColor("#82ccdd").
            captionBgColor("#60a3bc").menu()
            .addMenuItem("Asset Table Codes").description("Various master data for assets.")
                .addMenuItem(AssetClass.ENTITY_TITLE).description(String.format("%s Centre", AssetClass.ENTITY_TITLE)).centre(assetClassWebUiConfig.centre).done()
                .addMenuItem(AssetType.ENTITY_TITLE).description(String.format("%s Centre", AssetType.ENTITY_TITLE)).centre(assetTypeWebUiConfig.centre).done()
                .addMenuItem(AssetTypeOwnership.ENTITY_TITLE).description(String.format("%s Centre", AssetTypeOwnership.ENTITY_TITLE)).centre(assetTypeOwnershipWebUiConfig.centre).done()
                .addMenuItem(AssetTypeOperator.ENTITY_TITLE).description(String.format("%s Centre", AssetTypeOperator.ENTITY_TITLE)).centre(assetTypeOperatorshipWebUiConfig.centre).done()
                .addMenuItem(AssetTypeManager.ENTITY_TITLE).description(String.format("%s Centre", AssetTypeManager.ENTITY_TITLE)).centre(assetTypeManagementWebUiConfig.centre).done()
                .addMenuItem(AssetServiceStatus.ENTITY_TITLE).description(String.format("%s Centre", AssetServiceStatus.ENTITY_TITLE)).centre(assetServiceStatusWebUiConfig.centre).done()
            .done()
            .addMenuItem("Asset Service Codes").description("Various master data for assets service.")
            .addMenuItem(ServiceStatus.ENTITY_TITLE).description(String.format("%s Centre", ServiceStatus.ENTITY_TITLE)).centre(serviceStatusWebUiConfig.centre).done()
            .addMenuItem(ConditionRating.ENTITY_TITLE).description(String.format("%s Centre", ConditionRating.ENTITY_TITLE)).centre(conditionRatingWebUiConfig.centre).done()
        .done().
        done().done()
        .addModule("Organizational").
        description("Organizational entities").
        icon("mainMenu:tablecodes").
        detailIcon("mainMenu:tablecodes").
        bgColor("#b8e994").
        captionBgColor("#78e08f").menu()
            .addMenuItem(Role.ENTITY_TITLE).description(String.format("%s Centre", Role.ENTITY_TITLE)).centre(roleWebUiConfig.centre).done()
            .addMenuItem(BusinessUnit.ENTITY_TITLE).description(String.format("%s Centre", BusinessUnit.ENTITY_TITLE)).centre(buWebUiConfig.centre).done()
            .addMenuItem(Organization.ENTITY_TITLE).description(String.format("%s Centre", Organization.ENTITY_TITLE)).centre(orgWebUiConfig.centre).done()    
    .done().done()
    .setLayoutFor(Device.DESKTOP, null, "[[[], []], [[], []]]")
    .setLayoutFor(Device.TABLET, null,  "[[[{\"rowspan\":2}], []], [[]]]")
    .setLayoutFor(Device.MOBILE, null, "[ [[]],[[]], [[]], [[]] ]")
    .minCellWidth(100).minCellHeight(148).done();
}

}


