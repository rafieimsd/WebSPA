package main.java.net.seleucus.wsp.main;

import java.io.Console;

import main.java.net.seleucus.wsp.client.WSClient;
import main.java.net.seleucus.wsp.server.WSServer;

public class WebSpa {

    public static final String[] ALLOWED_FIRST_PARAM = {"-help", "-client", "-server", "-version", "-checker"};

    private Console myConsole;

    public WebSpa(final Console myConsole) {

        this.myConsole = myConsole;

    }

    public Console getConsole() {
        return myConsole;
    }

    public int processParameters(final String[] args) {
        int mode = -1;

        if (args.length > 0) {
            // java -jar webspa.jar -help
            if (args[0].equalsIgnoreCase(ALLOWED_FIRST_PARAM[0])) {
                mode = 0;
            }
            // java -jar webspa.jar -client
            if (args[0].equalsIgnoreCase(ALLOWED_FIRST_PARAM[1])) {
                mode = 1;
            }
            // java -jar webspa.jar -server
            if (args[0].equalsIgnoreCase(ALLOWED_FIRST_PARAM[2])) {
                mode = 2;
            }
            // java -jar webspa.jar -version
            if (args[0].equalsIgnoreCase(ALLOWED_FIRST_PARAM[3])) {
                mode = 3;
            }
            // java -jar webspa.jar -checker
            if (args[0].equalsIgnoreCase(ALLOWED_FIRST_PARAM[4])) {
                mode = 4;
            }
        }

        return mode;
    }

    public static void main(String[] args) throws Exception {
//test git 2+2
        final Console myConsole = System.console();
        if (myConsole == null) {
            System.err.println("Could not get console; will exit now...");
            System.exit(1);
        }

        WSGestalt myGestalt;
        WebSpa mySpa = new WebSpa(myConsole);
        int mode = mySpa.processParameters(args);

        switch (mode) {
            case 1:
                // System.out.println("client");
                myGestalt = new WSClient(mySpa);
                myGestalt.runConsole();
                break;
            case 2:
                // System.out.println("server");
                myGestalt = new WSServer(mySpa);
                myGestalt.runConsole();
                break;
            case 3:
                // System.out.println("version");
                myGestalt = new WSVersion(mySpa);
                myGestalt.runConsole();
                break;
            case 4:
                // System.out.println("version");
                myConsole.writer().println("00-this is checker mode!");
                myGestalt = new WSVersion(mySpa);
                myGestalt.runConsole();
                break;
            default:
                // System.out.println("default");
                myGestalt = new WSHelper(mySpa);
                myGestalt.runConsole();
                break;
        }

        myGestalt.exitConsole();

    }

}
