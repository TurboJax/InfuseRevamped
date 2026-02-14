package org.turbojax.data;

import org.turbojax.Infuse;

public class Messages extends TurboConfig {
    public Messages() {
        super(Infuse.getInstance(), "messages.yml");
    }
}