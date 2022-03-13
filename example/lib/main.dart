import 'package:flutter/material.dart';
import 'package:session/session.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
            appBar: AppBar(title: const Text('Session plugin')),
            body: RawMaterialButton(
                onPressed: () async {
                  var write = await Session.writeData('username', 'kamlesh');
                  print('Write: ' + write.toString());

                  var writeRead = await Session.readData('username');
                  print('Write read: ' + writeRead.toString());

                  var edit = await Session.editData('username', 'sachin');
                  print('Edit: ' + edit.toString());

                  var editRead = await Session.readData('username');
                  print('Edit read: ' + editRead.toString());

                  var delete = await Session.deleteData('username');
                  print('Delete: ' + delete.toString());

                  var keyExist = await Session.readData('username');
                  print('keyExist check: ' + keyExist.toString());
                },
                child: const Text('Session data'))));
  }
}
