import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//
public class TTTServer {
    public static void main(String args[]) throws IOException {
        int registryPort = 3001;
        System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT+"Server initiation: ");
        System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT+"   Main");
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT+" OK");

        try {
            TTTService tttGame =  new TTT();
            tttGame.initializingApp();
            Registry reg = LocateRegistry.createRegistry(registryPort);
            System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT+"   Local Registry:");
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT+" OK");
            reg.rebind("TTTService", tttGame);
            System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT+"   Rebind:");
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT+" OK");
            System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT+"   TTT server:");
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT+" ready");
        } catch(Exception e) {
            System.out.println("TTT server main " + e.getMessage());
        }
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT+"\n-------------------------------------------------");
        System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT+"Activity Details: \n");
        System.out.print(ConsoleColors.BLUE_BOLD_BRIGHT+"   [REFRESH] ");
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"All user data set up to date.");
    }
}