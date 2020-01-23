package helsinki.common.exceptions;

import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.from;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.orderBy;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.select;
import static ua.com.fielden.platform.utils.EntityUtils.fetch;

import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.Set;

import helsinki.assets.AssetFinDet;
import helsinki.projects.Project;
import ua.com.fielden.platform.dao.QueryExecutionModel;
import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.entity.meta.impl.AbstractBeforeChangeEventHandler;
import ua.com.fielden.platform.entity.query.fluent.fetch;
import ua.com.fielden.platform.entity.query.model.EntityResultQueryModel;
import ua.com.fielden.platform.entity.query.model.OrderingModel;
import ua.com.fielden.platform.error.Result;
public class AssetFinDetAcquiredDateWithinProjectPeriod extends AbstractBeforeChangeEventHandler<Date> {

    @Override
    public Result handle(MetaProperty<Date> property, final Date newValue, Set<Annotation> mutatorAnnotations) {
        final AssetFinDet finDet = property.getEntity();
        if (finDet.getProject() == null || newValue == null) {
            return Result.successful(newValue);    
        }
        
        final EntityResultQueryModel<Project> query = select(Project.class)
                .where().prop("id").eq().val(finDet.getProject()).and()
                .prop("startDate").le().val(newValue).and()
                .begin()
                .prop("finishDate").isNull().or()
                    .prop("finishDate").ge().val(newValue)
                 .end().model();
        final fetch<Project> fetch = fetch(Project.class).with("prop1.subprop", "prop2").fetchModel();
        final OrderingModel orderBy = orderBy().prop("prop1").asc().model();
        final QueryExecutionModel<Project, EntityResultQueryModel<Project>> qem = from(query).with(fetch).with(orderBy).model();
        return co(Project.class).exists(query) ? Result.successful(newValue) : Result.failure("Value for Acquire Date is outside the project period.");
        


    }

    
}
