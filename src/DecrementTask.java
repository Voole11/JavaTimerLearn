import java.util.TimerTask;

public class DecrementTask extends TimerTask {
    private  int seconds;

    public DecrementTask(int seconds){
        this.seconds = seconds;
    }

    public int getSeconds(){
        return seconds;
    }

    public void setSeconds(int seconds){
        this.seconds = seconds;
    }

    @Override
    public void run() {
        if(seconds > 0){
            seconds--;
            setSeconds(seconds);
        } else {
            this.cancel();
        }
    }
}
