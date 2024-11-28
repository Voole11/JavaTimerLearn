import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu {
    private int shutdownHours;
    private int shutdownMinutes;
    private int shutdownSeconds;
    private int totalShutDownTimeMills;
    private boolean running;


    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.showStartMenu();
    }


    public Menu(){
        this.shutdownHours = 0;
        this.shutdownMinutes = 0;
        this.shutdownSeconds = 0;
        this.running = true;
    }

    public void setShutdownHours(int hours){
        this.shutdownHours = hours;
    }

    public void setShutdownMinutes(int mins){
        this.shutdownMinutes = mins;
    }

    public void setShutdownSeconds(int secs){
        this.shutdownSeconds = secs;
    }

    public int getShutdownHours(){
        return shutdownHours;
    }

    public int getShutdownMinutes() {
        return shutdownMinutes;
    }

    public int getShutdownSeconds() {
        return shutdownSeconds;
    }

    public int getTotalShutDownTimeMills() {
        return totalShutDownTimeMills;
    }

    public void setTotalShutDownTimeMills(int shutdownHours, int shutdownMinutes, int shutdownSeconds) {
        this.totalShutDownTimeMills = 1000 * (shutdownHours * 3600 + shutdownMinutes * 60 + shutdownSeconds);
    }

    public void showStartMenu() {

         setTotalShutDownTimeMills(getShutdownHours(), getShutdownMinutes(), getShutdownSeconds());

        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("////////////////////////////////////////");
            System.out.println(getTotalShutDownTimeMills() + " миллисекунд");
            System.out.printf("Установленное время: %d часов %d минут %d секунд\n", getShutdownHours(), getShutdownMinutes(), getShutdownSeconds());
            System.out.println("////////////////////////////////////////");
            System.out.println("[1] - Часы");
            System.out.println("[2] - Минуты");
            System.out.println("[3] - Секунды");
            System.out.println("[4] - Старт");
            System.out.println("[5] - Отмена");
            System.out.println("[6] - Обновить");
            System.out.println("[7] - Обнулить таймер");
            System.out.println("[0] - Выход");
            System.out.println("Нажмите клавишу для взаимодействия: ");
            System.out.println("////////////////////////////////////////");

            choice = scanner.nextInt();

            if (choice == 1) {
                showFirstMenu();
            } else if (choice == 2) {
                showSecondMenu();
            } else if (choice == 3) {
                showThirdMenu();
            }else if (choice == 4) {
                startTimer();
            }else if (choice == 5) {
                stopTimer();
            }else if (choice == 6) {
                showCurrentTime();
            }else if (choice == 7) {
                nullCurrentTime();
            }else if (choice == 0) {
                System.out.println("Выход из программы");
            } else {
                System.out.println("Некорректный выбор, попробуйте снова.");
            }
        }
        scanner.close();
    }
    public void showFirstMenu() {
        Scanner scanner = new Scanner(System.in);
        int choise = -1;

        while (choise != 0){
            System.out.println("Введите количество часов, через которое компьютер выключится: ");
            System.out.println("[0] - Назад");
            int shutdownHoursTemp = 0;

            choise = scanner.nextInt();

            if (choise == 0){
                showStartMenu();
                break;
            } else if (choise > 0 && choise < 60){
                setTotalShutDownTimeMills(getTotalShutDownTimeMills() - (getShutdownHours() * 36000000), 0, 0);
                setShutdownHours(0);
                shutdownHoursTemp = shutdownHoursTemp + choise;
                setShutdownHours(shutdownHoursTemp);
                showStartMenu();
                scanner.close();
            } else if (choise > 59) {
                System.out.println("Количество часов не должно быть меньше 0 и больше 59");
            } else{
                System.out.println("Некорректный выбор, попробуйте снова.");
            }
        }
        scanner.close();
    }

    public void showSecondMenu() {
        Scanner scanner = new Scanner(System.in);
        int choise = -1;

        while (choise != 0){
            System.out.println("Введите количество минут, через которое компьютер выключится: ");
            System.out.println("[0] - Назад");
            int shutdownMinutesTemp = 0;

            choise = scanner.nextInt();

            if (choise == 0){
                showStartMenu();
                break;
            } else if (choise > 0 && choise < 60){
                setTotalShutDownTimeMills(0, getTotalShutDownTimeMills() - getShutdownMinutes() * 60000, 0);
                setShutdownMinutes(0);
                shutdownMinutesTemp = shutdownMinutesTemp + choise;
                setShutdownMinutes(shutdownMinutesTemp);
                showStartMenu();
                scanner.close();
            } else if (choise > 59) {
                System.out.println("Количество минут не должно быть меньше 0 и больше 59");
            } else{
                System.out.println("Некорректный выбор, попробуйте снова.");
            }
        }
        scanner.close();
    }

    public void showThirdMenu() {
        Scanner scanner = new Scanner(System.in);
        int choise = -1;

        while (choise != 0){
            System.out.println("Введите количество секунд, через которое компьютер выключится: ");
            System.out.println("[0] - Назад");
            int shutdownSecondssTemp = 0;

            choise = scanner.nextInt();

            if (choise == 0){
                showStartMenu();
                break;
            } else if (choise > 0 && choise < 99){
                setTotalShutDownTimeMills(0,0, getTotalShutDownTimeMills() - getShutdownSeconds() * 1000);
                setShutdownSeconds(0);
                shutdownSecondssTemp = shutdownSecondssTemp + choise;
                setShutdownSeconds(shutdownSecondssTemp);
                showStartMenu();
                scanner.close();
            } else if (choise > 99) {
                System.out.println("Количество секунд не должно быть меньше 0 и больше 99");
            } else{
                System.out.println("Некорректный выбор, попробуйте снова.");
            }
        }
        scanner.close();
    }

    public void startTimer(){
        Timer timer = new Timer();
        timer.cmdShutdownTimerStart(getTotalShutDownTimeMills());
    }

    public void stopTimer(){
        Timer timer = new Timer();
        timer.cmdShutdownTimerCancel();
    }

    public void showCurrentTime(){}

    public void nullCurrentTime(){
        setTotalShutDownTimeMills(0,0,0);
        setShutdownHours(0);
        setShutdownMinutes(0);
        setShutdownSeconds(0);
    }




//    public void timer(){
//        boolean x=true;
//        long displayMinutes=0;
//        long starttime=System.currentTimeMillis();
//        System.out.println("Timer:");
//        while(x)
//        {
//            TimeUnit.SECONDS.sleep(1);
//            long timepassed=System.currentTimeMillis()-starttime;
//            long secondspassed=timepassed/1000;
//            if(secondspassed==60)
//            {
//                secondspassed=0;
//                starttime=System.currentTimeMillis();
//            }
//            if((secondspassed%60)==0)
//                displayMinutes++;
//
//            System.out.println(displayMinutes+":"+secondspassed);
//        }
//    }

}
