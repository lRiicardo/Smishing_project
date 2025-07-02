import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});
  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  static const platform = MethodChannel('com.smishing/listener');
  final List<Map<String, String>> notificacoes = [];

  @override
  void initState() {
    super.initState();
    platform.setMethodCallHandler(_receberNotificacao);
  }

  Future<void> _receberNotificacao(MethodCall call) async {
    if (call.method == "novaNotificacao") {
      final args = Map<String, String>.from(call.arguments);
      setState(() => notificacoes.insert(0, args));
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: const Text('Log de Notificações')),
        body: ListView.builder(
          itemCount: notificacoes.length,
          itemBuilder: (context, index) {
            final n = notificacoes[index];
            return Card(
              margin: const EdgeInsets.all(8),
              child: ListTile(
                title: Text(n['titulo'] ?? 'Sem título'),
                subtitle: Text(n['texto'] ?? 'Sem conteúdo'),
              ),
            );
          },
        ),
      ),
    );
  }
}

