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
                .with("number", "desc", "assetType", "finDet", "regulatory", "keyService", "rate")
                .with("assetType.currOwnership.role", "assetType.currOwnership.bu", "assetType.currOwnership.org", "assetType.currOwnership.startDate")
                .with("currOwnership.role", "currOwnership.bu", "currOwnership.org", "currOwnership.startDate")
                .with("assetType.currManager.role", "assetType.currManager.bu", "assetType.currManager.org", "assetType.currManager.startDate")
                .with("currManager.role", "currManager.bu", "currManager.org", "currManager.startDate")
                .with("assetType.currOperator.role", "assetType.currOperator.bu", "assetType.currOperator.org", "assetType.currOperator.startDate")
                .with("currOperator.role", "currOperator.bu", "currOperator.org", "currOperator.startDate");

    }