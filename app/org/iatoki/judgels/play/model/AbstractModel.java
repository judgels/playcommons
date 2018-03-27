package org.iatoki.judgels.play.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractModel {

    public String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    public Date createdAt;

    public String createdIp;

    public String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    public Date updatedAt;

    public String updatedIp;
}
