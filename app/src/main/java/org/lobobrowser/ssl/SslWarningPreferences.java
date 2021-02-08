package org.lobobrowser.ssl;

public interface SslWarningPreferences {

    enum Behavior {
        PROCEED,
        CANCEL
    }

    void rememberBehaviorForDomain(String url, Behavior behavior);

    Behavior recallBehaviorForDomain(String url);
}
