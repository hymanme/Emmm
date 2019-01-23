package com.hymane.emmm;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hymane.emmm.core.Emmm;
import com.hymane.emmm.core.app.BaseApplication;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/11/13
 * Description:
 */
public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Emmm.init(this,
                new Emmm.Config().baseUrl("https://www.hymane.com"));
        if (BuildConfig.DEBUG) {   // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
}
