package com.reinaldo.bancodedados;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Criando um banco de dados
        try{

            SQLiteDatabase bancoDados = openOrCreateDatabase("app",MODE_PRIVATE,null);

            //criar a tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas( nome VARCHAR, idade INT(3))");

            //Inserir dados
            //bancoDados.execSQL("INSERT INTO pessoas(nome,idade) VALUES('Pedro', 50)");
            //bancoDados.execSQL("INSERT INTO pessoas(nome,idade) VALUES('Marian', 18)");

            //Recuperar pessoas
            /*String consulta =
                    "SELECT  nome, idade " +
                    "FROM pessoas WHERE nome = 'Reinaldo'";*/
            String consulta =
                    "SELECT  nome, idade " +
                            "FROM pessoas WHERE nome = 'Reinaldo'";

            Cursor cursor = bancoDados.rawQuery(consulta,null);

            //Indice da tabela
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");



            cursor.moveToFirst();//apos a busca dos dados voltar ao primeiro indice
            while (cursor !=null){
                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);

                Log.i("RESULTADO -nome: ", nome + " idade: "+idade);

                cursor.moveToNext();
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
