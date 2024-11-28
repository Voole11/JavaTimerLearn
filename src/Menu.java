import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu {
    private int shutdownHours;
    private int shutdownMinutes;
    private int shutdownSeconds;
    private int totalShutDownTimeMills;
    private boolean running;

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
        this.shutdownHours = mins;
    }

    public void setShutdownSeconds(int secs){
        this.shutdownHours = secs;
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

    public void setTotalShutDownTimeMills(int totalShutDownTimeMills) {
        this.totalShutDownTimeMills = totalShutDownTimeMills;
    }

    public void showStartMenu() {
//        String currentTime = String.format("Таймер сработает через: %d часов, %d минут %d секунд\n",
//                getShutdownHours(), getShutdownMinutes(), getShutdownSeconds());

        System.out.println(getTotalShutDownTimeMills());

        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("[1] - Часы");
            System.out.println("[2] - Минуты");
            System.out.println("[3] - Секунды");
            System.out.println("[0] - Выход");
            System.out.println("Нажмите клавишу для взаимодействия: ");

            choice = scanner.nextInt();

            if (choice == 1) {
                showFirstMenu();
            } else if (choice == 2) {
                showSecondMenu();
            } else if (choice == 3) {
                showThirdMenu();
            } else if (choice == 0) {
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
            int shutdownHoursM = 0;

            choise = scanner.nextInt();

            if (choise == 0){
                showStartMenu();
                break;
            } else if (choise > 0 && choise < 99){
                setShutdownHours(0);
                setTotalShutDownTimeMills(0);
                shutdownHoursM = shutdownHoursM + choise;
                setShutdownHours(shutdownHoursM);
                setTotalShutDownTimeMills(shutdownHoursM);
                showStartMenu();
                scanner.close();
            } else if (choise > 99) {
                System.out.println("Количество часов не должно быть меньше 0 и больше 99");
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
            int shutdownMinutesM = 0;

            choise = scanner.nextInt();

            if (choise == 0){
                showStartMenu();
                break;
            } else if (choise > 0 && choise < 99){
                setShutdownMinutes(0);
                setTotalShutDownTimeMills(0);
                shutdownMinutesM = shutdownMinutesM + choise;
                setShutdownMinutes(shutdownMinutesM);
                showStartMenu();
                scanner.close();
            } else if (choise > 99) {
                System.out.println("Количество минут не должно быть меньше 0 и больше 99");
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
            int shutdownSecondsM = 0;

            choise = scanner.nextInt();

            if (choise == 0){
                showStartMenu();
                break;
            } else if (choise > 0 && choise < 99){
                setShutdownSeconds(0);
                shutdownSecondsM = shutdownSecondsM + choise;
                setShutdownSeconds(shutdownSecondsM);
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
