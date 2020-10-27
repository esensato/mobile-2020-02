import 'package:flutter/material.dart';
import 'package:flutter_app/MenuItens.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    List<MenuItens> itensMenu = [
      MenuItens(title: 'Músicas', icon: Icon(Icons.audiotrack)),
      MenuItens(title: 'Fotos', icon: Icon(Icons.photo)),
      MenuItens(title: 'Alarme', icon: Icon(Icons.alarm)),
      MenuItens(title: 'Câmera', icon: Icon(Icons.camera))];

    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(title: Icon(Icons.home),
                       backgroundColor: Colors.amber,
                       leading: IconButton(icon: Icon(Icons.menu), onPressed: (){
                         print('Menu selecionado...');
                       }),
         actions: <Widget>[
           IconButton(icon: Icon(Icons.access_alarm))
         ]),
        body: Center(child: _ContadorStateful()),
        bottomNavigationBar: MeuBottomBar(),
        floatingActionButton: MeuActionButton(),
        persistentFooterButtons: <Widget>[IconButton(icon: Icon(Icons.airport_shuttle),
            onPressed: (){
                print('Agendar transporte...');
        })]
      )
    );
  }

}

class MeuActionButton extends StatelessWidget {
  const MeuActionButton({
    Key key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return FloatingActionButton(child: Icon(Icons.accessibility),
    onPressed: () {
      print('Floating pressionado');
    });
  }
}

class MeuBottomBar extends StatelessWidget {
  const MeuBottomBar({
    Key key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BottomAppBar(child: Row(
      children:<Widget>[Text('Item 1'), Text('Item 2'),Icon(Icons.home),
        IconButton(icon: Icon(Icons.star),
                   onPressed: (){
                                  print('Botão estrela pressionado!');
                                })]
    ));
  }
}

class _ContadorStateful extends StatefulWidget {
  @override
  State<_ContadorStateful> createState() => _ContadorState();
  
}

class _ContadorState extends State<_ContadorStateful> {

  int _contador = 0;

  @override
  Widget build(BuildContext context) {
    return Row(mainAxisAlignment: MainAxisAlignment.center,children: <Widget>[
      IconButton(icon: Icon(Icons.phonelink_erase), onPressed: (){
        setState((){
          _contador = 0;
        });
      }),
      Text('$_contador', style: TextStyle(
          color: Colors.deepPurple,
          fontSize: 30.0
      )), IconButton(icon: Icon(Icons.plus_one), onPressed: () {
        setState((){
          _contador++;
        });
        print(_contador);
      }), IconButton(icon:Icon(Icons.call_received), onPressed: (){
        setState(() {
          _contador--;
        });
      })
    ]);

  }
  
}
