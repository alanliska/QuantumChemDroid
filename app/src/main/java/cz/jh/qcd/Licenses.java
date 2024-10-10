package cz.jh.qcd;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Licenses extends MainActivity {
    TextView license_label;
    Button license_acpdfview;
    Button license_android_shell;
    Button license_catch2;
    Button license_cli11;
    Button license_cxxopts;
    Button license_eigen3;
    Button licensing_terms_eigen3;
    Button license_fmt;
    Button license_gau2grid;
    Button license_gemmi;
    Button license_graphview;
    Button license_json;
    Button license_lbfgs;
    Button license_libcint;
    Button license_libecpint;
    Button license_libxc;
    Button license_nanoflann;
    Button license_occ;
    Button licensing_terms_occ;
    Button license_openbabel;
    Button license_opsin;
    Button license_phmap;
    Button license_pocketfft;
    Button license_rapidjson;
    Button license_scnlib;
    Button license_spdlog;
    Button license_tinyply;
    Button license_ulysses;
    Button license_x11basic;
    Button licensing_terms_x11basic;
    Button license_xsum;
    Button quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.licenses);

        license_label = (TextView) findViewById(R.id.license_label);

        license_acpdfview = (Button) findViewById(R.id.license_acpdfview);
        license_acpdfview.setOnClickListener(license_acpdfviewClick);

        license_android_shell = (Button) findViewById(R.id.license_android_shell);
        license_android_shell.setOnClickListener(license_android_shellClick);

        license_catch2 = (Button) findViewById(R.id.license_catch2);
        license_catch2.setOnClickListener(license_catch2Click);

        license_cli11 = (Button) findViewById(R.id.license_cli11);
        license_cli11.setOnClickListener(license_cli11Click);

        license_cxxopts = (Button) findViewById(R.id.license_cxxopts);
        license_cxxopts.setOnClickListener(license_cxxoptsClick);

        license_eigen3 = (Button) findViewById(R.id.license_eigen3);
        license_eigen3.setOnClickListener(license_eigen3Click);

        licensing_terms_eigen3 = (Button) findViewById(R.id.licensing_terms_eigen3);
        licensing_terms_eigen3.setOnClickListener(licensing_terms_eigen3Click);

        license_fmt = (Button) findViewById(R.id.license_fmt);
        license_fmt.setOnClickListener(license_fmtClick);

        license_gau2grid = (Button) findViewById(R.id.license_gau2grid);
        license_gau2grid.setOnClickListener(license_gau2gridClick);

        license_gemmi = (Button) findViewById(R.id.license_gemmi);
        license_gemmi.setOnClickListener(license_gemmiClick);

        license_graphview = (Button) findViewById(R.id.license_graphview);
        license_graphview.setOnClickListener(license_graphviewClick);

        license_json = (Button) findViewById(R.id.license_json);
        license_json.setOnClickListener(license_jsonClick);

        license_lbfgs = (Button) findViewById(R.id.license_lbfgs);
        license_lbfgs.setOnClickListener(license_lbfgsClick);

        license_libcint = (Button) findViewById(R.id.license_libcint);
        license_libcint.setOnClickListener(license_libcintClick);

        license_libecpint = (Button) findViewById(R.id.license_libecpint);
        license_libecpint.setOnClickListener(license_libecpintClick);

        license_libxc = (Button) findViewById(R.id.license_libxc);
        license_libxc.setOnClickListener(license_libxcClick);

        license_nanoflann = (Button) findViewById(R.id.license_nanoflann);
        license_nanoflann.setOnClickListener(license_nanoflannClick);

        license_occ = (Button) findViewById(R.id.license_occ);
        license_occ.setOnClickListener(license_occClick);

        licensing_terms_occ = (Button) findViewById(R.id.licensing_terms_occ);
        licensing_terms_occ.setOnClickListener(licensing_terms_occClick);

        license_openbabel = (Button) findViewById(R.id.license_openbabel);
        license_openbabel.setOnClickListener(license_openbabelClick);

        license_opsin = (Button) findViewById(R.id.license_opsin);
        license_opsin.setOnClickListener(license_opsinClick);

        license_phmap = (Button) findViewById(R.id.license_phmap);
        license_phmap.setOnClickListener(license_phmapClick);

        license_pocketfft = (Button) findViewById(R.id.license_pocketfft);
        license_pocketfft.setOnClickListener(license_pocketfftClick);

        license_rapidjson = (Button) findViewById(R.id.license_rapidjson);
        license_rapidjson.setOnClickListener(license_rapidjsonClick);

        license_scnlib = (Button) findViewById(R.id.license_scnlib);
        license_scnlib.setOnClickListener(license_scnlibClick);

        license_spdlog = (Button) findViewById(R.id.license_spdlog);
        license_spdlog.setOnClickListener(license_spdlogClick);

        license_tinyply = (Button) findViewById(R.id.license_tinyply);
        license_tinyply.setOnClickListener(license_tinyplyClick);

        license_ulysses = (Button) findViewById(R.id.license_ulysses);
        license_ulysses.setOnClickListener(license_ulyssesClick);

        license_x11basic = (Button) findViewById(R.id.license_x11basic);
        license_x11basic.setOnClickListener(license_x11basicClick);

        licensing_terms_x11basic = (Button) findViewById(R.id.licensing_terms_x11basic);
        licensing_terms_x11basic.setOnClickListener(licensing_terms_x11basicClick);

        license_xsum = (Button) findViewById(R.id.license_xsum);
        license_xsum.setOnClickListener(license_xsumClick);

        quit = (Button) findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Licenses.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private View.OnClickListener license_acpdfviewClick; {
        license_acpdfviewClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_acpdfview();
            }
        };
    }
    public void alert_acpdfview() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-ACPDFVIEW")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-ACPDFVIEW.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_android_shellClick; {
        license_android_shellClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_android_shell();
            }
        };
    }
    public void alert_android_shell() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-ANDROID_SHELL")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-ANDROID_SHELL.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_catch2Click; {
        license_catch2Click = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_catch2();
            }
        };
    }
    public void alert_catch2() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-CATCH2")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-CATCH2.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_cli11Click; {
        license_cli11Click = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_cli11();
            }
        };
    }
    public void alert_cli11() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-CLI11")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-CLI11.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_cxxoptsClick; {
        license_cxxoptsClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_cxxopts();
            }
        };
    }
    public void alert_cxxopts() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-CXXOPTS")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-CXXOPTS.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_eigen3Click; {
        license_eigen3Click = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_eigen3();
            }
        };
    }
    public void alert_eigen3() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-EIGEN3")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-EIGEN3.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener licensing_terms_eigen3Click; {
        licensing_terms_eigen3Click = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_eigen3_lt();
            }
        };
    }
    public void alert_eigen3_lt() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSING_TERMS-EIGEN3")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSING_TERMS-EIGEN3.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_fmtClick; {
        license_fmtClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_fmt();
            }
        };
    }
    public void alert_fmt() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-FMT")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-FMT.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_gau2gridClick; {
        license_gau2gridClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_gau2grid();
            }
        };
    }
    public void alert_gau2grid() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-GAU2GRID")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-GAU2GRID.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_gemmiClick; {
        license_gemmiClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_gemmi();
            }
        };
    }
    public void alert_gemmi() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-GEMMI")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-GEMMI.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_graphviewClick; {
        license_graphviewClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_graphview();
            }
        };
    }
    public void alert_graphview() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-GRAPHVIEW")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-GRAPHVIEW.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_jsonClick; {
        license_jsonClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_json();
            }
        };
    }
    public void alert_json() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-JSON")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-JSON.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_lbfgsClick; {
        license_lbfgsClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_lbfgs();
            }
        };
    }
    public void alert_lbfgs() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-LBFGS++")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-LBFGS++.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_libcintClick; {
        license_libcintClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_libcint();
            }
        };
    }
    public void alert_libcint() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-LIBCINT")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-LIBCINT.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_libecpintClick; {
        license_libecpintClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_libecpint();
            }
        };
    }
    public void alert_libecpint() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-LIBECPINT")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-LIBECPINT.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_libxcClick; {
        license_libxcClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_libxc();
            }
        };
    }
    public void alert_libxc() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-LIBXC")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-LIBXC.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_nanoflannClick; {
        license_nanoflannClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_nanoflann();
            }
        };
    }
    public void alert_nanoflann() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-NANOFLANN")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-NANOFLANN.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_occClick; {
        license_occClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_occ();
            }
        };
    }
    public void alert_occ() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-OCC")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-OCC.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener licensing_terms_occClick; {
        licensing_terms_occClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_occ_lt();
            }
        };
    }
    public void alert_occ_lt() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSING_TERMS-OCC")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSING_TERMS-OCC.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_openbabelClick; {
        license_openbabelClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_openbabel();
            }
        };
    }
    public void alert_openbabel() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-OPENBABEL")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-OPENBABEL.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_opsinClick; {
        license_opsinClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_opsin();
            }
        };
    }
    public void alert_opsin() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-OPSIN")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-OPSIN.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_phmapClick; {
        license_phmapClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_phmap();
            }
        };
    }
    public void alert_phmap() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-PHMAP")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-PHMAP.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_pocketfftClick; {
        license_pocketfftClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_pocketfft();
            }
        };
    }
    public void alert_pocketfft() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-POCKETFFT")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-POCKETFFT.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_rapidjsonClick; {
        license_rapidjsonClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_rapidjson();
            }
        };
    }
    public void alert_rapidjson() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-RAPIDJSON")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-RAPIDJSON.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_scnlibClick; {
        license_scnlibClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_scnlib();
            }
        };
    }
    public void alert_scnlib() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-SCNLIB")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-SCNLIB.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_spdlogClick; {
        license_spdlogClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_spdlog();
            }
        };
    }
    public void alert_spdlog() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-SPDLOG")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-SPDLOG.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_tinyplyClick; {
        license_tinyplyClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_tinyply();
            }
        };
    }
    public void alert_tinyply() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-TINYPLY")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-TINYPLY.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_ulyssesClick; {
        license_ulyssesClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_ulysses();
            }
        };
    }
    public void alert_ulysses() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-ULYSSES")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-ULYSSES.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }
    private View.OnClickListener license_x11basicClick; {
        license_x11basicClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_x11basic();
            }
        };
    }
    public void alert_x11basic() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-X11-BASIC")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-X11-BASIC.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener licensing_terms_x11basicClick; {
        licensing_terms_x11basicClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_x11basic_lt();
            }
        };
    }
    public void alert_x11basic_lt() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSING_TERMS-X11-BASIC")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSING_TERMS-X11-BASIC.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private View.OnClickListener license_xsumClick; {
        license_xsumClick = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub //
                alert_xsum();
            }
        };
    }
    public void alert_xsum() {
        new AlertDialog.Builder(Licenses.this)
                .setTitle("LICENSE-XSUM")
                .setMessage(exec("cat "+getFilesDir()+"/licenses/LICENSE-XSUM.txt"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
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
