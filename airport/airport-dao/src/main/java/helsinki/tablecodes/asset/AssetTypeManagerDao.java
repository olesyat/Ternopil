package helsinki.tablecodes.asset;


import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAssetTypeManager}.
 *
 * @author Developers
 *
 */
@EntityType(AssetTypeManager.class)
public class AssetTypeManagerDao extends CommonEntityDao<AssetTypeManager> implements IAssetTypeManager {

    @Inject
    public AssetTypeManagerDao(final IFilter filter) {
        super(filter);
    }

    
    @Override
        public AssetTypeManager new_() {
            final AssetTypeManager ownership = super.new_();
            ownership.getProperty("role").setRequired(true);
            ownership.getProperty("bu").setRequired(true);
            ownership.getProperty("org").setRequired(true);
            
            return ownership; 
        }
    
    @Override
    protected IFetchProvider<AssetTypeManager> createFetchProvider() {
        return FETCH_PROVIDER;
        }
}
