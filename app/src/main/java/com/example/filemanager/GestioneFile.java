package com.example.filemanager;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class GestioneFile {

    public String leggiFile(String fileName, Context c) {

        String fileOut;
        StringBuilder sb = new StringBuilder(); //istanzio oggetto della classe stringBuilder

        try{ //racchiudo tutto in un blocco try poichè le operazioni con i file resttuiscpno delle eccezioni quali file not found e i/o
            BufferedReader fileIn= new BufferedReader(new InputStreamReader(c.openFileInput(fileName))); //questa riga di codixce permette di istanziare un oggetto buffered reader che al suo interno avrà memorizzato il contenmuto del file in byte
            while((fileOut = fileIn.readLine()) != null) {  //ciclo while che scorre il file riga per riga ed assegma il valore della riga a fileOut, il ciclo continua fino a che non si arriva alla fine del file quando fileOut sarà null
                sb.append(fileOut + "\n");  //Ogni riga letta dal file viene "appesa" assieme al carattere /n che permette di andare a capo nell'oggetto sb così, in questo modo sb avrà lo stesso contemuto del file
            }
        }
        catch(FileNotFoundException e){  //blocco catch per la file not found exception
            Log.e("mainActivity", "Il file non esiste");
        }
        catch (IOException e) { //blocco catch per la i/o exception
            e.printStackTrace();
        }
        return sb.toString();  //il metodo restituisce l'oggetto sb con all'interno il contenuto del file
    }

    public String scriviFile(String fileName,String contenuto, Context c) {

        String esito = "";  //poichè questo metodo crea o scrive (in caso esista già) un file non ha un responso grafico sul successo dell'operazione ed è quindi necessario restituire una stringa che contenga dei messaggi riguardanti il successo o fallimento dell'operazuone di scrittura
        FileOutputStream fileO;

        try {
            fileO = c.openFileOutput(fileName, Context.MODE_PRIVATE);  //apro il file all'interno di fileO
            fileO.write(contenuto.getBytes()); //scrivo nel file il contenuto della stringa str convertendolo però in bytes
            fileO.close(); //chiudo il file altrimenti potrei incappare in errori
            esito = "File scritto o creato con successo";  //memorizzo nella stringa esito un messaggio di successo
        }

        catch (FileNotFoundException e) {
            Log.e("MainActivity", "il file non esiste");
            e.printStackTrace();
            esito = "Il file a cui si vuole accedere non esiste"; //memorizzo nella stringa esito il messaggio del fallimento e la motivazione
        }

        catch (IOException e) {
            Log.e("MainActivity", "errore di I/O");
            e.printStackTrace();
            esito = "Impossibile gestire le operazioni di input o output"; //memorizzo nella stringa esito il messaggio del fallimento e la motivazione
        }
        return esito;
    }

    public String leggiFileRes(Context c) {
        Resources res;
        StringBuilder sb = new StringBuilder();
        res=c.getResources();
        InputStream fdl;
        fdl= res.openRawResource(R.raw.brani);
        try {
            BufferedReader fileIn = new BufferedReader(new InputStreamReader(fdl));
            String riga;
            while ((riga = fileIn.readLine()) != null) {  //ciclo while che scorre il file riga per riga ed assegma il valore della riga a fileOut, il ciclo continua fino a che non si arriva alla fine del file quando fileOut sarà null
                sb.append(riga + "\n");  //Ogni riga letta dal file viene "appesa" assieme al carattere /n che permette di andare a capo nell'oggetto sb così, in questo modo sb avrà lo stesso contemuto del file
            }
        }

        catch (IOException e) { //blocco catch per la i/o exception
            e.printStackTrace();
        }

         return sb.toString();
    }

    public String leggiFileAssets(Context c) {
        AssetManager am= c.getAssets();
        StringBuilder sb = new StringBuilder();
        try {
            InputStream fdl= am.open("brani.txt");
            BufferedReader fileIn = new BufferedReader(new InputStreamReader(fdl));
            String riga;
            while ((riga = fileIn.readLine()) != null) {  //ciclo while che scorre il file riga per riga ed assegma il valore della riga a fileOut, il ciclo continua fino a che non si arriva alla fine del file quando fileOut sarà null
                sb.append(riga + "\n");  //Ogni riga letta dal file viene "appesa" assieme al carattere /n che permette di andare a capo nell'oggetto sb così, in questo modo sb avrà lo stesso contemuto del file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}




