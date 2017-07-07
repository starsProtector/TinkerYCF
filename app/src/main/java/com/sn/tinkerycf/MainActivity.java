package com.sn.tinkerycf;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.tencent.tinker.lib.tinker.TinkerInstaller;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在布局里写一个错误,模拟有一个bug,然后通过热修复进行修改
        setContentView(R.layout.activity_main);

        // 通过TextView的点击完成修复工作（官方demo是在Service进行更新的操作,为了不复杂就）
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 加载补丁包：指定补丁包的位置，读取补丁包信息。注意:要加读取SD卡的权限
                TinkerInstaller.onReceiveUpgradePatch(
                        getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath()+"/test");// test：补丁包名称（可以自定义）
            }
        });

        //在写这段代码前,先运行项目,或者打一个有问题apk,这样下面修复问题打的apk,就有了差异包
        //这段代码就模拟问题已经修复了,然后通过到这个demo下,运行命令行,执行gradle tinkerPatchDebug,生成热修复的文件
        //注意:'gradle' 不是内部或外部命令，也不是可运行的程序,那么还要在电脑上配置一下
        ((TextView)findViewById(R.id.tv)).setText("OK");

        //这个是类加载器的父类,按住Ctrl+H,可以查看其所有的子类
//        ClassLoader
    }

}
