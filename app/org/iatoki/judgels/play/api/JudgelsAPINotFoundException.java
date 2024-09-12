package org.iatoki.judgels.play.api;

public final class JudgelsAPINotFoundException extends JudgelsAPIServerException {

    public JudgelsAPINotFoundException() {
        super("Not found.");
    }

    public JudgelsAPINotFoundException(String message) {
        super(message);
    }
}
