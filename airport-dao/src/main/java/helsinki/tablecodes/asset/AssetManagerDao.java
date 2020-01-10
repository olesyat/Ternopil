package helsinki.tablecodes.asset;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAssetManager}.
 *
 * @author Developers
 *
 */
@EntityType(AssetManager.class)
public class AssetManagerDao extends CommonEntityDao<AssetManager> implements IAssetManager {

    @Inject
    public AssetManagerDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<AssetManager> createFetchProvider() {
        // TODO: uncomment the following line and specify the properties, which are required for the UI in IAssetManager.FETCH_PROVIDER. Then remove the line after.
        // return FETCH_PROVIDER;
        throw new UnsupportedOperationException("Please specify the properties, which are required for the UI in IAssetManager.FETCH_PROVIDER");
    }
}
