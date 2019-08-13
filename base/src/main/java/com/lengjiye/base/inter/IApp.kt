package com.lengjiye.base.inter

import android.content.Context

interface IApp {

    val applicationContext: Context?

    val applicationId: String?

    val versionCode: Int?

    val versionName: String?

    val isDebug: Boolean?

    val buildType: String?
}
