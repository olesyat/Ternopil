package helsinki.tablecodes.asset;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAssetOperator}.
 *
 * @author Developers
 *
 */
@EntityType(AssetOperator.class)
public class AssetOperatorDao extends CommonEntityDao<AssetOperator> implements IAssetOperator {

    @Inject
    public AssetOperatorDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<AssetOperator> createFetchProvider() {
        // TODO: uncomment the following line and specify the properties, which are required for the UI in IAssetOperator.FETCH_PROVIDER. Then remove the line after.
         return FETCH_PROVIDER;
        //throw new UnsupportedOperationException("Please specify the properties, which are required for the UI in IAssetOperator.FETCH_PROVIDER");
    }
}
