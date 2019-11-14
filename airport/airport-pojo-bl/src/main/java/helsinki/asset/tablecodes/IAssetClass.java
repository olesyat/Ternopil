package helsinki.asset.tablecodes;

import ua.com.fielden.platform.dao.IEntityDao;
import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;

/**
 * Companion object for entity {@link AssetClass}.
 *
 * @author Developers
 *
 */
public interface IAssetClass extends IEntityDao<AssetClass> {

    static final IFetchProvider<AssetClass> FETCH_PROVIDER = EntityUtils.fetch(AssetClass.class)
            .with("key", "desc");
}
