package helsinki.asset.reports;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import helsinki.security.tokens.persistent.AssetErrorReport_CanSave_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAssetErrorReport}.
 *
 * @author Developers
 *
 */
@EntityType(AssetErrorReport.class)
public class AssetErrorReportDao extends CommonEntityDao<AssetErrorReport> implements IAssetErrorReport {

    @Inject
    public AssetErrorReportDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(AssetErrorReport_CanSave_Token.class)
    public AssetErrorReport save(AssetErrorReport entity) {
        return super.save(entity);
    }

    @Override
    protected IFetchProvider<AssetErrorReport> createFetchProvider() {
        // TODO: uncomment the following line and specify the properties, which are required for the UI in IAssetErrorReport.FETCH_PROVIDER. Then remove the line after.
        // return FETCH_PROVIDER;
        throw new UnsupportedOperationException("Please specify the properties, which are required for the UI in IAssetErrorReport.FETCH_PROVIDER");
    }
}
