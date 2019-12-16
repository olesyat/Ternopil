package helsinki.tablecodes.asset.ui_actions.producers;

import com.google.inject.Inject;

import helsinki.tablecodes.asset.AssetClass;
import helsinki.tablecodes.asset.ui_actions.OpenAssetClassMasterAction;
import ua.com.fielden.platform.security.Authorise;
import helsinki.security.tokens.open_compound_master.OpenAssetClassMasterAction_CanOpen_Token;
import helsinki.common.producers.AbstractProducerForOpenEntityMasterAction;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;

/**
 * A producer for new instances of entity {@link OpenAssetClassMasterAction}.
 *
 * @author Developers
 *
 */
public class OpenAssetClassMasterActionProducer extends AbstractProducerForOpenEntityMasterAction<AssetClass, OpenAssetClassMasterAction> {

    @Inject
    public OpenAssetClassMasterActionProducer(final EntityFactory factory, final ICompanionObjectFinder companionFinder) {
        super(factory, AssetClass.class, OpenAssetClassMasterAction.class, companionFinder);
    }

    @Override
    protected OpenAssetClassMasterAction provideDefaultValues(OpenAssetClassMasterAction openAction) {
        return super.provideDefaultValues(openAction);
    }
}
