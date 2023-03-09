package com.example.uiexample.common

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings

fun openNotificationsSettings(context: Context) {
    val intent = Intent()
    when {
        Build.VERSION.SDK_INT > Build.VERSION_CODES.O -> intent.setOpenSettingsForApiLarger25(context)
        else -> intent.setOpenSettingsForApiBetween21And25(context)
    }
    context.startActivity(intent)
}

private fun Intent.setOpenSettingsForApiLarger25(context: Context){
    action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
    putExtra("android.provider.extra.APP_PACKAGE", context.packageName)
}

private fun Intent.setOpenSettingsForApiBetween21And25(context: Context){
    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    putExtra("app_package", context.packageName)
    putExtra("app_uid", context.applicationInfo?.uid)
}
