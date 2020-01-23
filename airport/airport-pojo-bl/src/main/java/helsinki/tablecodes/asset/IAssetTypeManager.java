package helsinki.tablecodes.asset;


import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link AssetTypeManager}.
 *
 * @author Developers
 *
 */
public interface IAssetTypeManager extends IEntityDao<AssetTypeManager> {

    static final IFetchProvider<AssetTypeManager> FETCH_PROVIDER = EntityUtils.fetch(AssetTypeManager.class).with(
        "assetType", "startDate", "role", "bu", "org");

}

