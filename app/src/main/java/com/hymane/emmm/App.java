package com.hymane.emmm;

import android.app.Application;

import com.hymane.emmm.core.Emmm;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/11/13
 * Description:
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Emmm.init(this,
                new Emmm.Config()
                        .baseUrl("https://www.hymane.com"));
    }
}
