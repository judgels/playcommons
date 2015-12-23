package org.iatoki.judgels.play.models.entities;

import javax.persistence.MappedSuperclass;

/**
 * @deprecated Has been restructured to different package.
 */
@Deprecated
@MappedSuperclass
public abstract class AbstractModel {

    public String userCreate;

    public long timeCreate;

    public String ipCreate;

    public String userUpdate;

    public long timeUpdate;

    public String ipUpdate;
}
