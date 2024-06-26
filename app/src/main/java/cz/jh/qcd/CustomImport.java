package cz.jh.qcd;

import static cz.jh.qcd.Spannables.colorized_numbers;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CustomImport extends MainActivity {

    private TextView name_label;
    private EditText name_of_the_file;
    private TextView label00;
    private TextView label000;
    private Button root;
    private Button quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.customimport);

        name_label = (TextView) findViewById(R.id.name_label);
        name_of_the_file = (EditText) findViewById(R.id.name_of_the_file);
        name_of_the_file.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        name_of_the_file.addTextChangedListener(new TextWatcher() {
            int startChanged,beforeChanged,countChanged;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                startChanged = start;
                beforeChanged = before;
                countChanged = count;
            }
            @Override
            public void afterTextChanged(Editable s) {
                name_of_the_file.removeTextChangedListener(this);
                String text = name_of_the_file.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                name_of_the_file.getText().clear();
                name_of_the_file.append(colorized_numbers(text));
                // place the cursor at the original position
                name_of_the_file.setSelection(startChanged+countChanged);
                name_of_the_file.addTextChangedListener(this);
            }
        });
        label00 = (TextView) findViewById(R.id.label00);
        label000 = (TextView) findViewById(R.id.label000);

        root = (Button) findViewById(R.id.root);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Path = getFilesDir()+"";
                String NameOfTheFile = name_of_the_file.getText().toString();
                try {
                    exec("chmod 755 "+getFilesDir()+"/ImportedFile.txt");
                    exec("mv "+getFilesDir()+"/ImportedFile.txt "+Path+"/"+NameOfTheFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(CustomImport.this, MainActivity.class);
                startActivity(intent);
            }
        });

        quit = (Button) findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomImport.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    // Executes UNIX command.
    private String exec(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            int read;
            char[] buffer = new char[65536];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();
            process.waitFor();
            return output.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}