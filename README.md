# Session plugin
Session plugin for storing data using shared preferences in Android.
It is highly dependent on ```Session plugin```. Stored data is not any encrypted data. In order for the package to function properly,it is required to protect a device with a screen lock.

## Features
This plugin contains following methods:
1. ```writeData``` - saves user's input and key into shared preferences. Authentication is required to perform this action.
2. ```readData``` - reads data from shared preferences saved under input key. If no data under such key is present, an exception is thrown.
3. ```deleteData``` - deletes data from shared preferences saved under input key.
4. ```editData``` - edits data in shared preferences saved under input key. Authentication is required to perform this action.

## Usage
```dart
//Writing data example
String _data = 'Data';
String _key = 'Key';
var result = await Session.writeData(key, data); //returns true if everything goes fine. Can throw a SharedPreferencesException or DeviceNotSecuredException
```

```dart
//Reading data example
String _key = 'Key';
var result = await Session.readData(readKeyController.text); //returns data written under key if everything goes fine. Can throw a InvalidSignatureException, DeviceNotSecuredException or NoKeyInStorageException
```

```dart
//Deleting data example
String _key = 'Key';
var result = await Session.deleteData(readKeyController.text); //returns true if everything goes fine. Can throw a SharedPreferencesException or DeviceNotSecuredException
```

```dart
//Editing data example
String _data = 'Data';
String _key = 'Key';
var result = await Session.editData(key, data); //returns true if everything goes fine. Can throw a SharedPreferencesException or DeviceNotSecuredException
```


