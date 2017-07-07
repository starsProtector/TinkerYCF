package com.sn.tinkerycf;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.multidex.MultiDex;

import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

// 用于配置生成的MyApplication类,使用时改成自己的包名的前缀即可,然后加个自定义类名,不要和下面的类名一致.
// 提示:在清单文件里配置这里的MyApplication
@DefaultLifeCycle(application = "com.sn.MyApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false)

// 只有使用tinker,就必须继承DefaultApplicationLike,进行环境的搭建
public class MyApplicationLike extends DefaultApplicationLike {

    //这段代码直接从官方demo拷贝过来即可
    public MyApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag,
                             long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent,
                             Resources[] resources, ClassLoader[] classLoader, AssetManager[] assetManager) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent, resources, classLoader, assetManager);
    }

    public static  Context context;
    // 该方法可以看成MyApplication中的onCreate方法，比如上下文的使用
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
        TinkerInstaller.install(this);

        context=base;
    }
}
