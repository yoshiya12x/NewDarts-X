package e.yoppie.newdartsx.service

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import e.yoppie.newdartsx.R

@SuppressLint("Registered")
class BgmService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.bgm)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val requestCode = intent.getIntExtra("REQUEST_CODE", 0)
        val channelId = "default"
        val title = applicationContext.getString(R.string.app_name)
        val pendingIntent =
            PendingIntent.getActivity(applicationContext, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = NotificationChannel(channelId, title, NotificationManager.IMPORTANCE_DEFAULT)

        notificationManager.createNotificationChannel(notificationChannel)
        val notification = Notification.Builder(applicationContext, channelId)
            .setContentTitle(title)
            .setContentText("MediaPlay")
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setWhen(System.currentTimeMillis())
            .build()

        startForeground(1, notification)
        audioStart()

        return super.onStartCommand(intent, flags, startId)
    }

    private fun audioStart() {
        mediaPlayer.isLooping = true
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            audioStop()
            stopSelf()
        }
    }

    private fun audioStop(){
        mediaPlayer.stop()
        mediaPlayer.reset()
        mediaPlayer.release()
    }

    override fun onDestroy() {
        super.onDestroy()
        audioStop()
        stopSelf()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}