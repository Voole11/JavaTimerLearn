import java.sql.Time;
import java.util.Scanner;
import java.util.Timer;

public class Menu {
    private int shutdownHours;
    private int shutdownMinutes;
    private int shutdownSeconds;
    private int totalShutDownTimeMills;
    private boolean running;
    private MyTimer timer;


    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.timer = new MyTimer(menu.getTotalShutDownTimeMills() / 1000);
        menu.showStartMenu();

        // Регистрация шутингового крючка
        Runtime.getRuntime()
                .addShutdownHook(
                        new Thread(
                                () -> {
                                    //graceful shutdown steps
                                    Runtime.getRuntime().halt(0); //override the exit code to 0
                                }));

    }


    public Menu(){
        this.shutdownHours = 0;
        this.shutdownMinutes = 0;
        this.shutdownSeconds = 0;
        this.running = false;
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

    public void setRunning(boolean running){
        this.running = running;
    }

    public boolean getRunning(){
        return running;
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
            System.out.println("[6] - Показать оставшееся время");
            System.out.println("[7] - Обнулить таймер");
            System.out.println("[0] - Выход");
            System.out.println("Нажмите клавишу для взаимодействия: ");
            System.out.println("////////////////////////////////////////");

            while (true) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    break;
                } else {
                    System.out.println("Введите целое число: ");
                    scanner.next();
                }
            }

            if (choice == 1) {
                showFirstMenu();
            } else if (choice == 2) {
                showSecondMenu();
            } else if (choice == 3) {
                showThirdMenu();
            }else if (choice == 4) {
                startTimer(timer);
            }else if (choice == 5) {
                stopTimer(timer);
            }else if (choice == 6) {
                timer.showTime();
            }else if (choice == 7) {
                nullCurrentTime();
            }else if (choice == 0) {
                System.out.println("Выход из программы");
                stopTimer(timer, true);
                //Добавить выключение таймера
                Runtime.getRuntime().halt(0);
            } else {
                System.out.println("Некорректный выбор, попробуйте снова.");
            }
        }
        scanner.close();
    }
    public void showFirstMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0){
            System.out.println("Введите количество часов, через которое компьютер выключится: ");
            System.out.println("[0] - Назад");
            int shutdownHoursTemp = 0;

            while (true) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    break;
                } else {
                    System.out.println("Введите целое число: ");
                    scanner.next();
                }
            }

            if (choice == 0){
                showStartMenu();
                break;
            } else if (choice > 0 && choice < 60){
                setTotalShutDownTimeMills(getTotalShutDownTimeMills() - (getShutdownHours() * 36000000), 0, 0);
                setShutdownHours(0);
                shutdownHoursTemp = shutdownHoursTemp + choice;
                setShutdownHours(shutdownHoursTemp);
                showStartMenu();
                scanner.close();
            } else if (choice > 59) {
                System.out.println("Количество часов не должно быть меньше 0 и больше 59");
            } else{
                System.out.println("Некорректный выбор, попробуйте снова.");
            }
        }
        scanner.close();
    }

    public void showSecondMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0){
            System.out.println("Введите количество минут, через которое компьютер выключится: ");
            System.out.println("[0] - Назад");
            int shutdownMinutesTemp = 0;

            while (true) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    break;
                } else {
                    System.out.println("Введите целое число: ");
                    scanner.next();
                }
            }

            if (choice == 0){
                showStartMenu();
                break;
            } else if (choice > 0 && choice < 60){
                setTotalShutDownTimeMills(0, getTotalShutDownTimeMills() - getShutdownMinutes() * 60000, 0);
                setShutdownMinutes(0);
                shutdownMinutesTemp = shutdownMinutesTemp + choice;
                setShutdownMinutes(shutdownMinutesTemp);
                showStartMenu();
                scanner.close();
            } else if (choice > 59) {
                System.out.println("Количество минут не должно быть меньше 0 и больше 59");
            } else{
                System.out.println("Некорректный выбор, попробуйте снова.");
            }
        }
        scanner.close();
    }

    public void showThirdMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0){
            System.out.println("Введите количество секунд, через которое компьютер выключится: ");
            System.out.println("[0] - Назад");
            int shutdownSecondssTemp = 0;

            while (true) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    break;
                } else {
                    System.out.println("Введите целое число: ");
                    scanner.next();
                }
            }

            if (choice == 0){
                showStartMenu();
                break;
            } else if (choice > 0 && choice < 99){
                setTotalShutDownTimeMills(0,0, getTotalShutDownTimeMills() - getShutdownSeconds() * 1000);
                setShutdownSeconds(0);
                shutdownSecondssTemp = shutdownSecondssTemp + choice;
                setShutdownSeconds(shutdownSecondssTemp);
                showStartMenu();
                scanner.close();
            } else if (choice > 99) {
                System.out.println("Количество секунд не должно быть меньше 0 и больше 59");
            } else{
                System.out.println("Некорректный выбор, попробуйте снова.");
            }
        }
        scanner.close();
    }

    public void startTimer(MyTimer timer){
        // Ссылки разные
        setRunning(true);
        timer.cmdShutdownTimerStart(getTotalShutDownTimeMills() / 1000);
        timer.setSeconds(getTotalShutDownTimeMills());
        timer.scheduleTime();
    }

    public void stopTimer(MyTimer timer){
        // Это ссылки на разные таймеры, вот оно и не работает
        if (running == true) {
            setRunning(false);
            timer.cmdShutdownTimerCancel();
            timer.canclerSchedule();
        } else {
            System.out.println("Таймер не запущен");
        }
    }

    public void stopTimer(MyTimer timer, boolean fullExitOrNot){
        // Это ссылки на разные таймеры, вот оно и не работает
        if (running == true && fullExitOrNot == true) {
            setRunning(false);
            timer.cmdShutdownTimerCancel();
            timer.canclerSchedule();
        }
    }

    public void nullCurrentTime(){
        setTotalShutDownTimeMills(0,0,0);
        setShutdownHours(0);
        setShutdownMinutes(0);
        setShutdownSeconds(0);
    }
}
