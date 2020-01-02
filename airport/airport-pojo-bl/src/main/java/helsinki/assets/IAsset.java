package helsinki.assets;

import ua.com.fielden.platform.dao.IEntityDao;
import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;

/**
 * Companion object for entity {@link Asset}.
 *
 * @author Developers
 *
 */
public interface IAsset extends IEntityDao<Asset> {


        static final String DEFAULT_ASSET_NUMBER = "NEXT NUMBER WILL BE GENERATED UPON SAVE";

        static final IFetchProvider<Asset> FETCH_PROVIDER = EntityUtils.fetch(Asset.class)
                .with("number", "desc", "regulatory", "keyAsset", "finDet", 
                        "serviceStatus", "expDays");

    }