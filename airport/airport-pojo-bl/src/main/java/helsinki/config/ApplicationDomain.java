package helsinki.config;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import helsinki.tablecodes.asset.AssetClass;
import helsinki.personnel.Person;
import ua.com.fielden.platform.basic.config.IApplicationDomainProvider;
import ua.com.fielden.platform.domain.PlatformDomainTypes;
import ua.com.fielden.platform.entity.AbstractEntity;
import helsinki.assets.Asset;
import helsinki.tablecodes.asset.ui_actions.OpenAssetClassMasterAction;
import helsinki.tablecodes.asset.master.menu.actions.AssetClassMaster_OpenMain_MenuItem;
import helsinki.tablecodes.asset.master.menu.actions.AssetClassMaster_OpenAssetType_MenuItem;
import helsinki.assets.AssetFinDet;
import helsinki.organizational.Role;
import helsinki.projects.Project;
import helsinki.organizational.BusinessUnit;
import helsinki.organizational.Organization;
import helsinki.tablecodes.asset.AssetTypeOperator;
import helsinki.assets.AssetServiceStatus;
import helsinki.assets.errors.AssetErrorInteruptionReport;

/**
 * A class to register domain entities.
 * 
 * @author TG Team
 * 
 */
public class ApplicationDomain implements IApplicationDomainProvider {
    private static final Set<Class<? extends AbstractEntity<?>>> entityTypes = new LinkedHashSet<>();
    private static final Set<Class<? extends AbstractEntity<?>>> domainTypes = new LinkedHashSet<>();

    static {
        entityTypes.addAll(PlatformDomainTypes.types);
        add(Person.class);
        add(AssetClass.class);
        add(AssetClass.class);
        add(Asset.class);
        add(OpenAssetClassMasterAction.class);
        add(AssetClassMaster_OpenMain_MenuItem.class);
        add(AssetClassMaster_OpenAssetType_MenuItem.class);
        add(AssetFinDet.class);
        add(Role.class);
        add(Project.class);
        add(BusinessUnit.class);
        add(Organization.class);
        add(AssetTypeOperator.class);
        add(AssetServiceStatus.class);

        add(AssetErrorInteruptionReport.class);
    }

    private static void add(final Class<? extends AbstractEntity<?>> domainType) {
        entityTypes.add(domainType);
        domainTypes.add(domainType);
    }

    @Override
    public List<Class<? extends AbstractEntity<?>>> entityTypes() {
        return Collections.unmodifiableList(entityTypes.stream().collect(Collectors.toList()));
    }

    public List<Class<? extends AbstractEntity<?>>> domainTypes() {
        return Collections.unmodifiableList(domainTypes.stream().collect(Collectors.toList()));
    }
}
