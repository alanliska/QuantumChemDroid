package cz.jh.qcd;

import static cz.jh.qcd.Spannables.colorized_bash;
import static cz.jh.qcd.Spannables.colorized_elements;
import static cz.jh.qcd.Spannables.colorized_numbers;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import uk.ac.cam.ch.wwmm.opsin.NameToStructure;
import uk.ac.cam.ch.wwmm.opsin.NameToStructureConfig;
import uk.ac.cam.ch.wwmm.opsin.OpsinResult;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import android.content.Context;
import android.util.Log;

import com.jrummyapps.android.shell.CommandResult;
import com.jrummyapps.android.shell.Shell;

public class Ulysses extends MainActivity {

    private Handler handler = new Handler();
    private TextView ContentLabel;
    private EditText Content;
    private TextView CommandLabel;
    private EditText Command;
    Button openCommandfile;
    Button openIntCommandfile;
    Button saveCommandfile;
    Button saveExtCommandfile;
    private TextView BiasLabel;
    private EditText BiasFile;
    Button openBiasfile;
    Button openIntBiasfile;
    Button saveBiasfile;
    Button saveExtBiasfile;
    private TextView CoordLabel;
    private EditText CoordFile;
    Button openCoordfile;
    Button openIntCoordfile;
    Button saveCoordfile;
    Button saveExtCoordfile;
    Button generateXYZ;
    Button opsinXYZ;
    Button RunProgram;
    Button saveOutputfile;
    Button saveExtOutputfile;
    Button Highlight;
    Button Quit;
    MolCanvas_canvasView molCanvasView;

    private TextView textViewX;
    private TextView outputView;
    private EditText outputView2;
    private static final int READ_FILE6 = 106;
    private Uri documentUri6;
    private static final int READ_FILE26 = 1026;
    private Uri documentUri26;
    private static final int CREATE_FILE20 = 1020;
    private Uri documentUri20;
    private static final int CREATE_FILE01 = 101;
    private Uri documentUri0;
    private static final int CREATE_FILE21 = 1021;
    private Uri documentUri21;
    private static final int READ_FILE60 = 1060;
    private Uri documentUri60;
    private static final int CREATE_FILE200 = 10200;
    private Uri documentUri200;
    Button manual_ulysses;
    Button inToCanvas;
    Button outToCanvas;
    Button biasToCanvas;
    Button setParameters;
    Button generateBiasXYZ;
    Button opsinBiasXYZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.ulysses);

        ContentLabel = (TextView) findViewById(R.id.ContentLabel);
        Content = (EditText) findViewById(R.id.Content);
        Content.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/OutputTextSize.txt")).intValue());
        // disable - otherwise the text could not be selected
//        Content.setMovementMethod(new ScrollingMovementMethod());
//        Content.addTextChangedListener(new TextWatcher() {
//            int startChanged,beforeChanged,countChanged;
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                startChanged = start;
//                beforeChanged = before;
//                countChanged = count;
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//                Content.removeTextChangedListener(this);
//                String text = Content.getText().toString();
//                // important - not setText() - otherwise the keyboard would be reset after each type
//                Content.getText().clear();
//                Content.append(colorized_numbers(text));
//                // place the cursor at the original position
//                Content.setSelection(startChanged+countChanged);
//                Content.addTextChangedListener(this);
//            }
//        });
        CommandLabel = (TextView) findViewById(R.id.CommandLabel);
        Command = (EditText) findViewById(R.id.Command);
        Command.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        Command.addTextChangedListener(new TextWatcher() {
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
                Command.removeTextChangedListener(this);
                String text = Command.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                Command.getText().clear();
                Command.append(colorized_bash(text));
                // place the cursor at the original position
                Command.setSelection(startChanged+countChanged);
                Command.addTextChangedListener(this);
            }
        });
        openCommandfile = (Button) findViewById(R.id.openCommandfile);
        openCommandfile.setOnClickListener(openCommandfileClick);
        openIntCommandfile = (Button) findViewById(R.id.openIntCommandfile);
        saveCommandfile = (Button) findViewById(R.id.saveCommandfile);
        saveCommandfile.setOnClickListener(saveCommandfileClick);
        saveExtCommandfile = (Button) findViewById(R.id.saveExtCommandfile);
        saveExtCommandfile.setOnClickListener(saveExtCommandfileClick);
        BiasLabel = (TextView) findViewById(R.id.BiasLabel);
        BiasFile = (EditText) findViewById(R.id.BiasFile);
        BiasFile.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        BiasFile.addTextChangedListener(new TextWatcher() {
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
                BiasFile.removeTextChangedListener(this);
                String text = BiasFile.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                BiasFile.getText().clear();
                BiasFile.append(colorized_elements(text));
                // place the cursor at the original position
                BiasFile.setSelection(startChanged+countChanged);
                BiasFile.addTextChangedListener(this);
            }
        });
        openBiasfile = (Button) findViewById(R.id.openBiasfile);
        openBiasfile.setOnClickListener(openBiasfileClick);
        openIntBiasfile = (Button) findViewById(R.id.openIntBiasfile);
        saveBiasfile = (Button) findViewById(R.id.saveBiasfile);
        saveBiasfile.setOnClickListener(saveBiasfileClick);
        saveExtBiasfile = (Button) findViewById(R.id.saveExtBiasfile);
        saveExtBiasfile.setOnClickListener(saveExtBiasfileClick);
        CoordLabel = (TextView) findViewById(R.id.CoordLabel);
        CoordFile = (EditText) findViewById(R.id.CoordFile);
        CoordFile.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        CoordFile.addTextChangedListener(new TextWatcher() {
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
                CoordFile.removeTextChangedListener(this);
                String text = CoordFile.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                CoordFile.getText().clear();
                CoordFile.append(colorized_elements(text));
                // place the cursor at the original position
                CoordFile.setSelection(startChanged+countChanged);
                CoordFile.addTextChangedListener(this);
            }
        });
        openCoordfile = (Button) findViewById(R.id.openCoordfile);
        openCoordfile.setOnClickListener(openCoordfileClick);
        openIntCoordfile = (Button) findViewById(R.id.openIntCoordfile);
        saveCoordfile = (Button) findViewById(R.id.saveCoordfile);
        saveCoordfile.setOnClickListener(saveCoordfileClick);
        saveExtCoordfile = (Button) findViewById(R.id.saveExtCoordfile);
        saveExtCoordfile.setOnClickListener(saveExtCoordfileClick);
        generateXYZ = (Button) findViewById(R.id.generateXYZ);
        generateXYZ.setOnClickListener(GenerateXYZClick);
        opsinXYZ = (Button) findViewById(R.id.opsinXYZ);
        opsinXYZ.setOnClickListener(opsinXYZClick);
        generateBiasXYZ = (Button) findViewById(R.id.generateBiasXYZ);
        generateBiasXYZ.setOnClickListener(generateBiasXYZClick);
        opsinBiasXYZ = (Button) findViewById(R.id.opsinBiasXYZ);
        opsinBiasXYZ.setOnClickListener(opsinBiasXYZClick);
        RunProgram = (Button) findViewById(R.id.RunProgram);
        RunProgram.setOnClickListener(RunProgramClick);
        saveOutputfile = (Button) findViewById(R.id.saveOutputfile);
        saveOutputfile.setOnClickListener(saveOutputfileClick);
        saveExtOutputfile = (Button) findViewById(R.id.saveExtOutputfile);
        saveExtOutputfile.setOnClickListener(saveExtOutputfileClick);
        Highlight = (Button) findViewById(R.id.Highlight);
        Highlight.setOnClickListener(HighlightClick);
        textViewX = (TextView) findViewById(R.id.textViewX);
        outputView = (TextView) findViewById(R.id.outputView);
        outputView2 = (EditText) findViewById(R.id.outputView2);
        outputView2.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/OutputTextSize.txt")).intValue());
        Quit = (Button) findViewById(R.id.Quit);
        Quit.setOnClickListener(QuitClick);

        setParameters = (Button) findViewById(R.id.setParameters);
        setParameters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Ulysses.this, UlyssesOptions.class);
                startActivity(intent);
            }
        });

        manual_ulysses = (Button) findViewById(R.id.manual_ulysses);
        manual_ulysses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Ulysses.this, ManualUlysses.class);
                startActivity(intent);
            }
        });

        openIntBiasfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ulysses.this, UlyssesWork.class);
                startActivity(intent);
            }
        });

        openIntCoordfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ulysses.this, UlyssesWork1.class);
                startActivity(intent);
            }
        });

        openIntCommandfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ulysses.this, UlyssesCommand.class);
                startActivity(intent);
            }
        });
        inToCanvas = (Button) findViewById(R.id.inToCanvas);
        inToCanvas.setOnClickListener(inToCanvasClick);
        outToCanvas = (Button) findViewById(R.id.outToCanvas);
        outToCanvas.setOnClickListener(outToCanvasClick);
        biasToCanvas = (Button) findViewById(R.id.biasToCanvas);
        biasToCanvas.setOnClickListener(biasToCanvasClick);
    }

    public void onStart()
    {
        super.onStart();

        output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
        output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
        output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
        output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
    }

    private View.OnClickListener GenerateXYZClick; {

        GenerateXYZClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Biasfile = BiasFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Coord = CoordFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Biasfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Coord);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                alertGenerateXYZ();
                output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
            }
        };
    }


    public void alertGenerateXYZ(){
        // creating the EditText widget programatically
        EditText editText100 = new EditText(Ulysses.this);
        editText100.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        editText100.setTypeface(Typeface.MONOSPACE);
        editText100.addTextChangedListener(new TextWatcher() {
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
                editText100.removeTextChangedListener(this);
                String text = editText100.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                editText100.getText().clear();
                editText100.append(colorized_numbers(text));
                // place the cursor at the original position
                editText100.setSelection(startChanged+countChanged);
                editText100.addTextChangedListener(this);
            }
        });
        // create the AlertDialog as final
        final AlertDialog dialog = new AlertDialog.Builder(Ulysses.this)
                .setMessage("Please write the SMILES string to be converted to XYZ. ")
                .setTitle("OpenBABEL conversion")
                .setView(editText100)

                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String SmilesString = editText100.getText().toString();
                        try {
                            FileOutputStream fileout = openFileOutput("temp.smi", MODE_PRIVATE);
                            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                            outputWriter.write(SmilesString);
                            outputWriter.close();

                            com.jrummyapps.android.shell.Shell.SH.run("export HOME=/data/data/cz.jh.qcd/files ; cd $HOME ; export BABEL_DATADIR=$HOME/database/openbabel ; "+getApplicationInfo().nativeLibraryDir+"/libobabel.so -ismi temp.smi -oxyz --gen3d > ObabelOutput.txt");
                            String ObabelOutput = exec("cat "+getFilesDir()+"/ObabelOutput.txt");

                            FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                            OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                            outputWriter3.write(ObabelOutput);
                            outputWriter3.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                        exec("rm "+getFilesDir()+"/temp.smi");
                        // here it should be:
                        output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                        output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                        output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                        output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // removes the AlertDialog in the screen
                    }
                })
                .create();

        // set the focus change listener of the EditText10
        // this part will make the soft keyboard automatically visible
        editText100.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        dialog.show();

    }

    private View.OnClickListener opsinXYZClick; {

        opsinXYZClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Biasfile = BiasFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Coord = CoordFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("md_input_mol.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Biasfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Coord);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                alertOpsinXYZ();
                output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
            }
        };
    }


    public void alertOpsinXYZ(){
        // creating the EditText widget programatically
        EditText editText100 = new EditText(Ulysses.this);
        editText100.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        editText100.setTypeface(Typeface.MONOSPACE);
        editText100.addTextChangedListener(new TextWatcher() {
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
                editText100.removeTextChangedListener(this);
                String text = editText100.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                editText100.getText().clear();
                editText100.append(colorized_numbers(text));
                // place the cursor at the original position
                editText100.setSelection(startChanged+countChanged);
                editText100.addTextChangedListener(this);
            }
        });
        // create the AlertDialog as final
        final AlertDialog dialog = new AlertDialog.Builder(Ulysses.this)
                .setMessage("Please write the chemical name according to IUPAC to XYZ conversion. The result will be displayed as the updated input XYZ file.")
                .setTitle("OPSIN+OpenBABEL conversion")
                .setView(editText100)

                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String SmilesString = editText100.getText().toString();
                        try {
                            ////////////////////////////////////
                            NameToStructure nts = NameToStructure.getInstance();
                            NameToStructureConfig ntsconfig = new NameToStructureConfig();
//a new NameToStructureConfig starts as a copy of OPSIN's default configuration
                            ntsconfig.setAllowRadicals(true);
                            OpsinResult result = nts.parseChemicalName(SmilesString+"", ntsconfig);
                            String smiles = result.getSmiles();
                            /////////////////////////////////////
                            FileOutputStream fileout2 = openFileOutput("temp.smi", MODE_PRIVATE);
                            OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                            outputWriter2.write(smiles);
                            outputWriter2.close();

                            com.jrummyapps.android.shell.Shell.SH.run("export HOME=/data/data/cz.jh.qcd/files ; cd $HOME ; export BABEL_DATADIR=$HOME/database/openbabel ; "+getApplicationInfo().nativeLibraryDir+"/libobabel.so -ismi temp.smi -oxyz --gen3d > ObabelOutput.txt");
                            String ObabelOutput = exec("cat "+getFilesDir()+"/ObabelOutput.txt");

                            FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                            OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                            outputWriter3.write(ObabelOutput);
                            outputWriter3.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                        exec("rm "+getFilesDir()+"/temp.smi");
                        // here it should be:
                        output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                        output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                        output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                        output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // removes the AlertDialog in the screen
                    }
                })
                .create();

        // set the focus change listener of the EditText10
        // this part will make the soft keyboard automatically visible
        editText100.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        dialog.show();

    }

    private View.OnClickListener generateBiasXYZClick; {

        generateBiasXYZClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Biasfile = BiasFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Coord = CoordFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Biasfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Coord);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                alertGenerateBiasXYZ();
                output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
            }
        };
    }


    public void alertGenerateBiasXYZ(){
        // creating the EditText widget programatically
        EditText editText100 = new EditText(Ulysses.this);
        editText100.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        editText100.setTypeface(Typeface.MONOSPACE);
        editText100.addTextChangedListener(new TextWatcher() {
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
                editText100.removeTextChangedListener(this);
                String text = editText100.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                editText100.getText().clear();
                editText100.append(colorized_numbers(text));
                // place the cursor at the original position
                editText100.setSelection(startChanged+countChanged);
                editText100.addTextChangedListener(this);
            }
        });
        // create the AlertDialog as final
        final AlertDialog dialog = new AlertDialog.Builder(Ulysses.this)
                .setMessage("Please write the SMILES string to be converted to XYZ. ")
                .setTitle("OpenBABEL conversion")
                .setView(editText100)

                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String SmilesString = editText100.getText().toString();
                        try {
                            FileOutputStream fileout = openFileOutput("temp.smi", MODE_PRIVATE);
                            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                            outputWriter.write(SmilesString);
                            outputWriter.close();

                            com.jrummyapps.android.shell.Shell.SH.run("export HOME=/data/data/cz.jh.qcd/files ; cd $HOME ; export BABEL_DATADIR=$HOME/database/openbabel ; "+getApplicationInfo().nativeLibraryDir+"/libobabel.so -ismi temp.smi -oxyz --gen3d > ObabelOutput.txt");
                            String ObabelOutput = exec("cat "+getFilesDir()+"/ObabelOutput.txt");

                            FileOutputStream fileout3 = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                            OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                            outputWriter3.write(ObabelOutput);
                            outputWriter3.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                        exec("rm "+getFilesDir()+"/temp.smi");
                        // here it should be:
                        output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                        output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                        output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                        output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // removes the AlertDialog in the screen
                    }
                })
                .create();

        // set the focus change listener of the EditText10
        // this part will make the soft keyboard automatically visible
        editText100.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        dialog.show();

    }

    private View.OnClickListener opsinBiasXYZClick; {

        opsinBiasXYZClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Biasfile = BiasFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Coord = CoordFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("md_input_mol.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Biasfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Coord);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                alertBiasOpsinXYZ();
                output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
            }
        };
    }


    public void alertBiasOpsinXYZ(){
        // creating the EditText widget programatically
        EditText editText100 = new EditText(Ulysses.this);
        editText100.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        editText100.setTypeface(Typeface.MONOSPACE);
        editText100.addTextChangedListener(new TextWatcher() {
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
                editText100.removeTextChangedListener(this);
                String text = editText100.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                editText100.getText().clear();
                editText100.append(colorized_numbers(text));
                // place the cursor at the original position
                editText100.setSelection(startChanged+countChanged);
                editText100.addTextChangedListener(this);
            }
        });
        // create the AlertDialog as final
        final AlertDialog dialog = new AlertDialog.Builder(Ulysses.this)
                .setMessage("Please write the chemical name according to IUPAC to XYZ conversion. The result will be displayed as the updated input XYZ file.")
                .setTitle("OPSIN+OpenBABEL conversion")
                .setView(editText100)

                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String SmilesString = editText100.getText().toString();
                        try {
                            ////////////////////////////////////
                            NameToStructure nts = NameToStructure.getInstance();
                            NameToStructureConfig ntsconfig = new NameToStructureConfig();
//a new NameToStructureConfig starts as a copy of OPSIN's default configuration
                            ntsconfig.setAllowRadicals(true);
                            OpsinResult result = nts.parseChemicalName(SmilesString+"", ntsconfig);
                            String smiles = result.getSmiles();
                            /////////////////////////////////////
                            FileOutputStream fileout2 = openFileOutput("temp.smi", MODE_PRIVATE);
                            OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                            outputWriter2.write(smiles);
                            outputWriter2.close();

                            com.jrummyapps.android.shell.Shell.SH.run("export HOME=/data/data/cz.jh.qcd/files ; cd $HOME ; export BABEL_DATADIR=$HOME/database/openbabel ; "+getApplicationInfo().nativeLibraryDir+"/libobabel.so -ismi temp.smi -oxyz --gen3d > ObabelOutput.txt");
                            String ObabelOutput = exec("cat "+getFilesDir()+"/ObabelOutput.txt");

                            FileOutputStream fileout3 = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                            OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                            outputWriter3.write(ObabelOutput);
                            outputWriter3.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                        exec("rm "+getFilesDir()+"/temp.smi");
                        // here it should be:
                        output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                        output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                        output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                        output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // removes the AlertDialog in the screen
                    }
                })
                .create();

        // set the focus change listener of the EditText10
        // this part will make the soft keyboard automatically visible
        editText100.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        dialog.show();

    }

    private View.OnClickListener openBiasfileClick; {

        openBiasfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Biasfile = BiasFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Coord = CoordFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Biasfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Coord);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                read6(getApplicationContext());
                output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
            }
        };
    }

    private View.OnClickListener openCoordfileClick; {

        openCoordfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Biasfile = BiasFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Coord = CoordFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Biasfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Coord);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                read26(getApplicationContext());
                output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
            }
        };
    }

    private View.OnClickListener openCommandfileClick; {

        openCommandfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Biasfile = BiasFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Coord = CoordFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Biasfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Coord);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                read60(getApplicationContext());
                output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
            }
        };
    }

    private View.OnClickListener saveExtBiasfileClick; {

        saveExtBiasfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Biasfile = BiasFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Coord = CoordFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Biasfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Coord);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                write1(getApplicationContext());
                output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
            }
        };
    }

    private View.OnClickListener saveExtCoordfileClick; {

        saveExtCoordfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Biasfile = BiasFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Coord = CoordFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Biasfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Coord);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                write01(getApplicationContext());
                output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
            }
        };
    }

    private View.OnClickListener saveExtCommandfileClick; {

        saveExtCommandfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Biasfile = BiasFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Coord = CoordFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Biasfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Coord);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                write10(getApplicationContext());
                output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
            }
        };
    }

    private View.OnClickListener saveExtOutputfileClick; {

        saveExtOutputfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Biasfile = BiasFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Coord = CoordFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Biasfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Coord);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                write2(getApplicationContext());
                output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
            }
        };
    }

    private void read6(Context context6) {
        Intent intent6 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent6.addCategory(Intent.CATEGORY_OPENABLE);
        intent6.setType("text/plain");
        startActivityForResult(intent6, READ_FILE6);
    }

    private void read26(Context context26) {
        Intent intent26 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent26.addCategory(Intent.CATEGORY_OPENABLE);
        intent26.setType("text/plain");
        startActivityForResult(intent26, READ_FILE26);
    }

    private void write1(Context context1) {
        Intent intent1 = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent1.addCategory(Intent.CATEGORY_OPENABLE);
        intent1.setType("text/plain");
        intent1.putExtra(Intent.EXTRA_TITLE,"MyInputFile");
        startActivityForResult(intent1, CREATE_FILE20);
    }

    private void write01(Context context01) {
        Intent intent01 = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent01.addCategory(Intent.CATEGORY_OPENABLE);
        intent01.setType("text/plain");
        intent01.putExtra(Intent.EXTRA_TITLE,"MyInputFile");
        startActivityForResult(intent01, CREATE_FILE01);
    }

    private void write2(Context context2) {
        Intent intent2 = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent2.addCategory(Intent.CATEGORY_OPENABLE);
        intent2.setType("text/plain");
        intent2.putExtra(Intent.EXTRA_TITLE,"MyOutputFile");
        startActivityForResult(intent2, CREATE_FILE21);
    }

    private void read60(Context context60) {
        Intent intent60 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent60.addCategory(Intent.CATEGORY_OPENABLE);
        intent60.setType("text/plain");
        startActivityForResult(intent60, READ_FILE60);
    }

    private void write10(Context context10) {
        Intent intent10 = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent10.addCategory(Intent.CATEGORY_OPENABLE);
        intent10.setType("text/plain");
        intent10.putExtra(Intent.EXTRA_TITLE,"MyCommand");
        startActivityForResult(intent10, CREATE_FILE200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == READ_FILE6 && data != null) {
            try {
                documentUri6 = data.getData();
                String myData6 = "";
                ParcelFileDescriptor pfd6 = getContentResolver().openFileDescriptor(data.getData(), "r");
                FileInputStream fileInputStream = new FileInputStream(pfd6.getFileDescriptor());
                DataInputStream inp6 = new DataInputStream(fileInputStream);
                BufferedReader br6 = new BufferedReader(new InputStreamReader(inp6));
                String strLine6;
                while ((strLine6 = br6.readLine()) != null) {
                    myData6 = myData6 + strLine6 + "\n";
                }
                inp6.close();

                FileOutputStream fileout6 = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                OutputStreamWriter outputWriter6 = new OutputStreamWriter(fileout6);
                outputWriter6.write(myData6);
                outputWriter6.close();
                fileInputStream.close();
                pfd6.close();
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "File not read", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == READ_FILE26 && data != null) {
            try {
                documentUri26 = data.getData();
                String myData26 = "";
                ParcelFileDescriptor pfd26 = getContentResolver().openFileDescriptor(data.getData(), "r");
                FileInputStream fileInputStream = new FileInputStream(pfd26.getFileDescriptor());
                DataInputStream inp26 = new DataInputStream(fileInputStream);
                BufferedReader br26 = new BufferedReader(new InputStreamReader(inp26));
                String strLine26;
                while ((strLine26 = br26.readLine()) != null) {
                    myData26 = myData26 + strLine26 + "\n";
                }
                inp26.close();

                FileOutputStream fileout26 = openFileOutput("input.xyz", MODE_PRIVATE);
                OutputStreamWriter outputWriter26 = new OutputStreamWriter(fileout26);
                outputWriter26.write(myData26);
                outputWriter26.close();
                fileInputStream.close();
                pfd26.close();
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "File not read", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == CREATE_FILE20 && data != null) {
            // save input file
            Toast.makeText(getApplicationContext(), "File successfully created", Toast.LENGTH_SHORT).show();
            try {
                String fileContents20X = BiasFile.getText().toString();
                FileOutputStream fileout20 = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                OutputStreamWriter outputWriter20 = new OutputStreamWriter(fileout20);
                outputWriter20.write(fileContents20X + "\n");
                outputWriter20.close();

                documentUri20 = data.getData();
                ParcelFileDescriptor pfd20 = getContentResolver().openFileDescriptor(data.getData(), "w");
                FileOutputStream fileOutputStream20 = new FileOutputStream(pfd20.getFileDescriptor());
                String fileContents20 = exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz");
                fileOutputStream20.write((fileContents20 + "\n").getBytes());
                fileOutputStream20.close();
                pfd20.close();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "File not written", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == CREATE_FILE01 && data != null) {
            // save coordinate file
            Toast.makeText(getApplicationContext(), "File successfully created", Toast.LENGTH_SHORT).show();
            try {
                String fileContents0X = CoordFile.getText().toString();
                FileOutputStream fileout0 = openFileOutput("input.xyz", MODE_PRIVATE);
                OutputStreamWriter outputWriter0 = new OutputStreamWriter(fileout0);
                outputWriter0.write(fileContents0X + "\n");
                outputWriter0.close();
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");

                documentUri0 = data.getData();
                ParcelFileDescriptor pfd0 = getContentResolver().openFileDescriptor(data.getData(), "w");
                FileOutputStream fileOutputStream0 = new FileOutputStream(pfd0.getFileDescriptor());
//                String fileContents20 = InputFile.getText().toString();
                String fileContents0 = exec("cat "+getFilesDir()+"/Ulysses/input.xyz");
                fileOutputStream0.write((fileContents0 + "\n").getBytes());
                fileOutputStream0.close();
                pfd0.close();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "File not written", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == CREATE_FILE21 && data != null) {
            // save output file
            Toast.makeText(getApplicationContext(), "File successfully created", Toast.LENGTH_SHORT).show();
            try {
                documentUri21 = data.getData();
                ParcelFileDescriptor pfd21 = getContentResolver().openFileDescriptor(data.getData(), "w");
                FileOutputStream fileOutputStream21 = new FileOutputStream(pfd21.getFileDescriptor());
                String fileContents21 = outputView2.getText().toString();
                fileOutputStream21.write((fileContents21 + "\n").getBytes());
                fileOutputStream21.close();
                pfd21.close();
                FileOutputStream fileout21 = openFileOutput("input.out", MODE_PRIVATE);
                OutputStreamWriter outputWriter21 = new OutputStreamWriter(fileout21);
                outputWriter21.write(fileContents21 + "\n");
                outputWriter21.close();
                exec("mv "+getFilesDir()+"/input.out "+getFilesDir()+"/Ulysses/");
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "File not written", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == READ_FILE60 && data != null) {
            try {
                documentUri60 = data.getData();
                String myData60 = "";
                ParcelFileDescriptor pfd60 = getContentResolver().openFileDescriptor(data.getData(), "r");
                FileInputStream fileInputStream60 = new FileInputStream(pfd60.getFileDescriptor());
                DataInputStream inp60 = new DataInputStream(fileInputStream60);
                BufferedReader br60 = new BufferedReader(new InputStreamReader(inp60));
                String strLine60;
                while ((strLine60 = br60.readLine()) != null) {
                    myData60 = myData60 + strLine60 + "\n";
                }
                inp60.close();

                FileOutputStream fileout60 = openFileOutput("command.txt", MODE_PRIVATE);
                OutputStreamWriter outputWriter60 = new OutputStreamWriter(fileout60);
                outputWriter60.write(myData60);
                outputWriter60.close();
                fileInputStream60.close();
                pfd60.close();
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "File not read", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == CREATE_FILE200 && data != null) {
            // save command file
            Toast.makeText(getApplicationContext(), "File successfully created", Toast.LENGTH_SHORT).show();
            try {
                documentUri200 = data.getData();
                ParcelFileDescriptor pfd200 = getContentResolver().openFileDescriptor(data.getData(), "w");
                FileOutputStream fileOutputStream200 = new FileOutputStream(pfd200.getFileDescriptor());
                String fileContents200 = Command.getText().toString();
                fileOutputStream200.write((fileContents200 + "\n").getBytes());
                fileOutputStream200.close();
                pfd200.close();
                FileOutputStream fileout200 = openFileOutput("command.txt", MODE_PRIVATE);
                OutputStreamWriter outputWriter200 = new OutputStreamWriter(fileout200);
                outputWriter200.write(fileContents200);
                outputWriter200.close();
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "File not written", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private View.OnClickListener saveBiasfileClick; {

        saveBiasfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Biasfile = BiasFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Coord = CoordFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Biasfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Coord);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                alertSaveBias();
                output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
            }
        };
    }


    public void alertSaveBias(){
        // creating the EditText widget programatically
        EditText editText10 = new EditText(Ulysses.this);
        editText10.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        editText10.setTypeface(Typeface.MONOSPACE);
        editText10.addTextChangedListener(new TextWatcher() {
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
                editText10.removeTextChangedListener(this);
                String text = editText10.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                editText10.getText().clear();
                editText10.append(colorized_numbers(text));
                // place the cursor at the original position
                editText10.setSelection(startChanged+countChanged);
                editText10.addTextChangedListener(this);
            }
        });
        // create the AlertDialog as final
        final AlertDialog dialog = new AlertDialog.Builder(Ulysses.this)
                .setMessage("The file will be saved in the folder /data/data/cz.jh.qcd/files/Ulysses/work")
                .setTitle("Please write the desired filename (if already present, it will be overwritten)")
                .setView(editText10)

                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String Biasfile = BiasFile.getText().toString();
                        String SaveBiasName = editText10.getText().toString();
                        try {
                            FileOutputStream fileout = openFileOutput(SaveBiasName, MODE_PRIVATE);
                            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                            outputWriter.write(Biasfile+"\n");
                            outputWriter.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        exec("mv "+getFilesDir()+"/"+SaveBiasName+" "+getFilesDir()+"/Ulysses/work");
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // removes the AlertDialog in the screen
                    }
                })
                .create();

        // set the focus change listener of the EditText10
        // this part will make the soft keyboard automatically visible
        editText10.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        dialog.show();

    }


    private View.OnClickListener saveCoordfileClick; {

        saveCoordfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Biasfile = BiasFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Coord = CoordFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Biasfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Coord);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                alertSaveCoord();
                output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
            }
        };
    }


    public void alertSaveCoord(){
        // creating the EditText widget programatically
        EditText editText10 = new EditText(Ulysses.this);
        editText10.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        editText10.setTypeface(Typeface.MONOSPACE);
        editText10.addTextChangedListener(new TextWatcher() {
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
                editText10.removeTextChangedListener(this);
                String text = editText10.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                editText10.getText().clear();
                editText10.append(colorized_numbers(text));
                // place the cursor at the original position
                editText10.setSelection(startChanged+countChanged);
                editText10.addTextChangedListener(this);
            }
        });
        // create the AlertDialog as final
        final AlertDialog dialog = new AlertDialog.Builder(Ulysses.this)
                .setMessage("The file will be saved in the folder /data/data/cz.jh.qcd/files/Ulysses/work")
                .setTitle("Please write the desired filename (if already present, it will be overwritten)")
                .setView(editText10)

                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String Inputfile = CoordFile.getText().toString();
                        String SaveInputName = editText10.getText().toString();
                        try {
                            FileOutputStream fileout = openFileOutput(SaveInputName, MODE_PRIVATE);
                            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                            outputWriter.write(Inputfile+"\n");
                            outputWriter.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        exec("mv "+getFilesDir()+"/"+SaveInputName+" "+getFilesDir()+"/Ulysses/work");
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // removes the AlertDialog in the screen
                    }
                })
                .create();

        // set the focus change listener of the EditText10
        // this part will make the soft keyboard automatically visible
        editText10.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        dialog.show();

    }


    private View.OnClickListener saveCommandfileClick; {

        saveCommandfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Biasfile = BiasFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Coord = CoordFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Biasfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Coord);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                alertSaveCommand();
                output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
            }
        };
    }


    public void alertSaveCommand(){
        // creating the EditText widget programatically
        EditText editText10 = new EditText(Ulysses.this);
        editText10.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        editText10.setTypeface(Typeface.MONOSPACE);
        editText10.addTextChangedListener(new TextWatcher() {
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
                editText10.removeTextChangedListener(this);
                String text = editText10.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                editText10.getText().clear();
                editText10.append(colorized_numbers(text));
                // place the cursor at the original position
                editText10.setSelection(startChanged+countChanged);
                editText10.addTextChangedListener(this);
            }
        });
        // create the AlertDialog as final
        final AlertDialog dialog = new AlertDialog.Builder(Ulysses.this)
                .setMessage("The file will be saved in the folder /data/data/cz.jh.qcd/files/Ulysses/commands")
                .setTitle("Please write the desired filename (if already present, it will be overwritten)")
                .setView(editText10)

                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String Inputfile = Command.getText().toString();
                        String SaveInputName = editText10.getText().toString();
                        try {
                            FileOutputStream fileout = openFileOutput(SaveInputName, MODE_PRIVATE);
                            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                            outputWriter.write(Inputfile);
                            outputWriter.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        exec("mv "+getFilesDir()+"/"+SaveInputName+" "+getFilesDir()+"/Ulysses/commands");
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // removes the AlertDialog in the screen
                    }
                })
                .create();

        // set the focus change listener of the EditText10
        // this part will make the soft keyboard automatically visible
        editText10.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        dialog.show();

    }


    private View.OnClickListener RunProgramClick; {

        RunProgramClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Biasfile = BiasFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Coord = CoordFile.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Biasfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Coord);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");

                Arguments = Arguments.replace(" obabel ", " "+getApplicationInfo().nativeLibraryDir+"/libobabel.so ");
                Arguments = Arguments.replace(" occ ", " "+getApplicationInfo().nativeLibraryDir+"/libocc.so ");
//                Arguments = Arguments.replace(" occ_openmp ", " "+getApplicationInfo().nativeLibraryDir+"/libocc_openmp.so ");
                Arguments = Arguments.replace(" ulysses ", " "+getApplicationInfo().nativeLibraryDir+"/libulysses.so ");
                Arguments = Arguments.replace(" xbbc ", " "+getApplicationInfo().nativeLibraryDir+"/libxbbc.so ");
                Arguments = Arguments.replace(" xbvm ", " "+getApplicationInfo().nativeLibraryDir+"/libxbvm.so ");

//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(InputFile.getWindowToken(), 0);
                String command = "export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:"+getApplicationInfo().nativeLibraryDir+" ; "+Arguments;
//                String command = " export HOME="+getFilesDir()+"/ ; cd $HOME ; "+getApplicationInfo().nativeLibraryDir+"/"+NameOfProgram+" "+Arguments;

                new RunCommandTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, command);

                // TODO Auto-generated method stub //
            }
        };
    }

    // Ignore the bad AsyncTask usage.
    final class RunCommandTask extends AsyncTask<String, Void, CommandResult> {

        private ProgressDialog dialog;

        @Override protected void onPreExecute() {

            // this is cancellable progress dialog
            dialog = new ProgressDialog(Ulysses.this);
            dialog.setTitle("Please wait...");
            dialog.setMessage("Calculation is in progress.");
            dialog.setCancelable(false);
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog2, int which) {
                    dialog2.dismiss();
                }
            });
            dialog.show();

            // this was the original non-cancellable progress dialog
//            dialog = ProgressDialog.show(MainActivity.this, "Please wait...", "Calculation is in progress.");
//            dialog.setCancelable(false);
        }

        @Override protected CommandResult doInBackground(String... commands) {
            return com.jrummyapps.android.shell.Shell.SH.run(commands);
        }

        @Override protected void onPostExecute(CommandResult result) {
            if (!isFinishing()) {
                dialog.dismiss();
//                outputView2.setText(resultToHtml(result));
                String OutputofExecution = resultToHtml(result).toString();
                try {
                    FileOutputStream fileout = openFileOutput("LastExecutionOutput.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(OutputofExecution);
                    outputWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                outputView2.setText(OutputofExecution);
                output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
            }
        }

        private Spanned resultToHtml(CommandResult result) {
            StringBuilder html = new StringBuilder();
            // exit status
            html.append("<p><strong>Exit Code:</strong> ");
            if (result.isSuccessful()) {
                html.append("<font color='green'>").append(result.exitCode).append("</font>");
            } else {
                html.append("<font color='red'>").append(result.exitCode).append("</font>");
            }
            html.append("</p>");
            // stdout
            if (result.stdout.size() > 0) {
                html.append("<p><strong>STDOUT:</strong></p><p>")
                        .append(result.getStdout().replaceAll("\n", "<br>"))
                        .append("</p>");
            }
            // stderr
            if (result.stderr.size() > 0) {
                html.append("<p><strong>STDERR:</strong></p><p><font color='red'>")
                        .append(result.getStderr().replaceAll("\n", "<br>"))
                        .append("</font></p>");
            }
            return Html.fromHtml(html.toString());
        }

    }


    public void output2(final String str2) {
        Runnable proc2 = new Runnable() {
            public void run() {
                outputView2.setText(str2);
            }
        };
        handler.post(proc2);
    }

    private View.OnClickListener saveOutputfileClick; {

        saveOutputfileClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alertSaveOutput();
                output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
            }
        };
    }





    public void alertSaveOutput(){
        // creating the EditText widget programatically
        EditText editText15 = new EditText(Ulysses.this);
        editText15.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        editText15.setTypeface(Typeface.MONOSPACE);
        editText15.addTextChangedListener(new TextWatcher() {
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
                editText15.removeTextChangedListener(this);
                String text = editText15.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                editText15.getText().clear();
                editText15.append(colorized_numbers(text));
                // place the cursor at the original position
                editText15.setSelection(startChanged+countChanged);
                editText15.addTextChangedListener(this);
            }
        });
        // create the AlertDialog as final
        final AlertDialog dialog = new AlertDialog.Builder(Ulysses.this)
                .setMessage("The file will be saved in the folder /data/data/cz.jh.qcd/files/Ulysses/work")
                .setTitle("Please write the desired filename (if already present, it will be overwritten)")
                .setView(editText15)

                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String OutputProtocol = outputView2.getText().toString();
                        String SaveOutputName = editText15.getText().toString();
                        try {
                            FileOutputStream fileout = openFileOutput(SaveOutputName, MODE_PRIVATE);
                            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                            outputWriter.write(OutputProtocol);
                            outputWriter.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        exec("mv "+getFilesDir()+"/"+SaveOutputName+" "+getFilesDir()+"/Ulysses/work");
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // removes the AlertDialog in the screen
                    }
                })
                .create();

        // set the focus change listener of the EditText10
        // this part will make the soft keyboard automatically visible
        editText15.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });
        dialog.show();
    }
    private View.OnClickListener inToCanvasClick; {

        inToCanvasClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Biasfile = BiasFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Coord = CoordFile.getText().toString();

//                ProgressDialog progressDialog = new ProgressDialog(Ulysses.this);
//                progressDialog.setTitle("Please wait...");
//                progressDialog.setMessage("Exporting the structure...");
//                progressDialog.setCancelable(false);
//                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                progressDialog.show();
//
//                new Thread() {
//                    public void run() {

                try {
                    FileOutputStream fileout = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Biasfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Coord);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                exec("cp "+getFilesDir()+"/Ulysses/input.xyz "+getFilesDir()+"/structure.xyz");
                String XYZfile = exec("cat "+getFilesDir()+"/Ulysses/input.xyz");
                try {
                    while (XYZfile.contains("\t")){  //2 spaces
                        XYZfile = XYZfile.replace("\t", " "); //(2 spaces, 1 space)
                    }
                    while (XYZfile.contains("  ")){  //2 spaces
                        XYZfile = XYZfile.replace("  ", " "); //(2 spaces, 1 space)
                    }
                    while (XYZfile.contains("\n ")){  //2 spaces
                        XYZfile = XYZfile.replace("\n ", "\n"); //(2 spaces, 1 space)
                    }

                    // remove first two lines
                    // XYZfile = XYZfile.substring(XYZfile.indexOf(System.getProperty("line.separator")) + 2);
                    // remove first line
                    XYZfile = XYZfile.substring(XYZfile.indexOf(System.getProperty("line.separator"))+1);
                    // remove second line
                    XYZfile = XYZfile.substring(XYZfile.indexOf(System.getProperty("line.separator"))+1);
                    MolCanvas_canvasView.zmat.clear();
                    int lineNum = 0;
                    String[] curLine = XYZfile.split("\\n");
                    for (String s : curLine) {
                        lineNum++;
                        String[] splitted = s.split("\\s");
                        String atom = splitted[0].trim();
                        String x_coord_str = splitted[1].trim();
                        String y_coord_str = splitted[2].trim();
                        String z_coord_str = splitted[3].trim();
                        float x_coord = Float.valueOf(x_coord_str);
                        float y_coord = Float.valueOf(y_coord_str);
                        float z_coord = Float.valueOf(z_coord_str);
                        // important: border color is at first run black, there is no other set yet (in MolCanvas_main nor MolCanvas_periodicTable)
                        MolCanvas_canvasView.zmat.add(new MolCanvas_object(lineNum, atom, MolCanvas_methods.getElementColor(atom),
                                Color.BLACK, MolCanvas_methods.getElementRadius(atom),
                                MolCanvas_methods.Radius_pix(MolCanvas_methods.getElementRadius(atom),
                                        MolCanvas_preferences.get().getValue("conv"),
                                        MolCanvas_preferences.get().getValue("radius_scale"), MolCanvas_main.zoom_scale, z_coord),
                                0, x_coord, y_coord, z_coord,
                                MolCanvas_methods.AtomX_pix(x_coord, MolCanvas_preferences.get().getValue("conv"), MolCanvas_canvasView.width_pix,
                                        MolCanvas_main.zoom_scale),
                                MolCanvas_methods.AtomY_pix(y_coord, MolCanvas_preferences.get().getValue("conv"),
                                        MolCanvas_canvasView.height_pix, MolCanvas_main.zoom_scale),
                                0, "", MolCanvas_methods.getElementTextColor(atom), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
                                z_coord, 0.0f, 0.0f, 0.0f, 1));
                    }
                    for (MolCanvas_object object : MolCanvas_canvasView.zmat) {
                        if (object.getObjectType() == 1) {
                            MolCanvas_main.generatedLabels.add(new MolCanvas_object(object.getAtomNumber1(), object.getAtomSymbol1(),
                                    MolCanvas_methods.getElementTextColor(object.getAtomSymbol1()),
                                    object.getAtomBorderColor1(), 0,
                                    MolCanvas_methods.Text_pix(MolCanvas_preferences.get().getValue("text_size"), MolCanvas_main.zoom_scale,
                                            object.getAtom1Z_Ang()),
                                    object.getTouchTime(), object.getAtom1X_Ang(), object.getAtom1Y_Ang(),
                                    object.getAtom1Z_Ang(),
                                    object.getAtom1X_pix() + MolCanvas_preferences.get().getValue("text_shift_x_pix"),
                                    object.getAtom1Y_pix() + MolCanvas_preferences.get().getValue("text_shift_y_pix"), 0, "",
                                    0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
                                    object.getAtom12Z_Ang() + MolCanvas_preferences.get().getValue("text_shift_z_Ang"), 0.0f,
                                    0.0f, object.getDist2D_pix(), 4));
                        }
                    }
                    MolCanvas_canvasView.zmat.addAll(MolCanvas_main.generatedLabels);
                    MolCanvas_main.generatedLabels.clear();
                    MolCanvas_main.generateAllBonds();
                    MolCanvas_canvasView.zmat.sort(Comparator.comparing(a -> a.getAtom12Z_Ang()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(Ulysses.this, MolCanvas_main.class);
                startActivity(intent);

            }
        };
    }

    private View.OnClickListener biasToCanvasClick; {

        biasToCanvasClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Biasfile = BiasFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Coord = CoordFile.getText().toString();

//                ProgressDialog progressDialog = new ProgressDialog(Ulysses.this);
//                progressDialog.setTitle("Please wait...");
//                progressDialog.setMessage("Exporting the structure...");
//                progressDialog.setCancelable(false);
//                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                progressDialog.show();
//
//                new Thread() {
//                    public void run() {

                try {
                    FileOutputStream fileout = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Biasfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Coord);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                exec("cp "+getFilesDir()+"/Ulysses/input.xyz "+getFilesDir()+"/structure.xyz");
                String XYZfile = exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz");
                try {
                    while (XYZfile.contains("\t")){  //2 spaces
                        XYZfile = XYZfile.replace("\t", " "); //(2 spaces, 1 space)
                    }
                    while (XYZfile.contains("  ")){  //2 spaces
                        XYZfile = XYZfile.replace("  ", " "); //(2 spaces, 1 space)
                    }
                    while (XYZfile.contains("\n ")){  //2 spaces
                        XYZfile = XYZfile.replace("\n ", "\n"); //(2 spaces, 1 space)
                    }

                    // remove first two lines
                    // XYZfile = XYZfile.substring(XYZfile.indexOf(System.getProperty("line.separator")) + 2);
                    // remove first line
                    XYZfile = XYZfile.substring(XYZfile.indexOf(System.getProperty("line.separator"))+1);
                    // remove second line
                    XYZfile = XYZfile.substring(XYZfile.indexOf(System.getProperty("line.separator"))+1);
                    MolCanvas_canvasView.zmat.clear();
                    int lineNum = 0;
                    String[] curLine = XYZfile.split("\\n");
                    for (String s : curLine) {
                        lineNum++;
                        String[] splitted = s.split("\\s");
                        String atom = splitted[0].trim();
                        String x_coord_str = splitted[1].trim();
                        String y_coord_str = splitted[2].trim();
                        String z_coord_str = splitted[3].trim();
                        float x_coord = Float.valueOf(x_coord_str);
                        float y_coord = Float.valueOf(y_coord_str);
                        float z_coord = Float.valueOf(z_coord_str);
                        // important: border color is at first run black, there is no other set yet (in MolCanvas_main nor MolCanvas_periodicTable)
                        MolCanvas_canvasView.zmat.add(new MolCanvas_object(lineNum, atom, MolCanvas_methods.getElementColor(atom),
                                Color.BLACK, MolCanvas_methods.getElementRadius(atom),
                                MolCanvas_methods.Radius_pix(MolCanvas_methods.getElementRadius(atom),
                                        MolCanvas_preferences.get().getValue("conv"),
                                        MolCanvas_preferences.get().getValue("radius_scale"), MolCanvas_main.zoom_scale, z_coord),
                                0, x_coord, y_coord, z_coord,
                                MolCanvas_methods.AtomX_pix(x_coord, MolCanvas_preferences.get().getValue("conv"), MolCanvas_canvasView.width_pix,
                                        MolCanvas_main.zoom_scale),
                                MolCanvas_methods.AtomY_pix(y_coord, MolCanvas_preferences.get().getValue("conv"),
                                        MolCanvas_canvasView.height_pix, MolCanvas_main.zoom_scale),
                                0, "", MolCanvas_methods.getElementTextColor(atom), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
                                z_coord, 0.0f, 0.0f, 0.0f, 1));
                    }
                    for (MolCanvas_object object : MolCanvas_canvasView.zmat) {
                        if (object.getObjectType() == 1) {
                            MolCanvas_main.generatedLabels.add(new MolCanvas_object(object.getAtomNumber1(), object.getAtomSymbol1(),
                                    MolCanvas_methods.getElementTextColor(object.getAtomSymbol1()),
                                    object.getAtomBorderColor1(), 0,
                                    MolCanvas_methods.Text_pix(MolCanvas_preferences.get().getValue("text_size"), MolCanvas_main.zoom_scale,
                                            object.getAtom1Z_Ang()),
                                    object.getTouchTime(), object.getAtom1X_Ang(), object.getAtom1Y_Ang(),
                                    object.getAtom1Z_Ang(),
                                    object.getAtom1X_pix() + MolCanvas_preferences.get().getValue("text_shift_x_pix"),
                                    object.getAtom1Y_pix() + MolCanvas_preferences.get().getValue("text_shift_y_pix"), 0, "",
                                    0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
                                    object.getAtom12Z_Ang() + MolCanvas_preferences.get().getValue("text_shift_z_Ang"), 0.0f,
                                    0.0f, object.getDist2D_pix(), 4));
                        }
                    }
                    MolCanvas_canvasView.zmat.addAll(MolCanvas_main.generatedLabels);
                    MolCanvas_main.generatedLabels.clear();
                    MolCanvas_main.generateAllBonds();
                    MolCanvas_canvasView.zmat.sort(Comparator.comparing(a -> a.getAtom12Z_Ang()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(Ulysses.this, MolCanvas_main.class);
                startActivity(intent);

            }
        };
    }

    private View.OnClickListener outToCanvasClick; {

        outToCanvasClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String Biasfile = BiasFile.getText().toString();
                String Arguments = Command.getText().toString();
                String Coord = CoordFile.getText().toString();

//                ProgressDialog progressDialog = new ProgressDialog(Ulysses.this);
//                progressDialog.setTitle("Please wait...");
//                progressDialog.setMessage("Exporting the structure...");
//                progressDialog.setCancelable(false);
//                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                progressDialog.show();
//
//                new Thread() {
//                    public void run() {

                try {
                    FileOutputStream fileout = openFileOutput("md_bias_mol.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(Biasfile);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("command.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(Arguments);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("input.xyz", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Coord);
                    outputWriter3.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/md_bias_mol.xyz "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/command.txt "+getFilesDir()+"/Ulysses/");
                exec("mv "+getFilesDir()+"/input.xyz "+getFilesDir()+"/Ulysses/");
                exec("cp "+getFilesDir()+"/Ulysses/input_opt.xyz "+getFilesDir()+"/structure.xyz");
                String XYZfile = exec("cat "+getFilesDir()+"/Ulysses/input_opt.xyz");
                try {
                    while (XYZfile.contains("\t")){  //2 spaces
                        XYZfile = XYZfile.replace("\t", " "); //(2 spaces, 1 space)
                    }
                    while (XYZfile.contains("  ")){  //2 spaces
                        XYZfile = XYZfile.replace("  ", " "); //(2 spaces, 1 space)
                    }
                    while (XYZfile.contains("\n ")){  //2 spaces
                        XYZfile = XYZfile.replace("\n ", "\n"); //(2 spaces, 1 space)
                    }

                    // remove first two lines
                    // XYZfile = XYZfile.substring(XYZfile.indexOf(System.getProperty("line.separator")) + 2);
                    // remove first line
                    XYZfile = XYZfile.substring(XYZfile.indexOf(System.getProperty("line.separator"))+1);
                    // remove second line
                    XYZfile = XYZfile.substring(XYZfile.indexOf(System.getProperty("line.separator"))+1);
                    MolCanvas_canvasView.zmat.clear();
                    int lineNum = 0;
                    String[] curLine = XYZfile.split("\\n");
                    for (String s : curLine) {
                        lineNum++;
                        String[] splitted = s.split("\\s");
                        String atom = splitted[0].trim();
                        String x_coord_str = splitted[1].trim();
                        String y_coord_str = splitted[2].trim();
                        String z_coord_str = splitted[3].trim();
                        float x_coord = Float.valueOf(x_coord_str);
                        float y_coord = Float.valueOf(y_coord_str);
                        float z_coord = Float.valueOf(z_coord_str);
                        // important: border color is at first run black, there is no other set yet (in MolCanvas_main nor MolCanvas_periodicTable)
                        MolCanvas_canvasView.zmat.add(new MolCanvas_object(lineNum, atom, MolCanvas_methods.getElementColor(atom),
                                Color.BLACK, MolCanvas_methods.getElementRadius(atom),
                                MolCanvas_methods.Radius_pix(MolCanvas_methods.getElementRadius(atom),
                                        MolCanvas_preferences.get().getValue("conv"),
                                        MolCanvas_preferences.get().getValue("radius_scale"), MolCanvas_main.zoom_scale, z_coord),
                                0, x_coord, y_coord, z_coord,
                                MolCanvas_methods.AtomX_pix(x_coord, MolCanvas_preferences.get().getValue("conv"), MolCanvas_canvasView.width_pix,
                                        MolCanvas_main.zoom_scale),
                                MolCanvas_methods.AtomY_pix(y_coord, MolCanvas_preferences.get().getValue("conv"),
                                        MolCanvas_canvasView.height_pix, MolCanvas_main.zoom_scale),
                                0, "", MolCanvas_methods.getElementTextColor(atom), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
                                z_coord, 0.0f, 0.0f, 0.0f, 1));
                    }
                    for (MolCanvas_object object : MolCanvas_canvasView.zmat) {
                        if (object.getObjectType() == 1) {
                            MolCanvas_main.generatedLabels.add(new MolCanvas_object(object.getAtomNumber1(), object.getAtomSymbol1(),
                                    MolCanvas_methods.getElementTextColor(object.getAtomSymbol1()),
                                    object.getAtomBorderColor1(), 0,
                                    MolCanvas_methods.Text_pix(MolCanvas_preferences.get().getValue("text_size"), MolCanvas_main.zoom_scale,
                                            object.getAtom1Z_Ang()),
                                    object.getTouchTime(), object.getAtom1X_Ang(), object.getAtom1Y_Ang(),
                                    object.getAtom1Z_Ang(),
                                    object.getAtom1X_pix() + MolCanvas_preferences.get().getValue("text_shift_x_pix"),
                                    object.getAtom1Y_pix() + MolCanvas_preferences.get().getValue("text_shift_y_pix"), 0, "",
                                    0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
                                    object.getAtom12Z_Ang() + MolCanvas_preferences.get().getValue("text_shift_z_Ang"), 0.0f,
                                    0.0f, object.getDist2D_pix(), 4));
                        }
                    }
                    MolCanvas_canvasView.zmat.addAll(MolCanvas_main.generatedLabels);
                    MolCanvas_main.generatedLabels.clear();
                    MolCanvas_main.generateAllBonds();
                    MolCanvas_canvasView.zmat.sort(Comparator.comparing(a -> a.getAtom12Z_Ang()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(Ulysses.this, MolCanvas_main.class);
                startActivity(intent);

            }
        };
    }

    private View.OnClickListener HighlightClick; {

        HighlightClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                openhighlightdialog();
            }
        };
    }

    private void openhighlightdialog() {
        // TODO Auto-generated method stub //
        ProgressDialog progressDialog = new ProgressDialog(Ulysses.this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Highlighting numbers is in progress...");
        progressDialog.setCancelable(false);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        progressDialog.show();

        new Thread() {
            public void run() {
                try {
                    outputX(exec("cat "+getFilesDir()+"/LastExecutionOutput.txt"));
                    output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
                    output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
                    output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
                    output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
                    Toast.makeText(getApplicationContext(), "Numbers highlighted.", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                }

                onFinish();
            }

            // for displaying the output in the second TextView there must be different output2 than output, including the str2/proc2 variables
            public void outputX(final String strX) {
                Runnable procX = new Runnable() {
                    public void run() {
                        outputView2.setText(colorized_numbers(strX), EditText.BufferType.SPANNABLE);
                    }
                };
                handler.post(procX);
            }

            public void onFinish() {
                progressDialog.dismiss();
            }
        }.start();
    }


    @Override
    protected void onResume() {
        super.onResume();
        output3(exec("cat "+getFilesDir()+"/Ulysses/md_bias_mol.xyz"));
        output4(exec("cat "+getFilesDir()+"/Ulysses/input.xyz"));
        output5(exec("cat "+getFilesDir()+"/Ulysses/command.txt"));
        output(exec("ls -la "+getFilesDir()+"/Ulysses/"));
    }

    // for displaying the output in the second TextView there must be different output3 than output, including the str3/proc3 variables
    public void output3(final String str3) {
        Runnable proc3 = new Runnable() {
            public void run() {
                BiasFile.setText(colorized_elements(str3), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc3);
    }
    public void output4(final String str4) {
        Runnable proc4 = new Runnable() {
            public void run() {
                CoordFile.setText(colorized_elements(str4), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc4);
    }
    public void output5(final String str5) {
        Runnable proc5 = new Runnable() {
            public void run() {
                Command.setText(colorized_bash(str5), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc5);
    }
    public void output(final String str) {
        Runnable proc = new Runnable() {
            public void run() {
                Content.setText(str);
            }
        };
        handler.post(proc);
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

    private View.OnClickListener QuitClick; {

        QuitClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                Intent intent = new Intent(Ulysses.this, MainActivity.class);
                startActivity(intent);
            }
        };
    }


}