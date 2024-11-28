import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MyTimer extends TimerTask {
    private int seconds;

    public int getSeconds(){
        return seconds;
    }

    public  MyTimer(){

    }

    public MyTimer(int milliseconds){
        this.seconds = milliseconds / 1000;
    }

    public void cmdShutdownTimerStart(int shutdownTime){
        try {
            String command = String.format("shutdown /s /t %d", shutdownTime);

            // Выполнение команды
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void cmdShutdownTimerCancel(){
        try{
            String command = "shutdown -a";
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        if(seconds > 0){
            seconds--;
        } else {
            this.cancel();
        }
    }


    public static int[] convertSeconds(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        return new int[]{hours, minutes, seconds};
    }

    public void showTime(){
        int[] convertedTime = convertSeconds(seconds);
        System.out.printf("До выключения компьютера: %d часов %d минут %d секунд\n", convertedTime[0], convertedTime[1], convertedTime[2]);
    }
}
