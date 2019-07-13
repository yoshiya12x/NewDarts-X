package e.yoppie.newdartsx.util

import android.content.Context
import android.content.Intent
import e.yoppie.newdartsx.service.BgmService

object Bgm {
    fun start(context: Context) {
        val intentBgm = Intent(context, BgmService::class.java)
        intentBgm.putExtra("REQUEST_CODE", 1)
        context.startForegroundService(intentBgm)
    }

    fun stop(context: Context){
        val intentBgm = Intent(context, BgmService::class.java)
        intentBgm.putExtra("REQUEST_CODE", 1)
        context.stopService(intentBgm)
    }
}