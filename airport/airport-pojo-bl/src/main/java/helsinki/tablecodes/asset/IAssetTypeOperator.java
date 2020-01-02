package helsinki.tablecodes.asset;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link AssetTypeOperator}.
 *
 * @author Developers
 *
 */
public interface IAssetTypeOperator extends IEntityDao<AssetTypeOperator> {

    static final IFetchProvider<AssetTypeOperator> FETCH_PROVIDER = EntityUtils.fetch(AssetTypeOperator.class).with(
       "assetType", "date", "role", "org", "bu");

}
