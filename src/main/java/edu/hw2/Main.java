package edu.hw2;

import edu.hw2.Task1.Expr;
import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import edu.hw2.Task3.ConnectionManager.ConnectionManager;
import edu.hw2.Task3.ConnectionManager.DefaultConnectionManager;
import edu.hw2.Task3.ConnectionManager.FaultyConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import edu.hw2.Task4.Call;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
//        ConnectionManager defaultManager = new DefaultConnectionManager();
//        ConnectionManager faultyManager = new FaultyConnectionManager();
//        PopularCommandExecutor executor = new PopularCommandExecutor(faultyManager,5);
//        executor.updatePackages();
        var info = Call.callingInfo();
        System.out.println("Class name: " + info.className());
        System.out.println("Method name: " + info.methodName());
    }
}
