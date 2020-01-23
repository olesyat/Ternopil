package helsinki.assets.errors;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAssetErrorInteruptionReport}.
 *
 * @author Developers
 *
 */
@EntityType(AssetErrorInteruptionReport.class)
public class AssetErrorInteruptionReportDao extends CommonEntityDao<AssetErrorInteruptionReport> implements IAssetErrorInteruptionReport {

    @Inject
    public AssetErrorInteruptionReportDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<AssetErrorInteruptionReport> createFetchProvider() {
        // TODO: uncomment the following line and specify the properties, which are required for the UI in IAssetErrorInteruptionReport.FETCH_PROVIDER. Then remove the line after.
        // return FETCH_PROVIDER;
        throw new UnsupportedOperationException("Please specify the properties, which are required for the UI in IAssetErrorInteruptionReport.FETCH_PROVIDER");
    }
}
