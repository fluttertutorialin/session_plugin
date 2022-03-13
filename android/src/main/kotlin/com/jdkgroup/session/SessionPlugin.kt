package com.jdkgroup.session

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry


class SessionPlugin: FlutterPlugin, MethodCallHandler, ActivityAware,
  PluginRegistry.ActivityResultListener {
  private lateinit var channel: MethodChannel
  private lateinit var context: Context
  private lateinit var activity: Activity
  private var keyToSign: String = ""

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "session")
    channel.setMethodCallHandler(this)

    context = flutterPluginBinding.applicationContext
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "writeData") {
      val key = call.argument<String>("key")
      val dataToWrite = call.argument<String>("data")
      if (key != null && dataToWrite != null) {
        writeData(key, dataToWrite)
        result.success(true)
      } else {
        result.success(false)
      }
    } else if (call.method == "readData"){
      val key = call.argument<String>("key")
      if(key != null){
        val userData = readData(key)
        if(userData != false){
          result.success(userData)
        }else{
          result.success(false)
        }
      }
    }
    else if (call.method == "editData"){
      val key = call.argument<String>("key")
      val dataToWrite = call.argument<String>("data")
      if (key != null && dataToWrite != null) {
        editData(key, dataToWrite)
        result.success(true)
      }else{
        result.success(false)
      }
    }
    else if (call.method == "deleteData"){
      val key = call.argument<String>("key")
      if (key != null) {
        deleteData(key)
        result.success(true)
      }else{
        result.success(false)
      }
    }
    else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  private fun writeData(key: String, data: String) {
    try {
      val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
      with(sharedPref.edit()) {
        putString(key, data)
        readData("key")
        apply()
      }
    } catch (e: Exception) {
    }
  }

  private fun readData(key: String): Any {
    val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
    val textToRead : String? = sharedPref.getString(key, null)
    if(textToRead.isNullOrEmpty()){
      return false
    }else{
      if(textToRead != null){
        return textToRead
      }
      return false
    }
  }

  private fun editData(key: String, data: String){
    keyToSign = key
      val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
      with (sharedPref.edit()) {
        putString(key, data)
        apply()
    }
  }

  private fun deleteData(key: String){
    try{
      val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
      with (sharedPref.edit()) {
        remove(key)
        apply()
      }
    }catch (e: Exception){

    }
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    activity = binding.activity
    binding.addActivityResultListener(this)
  }

  override fun onDetachedFromActivityForConfigChanges() {
    TODO("Not yet implemented")
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    TODO("Not yet implemented")
  }

  override fun onDetachedFromActivity() {
    TODO("Not yet implemented")
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
    return true
  }
}
