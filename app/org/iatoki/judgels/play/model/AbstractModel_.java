package org.iatoki.judgels.play.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AbstractModel.class)
public abstract class AbstractModel_ {

        public static volatile SingularAttribute<AbstractModel, String> createdBy;
        public static volatile SingularAttribute<AbstractModel, Date> createdAt;
        public static volatile SingularAttribute<AbstractModel, String> updatedBy;
        public static volatile SingularAttribute<AbstractModel, Date> updatedAt;
        public static volatile SingularAttribute<AbstractModel, String> updatedIp;
        public static volatile SingularAttribute<AbstractModel, String> createdIp;
}
