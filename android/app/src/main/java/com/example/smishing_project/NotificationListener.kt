package com.example.smishing_project

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import android.os.Handler
import android.os.Looper
import io.flutter.plugin.common.MethodChannel
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache

class NotificationListener : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val extras = sbn.notification.extras
        val titulo = extras.getString("android.title") ?: return
        val texto = extras.getString("android.text") ?: return

        val engine = FlutterEngineCache.getInstance().get("main_engine") ?: return
        val channel = MethodChannel(engine.dartExecutor, "com.smishing/listener")

        val data = mapOf("titulo" to titulo, "texto" to texto)

        Handler(Looper.getMainLooper()).post {
            channel.invokeMethod("novaNotificacao", data)
        }
    }
}
