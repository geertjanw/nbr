/* 
 * Maui, Maltcms User Interface. 
 * Copyright (C) 2008-2014, The authors of Maui. All rights reserved.
 *
 * Project website: http://maltcms.sf.net
 *
 * Maui may be used under the terms of either the
 *
 * GNU Lesser General Public License (LGPL)
 * http://www.gnu.org/licenses/lgpl.html
 *
 * or the
 *
 * Eclipse Public License (EPL)
 * http://www.eclipse.org/org/documents/epl-v10.php
 *
 * As a user/recipient of Maui, you may choose which license to receive the code 
 * under. Certain files or entire directories may not be covered by this 
 * dual license, but are subject to licenses compatible to both LGPL and EPL.
 * License exceptions are explicitly declared in all relevant files or in a 
 * LICENSE file in the relevant directories.
 *
 * Maui is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. Please consult the relevant license documentation
 * for details.
 */
package org.netbeans.r.rserve.api;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import org.netbeans.api.keyring.Keyring;
import org.netbeans.r.rserve.impl.StartRserveOriginal;
import org.netbeans.r.rserve.impl.StreamHog;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

/**
 *
 * @author Nils Hoffmann
 */
public final class RserveConnectionFactory implements PreferenceChangeListener {

    private static RserveConnectionFactory rcf = new RserveConnectionFactory();
    public static final String KEY_RBINARY_LOCATIONS = "rbinaryLocations";
    public static final String KEY_RSERVE_HOST = "rserveHost";
    public static final String KEY_RSERVE_PORT = "rservePort";
    public static final String KEY_RSERVE_LOCAL = "true";
    public static final String KEY_RSERVE_USER = "rserveUser";
    private boolean isLocalServer = false;
    private RConnection activeConnection = null;
    public static final String defaultUnixRlocations
            = "/Library/Frameworks/R.framework/Resources/bin/R,"
            + "/bin/sh,"
            + "/usr/local/lib/R/bin/R,"
            + "/usr/lib/R/bin/R,"
            + "/usr/local/bin/R,"
            + "/sw/bin/R,"
            + "/usr/common/bin/R,"
            + "/opt/bin/R,"
            + "/opt/local/bin/R,"
            + "/vol/r-2.13/bin/R";
    public static final String KEY_RSERVECALLS = "rserveCalls";
    ///Library/Frameworks/R.framework/Resources/library/Rserve/libs/Rserve-bin.so,
    public static final String defaultRserveCalls = "INLINEARGUMENT,Rserve";
    public static final String KEY_RARGS = "rargs";
    public static final String defaultRargs = "--vanilla --no-save";// --gui-none";
    public static final String KEY_RSERVEARGS = "rserveArgs";
    public static final String defaultRserveArgs = "";
    public static final String KEY_RBINARY_LOCATION = "rBinaryLocation";
    public static final String KEY_RSERVECALL = "rServeCall";
    public static final String KEY_RSERVE_DEBUG = "rServeDebug";
    public static final String defaultRserveDebug = "false";
    private String[] unixRlocations;
    private File rBinaryLocation;
    private String rArgs;
    private String rServeArgs;
    private String[] rserveCalls;
    private String rserveCall;
    private String rserveRemoteHost;
    private String rserveRemotePort;
    private boolean debug = false;
    private String userName;
    private final AtomicBoolean startedLocal = new AtomicBoolean(false);

    private RserveConnectionFactory() {
        NbPreferences.forModule(RserveConnectionFactory.class).addPreferenceChangeListener(this);
        loadPreferences();
    }

    private void loadPreferences() {
        unixRlocations = NbPreferences.forModule(RserveConnectionFactory.class).get(RserveConnectionFactory.KEY_RBINARY_LOCATIONS, defaultUnixRlocations).split(",");
        rArgs = NbPreferences.forModule(RserveConnectionFactory.class).get(RserveConnectionFactory.KEY_RARGS, defaultRargs);
        String rbinary = NbPreferences.forModule(RserveConnectionFactory.class).get(KEY_RBINARY_LOCATION, null);
        if (rbinary != null && !rbinary.isEmpty()) {
            rBinaryLocation = new File(rbinary);
        }
        rserveCalls = NbPreferences.forModule(RserveConnectionFactory.class).get(RserveConnectionFactory.KEY_RSERVECALLS, defaultRserveCalls).split(",");
        rServeArgs = NbPreferences.forModule(RserveConnectionFactory.class).get(RserveConnectionFactory.KEY_RSERVEARGS, defaultRserveArgs);
        rserveCall = NbPreferences.forModule(RserveConnectionFactory.class).get(RserveConnectionFactory.KEY_RSERVECALL, null);
        rserveRemoteHost = NbPreferences.forModule(RserveConnectionFactory.class).get(RserveConnectionFactory.KEY_RSERVE_HOST, "127.0.0.1");
        rserveRemotePort = NbPreferences.forModule(RserveConnectionFactory.class).get(RserveConnectionFactory.KEY_RSERVE_PORT, "6311");
        boolean localServer = Boolean.valueOf(NbPreferences.forModule(RserveConnectionFactory.class).get(RserveConnectionFactory.KEY_RSERVE_LOCAL, "true"));
        isLocalServer = localServer;
        debug = Boolean.valueOf(NbPreferences.forModule(RserveConnectionFactory.class).get(RserveConnectionFactory.KEY_RSERVE_DEBUG, "false"));
        userName = NbPreferences.forModule(RserveConnectionFactory.class).get(RserveConnectionFactory.KEY_RSERVE_USER, null);
        StringBuilder sb = new StringBuilder();
        sb.append("unixRlocations: ").append(Arrays.toString(unixRlocations)).append("\n");
        sb.append("rBinaryLocation: ").append(rBinaryLocation).append("\n");
        sb.append("rArgs: ").append(rArgs).append("\n");
        sb.append("rServeArgs: ").append(rServeArgs).append("\n");
        sb.append("rserveCalls: ").append(Arrays.toString(rserveCalls)).append("\n");
        sb.append("rserveCall: ").append(rserveCall).append("\n");
        sb.append("Remote Host: ").append(rserveRemoteHost).append("\n");
        sb.append("Remote Port: ").append(rserveRemotePort).append("\n");
        sb.append("Current mode: ").append(isLocalServer ? "Local" : "Remote").append("\n");
        sb.append("Debug mode: ").append(debug ? "TRUE" : "FALSE").append("\n");
        Logger.getLogger(RserveConnectionFactory.class.getName()).info(sb.toString());
    }

    public static RserveConnectionFactory getInstance() {
        return rcf;
    }

    //DO NOT CHANGE!!!
    public static RConnection getDefaultConnection() {
        RserveConnectionFactory factory = RserveConnectionFactory.getInstance();
        if (factory.isLocalServer) {
            return factory.getLocalConnection();
        } else {
            return factory.getRemoteConnection();
        }
    }

    private void setConnection(RConnection connection) {
        if (this.activeConnection == null) {
            this.activeConnection = connection;
        } else {
            throw new IllegalStateException("Can not reset active connection! Call RserveConnectionFactory.closeConnection() before!");
        }
    }

    public synchronized void closeConnection() {
        if (this.activeConnection != null) {
            Logger.getLogger(RserveConnectionFactory.class.getName()).info("Closing connection to Rserve!");
            if (isLocalServer) {
                try {
                    Logger.getLogger(RserveConnectionFactory.class.getName()).info("Shutting down local server!");
                    this.activeConnection.serverShutdown();
                } catch (RserveException ex) {
                    try {
                        this.activeConnection.shutdown();
                    } catch (RserveException ex1) {
                        Exceptions.printStackTrace(ex1);
                    }
                } finally {
                }
            }
            this.activeConnection.close();
            this.activeConnection = null;
        }
    }

    private boolean isWindows() {
        String osname = System.getProperty("os.name");
//        System.out.println("os.name: " + osname);
        if (osname != null && osname.length() >= 7 && osname.substring(0, 7).
                equals("Windows")) {
            return true;
        }
        return false;
    }

    private boolean isDarwin() {
        String osname = System.getProperty("os.name");
        if (osname != null && osname.equals("Mac OS X")) {
            return true;
        }
        return false;
    }

    private List<String> buildRserveCommand(File rBinaryLocation, boolean debug, String rServeCallValue, String rServeArgs, String rArgs) {
        List<String> commandList = new ArrayList<String>();
        String[] rRargs = rArgs.trim().split(" ");
        if (isWindows()) {
            commandList.add("\"" + rBinaryLocation.getAbsolutePath() + "\"");
        } else {
            if (rBinaryLocation.equals("/bin/sh")) {
                commandList.add(rBinaryLocation.getAbsolutePath());
                commandList.add("-c");
            } else {
                commandList.add(rBinaryLocation.getAbsolutePath());
            }

        }
        StringBuilder args = new StringBuilder();
        for (String arg : rRargs) {
            args.append(arg);
            args.append(" ");
        }
        String[] rSArgs = rServeArgs.trim().split(" ");
        for (String arg : rSArgs) {
            args.append(arg);
            args.append(" ");
        }
        String jointArgs = args.toString().trim();

        if (isWindows()) {
            commandList.addAll(Arrays.asList("-e", "\"library(Rserve);Rserve(" + (debug ? "TRUE" : "FALSE") + ",args='" + jointArgs + "')\""));
        } else {
            if (rServeCallValue.equals("INLINEARGUMENT")) {
                if (isDarwin()) {
                    commandList.addAll(Arrays.asList("-e", "library(Rserve);Rserve(" + (debug ? "TRUE" : "FALSE") + ",args='" + jointArgs + "')"));
                } else {
                    commandList.addAll(Arrays.asList("-e", "\"library(Rserve);Rserve(" + (debug ? "TRUE" : "FALSE") + ",args='" + jointArgs + "')\""));
                }
            } else {
                commandList.addAll(Arrays.asList("CMD", rServeCallValue));
                commandList.addAll(Arrays.asList(rRargs));
                commandList.addAll(Arrays.asList(rSArgs));
            }
        }

        return commandList;
    }

    private synchronized RConnection startLocalAndConnect() {
        if (StartRserveOriginal.checkLocalRserve()) {
            try {
                return new RConnection();
            } catch (RserveException ex) {
                throw new RuntimeException("Could not connect to local Rserve!");
            }
        }
        if (rBinaryLocation == null) {
            rBinaryLocation = getRBinaryLocation();
        }
        Logger.getLogger(RserveConnectionFactory.class.getName()).log(Level.INFO, "R binary location: {0}", rBinaryLocation);
        if (startedLocal.compareAndSet(false, true)) {
            if (rserveCall == null) {
                int exitValue = -1;
                for (String rserveCallValue : rserveCalls) {
                    List<String> rserveCmd = buildRserveCommand(rBinaryLocation, debug, rserveCallValue, rServeArgs, rArgs);
                    Logger.getLogger(RserveConnectionFactory.class.getName()).log(Level.INFO, "Trying to run: ''{0}''", rserveCmd);
                    Process p = null;
                    try {
                        p = startProcessAndWait(rserveCmd, true);
                        int retries = 5;
                        for (int i = 0; i < retries; i++) {
                            try {
                                RConnection conn = new RConnection(rserveRemoteHost, Integer.parseInt(rserveRemotePort));//rserveConnection.getConnection();
                                Logger.getLogger(RserveConnectionFactory.class.getName()).log(Level.INFO, "Connected to Rserve version {0}", conn.getServerVersion());
                                Logger.getLogger(RserveConnectionFactory.class.getName()).info("Keeping rserveCall for future reference!");
                                rserveCall = rserveCallValue;
                                startedLocal.compareAndSet(true, false);
                                return conn;
                            } catch (RserveException ex2) {
                                Logger.getLogger(RserveConnectionFactory.class.getName()).log(
                                        Level.WARNING, "Failed to connect on try {0}/{1}", new Object[]{1 + i, retries});
                            }

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ix) {
                            };
                        }
                    } catch (Exception e) {
                        Logger.getLogger(RserveConnectionFactory.class.getName()).warning(e.getLocalizedMessage());
                    } finally {
                        if (p != null) {
                            p.destroy();
                        }
                    }
                }
                startedLocal.compareAndSet(true, false);
                throw new RuntimeException("Could not determine local Rserve command!");
            }
        } else {
            throw new IllegalStateException("Already tried to start local instance!");
        }
        return null;
    }

    private Process startProcess(List<String> command, boolean capture) {
        boolean isWindows = isWindows();
        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            String pname = command.get(0);
            if (isWindows) {
                pname = pname.substring(pname.lastIndexOf("\\") + 1);
            } else {
                pname = pname.substring(pname.lastIndexOf("/") + 1);
            }
            Process p = pb.start();
            StreamHog errorHog = new StreamHog(pname,
                    p.getErrorStream(),
                    capture);
            StreamHog outputHog = new StreamHog(pname,
                    p.getInputStream(),
                    capture);
            return p;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    private Process startProcessAndWait(List<String> commandString, boolean capture) {
        boolean isWindows = isWindows();
        Process p = null;
        try {
            ProcessBuilder bp = new ProcessBuilder(commandString);
            String pname = commandString.get(0);
            if (isWindows) {
                pname = pname.substring(pname.lastIndexOf("\\") + 1);
            } else {
                pname = pname.substring(pname.lastIndexOf("/") + 1);
            }
            p = bp.start();
            StreamHog errorHog = new StreamHog(pname,
                    p.getErrorStream(),
                    capture);
            StreamHog outputHog = new StreamHog(pname,
                    p.getInputStream(),
                    capture);
            if (!isWindows) {
                /*
                 * on Windows the process will never return, so we cannot wait
                 */
                try {
                    p.waitFor();
                } catch (InterruptedException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
            return p;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
            if (p != null) {
                Logger.getLogger(RserveConnectionFactory.class.getName()).info("Trying to destroy process!");
                p.destroy();
            }
        }
        return p;
    }

    private synchronized RConnection connectLocal() {
        Logger.getLogger(RserveConnectionFactory.class.getName()).info(
                "Trying to connect to local Rserve ...");
        RConnection connection = null;
        RserveConnection rserveConnection;
        try {
            Logger.getLogger(RserveConnectionFactory.class.getName()).info("Checking for an already running Rserve");
            rserveConnection = new RserveConnection(InetAddress.getLoopbackAddress(), 6311, false);
            connection = rserveConnection.getConnection();
            isLocalServer = true;
            Logger.getLogger(RserveConnectionFactory.class.getName()).info("Found an already running Rserve instance!");
            setConnection(connection);
            return connection;
        } catch (RserveException ex) {
            Logger.getLogger(RserveConnectionFactory.class.getName()).severe("Received an Rserve Exception: " + ex.getLocalizedMessage() + " with description: " + ex.getRequestErrorDescription());
            Logger.getLogger(RserveConnectionFactory.class.getName()).info("Failed to connect to local Rserve ... launching new local instance!");
            connection = startLocalAndConnect();
            if (connection != null) {
                if (connection.isConnected()) {
                    isLocalServer = true;
                    setConnection(connection);
                    Logger.getLogger(RserveConnectionFactory.class.getName()).info("Connected to Rserve!");
                    return connection;
                }
            }
        }
        throw new NullPointerException();
    }

    private RConnection connectRemote(InetAddress address, int port, String username, char[] password) {
        Logger.getLogger(RserveConnectionFactory.class.getName()).log(
                Level.INFO, "Trying to connect to remote Rserve at {0}:{1} ...", new Object[]{address, port});
        RserveConnection rserveConnection;
        for (int i = 0; i < 5; i++) {
            try {
                rserveConnection = new RserveConnection(address, port, username, String.valueOf(password), false);
                setConnection(rserveConnection.getConnection());
                return activeConnection;
            } catch (RserveException ex) {
                Logger.getLogger(RserveConnectionFactory.class.getName()).log(Level.SEVERE, "Failed to connect on try " + "Failed to connect on try " + (1 + i) + "/5" + "! Caught exception while trying to connect remotely: ", ex);
            }
            try {
                //wait for some time before we retry
                Thread.sleep(1000);
            } catch (InterruptedException ix) {
            };
        }
        return null;
    }

    /**
     * Returns the r binary location
     *
     * @return the location of the R binary
     */
    public File getRBinaryLocation() {
        if (rBinaryLocation != null) {
            return rBinaryLocation;
        }
        String osname = System.getProperty("os.name");
        if (osname != null && osname.length() >= 7 && osname.substring(0, 7).
                equals("Windows")) {
            return getRBinaryLocationWindows();
        }
        return getRBinaryLocationUnix();
    }

    public void setRBinaryLocation(File location) {
        if (location.exists()) {
            rBinaryLocation = location;
        } else {
            throw new IllegalArgumentException("File " + location + " does not exist!");
        }
    }

    private File getRBinaryLocationUnix() {
        if (rBinaryLocation != null) {
            return rBinaryLocation;
        }
        for (String location : defaultUnixRlocations.split(",")) {
            if (new File(location).exists()) {
                return new File(location);
            }
        }
        return null;
    }

    private File getRBinaryLocationWindows() {
        String osname = System.getProperty("os.name");
        if (osname != null && osname.length() >= 7 && osname.substring(0, 7).
                equals("Windows")) {
            Logger.getLogger(RserveConnectionFactory.class.getName()).info(
                    "Windows: querying registry to find where R is installed ...");
            String installPath = null;
            try {
                Process rp = Runtime.getRuntime().exec(
                        "reg query HKLM\\Software\\R-core\\R");
                StreamHog regHog = new StreamHog("reg query", rp.getInputStream(), true);
                rp.waitFor();
                regHog.join();
                installPath = regHog.getInstallPath();
                Logger.getLogger(RserveConnectionFactory.class.getName()).log(Level.INFO, "Using R installation location: {0}", installPath);
            } catch (Exception rge) {
                Logger.getLogger(RserveConnectionFactory.class.getName()).log(Level.SEVERE, "ERROR: unable to run REG to find the location of R: {0}", rge);
                return null;
            }
            if (installPath == null) {
                Logger.getLogger(RserveConnectionFactory.class.getName()).log(Level.SEVERE,
                        "ERROR: canot find path to R. Make sure reg is available and R was installed with registry settings.");
                return null;
            }
            if (new File(installPath, "\\bin\\x64\\R.exe").exists()) {
                return new File(installPath, "\\bin\\x64\\R.exe");
            }
            Logger.getLogger(RserveConnectionFactory.class.getName()).log(Level.WARNING, "Falling back to default 32bit R instance");
            return new File(installPath, "\\bin\\R.exe");
        }
        return null;

    }

    /**
     * If the factory already has an active connection, the active connection is
     * returned. Otherwise, this method tries to connectLocal to an already
     * running local instance of Rserve. If that fails, it will try to launch a
     * new Rserve instance and connect to it. Otherwise it will return null.
     *
     * @return the local connection
     * @throws RserveException
     */
    private RConnection getLocalConnection() {
        if (activeConnection == null) {
            activeConnection = connectLocal();
        } else {
            if (!activeConnection.isConnected()) {
                activeConnection = null;
                activeConnection = connectLocal();
            }
        }
        return activeConnection;
    }

    /**
     * If the factory already has an active connection, the active connection is
     * returned. Otherwise, this method creates a remote connection to an rserve
     * running at address and port. This method does not allow unauthenticated
     * access to Rserve.
     *
     * @param address
     * @param port
     * @param username
     * @param password
     * @return an active RConnection to the remote server or null if anything
     * failed
     */
    private RConnection getRemoteConnection(InetAddress address, int port,
            String username, char[] password) {
        if (activeConnection != null) {
            if (!activeConnection.isConnected()) {
                activeConnection = null;
            } else {
                return activeConnection;
            }
        }
        return connectRemote(address, port, username, password);
    }

    private RConnection getRemoteConnection() {
        if (activeConnection != null) {
            if (!activeConnection.isConnected()) {
                activeConnection = null;
            } else {
                return activeConnection;
            }
        }
        InetAddress address;
        try {
            address = InetAddress.getByName(rserveRemoteHost);
            int port = Integer.parseInt(rserveRemotePort);
            String username = userName;
            char[] password = Keyring.read("Rserve://" + rserveRemoteHost.trim());
            return connectRemote(address, port, username, password);
        } catch (UnknownHostException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    @Override
    public void preferenceChange(PreferenceChangeEvent pce) {
        loadPreferences();
    }
}
