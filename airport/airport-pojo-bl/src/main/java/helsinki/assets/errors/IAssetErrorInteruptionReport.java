package helsinki.assets.errors;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link AssetErrorInteruptionReport}.
 *
 * @author Developers
 *
 */
public interface IAssetErrorInteruptionReport extends IEntityDao<AssetErrorInteruptionReport> {

    static final IFetchProvider<AssetErrorInteruptionReport> FETCH_PROVIDER = EntityUtils.fetch(AssetErrorInteruptionReport.class).with(
        // TODO: uncomment the following line and specify the properties, which are required for the UI. Then remove the line after.
        // "key", "desc");
        "Please specify the properties, which are required for the UI");

}
