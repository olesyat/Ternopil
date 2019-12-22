package helsinki.asset.reports;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAssetErrorReportLocator}.
 *
 * @author Developers
 *
 */
@EntityType(AssetErrorReportLocator.class)
public class AssetErrorReportLocatorDao extends CommonEntityDao<AssetErrorReportLocator> implements IAssetErrorReportLocator {

    @Inject
    public AssetErrorReportLocatorDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<AssetErrorReportLocator> createFetchProvider() {
        return FETCH_PROVIDER;
    }

}
