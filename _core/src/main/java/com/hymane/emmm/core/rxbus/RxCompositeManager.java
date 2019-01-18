package com.hymane.emmm.core.rxbus;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2016-08-05
 * Description: Composite 集合
 */
public class RxCompositeManager {
    private Map<Object, CompositeDisposable> compositeDisposableMap;

    private RxCompositeManager() {
        compositeDisposableMap = new HashMap<>();
    }

    public static RxCompositeManager getInstance() {
        return Inner.INSTANCE;
    }

    public synchronized void add(Object tag, Disposable d) {
        if (!compositeDisposableMap.containsKey(tag)) {
            compositeDisposableMap.put(tag, new CompositeDisposable());
        }
        CompositeDisposable compositeDisposable = compositeDisposableMap.get(tag);
        if (compositeDisposable != null) {
            compositeDisposable.add(d);
        }
    }

    public synchronized void remove(Object tag) {
        if (compositeDisposableMap.containsKey(tag)) {
            CompositeDisposable compositeDisposable = compositeDisposableMap.get(tag);
            if (compositeDisposable != null) {
                compositeDisposable.clear();
                compositeDisposableMap.remove(tag);
            }
        }
    }

    private static final class Inner {
        private static RxCompositeManager INSTANCE = new RxCompositeManager();
    }
}