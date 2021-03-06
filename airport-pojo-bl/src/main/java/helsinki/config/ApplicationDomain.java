package helsinki.config;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import helsinki.assets.Asset;
import helsinki.assets.AssetFinDet;
import helsinki.assets.AssetServiceStatus;
import helsinki.organizational.BusinessUnit;
import helsinki.organizational.Organization;
import helsinki.organizational.Role;
import helsinki.personnel.Person;
import helsinki.projects.Project;
import helsinki.tablecodes.asset.AssetClass;
import helsinki.tablecodes.asset.AssetType;
import helsinki.tablecodes.asset.AssetTypeManager;
import helsinki.tablecodes.asset.AssetTypeOperator;
//import helsinki.tablecodes.asset.AssetTypeOwnership;
import helsinki.tablecodes.asset.master.menu.actions.AssetClassMaster_OpenAssetType_MenuItem;
import helsinki.tablecodes.asset.master.menu.actions.AssetClassMaster_OpenMain_MenuItem;
import helsinki.tablecodes.asset.ui_actions.OpenAssetClassMasterAction;
import helsinki.tablecodes.service.ConditionRating;
import helsinki.tablecodes.service.ServiceStatus;
import ua.com.fielden.platform.basic.config.IApplicationDomainProvider;
import ua.com.fielden.platform.domain.PlatformDomainTypes;
import ua.com.fielden.platform.entity.AbstractEntity;
import helsinki.tablecodes.asset.AbstractOwnership;
import helsinki.tablecodes.asset.AssetTypeOwnership;
import helsinki.tablecodes.asset.AssetOwnership;
import helsinki.tablecodes.asset.AssetOperator;
import helsinki.tablecodes.asset.AssetManager;

/**
 * A class to register domain entities.
 * 
 * @author Developers
 * 
 */
public class ApplicationDomain implements IApplicationDomainProvider {
	private static final Set<Class<? extends AbstractEntity<?>>> entityTypes = new LinkedHashSet<>();
	private static final Set<Class<? extends AbstractEntity<?>>> domainTypes = new LinkedHashSet<>();

	static {
		entityTypes.addAll(PlatformDomainTypes.types);
		add(Person.class);
		add(AssetClass.class);
		add(AssetType.class);
		add(Asset.class);

		add(OpenAssetClassMasterAction.class);
		add(AssetClassMaster_OpenMain_MenuItem.class);
		add(AssetClassMaster_OpenAssetType_MenuItem.class);

		add(Project.class);
		add(AssetServiceStatus.class);
		add(ConditionRating.class);
		add(ServiceStatus.class);
		add(AssetFinDet.class);

		add(Role.class);
		add(BusinessUnit.class);
		add(Organization.class);

		//add(AssetTypeOwnership.class);
		add(AssetTypeManager.class);
		add(AssetTypeOperator.class);

		add(AssetTypeOwnership.class);
		add(AssetOwnership.class);
		add(AssetOperator.class);
		add(AssetManager.class);
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