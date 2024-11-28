public class Timer {
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
}
