# QuantumChemDroid
OCC + ULYSSES

Description & Use:
QuantumChemDroid is a mobile app capable of performing the semiempirical calculations (due to contained ULYSSES code) as well as the single point ab-initio computations (powered by OCC code). All the contained binaries are optimized for maximum performance in the mobile devices. OCC is parallelized, the users can choose number of OpenMP or std. threads for running the job. The app is working completely offline, does not collect, transmit, or distribute any personally identifiable information of the app users.

IMPORTANT !!!
Although this app is composed of open-source codes and resources, licenses for some components require the users to cite the original references when publishing the results. Please check all the licensing information under the buttons 'License' and 'About the app'.
All the users of the QuantumChemDroid app comply by downloading, installing and using it with all the licensing conditions of the individual software components and take the responsibility for keeping them. 

License: The QuantumChemDroid app repository (except from the binary content in /app/src/main/jniLibs/) itself is covered by GPL3 license. However, the included third-party software as well as the corresponding documentation retains the original licenses - please see the individual entries below. 
App source code: https://github.com/alanliska/QuantumChemDroid

Contact:
Compilation of the source code for Android as well as the Android app development was done by Alan Liška (alan.liska@jh-inst.cas.cz) and Veronika Růžičková (sucha.ver@gmail.com), J. Heyrovský Institute of Physical Chemistry of the CAS, v.v.i., Dolejškova 3/2155, 182 23 Praha 8, Czech Republic.
Website: http://www.jh-inst.cas.cz/~liska/MobileChemistry.htm

List of used third-party software:
ACPDFVIEW, ANDROID SHELL, CATCH2, CLI11, CXXOPTS, EIGEN3, FMT, GAU2GRID, GEMMI, GRAPHVIEW, JSON, LBFGS++, LIBCINT, LIBECPINT, LIBXC, NANOFLANN, OCC, OPENBABEL, OPSIN, PHMAP, POCKETFFT, RAPIDJSON, SCNLIB, SPDLOG, SUBPROCESS, TINYPLY, ULYSSES, X11-BASIC, XSUM.  

More info on licenses & references - please refer to the licensing information inside of the app.

==================================================================================================

Licenses and references to used third-party software:

 * ACPDFVIEW
 Author: Bhuvaneshw (Github)
 Source code: https://github.com/Bhuvaneshw/acpdfview
 License: GNU GPL-3.0

 * ANDROID SHELL
 Author: Jorrit "Chainfire" Jongma (JRummy Apps Inc.)
 Source code: https://github.com/aa668086/android-shell-master
 License: Apache License, Version 2.0

 * CATCH2
 Authors/developers: Catch2 Authors (for details, please see the project homepage)
 Source code: https://github.com/catchorg/Catch2
 License: BSL-1.0 license

 * CLI11
 Author: Henry Schreiner
 Source code: https://github.com/CLIUtils/CLI11
 License: open source

 * CXXOPTS
 Author: Jarryd Beck
 Source code: https://github.com/jarro2783/cxxopts
 License: MIT License

 * EIGEN3
 Authors: Benoît Jacob, Gaël Guennebaud and many others (please check the homepage https://eigen.tuxfamily.org/index.php?title=Main_Page)
 Source code: https://eigen.tuxfamily.org/index.php?title=Main_Page
 License: MPL2, earlier versions LGPL3+

 * FMT
 Authors/developers: Victor Zverovich and others (for details, please see the project homepage)
 Source code: https://github.com/fmtlib/fmt
 License: open source

 * GAU2GRID
 Authors/developers: Daniel G. A. Smith, Rob M. Parrish, Lori A. Burns, Andy C. Simmonett, Ben Pritchard
 Source code: https://github.com/dgasmith/gau2grid
 License: BSD-3-Clause license

 * GEMMI
 Author: Marcin Wojdyr
 Ref.: Wojdyr, M., (2022). GEMMI: A library for structural biology. Journal of Open Source Software, 7(73), 4200, https://doi.org/10.21105/joss.04200. 
 Source code: https://github.com/project-gemmi/gemmi
 License: MPLv2, LGPLv3

 * GRAPHVIEW
 Author: Jonas Gehring
 Source code: https://github.com/jjoe64/GraphView
 License: Apache License, Version 2.0

 * JSON
 Author: Niels Lohmann
 Source code: https://github.com/nlohmann/json
 License: MIT License

 * LBFGS++
 Authors: Jorge Nocedal, Naoaki Okazaki, Yixuan Qiu, Dirk Toewe
 Source code: https://github.com/yixuan/LBFGSpp/
 License: MIT license

 * LIBCINT
 Author: Qiming Sun
 Source code: https://github.com/sunqm/libcint
 License: BSD-2-Clause license

 * LIBECPINT
 Author: Robert A. Shaw
 Source code: https://github.com/robashaw/libecpint
 License: MIT license

 * LIBXC
 Authors: Miguel AL Marques, Micael JT Oliveira, Tobias Burnus, Susi Lehtola, Conrad Steigemann
 Ref.: Lehtola, S., Steigemann C., Oliveira, M.J.T., Marques, M.A.L.: "Recent developments in libxc—A comprehensive library of functionals for density functional theory." SoftwareX 7 (2018): 1.
 Source code: http://www.tddft.org/programs/libxc/
 License: MPL

 * NANOFLANN
 Authors/developers: Marius Muja, David G. Lowe, Jose L. Blanco
 Source code: https://github.com/jlblancoc/nanoflann
 License: BSD License

 * OCC
 Author: Peter Spackman
 Source code: https://github.com/peterspackman/occ
 License: GNU GPL

 * OPENBABEL
 Ref.: N M O'Boyle, M Banck, C A James, C Morley, T Vandermeersch, and G R Hutchison. "Open Babel: An open chemical toolbox." J. Cheminf. (2011), 3, 33. DOI:10.1186/1758-2946-3-33
 Source code: http://openbabel.org/wiki/Main_Page, https://github.com/openbabel/openbabel
 License: GPL-2.0

 * OPSIN
 Authors/developers: Rich Apodaca, Albina Asadulina, Peter Corbett, Daniel Lowe (Current maintainer), John Mayfield, Peter Murray-Rust, Noel O'Boyle, Mark Williamson
 Ref.: Lowe, Daniel M., Peter T. Corbett, Peter Murray-Rust, and Robert C. Glen. "Chemical name to structure: OPSIN, an open source solution." (2011): 739-753.
 Source code: https://github.com/dan2097/opsin
 License: MIT License

 * PHMAP
 Author: Gregory Popovitch
 Source code: https://github.com/greg7mdp/parallel-hashmap
 License: Apache-2.0 license

 * POCKETFFT
 Authors: Martin Reinecke, Peter Bell
 Source code: https://github.com/hayguen/pocketfft
 License: BSD-3-Clause license

 * RAPIDJSON
 Authors/developers: THL A29 Limited, a Tencent company, and Milo Yip
 Source code: https://github.com/Tencent/rapidjson
 License: MIT License

 * SCNLIB
 Authors: Elias Kosunen and others (for details, please see the project homepage)
 Source code: https://github.com/eliaskosunen/scnlib
 License: Apache-2.0 license

 * SPDLOG
 Authors: Gabi Melman and others (for details, please see the project homepage)
 Source code: https://github.com/gabime/spdlog
 License: MIT license

 * SUBPROCESS (for details, please see the sources of OCC)

 * TINYPLY
 Authors/developers: Dimitri Diakopoulos and others (for details, please see the project homepage)
 Source code: https://github.com/ddiakopoulos/tinyply
 License: public domain

 * ULYSSES
 Authors: Filipe Menezes, Grzegorz M. Popowicz
 Ref.: Menezes, F.; Popowicz, G. M. ULYSSES: An Efficient and Easy to Use Semiempirical Library for C++. J. Chem. Inf. Model. 2022, 10.1021/acs.jcim.2c00757
 References to the methods contained:
 -NDDO methods:
  MNDO: a) Dewar, M. J. S.; Thiel, W. Ground states of molecules. 38. The MNDO method. Approximations and parameters. J. Am. Chem. Soc. 1977, 99, 4899. 10.1021/ja00457a004; b) Dewar, M. J. S.; Thiel, W. A semiempirical model for the two-center repulsion integrals in the NDDO approximation. Theor. Chim. Acta (Berl.) 1977, 46, 89. 10.1007/BF00548085
  AM1: Dewar, M. J. S.; Zoebisch, E. G.; Healy, E. F.; Stewart, J. J. P. Development and use of quantum mechanical molecular models. 76. AM1: a new general purpose quantum mechanical molecular model. J. Am. Chem. Soc. 1985, 107, 3902. 10.1021/ja00299a024
  PM3: a) Stewart, J. J. P. Optimization of Parameters for Semi-Empirical Methods I. Method. J. Comput. Chem. 1989, 10, 209. 10.1002/jcc.540100208; b) Stewart, J. J. P. Optimization of parameters for semiempirical methods. III Extension of PM3 to Be, Mg, Zn, Ga, Ge, As, Se, Cd, In, Sn, Sb, Te, Hg, Tl, Pb, and Bi. J. Comput. Chem. 1991, 12, 320. 10.1002/jcc.540120306
  PM3-PDDG: a) Repasky, M. P.; Chandrasekhar, J.; Jorgensen, W. L. PDDG/PM3 and PDDG/MNDO: improved semiempirical methods. J. Comput. Chem. 2002, 23, 1601. 10.1002/jcc.10162; b) Tubert-Brohman, I.; Guimaraes, C. R. W.; Repasky, M. P.; Jorgensen, W. L. Extension of the PDDG/PM3 and PDDG/MNDO semiempirical molecular orbital methods to the halogens. J. Comput. Chem. 2004, 25, 138. 10.1002/jcc.10356; c) Tubert-Brohman, I.; Guimarães, C. R. W.; Jorgensen, W. L. Extension of the PDDG/PM3 Semiempirical Molecular Orbital Method to Sulfur, Silicon, and Phosphorus. J. Chem. Theory Comput. 2005, 1, 817. 10.1021/ct0500287
  MNDO-PDDG: a) Repasky, M. P.; Chandrasekhar, J.; Jorgensen, W. L. PDDG/PM3 and PDDG/MNDO: improved semiempirical methods. J. Comput. Chem. 2002, 23, 1601. 10.1002/jcc.10162; b) Tubert-Brohman, I.; Guimaraes, C. R. W.; Repasky, M. P.; Jorgensen, W. L. Extension of the PDDG/PM3 and PDDG/MNDO semiempirical molecular orbital methods to the halogens. J. Comput. Chem. 2004, 25, 138. 10.1002/jcc.10356
  PM3-BP: Giese, T. J.; Sherer, E. C.; Cramer, C. J.; York, D. M. A Semiempirical Quantum Model for Hydrogen-Bonded Nucleic Acid Base Pairs. J. Chem. Theory Comput. 2005, 1, 1275. 10.1021/ct050102l
  RM1: Rocha, G. B.; Freire, R. O.; Simas, A. M.; Stewart, J. J. P. RM1: a reparameterization of AM1 for H, C, N, O, P, S, F, Cl, Br, and I. J. Comput. Chem. 2006, 27, 1101. 10.1002/jcc.20425
  MNDOd: a) Thiel, W.; Voityuk, A. A. Extension of MNDO to d Orbitals: Parameters and Results for the Second-Row Elements and for the Zinc Group. J. Phys. Chem. 1996, 100, 616-626. 10.1021/jp952148o; b) Thiel, W.; Voityuk, A. A. Extension of the MNDO formalism to d orbitals: Integral approximations and preliminary numerical results. Theor. Chim. Acta 1992, 81, 391. 10.1007/BF01134863
  PM6: Stewart, J. J. P. Optimization of parameters for semiempirical methods V: Modification of NDDO approximations. J. Mol. Model. 2007, 13, 1173. 10.1007/s00894-007-0233-4
 -GFN2-xTB:
  Bannwarth, C.; Ehlert, S.; Grimme, S. GFN2-xTB - An Accurate and Broadly Parametrized Self-Consistent Tight-Binding Quantum Chemical Method with Multipole Electrostatics and Density-Dependent Dispersion Contributions. J. Chem. Theory Comput. 2019, 15, 1652-1671. 10.1021/acs.jctc.8b01176
 -Non-Bonded Corrections:
  D3: Grimme, S.; Antony, J.; Ehrlich, S.; Krieg, H. A consistent and accurate ab initio parametrization of density functional dispersion correction (DFT-D) for the 94 elements H-Pu. J. Chem. Phys. 2010, 132, 154104. 10.1063/1.3382344
  D4: Caldeweyher, E.; Ehlert, S.; Hansen, A.; Neugebauer, H.; Spicher, S.; Bannwarth, C.; Grimme, S. A generally applicable atomic-charge dependent London dispersion correction. J. Chem. Phys. 2019, 150, 154122. 10.1063/1.5090222
  H4: Rezac, J.; Hobza, P. Advanced Corrections of Hydrogen Bonding. J. Chem. Theory Comput. 2012, 8, 141-151. 10.1021/ct200751e
  X: Rezac, J.; Hobza, P. A halogen-bonding correction for the semiempirical PM6 method. Chem. Phys. Lett. 2011, 506, 286-289. 10.1016/j.cplett.2011.03.009
  H+: Kromann, J. C.; Christensen, A. S.; Steinmann, C.; Korth, M.; Jensen, J. H. A third-generation dispersion and third-generation hydrogen bonding corrected PM6 method: PM6-D3H+. Peer J. 2014, 2, e449. 10.7717/peerj.449
      Please note that certain methods or even specific element parameterisations might require additional references. These may be found in the ULYSSES paper. Lists of available elements are available from the supplementary material.
 Source code: https://gitlab.com/siriius/ulysses
 License: GNU Affero General Public License

 * X11-BASIC
 Author: Markus Hoffmann
 Source code: https://github.com/kollokollo/X11Basic
 License: GPL-2.0

 * XSUM
 Author: Yaser Afshar
 Source code: https://github.com/yafshar/xsum
 License: GNU LGPL-2.1

Manuals:

 * OCC: own manual

 * ULYSSES: own manual

 * X11-BASIC: https://x11-basic.sourceforge.net/X11-Basic-manual.pdf (GNU Free Documentation License)

Sources of the pre-compiled executables:

 * obabel: https://github.com/alanliska/openbabel-for-phreeqc-plus

 * occ: https://github.com/alanliska/occ-for-qcd

 * ulysses: https://github.com/alanliska/ulysses-for-qcd

 * xbbc: https://github.com/alanliska/X11-Basic-for-Android

 * xbvm: https://github.com/alanliska/X11-Basic-for-Android

Packed shared libraries:

 * libomp.so: from the official Google Android NDK (r25b)

Info on licenses: please see the full-text licenses under the button Licenses. 

ACKNOWLEDGEMENTS: 

The authors appreciate the financial support from the GACR projects 18-12150S, 19-22806S, 21-23261S, 23-06465S, and internal (institutional) support RVO: 61388955. 
