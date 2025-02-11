package cz.jh.qcd;

import static cz.jh.qcd.Spannables.colorized_numbers;

import android.content.Intent;
import android.os.Bundle;
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

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


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
    private TextView DispCorrGrimme;
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
    private TextView CalcDensLabel;
    private TextView CalcDensityFileLabel;
    private TextView ElecRxLabel;
    private TextView OrbRxLabel;
    private TextView KoopmanIpLabel;
    private TextView IpLabel;
    private TextView EaLabel;
    private TextView ElngLabel;
    private TextView HardnessLabel;
    private TextView MdOptGeomLabel;
    private TextView MdShakeLabel;
    private TextView MdEquilibrateLabel;
    private TextView MdPrintFreqLabel;
    private TextView MdMetaLabel;
    private TextView MdNumbStructLabel;
    private TextView MdKappaLabel;
    private TextView MdAlphaLabel;
    private TextView MdMtdCollectLabel;
    private TextView MdMtdRestraintsLabel;
    private TextView MdDriftThrLabel;
    private TextView MdGeomLocLabel;
    private TextView MdTrajLocLabel;
    private TextView MdEquiLocLabel;

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
    private EditText CalcDensityFile;
    private EditText MdPrintFreq;
    private EditText MdNumbStruct;
    private EditText MdKappa;
    private EditText MdAlpha;
    private EditText MdMtdCollect;
    private EditText MdMtdRestraints;
    private EditText MdDriftThr;
    private EditText MdGeomLoc;
    private EditText MdTrajLoc;
    private EditText MdEquiLoc;

    private Spinner VerbositySpinner;
    private Spinner RuntypeSpinner;
    private Spinner OptSpinner;
    private Spinner TsSpinner;
    private Spinner ThermoSpinner;
    private Spinner BasisSpinner;
    private Spinner DispCorrSpinner;
    private Spinner DispCorrGrimmeSpinner;
    private Spinner PointGroupSpinner;
    private Spinner RadicalSpinner;
    private Spinner SolventSpinner;
    private Spinner PrintSpinner;
    private Spinner BFGSSpinner;
    private Spinner HessianUpdateSpinner;
    private Spinner RFOSpinner;
    private Spinner SolverSpinner;
    private Spinner CalcDensSpinner;
    private Spinner ElecRxSpinner;
    private Spinner OrbRxSpinner;
    private Spinner KoopmanIpSpinner;
    private Spinner IpSpinner;
    private Spinner EaSpinner;
    private Spinner ElngSpinner;
    private Spinner HardnessSpinner;
    private Spinner MdOptGeomSpinner;
    private Spinner MdEquilibrateSpinner;
    private Spinner MdMetaSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
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
        CalcDensLabel = (TextView) findViewById(R.id.CalcDensLabel);
        CalcDensityFileLabel = (TextView) findViewById(R.id.CalcDensityFileLabel);
        ElecRxLabel = (TextView) findViewById(R.id.ElecRxLabel);
        OrbRxLabel = (TextView) findViewById(R.id.OrbRxLabel);
        KoopmanIpLabel = (TextView) findViewById(R.id.KoopmanIpLabel);
        IpLabel = (TextView) findViewById(R.id.IpLabel);
        EaLabel = (TextView) findViewById(R.id.EaLabel);
        ElngLabel = (TextView) findViewById(R.id.ElngLabel);
        HardnessLabel = (TextView) findViewById(R.id.HardnessLabel);
        MdOptGeomLabel = (TextView) findViewById(R.id.MdOptGeomLabel);
        MdShakeLabel = (TextView) findViewById(R.id.MdShakeLabel);
        MdPrintFreqLabel = (TextView) findViewById(R.id.MdPrintFreqLabel);
        MdMetaLabel = (TextView) findViewById(R.id.MdMetaLabel);
        MdNumbStructLabel = (TextView) findViewById(R.id.MdNumbStructLabel);
        MdKappaLabel = (TextView) findViewById(R.id.MdKappaLabel);
        MdAlphaLabel = (TextView) findViewById(R.id.MdAlphaLabel);
        MdMtdCollectLabel = (TextView) findViewById(R.id.MdMtdCollectLabel);
        MdMtdRestraintsLabel = (TextView) findViewById(R.id.MdMtdRestraintsLabel);
        MdDriftThrLabel = (TextView) findViewById(R.id.MdDriftThrLabel);
        MdGeomLocLabel = (TextView) findViewById(R.id.MdGeomLocLabel);
        MdTrajLocLabel = (TextView) findViewById(R.id.MdTrajLocLabel);
        MdEquiLocLabel = (TextView) findViewById(R.id.MdEquiLocLabel);

        MdEquiLoc = (EditText) findViewById(R.id.MdEquiLoc);
        MdEquiLoc.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        MdEquiLoc.addTextChangedListener(new TextWatcher() {
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
                MdEquiLoc.removeTextChangedListener(this);
                String text = MdEquiLoc.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                MdEquiLoc.getText().clear();
                MdEquiLoc.append(colorized_numbers(text));
                // place the cursor at the original position
                MdEquiLoc.setSelection(startChanged+countChanged);
                MdEquiLoc.addTextChangedListener(this);
            }
        });
        MdTrajLoc = (EditText) findViewById(R.id.MdTrajLoc);
        MdTrajLoc.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        MdTrajLoc.addTextChangedListener(new TextWatcher() {
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
                MdTrajLoc.removeTextChangedListener(this);
                String text = MdTrajLoc.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                MdTrajLoc.getText().clear();
                MdTrajLoc.append(colorized_numbers(text));
                // place the cursor at the original position
                MdTrajLoc.setSelection(startChanged+countChanged);
                MdTrajLoc.addTextChangedListener(this);
            }
        });
        MdGeomLoc = (EditText) findViewById(R.id.MdGeomLoc);
        MdGeomLoc.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        MdGeomLoc.addTextChangedListener(new TextWatcher() {
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
                MdGeomLoc.removeTextChangedListener(this);
                String text = MdGeomLoc.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                MdGeomLoc.getText().clear();
                MdGeomLoc.append(colorized_numbers(text));
                // place the cursor at the original position
                MdGeomLoc.setSelection(startChanged+countChanged);
                MdGeomLoc.addTextChangedListener(this);
            }
        });
        MdNumbStruct = (EditText) findViewById(R.id.MdNumbStruct);
        MdNumbStruct.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        MdNumbStruct.addTextChangedListener(new TextWatcher() {
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
                MdNumbStruct.removeTextChangedListener(this);
                String text = MdNumbStruct.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                MdNumbStruct.getText().clear();
                MdNumbStruct.append(colorized_numbers(text));
                // place the cursor at the original position
                MdNumbStruct.setSelection(startChanged+countChanged);
                MdNumbStruct.addTextChangedListener(this);
            }
        });
        MdKappa = (EditText) findViewById(R.id.MdKappa);
        MdKappa.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        MdKappa.addTextChangedListener(new TextWatcher() {
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
                MdKappa.removeTextChangedListener(this);
                String text = MdKappa.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                MdKappa.getText().clear();
                MdKappa.append(colorized_numbers(text));
                // place the cursor at the original position
                MdKappa.setSelection(startChanged+countChanged);
                MdKappa.addTextChangedListener(this);
            }
        });
        MdAlpha = (EditText) findViewById(R.id.MdAlpha);
        MdAlpha.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        MdAlpha.addTextChangedListener(new TextWatcher() {
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
                MdAlpha.removeTextChangedListener(this);
                String text = MdAlpha.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                MdAlpha.getText().clear();
                MdAlpha.append(colorized_numbers(text));
                // place the cursor at the original position
                MdAlpha.setSelection(startChanged+countChanged);
                MdAlpha.addTextChangedListener(this);
            }
        });
        MdMtdCollect = (EditText) findViewById(R.id.MdMtdCollect);
        MdMtdCollect.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        MdMtdCollect.addTextChangedListener(new TextWatcher() {
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
                MdMtdCollect.removeTextChangedListener(this);
                String text = MdMtdCollect.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                MdMtdCollect.getText().clear();
                MdMtdCollect.append(colorized_numbers(text));
                // place the cursor at the original position
                MdMtdCollect.setSelection(startChanged+countChanged);
                MdMtdCollect.addTextChangedListener(this);
            }
        });
        MdMtdRestraints = (EditText) findViewById(R.id.MdMtdRestraints);
        MdMtdRestraints.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        MdMtdRestraints.addTextChangedListener(new TextWatcher() {
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
                MdMtdRestraints.removeTextChangedListener(this);
                String text = MdMtdRestraints.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                MdMtdRestraints.getText().clear();
                MdMtdRestraints.append(colorized_numbers(text));
                // place the cursor at the original position
                MdMtdRestraints.setSelection(startChanged+countChanged);
                MdMtdRestraints.addTextChangedListener(this);
            }
        });
        MdDriftThr = (EditText) findViewById(R.id.MdDriftThr);
        MdDriftThr.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        MdDriftThr.addTextChangedListener(new TextWatcher() {
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
                MdDriftThr.removeTextChangedListener(this);
                String text = MdDriftThr.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                MdDriftThr.getText().clear();
                MdDriftThr.append(colorized_numbers(text));
                // place the cursor at the original position
                MdDriftThr.setSelection(startChanged+countChanged);
                MdDriftThr.addTextChangedListener(this);
            }
        });
        MdPrintFreq = (EditText) findViewById(R.id.MdPrintFreq);
        MdPrintFreq.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        MdPrintFreq.addTextChangedListener(new TextWatcher() {
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
                MdPrintFreq.removeTextChangedListener(this);
                String text = MdPrintFreq.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                MdPrintFreq.getText().clear();
                MdPrintFreq.append(colorized_numbers(text));
                // place the cursor at the original position
                MdPrintFreq.setSelection(startChanged+countChanged);
                MdPrintFreq.addTextChangedListener(this);
            }
        });
        CalcDensityFile = (EditText) findViewById(R.id.CalcDensityFile);
        CalcDensityFile.setTextSize(Integer.valueOf(exec("cat "+getFilesDir()+"/InputTextSize.txt")).intValue());
        CalcDensityFile.addTextChangedListener(new TextWatcher() {
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
                CalcDensityFile.removeTextChangedListener(this);
                String text = CalcDensityFile.getText().toString();
                // important - not setText() - otherwise the keyboard would be reset after each type
                CalcDensityFile.getText().clear();
                CalcDensityFile.append(colorized_numbers(text));
                // place the cursor at the original position
                CalcDensityFile.setSelection(startChanged+countChanged);
                CalcDensityFile.addTextChangedListener(this);
            }
        });
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

        Spinner DispCorrGrimmeSpinner = (Spinner) findViewById(R.id.DispCorrGrimmeSpinner);
        String DispCorrGrimmeSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/correction.tmp");
        String[] DispCorrGrimmeSpinnerValues = {DispCorrGrimmeSpinner_str,"0","1"};
        ArrayAdapter<String> DispCorrGrimmeSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, DispCorrGrimmeSpinnerValues);
        DispCorrGrimmeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DispCorrGrimmeSpinner.setAdapter(DispCorrGrimmeSpinnerAdapter);
        DispCorrGrimmeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("grimme_corr.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/grimme_corr.tmp "+getFilesDir()+"/Ulysses");
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

        Spinner CalcDensSpinner = (Spinner) findViewById(R.id.CalcDensSpinner);
        String CalcDensSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/calc_density.tmp");
        String[] CalcDensSpinnerValues = {CalcDensSpinner_str,"0","1"};
        ArrayAdapter<String> CalcDensSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, CalcDensSpinnerValues);
        CalcDensSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CalcDensSpinner.setAdapter(CalcDensSpinnerAdapter);
        CalcDensSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("calc_density.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/calc_density.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner ElecRxSpinner = (Spinner) findViewById(R.id.ElecRxSpinner);
        String ElecRxSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/elec_rx.tmp");
        String[] ElecRxSpinnerValues = {ElecRxSpinner_str,"0","1"};
        ArrayAdapter<String> ElecRxSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, ElecRxSpinnerValues);
        ElecRxSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ElecRxSpinner.setAdapter(ElecRxSpinnerAdapter);
        ElecRxSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("elec_rx.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/elec_rx.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner OrbRxSpinner = (Spinner) findViewById(R.id.OrbRxSpinner);
        String OrbRxSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/orb_rx.tmp");
        String[] OrbRxSpinnerValues = {OrbRxSpinner_str,"0","1"};
        ArrayAdapter<String> OrbRxSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, OrbRxSpinnerValues);
        OrbRxSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OrbRxSpinner.setAdapter(OrbRxSpinnerAdapter);
        OrbRxSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("orb_rx.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/orb_rx.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner KoopmanIpSpinner = (Spinner) findViewById(R.id.KoopmanIpSpinner);
        String KoopmanIpSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/koopman_ip.tmp");
        String[] KoopmanIpSpinnerValues = {KoopmanIpSpinner_str,"0","1"};
        ArrayAdapter<String> KoopmanIpSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, KoopmanIpSpinnerValues);
        KoopmanIpSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        KoopmanIpSpinner.setAdapter(KoopmanIpSpinnerAdapter);
        KoopmanIpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("koopman_ip.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/koopman_ip.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner IpSpinner = (Spinner) findViewById(R.id.IpSpinner);
        String IpSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/ip.tmp");
        String[] IpSpinnerValues = {IpSpinner_str,"0","1"};
        ArrayAdapter<String> IpSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, IpSpinnerValues);
        IpSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        IpSpinner.setAdapter(IpSpinnerAdapter);
        IpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("ip.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/ip.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner EaSpinner = (Spinner) findViewById(R.id.EaSpinner);
        String EaSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/ea.tmp");
        String[] EaSpinnerValues = {EaSpinner_str,"0","1"};
        ArrayAdapter<String> EaSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, EaSpinnerValues);
        EaSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        EaSpinner.setAdapter(EaSpinnerAdapter);
        EaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("ea.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/ea.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner ElngSpinner = (Spinner) findViewById(R.id.ElngSpinner);
        String ElngSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/electronegativity.tmp");
        String[] ElngSpinnerValues = {ElngSpinner_str,"0","1"};
        ArrayAdapter<String> ElngSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, ElngSpinnerValues);
        ElngSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ElngSpinner.setAdapter(ElngSpinnerAdapter);
        ElngSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("electronegativity.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/electronegativity.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner HardnessSpinner = (Spinner) findViewById(R.id.HardnessSpinner);
        String HardnessSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/hardness.tmp");
        String[] HardnessSpinnerValues = {HardnessSpinner_str,"0","1"};
        ArrayAdapter<String> HardnessSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, HardnessSpinnerValues);
        HardnessSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HardnessSpinner.setAdapter(HardnessSpinnerAdapter);
        HardnessSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("hardness.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/hardness.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner MdOptGeomSpinner = (Spinner) findViewById(R.id.MdOptGeomSpinner);
        String MdOptGeomSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/md_opt_geometry.tmp");
        String[] MdOptGeomSpinnerValues = {MdOptGeomSpinner_str,"0","1"};
        ArrayAdapter<String> MdOptGeomSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, MdOptGeomSpinnerValues);
        MdOptGeomSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MdOptGeomSpinner.setAdapter(MdOptGeomSpinnerAdapter);
        MdOptGeomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("md_opt_geometry.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/md_opt_geometry.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner MdShakeSpinner = (Spinner) findViewById(R.id.MdShakeSpinner);
        String MdShakeSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/md_shake.tmp");
        String[] MdShakeSpinnerValues = {MdShakeSpinner_str,"0","1","2"};
        ArrayAdapter<String> MdShakeSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, MdShakeSpinnerValues);
        MdShakeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MdShakeSpinner.setAdapter(MdShakeSpinnerAdapter);
        MdShakeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("md_shake.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/md_shake.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner MdEquilibrateSpinner = (Spinner) findViewById(R.id.MdEquilibrateSpinner);
        String MdEquilibrateSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/md_do_equilibration.tmp");
        String[] MdEquilibrateSpinnerValues = {MdEquilibrateSpinner_str,"0","1"};
        ArrayAdapter<String> MdEquilibrateSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, MdEquilibrateSpinnerValues);
        MdEquilibrateSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MdEquilibrateSpinner.setAdapter(MdEquilibrateSpinnerAdapter);
        MdEquilibrateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("md_do_equilibration.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/md_do_equilibration.tmp "+getFilesDir()+"/Ulysses");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner MdMetaSpinner = (Spinner) findViewById(R.id.MdMetaSpinner);
        String MdMetaSpinner_str = exec("cat "+getFilesDir()+"/Ulysses/md_mtd.tmp");
        String[] MdMetaSpinnerValues = {MdMetaSpinner_str,"0","1"};
        ArrayAdapter<String> MdMetaSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, MdMetaSpinnerValues);
        MdMetaSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MdMetaSpinner.setAdapter(MdMetaSpinnerAdapter);
        MdMetaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                try {
                    FileOutputStream fileout50 = openFileOutput("md_mtd.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout50);
                    outputWriter.write(item);
                    outputWriter.close();
                } catch (Exception e) {
                }
                exec("mv "+getFilesDir()+"/md_mtd.tmp "+getFilesDir()+"/Ulysses");
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
        output23(exec("cat "+getFilesDir()+"/Ulysses/multiplicity.tmp"));
        // must not be the same as in Ulysses.java - i.e. not 4 and 5 - otherwise different text field are displayed
        output14(exec("cat "+getFilesDir()+"/Ulysses/temperature.tmp"));
        output15(exec("cat "+getFilesDir()+"/Ulysses/electronic_temperature.tmp"));
        output6(exec("cat "+getFilesDir()+"/Ulysses/time_step.tmp"));
        output7(exec("cat "+getFilesDir()+"/Ulysses/simulation_time.tmp"));
        output8(exec("cat "+getFilesDir()+"/Ulysses/energy_threshold.tmp"));
        output9(exec("cat "+getFilesDir()+"/Ulysses/gradient_threshold.tmp"));
        output10(exec("cat "+getFilesDir()+"/Ulysses/md_print_freq.tmp"));
        output11(exec("cat "+getFilesDir()+"/Ulysses/md_numb_struct.tmp"));
        output12(exec("cat "+getFilesDir()+"/Ulysses/md_kappa.tmp"));
        output13(exec("cat "+getFilesDir()+"/Ulysses/md_alpha.tmp"));
        output24(exec("cat "+getFilesDir()+"/Ulysses/md_mtdcollect.tmp"));
        output25(exec("cat "+getFilesDir()+"/Ulysses/md_restraints.tmp"));
        output16(exec("cat "+getFilesDir()+"/Ulysses/md_drift_thresh.tmp"));
        output17(exec("cat "+getFilesDir()+"/Ulysses/density_file.tmp"));
        output18(exec("cat "+getFilesDir()+"/Ulysses/md_geometry_location.tmp"));
        output19(exec("cat "+getFilesDir()+"/Ulysses/md_trajectory_location.tmp"));
        output20(exec("cat "+getFilesDir()+"/Ulysses/md_equilibration_location.tmp"));
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
                String PF = MdPrintFreq.getText().toString();
                String NStr = MdNumbStruct.getText().toString();
                String Kappa = MdKappa.getText().toString();
                String Alpha = MdAlpha.getText().toString();
                String MtdCollect = MdMtdCollect.getText().toString();
                String Restraints = MdMtdRestraints.getText().toString();
                String DriftThr = MdDriftThr.getText().toString();
                String DensFile = CalcDensityFile.getText().toString();
                String GeomLoc = MdGeomLoc.getText().toString();
                String TrajLoc = MdTrajLoc.getText().toString();
                String EquiLoc = MdEquiLoc.getText().toString();

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
                    FileOutputStream fileout10 = openFileOutput("md_print_freq.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter10 = new OutputStreamWriter(fileout10);
                    outputWriter10.write(PF);
                    outputWriter10.close();
                    FileOutputStream fileout11 = openFileOutput("md_numb_struct.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter11 = new OutputStreamWriter(fileout11);
                    outputWriter11.write(NStr);
                    outputWriter11.close();
                    FileOutputStream fileout12 = openFileOutput("md_kappa.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter12 = new OutputStreamWriter(fileout12);
                    outputWriter12.write(Kappa);
                    outputWriter12.close();
                    FileOutputStream fileout13 = openFileOutput("md_alpha.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter13 = new OutputStreamWriter(fileout13);
                    outputWriter13.write(Alpha);
                    outputWriter13.close();
                    FileOutputStream fileout14 = openFileOutput("md_mtdcollect.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter14 = new OutputStreamWriter(fileout14);
                    outputWriter14.write(MtdCollect);
                    outputWriter14.close();
                    FileOutputStream fileout15 = openFileOutput("md_restraints.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter15 = new OutputStreamWriter(fileout15);
                    outputWriter15.write(Restraints);
                    outputWriter15.close();
                    FileOutputStream fileout16 = openFileOutput("md_drift_thresh.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter16 = new OutputStreamWriter(fileout16);
                    outputWriter16.write(DriftThr);
                    outputWriter16.close();
                    FileOutputStream fileout17 = openFileOutput("density_file.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter17 = new OutputStreamWriter(fileout17);
                    outputWriter17.write(DensFile);
                    outputWriter17.close();
                    FileOutputStream fileout18 = openFileOutput("md_geometry_location.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter18 = new OutputStreamWriter(fileout18);
                    outputWriter18.write(GeomLoc);
                    outputWriter18.close();
                    FileOutputStream fileout19 = openFileOutput("md_trajectory_location.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter19 = new OutputStreamWriter(fileout19);
                    outputWriter19.write(TrajLoc);
                    outputWriter19.close();
                    FileOutputStream fileout20 = openFileOutput("md_equilibration_location.tmp", MODE_PRIVATE);
                    OutputStreamWriter outputWriter20 = new OutputStreamWriter(fileout20);
                    outputWriter20.write(EquiLoc);
                    outputWriter20.close();
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
                exec("mv "+getFilesDir()+"/md_print_freq.tmp "+getFilesDir()+"/Ulysses");
                exec("mv "+getFilesDir()+"/md_numb_struct.tmp "+getFilesDir()+"/Ulysses");
                exec("mv "+getFilesDir()+"/md_kappa.tmp "+getFilesDir()+"/Ulysses");
                exec("mv "+getFilesDir()+"/md_alpha.tmp "+getFilesDir()+"/Ulysses");
                exec("mv "+getFilesDir()+"/md_mtdcollect.tmp "+getFilesDir()+"/Ulysses");
                exec("mv "+getFilesDir()+"/md_restraints.tmp "+getFilesDir()+"/Ulysses");
                exec("mv "+getFilesDir()+"/md_drift_thresh.tmp "+getFilesDir()+"/Ulysses");
                exec("mv "+getFilesDir()+"/density_file.tmp "+getFilesDir()+"/Ulysses");
                exec("mv "+getFilesDir()+"/md_geometry_location.tmp "+getFilesDir()+"/Ulysses");
                exec("mv "+getFilesDir()+"/md_trajectory_location.tmp "+getFilesDir()+"/Ulysses");
                exec("mv "+getFilesDir()+"/md_equilibration_location.tmp "+getFilesDir()+"/Ulysses");
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
    public void output23(final String str23) {
        Runnable proc23 = new Runnable() {
            public void run() {
                Multiplicity.setText(colorized_numbers(str23), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc23);
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
    public void output10(final String str10) {
        Runnable proc10 = new Runnable() {
            public void run() {
                MdPrintFreq.setText(colorized_numbers(str10), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc10);
    }
    public void output11(final String str11) {
        Runnable proc11 = new Runnable() {
            public void run() {
                MdNumbStruct.setText(colorized_numbers(str11), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc11);
    }
    public void output12(final String str12) {
        Runnable proc12 = new Runnable() {
            public void run() {
                MdKappa.setText(colorized_numbers(str12), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc12);
    }
    public void output13(final String str13) {
        Runnable proc13 = new Runnable() {
            public void run() {
                MdAlpha.setText(colorized_numbers(str13), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc13);
    }
    public void output24(final String str24) {
        Runnable proc24 = new Runnable() {
            public void run() {
                MdMtdCollect.setText(colorized_numbers(str24), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc24);
    }
    public void output25(final String str25) {
        Runnable proc25 = new Runnable() {
            public void run() {
                MdMtdRestraints.setText(colorized_numbers(str25), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc25);
    }
    public void output16(final String str16) {
        Runnable proc16 = new Runnable() {
            public void run() {
                MdDriftThr.setText(colorized_numbers(str16), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc16);
    }
    public void output17(final String str17) {
        Runnable proc17 = new Runnable() {
            public void run() {
                CalcDensityFile.setText(colorized_numbers(str17), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc17);
    }
    public void output18(final String str18) {
        Runnable proc18 = new Runnable() {
            public void run() {
                MdGeomLoc.setText(colorized_numbers(str18), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc18);
    }
    public void output19(final String str19) {
        Runnable proc19 = new Runnable() {
            public void run() {
                MdTrajLoc.setText(colorized_numbers(str19), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc19);
    }
    public void output20(final String str20) {
        Runnable proc20 = new Runnable() {
            public void run() {
                MdEquiLoc.setText(colorized_numbers(str20), EditText.BufferType.SPANNABLE);
            }
        };
        handler.post(proc20);
    }

}

