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
 * desc   : oppo
 * version: 1.0
 */
class OppoPhoneRom : PhoneRom {
    companion object {
        private const val CONTENT_KEY = "hide_navigationbar_enable"
    }

    override fun isCurrentPhoneRom(brand: String, product: String, model: String) =
        brand.contains("oppo") || brand.contains("realme")

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun navigationBarExist(activity: Activity): Boolean {
        return try {
            Settings.Secure.getInt(activity.contentResolver, CONTENT_KEY) == 0
        } catch (e: Exception) {
            defaultPhoneRom.navigationBarExist(activity)
        }
    }

    override fun navigationStateUri() = arrayOf(Settings.Secure.getUriFor(CONTENT_KEY))
}