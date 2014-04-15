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

import java.net.InetAddress;
import java.util.logging.Logger;
import org.openide.util.Exceptions;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

/**
 *
 * @author Nils Hoffmann
 */
public class RserveConnection extends Thread {

    public static enum State {

        INITIALIZED, CONNECTED, CLOSED
    };

    public static enum Scope {

        LOCAL, REMOTE
    };
    private static int sessionCounter = 0;
    private final InetAddress hostIp;
    private final int hostPort;
    private int connectionLock;
    private String userName;
    private String password;
    private final Scope scope;
    private RConnection connection;
    private final boolean startedLocalRserve;
    private State state = State.INITIALIZED;

    public RserveConnection(InetAddress hostIp, int hostPort, boolean startedLocalRserve) {
        this(hostIp, hostPort, null, null, startedLocalRserve);
    }

    public RserveConnection(InetAddress hostIp, int hostPort, String userName, String password, boolean startedLocalRserve) {
        super("RserveSession" + (sessionCounter++));
        if (hostIp == null || hostIp.isLoopbackAddress()) {
            scope = Scope.LOCAL;
        } else {
            scope = Scope.REMOTE;
        }
        Logger.getLogger(RserveConnection.class.getName()).info("Session scope: " + scope);
        this.hostIp = hostIp;
        this.hostPort = hostPort;
        this.userName = userName;
        this.password = password;
        this.startedLocalRserve = startedLocalRserve;
        Runtime.getRuntime().addShutdownHook(this);
    }

    protected RConnection connect() throws RserveException, IllegalStateException, IllegalArgumentException {
        if (state == State.INITIALIZED) {
            if (hostIp == null || scope == Scope.LOCAL) {
                Logger.getLogger(RserveConnection.class.getName()).info("Localhost session.");
                connection = new RConnection();
            } else {
                Logger.getLogger(RserveConnection.class.getName()).info("Session on: " + hostIp.toString() + " at port " + hostPort);
                connection = new RConnection(hostIp.getHostAddress(), hostPort);
            }
            if (connection.needLogin()) {
                if (userName == null) {
                    throw new IllegalArgumentException("Connection requires login but userName is null!");
                }
                if (password == null) {
                    throw new IllegalArgumentException("Connection requires login but password is null!");
                }
                connection.login(userName, password);
            }
            //connectionLock = connection.lock();
            return connection;
        } else {
            throw new IllegalStateException("RConnection was already INITIALIZED");
        }
    }

    public RConnection getConnection() throws IllegalStateException, RserveException {
        switch (state) {
            case CLOSED:
                throw new IllegalStateException("RConnection was already closed!");
            case INITIALIZED:
                connection = connect();
                break;
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            switch (state) {
                case CONNECTED:
                    switch (scope) {
                        case LOCAL:
                            if (startedLocalRserve) {
                                try {
                                    Logger.getLogger(RserveConnection.class.getName()).info("Shutting down local server...");
                                    connection.serverShutdown();
                                    Logger.getLogger(RserveConnection.class.getName()).info("done!");
                                } catch (RserveException ex) {
                                    Exceptions.printStackTrace(ex);
                                }
                            }
                            break;
                        case REMOTE:
                            Logger.getLogger(RserveConnection.class.getName()).info("Remote server, not shutting down.");
                            break;
                    }
                    break;
                case CLOSED:
                    throw new IllegalStateException("Connection was already closed!");
            }
            try {
                //connection.unlock(connectionLock);
                if (startedLocalRserve) {
                    connection.shutdown();
                }
                connection.close();
                connection = null;
            } catch (RserveException ex) {
                Exceptions.printStackTrace(ex);
            }

        } else {
            Logger.getLogger(RserveConnection.class.getName()).info("Connection was already closed!");
        }
    }

    @Override
    public void run() {
        super.run();
        closeConnection();
    }
}
