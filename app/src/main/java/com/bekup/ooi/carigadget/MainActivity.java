package com.bekup.ooi.carigadget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
     Button btnsamsung,btnapple,btnxiaomi,btnlenovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsamsung=(Button) findViewById(R.id.btnsamsung);
        btnapple=(Button) findViewById(R.id.btnapple);
        btnxiaomi=(Button) findViewById(R.id.btnxiaomi);
        btnlenovo=(Button) findViewById(R.id.btnlenovo);

        btnsamsung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeJenisProduk("samsung");
            }
        });

        btnapple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeJenisProduk("apple");
            }
        });

        btnxiaomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeJenisProduk("xiaomi");
            }
        });

        btnlenovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeJenisProduk("lenovo");
            }
        });

    }

    public void KeJenisProduk(String JenisP){
        Intent pindah = new Intent(MainActivity.this,JenisProduk.class);
        pindah.putExtra("slug",JenisP);
        startActivity(pindah);
    }

}
