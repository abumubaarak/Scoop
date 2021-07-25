package com.example.scoop

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.alterac.blurkit.BlurKit
import timber.log.Timber

@HiltAndroidApp
class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant( Timber.DebugTree());
        }
        BlurKit.init(this);

    }
}