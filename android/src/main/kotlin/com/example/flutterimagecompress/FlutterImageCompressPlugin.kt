package com.example.flutterimagecompress

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class FlutterImageCompressPlugin(val registrar: Registrar) : MethodCallHandler {
    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar): Unit {
            val channel = MethodChannel(registrar.messenger(), "flutter_image_compress")
            channel.setMethodCallHandler(FlutterImageCompressPlugin(registrar))
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result): Unit {
        when (call.method) {
            "getPlatformVersion" -> result.success("Android ${android.os.Build.VERSION.RELEASE}")
            "compressWithList" -> CompressListHandler(call, result).handle(registrar.activity())
            "compressWithFile" -> CompressFileHandler(call, result).handle(registrar.activity())
            "compressWithFileAndGetFile" -> CompressFileHandler(call, result).handleGetFile(registrar.activity())
            else -> result.notImplemented()
        }
    }
}
