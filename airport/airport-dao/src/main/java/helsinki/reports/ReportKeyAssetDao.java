package helsinki.reports;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import helsinki.security.tokens.persistent.ReportKeyAsset_CanSave_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IReportKeyAsset}.
 *
 * @author Developers
 *
 */
@EntityType(ReportKeyAsset.class)
public class ReportKeyAssetDao extends CommonEntityDao<ReportKeyAsset> implements IReportKeyAsset {

    @Inject
    public ReportKeyAssetDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(ReportKeyAsset_CanSave_Token.class)
    public ReportKeyAsset save(ReportKeyAsset entity) {
        return super.save(entity);
    }

    @Override
    protected IFetchProvider<ReportKeyAsset> createFetchProvider() {
        // TODO: uncomment the following line and specify the properties, which are required for the UI in IReportKeyAsset.FETCH_PROVIDER. Then remove the line after.
        // return FETCH_PROVIDER;
        throw new UnsupportedOperationException("Please specify the properties, which are required for the UI in IReportKeyAsset.FETCH_PROVIDER");
    }
}
