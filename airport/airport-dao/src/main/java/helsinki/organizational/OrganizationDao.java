package helsinki.organizational;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IOrganization}.
 *
 * @author Developers
 *
 */
@EntityType(Organization.class)
public class OrganizationDao extends CommonEntityDao<Organization> implements IOrganization {

    @Inject
    public OrganizationDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<Organization> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}
