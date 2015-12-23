package org.iatoki.judgels.play.models.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @deprecated Has been restructured to different package.
 */
@Deprecated
@MappedSuperclass
public abstract class AbstractJidCacheModel extends AbstractModel {

    @Id
    @GeneratedValue
    public long id;

    public String jid;

    public String displayName;
}
