package org.iatoki.judgels.play;

import play.Play;
import play.i18n.Messages;

public final class JudgelsPlayMessages {

    private JudgelsPlayMessages() {
        // prevent instantiation
    }

    public static String get(String namespace, Object... objects) {
        if (Play.application().isDev() && !Messages.isDefined(namespace)) {
            throw new RuntimeException("Messages " + namespace + " is not defined.");
        }
        return Messages.get(namespace, objects);
    }
}
