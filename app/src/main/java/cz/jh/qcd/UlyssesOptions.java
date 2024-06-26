package cz.jh.qcd;

import static cz.jh.qcd.Spannables.colorized_numbers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class UlyssesOptions extends Ulysses {

    private Button Quit;
    private TextView Label;
    private TextView GeneralSettings;
    private TextView Runtype;
    private TextView Opt;
    private TextView Ts;
    private TextView Thermo;
    private TextView Basis;
    private TextView DispCorr;
    private TextView LevelShiftLabel;
    private TextView PointGroup;
    private TextView ChargeLabel;
    private TextView MultLabel;
    private TextView Radical;
    private TextView Solvent;
    private TextView TemperatureLabel;
    private TextView ElTemperatureLabel;
    private TextView PrintOpt;
    private TextView MolDyn;
    private TextView SimTimeLabel;
    private TextView GeometryOpt;
    private TextView HessianUpdate;
    private TextView RFO;
    private TextView Solver;
    private TextView EnergyThrLabel;
    private TextView GradThrLabel;

    private Handler handler = new Handler();

    private EditText LevelShift;
    private EditText Charge;
    private EditText Multiplicity;
    private EditText Temperature;
    private EditText ElTemperature;
    private EditText TimeStep;
    private EditText SimTime;
    private EditText EnergyThr;
    private EditText GradThr;

    private Spinner VerbositySpinner;
    private Spinner RuntypeSpinner;
    private Spinner OptSpinner;
    private Spinner TsSpinner;
    private Spinner ThermoSpinner;
    private Spinner BasisSpinner;
    private Spinner DispCorrSpinner;
    private Spinner PointGroupSpinner;
    private Spinner RadicalSpinner;
    private Spinner SolventSpinner;
    private Spinner PrintSpinner;
    private Spinner BFGSSpinner;
    private Spinner HessianUpdateSpinner;
    private Spinner RFOSpinner;
    private Spinner SolverSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ulyssesoptions);

        Quit = (Button) findViewById(R.id.Quit);
        Quit.setOnClickListener(QuitClick);
        Label = (TextView) findViewById(R.id.Label);
        GeneralSettings = (TextView) findViewById(R.id.GeneralSettings);
        Runtype = (TextView) findViewById(R.id.Runtype);
        Opt = (TextView) findViewById(R.id.Opt);
        Ts = (TextView) findViewById(R.id.Ts);
        Thermo = (TextView) findViewById(R.id.Thermo);
        Basis = (TextView) findViewById(R.id.Basis);
        DispCorr = (TextView) findViewById(R.id.DispCorr);
        LevelShiftLabel = (TextView) findViewById(R.id.LevelShiftLabel);
        PointGroup = (TextView) findViewById(R.id.PointGroup);
        ChargeLabel = (TextView) findViewById(R.id.ChargeLabel);
        MultLabel = (TextView) findViewById(R.id.MultLabel);
        Radical = (TextView) findViewById(R.id.Radical);
        Solvent = (TextView) findViewById(R.id.Solvent);
        TemperatureLabel = (TextView) findViewById(R.id.TemperatureLabel);
        ElTemperatureLabel = (TextView) findViewById(R.id.ElTemperatureLabel);
        PrintOpt = (TextView) findViewById(R.id.PrintOpt);
        MolDyn = (TextView) findViewById(R.id.MolDyn);
        SimTimeLabel = (TextView) findViewById(R.id.SimTimeLabel);
        GeometryOpt = (TextView) findViewById(R.id.GeometryOpt);
        HessianUpdate = (TextView) findViewById(R.id.HessianUpdate);
        RFO = (TextView) findViewById(R.id.RFO);
        Solver = (TextView) findViewById(R.id.Solver);
        EnergyThrLabel = (TextView) findViewById(R.id.EnergyThrLabel);
        GradThrLabel = (TextView) findViewById(R.id.GradThrLabel);

        LevelShift = (EditText) findViewById(R.id.LevelShift);
        LevelShift.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        LevelShift.addTextChangedListener(new TextWatcher() {
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
                LevelShift.removeTextChangedListener(this);
                String text = LevelShift.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                LevelShift.getText().clear();
                LevelShift.append(colorized_numbers(text));
                // place the cursor at the original position
                LevelShift.setSelection(startChanged+countChanged);
                LevelShift.addTextChangedListener(this);
            }
        });
        Charge = (EditText) findViewById(R.id.Charge);
        Charge.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        Charge.addTextChangedListener(new TextWatcher() {
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
                Charge.removeTextChangedListener(this);
                String text = Charge.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                Charge.getText().clear();
                Charge.append(colorized_numbers(text));
                // place the cursor at the original position
                Charge.setSelection(startChanged+countChanged);
                Charge.addTextChangedListener(this);
            }
        });
        Multiplicity = (EditText) findViewById(R.id.Multiplicity);
        Multiplicity.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        Multiplicity.addTextChangedListener(new TextWatcher() {
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
                Multiplicity.removeTextChangedListener(this);
                String text = Multiplicity.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                Multiplicity.getText().clear();
                Multiplicity.append(colorized_numbers(text));
                // place the cursor at the original position
                Multiplicity.setSelection(startChanged+countChanged);
                Multiplicity.addTextChangedListener(this);
            }
        });
        Temperature = (EditText) findViewById(R.id.Temperature);
        Temperature.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        Temperature.addTextChangedListener(new TextWatcher() {
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
                Temperature.removeTextChangedListener(this);
                String text = Temperature.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                Temperature.getText().clear();
                Temperature.append(colorized_numbers(text));
                // place the cursor at the original position
                Temperature.setSelection(startChanged+countChanged);
                Temperature.addTextChangedListener(this);
            }
        });
        ElTemperature = (EditText) findViewById(R.id.ElTemperature);
        ElTemperature.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        ElTemperature.addTextChangedListener(new TextWatcher() {
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
                ElTemperature.removeTextChangedListener(this);
                String text = ElTemperature.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                ElTemperature.getText().clear();
                ElTemperature.append(colorized_numbers(text));
                // place the cursor at the original position
                ElTemperature.setSelection(startChanged+countChanged);
                ElTemperature.addTextChangedListener(this);
            }
        });
        TimeStep = (EditText) findViewById(R.id.TimeStep);
        TimeStep.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        TimeStep.addTextChangedListener(new TextWatcher() {
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
                TimeStep.removeTextChangedListener(this);
                String text = TimeStep.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                TimeStep.getText().clear();
                TimeStep.append(colorized_numbers(text));
                // place the cursor at the original position
                TimeStep.setSelection(startChanged+countChanged);
                TimeStep.addTextChangedListener(this);
            }
        });
        SimTime = (EditText) findViewById(R.id.SimTime);
        SimTime.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        SimTime.addTextChangedListener(new TextWatcher() {
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
                SimTime.removeTextChangedListener(this);
                String text = SimTime.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                SimTime.getText().clear();
                SimTime.append(colorized_numbers(text));
                // place the cursor at the original position
                SimTime.setSelection(startChanged+countChanged);
                SimTime.addTextChangedListener(this);
            }
        });
        EnergyThr = (EditText) findViewById(R.id.EnergyThr);
        EnergyThr.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        EnergyThr.addTextChangedListener(new TextWatcher() {
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
                EnergyThr.removeTextChangedListener(this);
                String text = EnergyThr.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                EnergyThr.getText().clear();
                EnergyThr.append(colorized_numbers(text));
                // place the cursor at the original position
                EnergyThr.setSelection(startChanged+countChanged);
                EnergyThr.addTextChangedListener(this);
            }
        });
        GradThr = (EditText) findViewById(R.id.GradThr);
        GradThr.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        GradThr.addTextChangedListener(new TextWatcher() {
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
                GradThr.removeTextChangedListener(this);
                String text = GradThr.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                GradThr.getText().clear();
                GradThr.append(colorized_numbers(text));
                // place the cursor at the original position
                GradThr.setSelection(startChanged+countChanged);
                GradThr.addTextChangedListener(this);
            }
        });


        Spinner VerbositySpinner = (Spinner) findViewById(R.id.VerbositySpinner);
        String VerbositySpinner_str = exec("cat "+getFilesDir()+"/Ulysses/verbosity.tmp");
        String[] VerbositySpinnerValues = {VerbositySpinner_str,"0","1"};
        ArrayAdapter<String> VerbositySpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, VerbositySpinnerValues);
        VerbositySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        VerbositySpinner.setAdapter(VerbositySpinnerAdapter);
        VerbositySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("verbosity.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/verbosity.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner RuntypeSpinner = (Spinner) findViewById(R.id.RuntypeSpinner);
        String RuntypeSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/runtype.tmp");
        String[] RuntypeSpinnerValues = {RuntypeSpinner_str,"nddo", "xtb", "md"};
        ArrayAdapter<String> RuntypeSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, RuntypeSpinnerValues);
        RuntypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        RuntypeSpinner.setAdapter(RuntypeSpinnerAdapter);
        RuntypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("runtype.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/runtype.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner OptSpinner = (Spinner) findViewById(R.id.OptSpinner);
        String OptSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/opt.tmp");
        String[] OptSpinnerValues = {OptSpinner_str,"0","1"};
        ArrayAdapter<String> OptSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, OptSpinnerValues);
        OptSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OptSpinner.setAdapter(OptSpinnerAdapter);
        OptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("opt.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/opt.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner TsSpinner = (Spinner) findViewById(R.id.TsSpinner);
        String TsSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/ts.tmp");
        String[] TsSpinnerValues = {TsSpinner_str,"0","1"};
        ArrayAdapter<String> TsSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, TsSpinnerValues);
        TsSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TsSpinner.setAdapter(TsSpinnerAdapter);
        TsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("ts.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/ts.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner ThermoSpinner = (Spinner) findViewById(R.id.ThermoSpinner);
        String ThermoSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/thermo.tmp");
        String[] ThermoSpinnerValues = {ThermoSpinner_str,"0","1"};
        ArrayAdapter<String> ThermoSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, ThermoSpinnerValues);
        ThermoSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ThermoSpinner.setAdapter(ThermoSpinnerAdapter);
        ThermoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("thermo.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/thermo.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner BasisSpinner = (Spinner) findViewById(R.id.BasisSpinner);
        String BasisSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/basis_set.tmp");
        String[] BasisSpinnerValues = {BasisSpinner_str,"am1", "pm3", "pm3pddg", "pm3bp", "rm1", "pm6", "mndo", "mndopddg", "mndod", "gfn2"};
        ArrayAdapter<String> BasisSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, BasisSpinnerValues);
        BasisSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BasisSpinner.setAdapter(BasisSpinnerAdapter);
        BasisSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("basis_set.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/basis_set.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner DispCorrSpinner = (Spinner) findViewById(R.id.DispCorrSpinner);
        String DispCorrSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/correction.tmp");
        String[] DispCorrSpinnerValues = {DispCorrSpinner_str,"0","D3H+","D3H4","D3H4X"};
        ArrayAdapter<String> DispCorrSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, DispCorrSpinnerValues);
        DispCorrSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DispCorrSpinner.setAdapter(DispCorrSpinnerAdapter);
        DispCorrSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("correction.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/correction.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner PointGroupSpinner = (Spinner) findViewById(R.id.PointGroupSpinner);
        String PointGroupSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/point_group.tmp");
        String[] PointGroupSpinnerValues = {PointGroupSpinner_str,"DINFh", "CINFv", "C2h", "C3h", "C4h", "C5h", "C6h", "C2v", "C3v", "C4v", "C5v", "C6v", "C7v", "C8v", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "Cs", "Ci", "D2h", "D3h", "D4h", "D5h", "D6h", "D7h", "D8h", "D2d", "D3d", "D4d", "D5d", "D6d", "D7d", "D8d", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "T", "Th", "Td", "O", "Oh", "I", "Ih", "S2", "S4", "S6", "S8", "S10", "S12"};
        ArrayAdapter<String> PointGroupSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, PointGroupSpinnerValues);
        PointGroupSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PointGroupSpinner.setAdapter(PointGroupSpinnerAdapter);
        PointGroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("point_group.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/point_group.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner RadicalSpinner = (Spinner) findViewById(R.id.RadicalSpinner);
        String RadicalSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/radical.tmp");
        String[] RadicalSpinnerValues = {RadicalSpinner_str,"0","RHF","UHF"};
        ArrayAdapter<String> RadicalSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, RadicalSpinnerValues);
        RadicalSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        RadicalSpinner.setAdapter(RadicalSpinnerAdapter);
        RadicalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("radical.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/radical.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner SolventSpinner = (Spinner) findViewById(R.id.SolventSpinner);
        String SolventSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/solvent.tmp");
        String[] SolventSpinnerValues = {SolventSpinner_str,"without_solvent","water","acetone","acetonitrile","aniline","benzaldehyde","benzene","dichloromethane","chloroform","carbon disulfide","dioxane","dmf","dmso","ethanol","diethyl ether","ethyl acetate","furane","hexadecane","hexane","methanol","nitromethane","octanol","phenol","thf","toluene","water","octanol wet"};
        ArrayAdapter<String> SolventSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, SolventSpinnerValues);
        SolventSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SolventSpinner.setAdapter(SolventSpinnerAdapter);
        SolventSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("solvent.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/solvent.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner PrintSpinner = (Spinner) findViewById(R.id.PrintSpinner);
        String PrintSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/charge_types.tmp");
        String[] PrintSpinnerValues = {PrintSpinner_str,"Mulliken","Loewdin","CM1","CM2","CM3","CM5","Goedecker"};
        ArrayAdapter<String> PrintSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, PrintSpinnerValues);
        PrintSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PrintSpinner.setAdapter(PrintSpinnerAdapter);
        PrintSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("charge_types.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/charge_types.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner BFGSSpinner = (Spinner) findViewById(R.id.BFGSSpinner);
        String BFGSSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/bfgs.tmp");
        String[] BFGSSpinnerValues = {BFGSSpinner_str,"1","2","3","4","5","6"};
        ArrayAdapter<String> BFGSSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, BFGSSpinnerValues);
        BFGSSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BFGSSpinner.setAdapter(BFGSSpinnerAdapter);
        BFGSSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("bfgs.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/bfgs.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner HessianUpdateSpinner = (Spinner) findViewById(R.id.HessianUpdateSpinner);
        String HessianUpdateSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/hessian_update.tmp");
        String[] HessianUpdateSpinnerValues = {HessianUpdateSpinner_str,"0","1","2","3","4"};
        ArrayAdapter<String> HessianUpdateSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, HessianUpdateSpinnerValues);
        HessianUpdateSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HessianUpdateSpinner.setAdapter(HessianUpdateSpinnerAdapter);
        HessianUpdateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("hessian_update.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/hessian_update.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner RFOSpinner = (Spinner) findViewById(R.id.RFOSpinner);
        String RFOSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/rfo.tmp");
        String[] RFOSpinnerValues = {RFOSpinner_str,"0","1"};
        ArrayAdapter<String> RFOSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, RFOSpinnerValues);
        RFOSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        RFOSpinner.setAdapter(RFOSpinnerAdapter);
        RFOSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("rfo.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/rfo.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner SolverSpinner = (Spinner) findViewById(R.id.SolverSpinner);
        String SolverSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/solver.tmp");
        String[] SolverSpinnerValues = {SolverSpinner_str,"0","3","4"};
        ArrayAdapter<String> SolverSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, SolverSpinnerValues);
        SolverSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SolverSpinner.setAdapter(SolverSpinnerAdapter);
        SolverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("solver.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/solver.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void onStart()
    {
        super.onStart();
        output1(exec("cat "+getFilesDir()+"/Ulysses/level_shift.tmp"));
        output2(exec("cat "+getFilesDir()+"/Ulysses/charge.tmp"));
        output3(exec("cat "+getFilesDir()+"/Ulysses/multiplicity.tmp"));
        // must not be the same as in Ulysses.java - i.e. not 4 and 5 - otherwise different text field are displayed
        output14(exec("cat "+getFilesDir()+"/Ulysses/temperature.tmp"));
        output15(exec("cat "+getFilesDir()+"/Ulysses/electronic_temperature.tmp"));
        output6(exec("cat "+getFilesDir()+"/Ulysses/time_step.tmp"));
        output7(exec("cat "+getFilesDir()+"/Ulysses/simulation_time.tmp"));
        output8(exec("cat "+getFilesDir()+"/Ulysses/energy_threshold.tmp"));
        output9(exec("cat "+getFilesDir()+"/Ulysses/gradient_threshold.tmp"));
    }

    private View.OnClickListener QuitClick; {
        QuitClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                String LS = LevelShift.getText().toString();
                String C = Charge.getText().toString();
                String Mult = Multiplicity.getText().toString();
                String Temp = Temperature.getText().toString();
                String ElTemp = ElTemperature.getText().toString();
                String TS = TimeStep.getText().toString();
                String ST = SimTime.getText().toString();
                String ET = EnergyThr.getText().toString();
                String GT = GradThr.getText().toString();
                try {
                    FileOutputStream fileout = openFileOutput("level_shift.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(LS);
                    outputWriter.close();
                    FileOutputStream fileout2 = openFileOutput("charge.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    outputWriter2.write(C);
                    outputWriter2.close();
                    FileOutputStream fileout3 = openFileOutput("multiplicity.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    outputWriter3.write(Mult);
                    outputWriter3.close();
                    FileOutputStream fileout4 = openFileOutput("temperature.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter4 = new OutputStreamWriter(fileout4);
                    outputWriter4.write(Temp);
                    outputWriter4.close();
                    FileOutputStream fileout5 = openFileOutput("electronic_temperature.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter5 = new OutputStreamWriter(fileout5);
                    outputWriter5.write(ElTemp);
                    outputWriter5.close();
                    FileOutputStream fileout6 = openFileOutput("time_step.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter6 = new OutputStreamWriter(fileout6);
                    outputWriter6.write(TS);
                    outputWriter6.close();
                    FileOutputStream fileout7 = openFileOutput("simulation_time.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter7 = new OutputStreamWriter(fileout7);
                    outputWriter7.write(ST);
                    outputWriter7.close();
                    FileOutputStream fileout8 = openFileOutput("energy_threshold.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter8 = new OutputStreamWriter(fileout8);
                    outputWriter8.write(ET);
                    outputWriter8.close();
                    FileOutputStream fileout9 = openFileOutput("gradient_threshold.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter9 = new OutputStreamWriter(fileout9);
                    outputWriter9.write(GT);
                    outputWriter9.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exec("mv "+getFilesDir()+"/level_shift.tmp "+getFilesDir()+"/Ulysses");
                exec("mv "+getFilesDir()+"/charge.tmp "+getFilesDir()+"/Ulysses");
                exec("mv "+getFilesDir()+"/multiplicity.tmp "+getFilesDir()+"/Ulysses");
                exec("mv "+getFilesDir()+"/temperature.tmp "+getFilesDir()+"/Ulysses");
                exec("mv "+getFilesDir()+"/electronic_temperature.tmp "+getFilesDir()+"/Ulysses");
                exec("mv "+getFilesDir()+"/time_step.tmp "+getFilesDir()+"/Ulysses");
                exec("mv "+getFilesDir()+"/simulation_time.tmp "+getFilesDir()+"/Ulysses");
                exec("mv "+getFilesDir()+"/energy_threshold.tmp "+getFilesDir()+"/Ulysses");
                exec("mv "+getFilesDir()+"/gradient_threshold.tmp "+getFilesDir()+"/Ulysses");
                Intent intent = new Intent(UlyssesOptions.this, Ulysses.class);
                startActivity(intent);
            }
        };
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

    public void output1(final String str1) {
        Runnable proc1 = new Runnable() {
            public void run() {
                LevelShift.setText(colorized_numbers(str1), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc1);
    }
    public void output2(final String str2) {
        Runnable proc2 = new Runnable() {
            public void run() {
                Charge.setText(colorized_numbers(str2), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc2);
    }
    public void output3(final String str3) {
        Runnable proc3 = new Runnable() {
            public void run() {
                Multiplicity.setText(colorized_numbers(str3), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc3);
    }
    public void output14(final String str14) {
        Runnable proc14 = new Runnable() {
            public void run() {
                Temperature.setText(colorized_numbers(str14), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc14);
    }
    public void output15(final String str15) {
        Runnable proc15 = new Runnable() {
            public void run() {
                ElTemperature.setText(colorized_numbers(str15), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc15);
    }
    public void output6(final String str6) {
        Runnable proc6 = new Runnable() {
            public void run() {
                TimeStep.setText(colorized_numbers(str6), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc6);
    }
    public void output7(final String str7) {
        Runnable proc7 = new Runnable() {
            public void run() {
                SimTime.setText(colorized_numbers(str7), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc7);
    }
    public void output8(final String str8) {
        Runnable proc8 = new Runnable() {
            public void run() {
                EnergyThr.setText(colorized_numbers(str8), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc8);
    }
    public void output9(final String str9) {
        Runnable proc9 = new Runnable() {
            public void run() {
                GradThr.setText(colorized_numbers(str9), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc9);
    }

}

