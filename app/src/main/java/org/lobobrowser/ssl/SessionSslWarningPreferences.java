package org.lobobrowser.ssl;

import java.util.HashMap;
import javax.inject.Singleton;

@Singleton
public class SessionSslWarningPreferences implements SslWarningPreferences {

    private HashMap<String, Behavior> ignoredSslWarnings = new HashMap<>();

    public Behavior recallBehaviorForDomain(String url) {
        if (ignoredSslWarnings.containsKey(url)) {
            return ignoredSslWarnings.get(url);
        }

        return null;
    }

    public void rememberBehaviorForDomain(String url, Behavior behavior) {
        if (ignoredSslWarnings.containsKey(url)) {
            return;
        }

        ignoredSslWarnings.put(url, behavior);
    }


}
