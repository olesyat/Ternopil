package helsinki.assets;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link AssetServiceStatus}.
 *
 * @author Developers
 *
 */
public interface IAssetServiceStatus extends IEntityDao<AssetServiceStatus> {

    static final IFetchProvider<AssetServiceStatus> FETCH_PROVIDER = EntityUtils.fetch(AssetServiceStatus.class).with(
        // TODO: uncomment the following line and specify the properties, which are required for the UI. Then remove the line after.
        // "key", "desc");
        "Please specify the properties, which are required for the UI");

}
