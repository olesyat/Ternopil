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
            final AssetTypeManager manager = super.new_();
            manager.getProperty("role").setRequired(true);
            manager.getProperty("bu").setRequired(true);
            manager.getProperty("org").setRequired(true);
            
            return manager; 
        }
    
    @Override
    protected IFetchProvider<AssetTypeManager> createFetchProvider() {
        return FETCH_PROVIDER;
        }
}
