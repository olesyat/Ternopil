package helsinki.tablecodes.asset;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAssetTypeOperator}.
 *
 * @author Developers
 *
 */
@EntityType(AssetTypeOperator.class)
public class AssetTypeOperatorDao extends CommonEntityDao<AssetTypeOperator> implements IAssetTypeOperator {

    @Inject
    public AssetTypeOperatorDao(final IFilter filter) {
        super(filter);
    }

    
    @Override
    public AssetTypeOperator new_() {
        final AssetTypeOperator operator = super.new_();
        operator.getProperty("role").setRequired(true);
        operator.getProperty("bu").setRequired(true);
        operator.getProperty("org").setRequired(true);
        
        return operator; 
    }
    
    @Override
    protected IFetchProvider<AssetTypeOperator> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}
