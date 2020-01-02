package helsinki.tablecodes.asset;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link AssetClass}.
 *
 * @author Developers
 *
 */
public interface IAssetType extends IEntityDao<AssetClass> {

    static final IFetchProvider<AssetClass> FETCH_PROVIDER = EntityUtils.fetch(AssetClass.class)
            
       .with("name", "desc", "assetClass", "currentOwnership.role", "currentOwnership.role", 
               "currentOwnership.bu");
}
