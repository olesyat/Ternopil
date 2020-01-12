package helsinki.tablecodes.asset;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAssetOwnership}.
 *
 * @author Developers
 *
 */
@EntityType(AssetOwnership.class)
public class AssetOwnershipDao extends CommonEntityDao<AssetOwnership> implements IAssetOwnership {

    @Inject
    public AssetOwnershipDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<AssetOwnership> createFetchProvider() {
        // TODO: uncomment the following line and specify the properties, which are required for the UI in IAssetOwnership.FETCH_PROVIDER. Then remove the line after.
        return FETCH_PROVIDER;
        //throw new UnsupportedOperationException("Please specify the properties, which are required for the UI in IAssetOwnership.FETCH_PROVIDER");
    }
}
