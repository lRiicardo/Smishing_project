package com.example.smishing_project

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache

class MainActivity: FlutterActivity() {
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        FlutterEngineCache.getInstance().put("main_engine", flutterEngine)
        super.configureFlutterEngine(flutterEngine)
    }
}
