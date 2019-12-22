package helsinki.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import helsinki.asset.reports.AssetErrorReport;
/**
 * A security token for entity {@link AssetErrorReport} to guard Save.
 *
 * @author Developers
 *
 */
public class AssetErrorReport_CanSave_Token extends ???ModuleToken {
    public final static String TITLE = format(Template.SAVE.forTitle(), AssetErrorReport.ENTITY_TITLE);
    public final static String DESC = format(Template.SAVE.forDesc(), AssetErrorReport.ENTITY_TITLE);
}
