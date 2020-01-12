package helsinki.assets;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAssetServiceStatus}.
 *
 * @author Developers
 *
 */
@EntityType(AssetServiceStatus.class)
public class AssetServiceStatusDao extends CommonEntityDao<AssetServiceStatus> implements IAssetServiceStatus {

    @Inject
    public AssetServiceStatusDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<AssetServiceStatus> createFetchProvider() {
        // TODO: uncomment the following line and specify the properties, which are required for the UI in IAssetServiceStatus.FETCH_PROVIDER. Then remove the line after.
        return FETCH_PROVIDER;
        //throw new UnsupportedOperationException("Please specify the properties, which are required for the UI in IAssetServiceStatus.FETCH_PROVIDER");
    }
}
