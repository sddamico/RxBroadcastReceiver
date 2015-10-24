package com.sddamico.rxbroadcastreceiver;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;

import rx.Observable;
import rx.functions.Action1;

import static com.jakewharton.rxbinding.internal.Preconditions.checkUiThread;

/**
 * Static factory methods for creating {@linkplain Observable observables} and {@linkplain Action1
 * actions} relating to broadcasts.
 */
public final class RxBroadcastManager {

    public static Observable<Broadcast> broadcasts(@NonNull Context context, @NonNull IntentFilter filter) {
        return Observable.create(new BroadcastOnSubscribe(context, filter));
    }

    public static Action1<Broadcast> abort() {
        return new Action1<Broadcast>() {
            @Override public void call(Broadcast broadcast) {
                broadcast.receiver.abortBroadcast();
            }
        };
    }

    public static Action1<Broadcast> setResultCode(final int code) {
        return new Action1<Broadcast>() {
            @Override public void call(Broadcast broadcast) {
                broadcast.receiver.setResultCode(code);
            }
        };
    }

    public static Action1<Broadcast> setResultData(final String data) {
        return new Action1<Broadcast>() {
            @Override public void call(Broadcast broadcast) {
                checkUiThread();

                broadcast.receiver.setResultData(data);
            }
        };
    }

    public static Action1<Broadcast> setResultExtras(final Bundle extras) {
        return new Action1<Broadcast>() {
            @Override public void call(Broadcast broadcast) {
                checkUiThread();

                broadcast.receiver.setResultExtras(extras);
            }
        };
    }

    public static Action1<Broadcast> setResult(final int code, final String data, final Bundle extras) {
        return new Action1<Broadcast>() {
            @Override public void call(Broadcast broadcast) {
                checkUiThread();

                broadcast.receiver.setResult(code, data, extras);
            }
        };
    }

    private RxBroadcastManager() {
        throw new AssertionError("No instances.");
    }

}
