package com.ahmetmatematikci.dosyasistemi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText metinGirisi;

    private final String DOSYAADI = "dosya.txt";
    private final String DIZINISMI = "birdizin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        metinGirisi = (EditText) findViewById(R.id.editText);

    }

       public void onButtonClick(View view){
        switch (view.getId()){
            case R.id.dosyakaydet:

                try {
                    File dizin = getDir(DIZINISMI,MODE_PRIVATE);
                    File dosya = new File(dizin,DOSYAADI);

                    FileOutputStream fileOutputStream = new FileOutputStream(dosya);
                    try{
                        dosyaKaydet(fileOutputStream);
                        metinGirisi.setText("Dosya Kaydedildi");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            break;

            case R.id.dosyayukle:

                try {
                    File dizin = getDir(DIZINISMI,MODE_PRIVATE);
                    File dosya = new File(dizin,DOSYAADI);
                    FileInputStream fileIn = new FileInputStream(dosya);
                    try {
                        dateiLaden(fileIn);
                    }catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


                break;


        }
    }

    private void dateiLaden(FileInputStream fileIn) throws IOException {
        BufferedReader fileBuffer = new BufferedReader(new InputStreamReader(fileIn));
        String text = "";
        String satir;

        try {
            while ((satir = fileBuffer.readLine()) !=null){
                text += satir;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            fileBuffer.close();
        }
        metinGirisi.setText(text);
    }

    private void dosyaKaydet(FileOutputStream fileOutputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
        String metin = metinGirisi.getText().toString();

        try {
            writer.write(metin);
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer !=null){
                writer.close();
            }
        }
    }

}

