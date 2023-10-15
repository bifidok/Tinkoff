package edu.hw2.Task4;

public class Call {
    private Call() {
    }

    public static CallingInfo callingInfo() {
        var stackTrace = new Throwable().getStackTrace();
        int stackLength = stackTrace.length;
        String callerMethodName = stackTrace[1].getMethodName();
        String callerClassName = stackTrace[1].getClassName();
        return new CallingInfo(callerClassName, callerMethodName);
    }
}
