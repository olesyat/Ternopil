package helsinki.reports;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IReportKeyAssetLocator}.
 *
 * @author Developers
 *
 */
@EntityType(ReportKeyAssetLocator.class)
public class ReportKeyAssetLocatorDao extends CommonEntityDao<ReportKeyAssetLocator> implements IReportKeyAssetLocator {

    @Inject
    public ReportKeyAssetLocatorDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<ReportKeyAssetLocator> createFetchProvider() {
        return FETCH_PROVIDER;
    }

}
