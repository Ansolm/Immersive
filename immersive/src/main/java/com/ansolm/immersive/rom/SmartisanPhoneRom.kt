package com.ansolm.immersive.rom

import android.app.Activity
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import com.ansolm.immersive.Immersive.defaultPhoneRom
import com.ansolm.immersive.PhoneRom

/**
 * author : ansolm
 * e-mail : ansolm@outlook.com
 * time   : 2019/8/5
 * desc   : 小米
 * version: 1.0
 */
class SmartisanPhoneRom : PhoneRom {
    companion object {
        private const val CONTENT_KEY = "navigationbar_trigger_mode"
    }

    override fun isCurrentPhoneRom(brand: String, product: String, model: String) = brand.contains("smartisan")

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun navigationBarExist(activity: Activity): Boolean {
        return try {
            Settings.Global.getInt(activity.contentResolver, CONTENT_KEY) == 0
        } catch (e: Exception) {
            defaultPhoneRom.navigationBarExist(activity)
        }
    }

    override fun navigationStateUri() = arrayOf(Settings.Global.getUriFor(CONTENT_KEY))
}