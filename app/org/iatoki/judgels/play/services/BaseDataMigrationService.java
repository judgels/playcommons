package org.iatoki.judgels.play.services;

import java.sql.SQLException;

/**
 * @deprecated Has been restructured to different package.
 */
@Deprecated
public interface BaseDataMigrationService {

    long getCodeDataVersion();

    void checkDatabase() throws SQLException;
}
