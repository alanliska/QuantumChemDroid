package cz.jh.qcd;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CustomExport extends MainActivity {

    private TextView label00;
    private TextView label000;
    private Button root;
    private Button quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.customexport);

        label00 = (TextView) findViewById(R.id.label00);
        label000 = (TextView) findViewById(R.id.label000);

        root = (Button) findViewById(R.id.root);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Path = getFilesDir()+"";
                try {
                    FileOutputStream fileout = openFileOutput("CustomExportPath.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Path);
                    outputWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(CustomExport.this, CustomPicker.class);
                startActivity(intent);
            }
        });

        quit = (Button) findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomExport.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
