package cn.lovesykun.miui_force_fps;
import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        if (lpparam.packageName.equals("com.miui.powerkeeper")) {
            XposedHelpers.findAndHookMethod("com.miui.powerkeeper.statemachine.DisplayFrameSetting", lpparam.classLoader, "setScreenEffect", String.class, int.class, int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                    String pkgName = (String)param.args[0];
                    Log.d("MuiForceFPS", "force fps for: " + pkgName);
                    param.setResult(null);
                }
            });
        }
    }
}
