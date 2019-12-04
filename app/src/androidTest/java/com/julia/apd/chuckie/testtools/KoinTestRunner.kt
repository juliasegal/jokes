package com.julia.apd.chuckie.testtools

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.julia.apd.chuckie.mocks.KoinTestApp


class KoinTestRunner: AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(
            cl, KoinTestApp::class.java.name, context
        )
    }
}