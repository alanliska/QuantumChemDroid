package cz.jh.qcd;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
/*import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;*/

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static android.provider.Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION;
import static cz.jh.qcd.Spannables.colorized_numbers;


public class MainActivity extends AppCompatActivity {

    // very important - each code must be different!!!
    private static final int MY_PERMISSION_REQUEST_STORAGE = 1;
//    private static final int MANAGE_ALL_FILES_ACCESS_PERMISSION = 2;
    private Uri documentUri1;
    private Uri documentUri2;
    private Uri documentUri3;
    private Uri documentUri4;
    private Uri documentUri5;
    private TextView label1;
    private TextView label2;
    private TextView label3;
    private TextView label4;
    private TextView label5;
    private TextView label6;
    private TextView label7;
    private TextView label8;
    private Handler handler = new Handler();
    Button start_siamquantum;
    Button start_occ;
    Button start_ulysses;
    Button start_opsin;
    Button start_scatter;
    Button start_line;
    private static final int READ_FILE10 = 5010;
    private Uri documentUri10;
    Button About;
    public ProgressDialog progressDialog;
    Button Manual;
    Button start_shelltools;
    Button start_editor;
    Button start_editor_int;
    Button custom_export;
    Button custom_import;
    Button delete_file;
    Button change_size;
    Button Licenses;
    Button PrivacyPolicy;
    Button start_canvas3d;

    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label1 = (TextView) findViewById(R.id.label1);
        label2 = (TextView) findViewById(R.id.label2);
        label3 = (TextView) findViewById(R.id.label3);
        label4 = (TextView) findViewById(R.id.label4);
        label5 = (TextView) findViewById(R.id.label5);
        label6 = (TextView) findViewById(R.id.label6);
        label7 = (TextView) findViewById(R.id.label7);
        label8 = (TextView) findViewById(R.id.label8);

        start_editor = (Button) findViewById(R.id.start_editor);
        start_editor_int = (Button) findViewById(R.id.start_editor_int);

        About = (Button) findViewById(R.id.About);
        About.setOnClickListener(onAboutClick);

        custom_export = (Button) findViewById(R.id.custom_export);

        custom_import = (Button) findViewById(R.id.custom_import);
        custom_import.setOnClickListener(custom_importClick);

        delete_file = (Button) findViewById(R.id.delete_file);

        PrivacyPolicy = (Button) findViewById(R.id.PrivacyPolicy);
        PrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PrivacyPolicy.class);
                startActivity(intent);
            }
        });

        start_canvas3d = (Button) findViewById(R.id.start_canvas3d);
        start_canvas3d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Canvas3d_main.class);
                startActivity(intent);
            }
        });

        custom_export = (Button) findViewById(R.id.custom_export);
        custom_export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomExport.class);
                startActivity(intent);
            }
        });

        delete_file = (Button) findViewById(R.id.delete_file);
        delete_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DeleteFile.class);
                startActivity(intent);
            }
        });

        Licenses = (Button) findViewById(R.id.Licenses);
        Licenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Licenses.class);
                startActivity(intent);
            }
        });

        start_occ = (Button) findViewById(R.id.start_occ);
        start_occ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Occ.class);
                startActivity(intent);
            }
        });

        start_ulysses = (Button) findViewById(R.id.start_ulysses);
        start_ulysses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Ulysses.class);
                startActivity(intent);
            }
        });

        start_opsin = (Button) findViewById(R.id.start_opsin);
        start_opsin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Opsin.class);
                startActivity(intent);
            }
        });

        start_scatter = (Button) findViewById(R.id.start_scatter);
        start_scatter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Scatter.class);
                startActivity(intent);
            }
        });

        start_line = (Button) findViewById(R.id.start_line);
        start_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Graph.class);
                startActivity(intent);
            }
        });

        start_shelltools = (Button) findViewById(R.id.start_shelltools);
        start_shelltools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShellTools.class);
                startActivity(intent);
            }
        });

        change_size = (Button) findViewById(R.id.change_size);
        change_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChangeSize.class);
                startActivity(intent);
            }
        });

        start_editor = (Button) findViewById(R.id.start_editor);
        start_editor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditExternalFile.class);
                startActivity(intent);
            }
        });

        start_editor_int = (Button) findViewById(R.id.start_editor_int);
        start_editor_int.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditInternalFile.class);
                startActivity(intent);
            }
        });

        // give the app permissions to access the storage
        {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_STORAGE);

                } else {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_STORAGE);
                }
                ;
            } else {
                // do nothing
            }
            ;
        }

        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        SharedPreferences.Editor editor = wmbPreference.edit();

        if (isFirstRun){

            try {
                FileOutputStream fileout = openFileOutput("BinaryPath.txt", MODE_PRIVATE);
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                outputWriter.write(getApplicationInfo().nativeLibraryDir);
                outputWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Please wait...");
            progressDialog.setMessage("Installing QuantumChemDroid. It may take a while.");
            progressDialog.show();
            new Thread() {
                public void run() {

            // Code to run once
            exec("mkdir "+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+"/qcd");


                    copyFromAssetsToInternalStorage("assets.zip");
//                    String zipFilePath = getFilesDir()+"/assets.zip";
                    String zipFilePath = getFilesDir()+File.separator+"assets.zip";
//                    String destDir = getFilesDir()+"/" ;
                    try {
//                        unzip(new File(zipFilePath),destDir);
                        unzip(new File(zipFilePath));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    exec("rm "+getFilesDir()+File.separator+"assets.zip");
                    exec("chmod -R 755 "+getFilesDir()+"/");

                    // not needed now
//                    exec("cp "+getApplicationInfo().nativeLibraryDir+"/libint.so "+getFilesDir()+"/");
//                    exec("mv "+getFilesDir()+"/libint.so "+getFilesDir()+"/libint.zip");
//                    String zipFilePath2 = getFilesDir()+"/libint.zip";
//                    String destDir2 = getFilesDir()+"/" ;
//                    try {
//                        unzip(new File(zipFilePath2),destDir2);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    exec("rm "+getFilesDir()+"/libint.zip");
//
//                    exec("cp "+getApplicationInfo().nativeLibraryDir+"/libpython_assets.so "+getFilesDir()+"/");
//                    exec("mv "+getFilesDir()+"/libpython_assets.so "+getFilesDir()+"/libpython_assets.zip");
//                    String zipFilePath3 = getFilesDir()+"/libpython_assets.zip";
//                    String destDir3 = getFilesDir()+"/" ;
//                    try {
//                        unzip(new File(zipFilePath3),destDir3);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    exec("rm "+getFilesDir()+"/libpython_assets.zip");

                    exec("chmod -R 755 "+getFilesDir()+"/");

            onFinish();
        }
        public void onFinish(){
            progressDialog.dismiss();
        }
    }.start();

            editor.putBoolean("FIRSTRUN", false);
            editor.apply();
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    private View.OnClickListener onAboutClick; {

        onAboutClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alertAbout();
            }
        };
    }

    public void alertAbout() {

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("About the QuantumChemDroid app")
                .setMessage(exec("cat "+getFilesDir()+"/About.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
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

    private View.OnClickListener custom_importClick; {

        custom_importClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                read10(getApplicationContext());
            }
        };
    }

    private void read10(Context context10) {
        Intent intent10 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent10.addCategory(Intent.CATEGORY_OPENABLE);
        intent10.setType("text/plain");
        startActivityForResult(intent10, READ_FILE10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == READ_FILE10 && data != null) {
            // open atmospheric profile file
            try {
                documentUri10 = data.getData();
                String myData = "";
                ParcelFileDescriptor pfd10 = getContentResolver().openFileDescriptor(data.getData(), "r");
                FileInputStream fileInputStream = new FileInputStream(pfd10.getFileDescriptor());
                DataInputStream inp = new DataInputStream(fileInputStream);
                BufferedReader br = new BufferedReader(new InputStreamReader(inp));
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    myData = myData + strLine + "\n";
                }
                inp.close();

                FileOutputStream fileout = openFileOutput("ImportedFile.txt", MODE_PRIVATE);
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                outputWriter.write(myData);
                outputWriter.close();
                fileInputStream.close();
                pfd10.close();
                Toast.makeText(getApplicationContext(), "File read successfully.", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(MainActivity.this, CustomImport.class);
                startActivity(intent);



            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "File not read", Toast.LENGTH_SHORT).show();
            }
        }

}

    protected void copyFromAssetsToInternalStorage(String filename){
        AssetManager assetManager = getAssets();

        try {
            InputStream input = assetManager.open(filename);
            OutputStream output = openFileOutput(filename, Context.MODE_PRIVATE);

            copyFile(input, output);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

//    public static void unzip(File source, String out) throws IOException {
//        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(source))) {
//
//            ZipEntry entry = zis.getNextEntry();
//
//            while (entry != null) {
//
//                File file = new File(out, entry.getName());
//
//                if (entry.isDirectory()) {
//                    file.mkdirs();
//                } else {
//                    File parent = file.getParentFile();
//
//                    if (!parent.exists()) {
//                        parent.mkdirs();
//                    }
//
//                    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
//
//                        int bufferSize = Math.toIntExact(entry.getSize());
//                        byte[] buffer = new byte[bufferSize > 0 ? bufferSize : 1];
//                        int location;
//
//                        while ((location = zis.read(buffer)) != -1) {
//                            bos.write(buffer, 0, location);
//                        }
//                    }
//                }
//                entry = zis.getNextEntry();
//            }
//        }
//    }

    public void unzip(File source) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(source))) {

            ZipEntry entry = zis.getNextEntry();
            File outPath = new File(getFilesDir()+"");

            while (entry != null) {
                File file = new File(outPath, entry.getName());
                String canonicalPath = file.getCanonicalPath();
                if (canonicalPath.startsWith(outPath.getCanonicalPath() + File.separator)) {
//                    File verifiedFile = new File(canonicalPath,entry.getName());
//                    continue;
                    if (entry.isDirectory()) {
                        file.mkdirs();
                    } else {
                        File parent = file.getParentFile();

                        if (!parent.exists()) {
                            parent.mkdirs();
                        }

                        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {

                            int bufferSize = Math.toIntExact(entry.getSize());
                            byte[] buffer = new byte[bufferSize > 0 ? bufferSize : 1];
                            int location;

                            while ((location = zis.read(buffer)) != -1) {
                                bos.write(buffer, 0, location);
                            }
                        }
                    }
                    entry = zis.getNextEntry();
                } else {
                    throw new IllegalStateException("Unreachable location");
                }
            }
        }
    }



}
