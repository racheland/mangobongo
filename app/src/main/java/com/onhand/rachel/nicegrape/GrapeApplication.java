package com.onhand.rachel.nicegrape;

import android.app.Application;

import com.onhand.rachel.nicegrape.pin.CustomPinActivity;
import com.github.omadahealth.lollipin.lib.managers.LockManager;

public class GrapeApplication extends Application {

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate() {
        super.onCreate();

        LockManager<CustomPinActivity> lockManager = LockManager.getInstance();
//        lockManager.getAppLock().setLogoId(R.drawable.grape_1);
    }
}