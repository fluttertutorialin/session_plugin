import 'dart:async';
import 'package:flutter/services.dart';

import 'exceptions.dart';

class Session {
  static const MethodChannel _channel = MethodChannel('session');

  static Future<bool> writeData(String key, String data) async {
    try {
      var result =
          await _channel.invokeMethod('writeData', {'key': key, 'data': data});
      if (result == true) {
        return true;
      } else {
        throw SharedPreferencesException(
            'Writing to shared preferences failed. Consider reopening or reinstalling the app.');
      }
    } on PlatformException {
      return false;
    }
  }

  static Future<dynamic> readData(String key) async {
    try {
      var data = await _channel.invokeMethod('readData', {'key': key});
      if (data != false) {
        return data.toString().substring(
            data.toString().indexOf(':') + 1, data.toString().length);
      } else {
        throw NoKeyInStorageException(
            'No such key found in phone storage. Consider saving it to storage before reading.');
      }
    } on PlatformException {
      return false;
    }
  }

  static Future<bool> editData(String key, String data) async {
    try {
      var result =
          await _channel.invokeMethod('editData', {'key': key, 'data': data});
      if (result == true) {
        return true;
      } else {
        throw SharedPreferencesException(
            'Writing to shared preferences failed. Consider reopening or reinstalling the app.');
      }
    } on PlatformException {
      return false;
    }
  }

  static Future<bool> deleteData(String key) async {
    try {
      var result = await _channel.invokeMethod('deleteData', {'key': key});
      if (result != false) {
        return true;
      } else {
        throw SharedPreferencesException('Writing to shared preferences failed. Consider reopening or reinstalling the app.');
      }
    } on PlatformException {
      return false;
    }
  }
}
