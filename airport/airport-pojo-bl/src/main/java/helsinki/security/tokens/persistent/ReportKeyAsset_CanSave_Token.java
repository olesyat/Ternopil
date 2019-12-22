package helsinki.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import helsinki.reports.ReportKeyAsset;
/**
 * A security token for entity {@link ReportKeyAsset} to guard Save.
 *
 * @author Developers
 *
 */
public class ReportKeyAsset_CanSave_Token extends ???ModuleToken {
    public final static String TITLE = format(Template.SAVE.forTitle(), ReportKeyAsset.ENTITY_TITLE);
    public final static String DESC = format(Template.SAVE.forDesc(), ReportKeyAsset.ENTITY_TITLE);
}
