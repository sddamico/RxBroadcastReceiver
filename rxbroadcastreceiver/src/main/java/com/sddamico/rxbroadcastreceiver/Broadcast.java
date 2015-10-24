package com.sddamico.rxbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.annotation.NonNull;

public final class Broadcast {

    public static Broadcast create(@NonNull Intent intent, @NonNull BroadcastReceiver receiver) {
        return new Broadcast(intent, receiver);
    }

    final Intent intent;

    final BroadcastReceiver receiver;

    private Broadcast(@NonNull Intent intent, @NonNull BroadcastReceiver receiver) {
        this.intent = intent;
        this.receiver = receiver;
    }
}
