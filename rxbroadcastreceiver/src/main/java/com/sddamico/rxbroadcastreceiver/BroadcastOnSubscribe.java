package com.sddamico.rxbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.jakewharton.rxbinding.internal.MainThreadSubscription;

import rx.Observable;
import rx.Subscriber;

import static com.jakewharton.rxbinding.internal.Preconditions.checkUiThread;

final class BroadcastOnSubscribe implements Observable.OnSubscribe<Broadcast> {
    private final Context context;
    private final IntentFilter filter;

    BroadcastOnSubscribe(Context context, IntentFilter filter) {
        this.context = context;
        this.filter = filter;
    }

    @Override public void call(final Subscriber<? super Broadcast> subscriber) {
        checkUiThread();

        final BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override public void onReceive(Context context, Intent intent) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(Broadcast.create(intent, this));
                }
            }
        };

        context.registerReceiver(receiver, filter);

        subscriber.add(new MainThreadSubscription() {

            @Override protected void onUnsubscribe() {
                context.unregisterReceiver(receiver);
            }
        });
    }
}
