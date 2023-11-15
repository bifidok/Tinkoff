package edu.hw7;

    import org.apache.logging.log4j.LogManager;
    import org.apache.logging.log4j.Logger;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }


    public static void main(String[] args) throws InterruptedException {
        //var result = Task1.increaseCounter(10,10000);
        //Task4SingleThread pi = new Task4SingleThread();
//        long time1 = System.nanoTime();
//        System.out.println(pi.calculate(points));
//        long res1 = System.nanoTime() - time1;
        int threadsMax = 10;
        long [] srednie = new long [threadsMax];
        long [] sredniesred = new long [threadsMax];
        for (int i = 1; i <= threadsMax; i++){
            for (int it = 0; it < threadsMax; it++) {
                Task4MultiThread piMulti = new Task4MultiThread(i);
                int points = 10000;
                long time2 = System.nanoTime();
                System.out.println(piMulti.calculate(points));
                long res2 = System.nanoTime() - time2;
                sredniesred[it] = res2;
            }
            long srednee = 0;
            for(int j = 0; j < sredniesred.length; j++)srednee += sredniesred[j];
            srednie[i - 1] = srednee / sredniesred.length;
        }

        for(int i = 0; i < srednie.length; i++){
            System.out.println((i + 1) + " - " + srednie[i] + " | милисекунды - " + (srednie[i] / 1000000));
        }
        //System.out.println("Multi faster - " + (res2 < res1));
    }
}
