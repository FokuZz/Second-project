import java.util.*;

public class Accounting {
    static ArrayList<MonthlyReport> mouths = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static MonthlyReport mouth;
    static YearlyReport year = new YearlyReport();

    public static void main(String[] args) {
        System.out.println("Введите необходимый год для просмотра отчётов");
        int yearNum = scanner.nextInt();
        while (true) {
            try {
                printMenu("Меню1");
                int command = scanner.nextInt();
                switch (command) {
                    default:
                        System.out.println("Такого пункта нет\n");
                        break;
                    case (2021):
                        scanner.close();
                        return;
                    case (1):
                        if (!mouths.isEmpty()){// Для отсутствия проблем
                            System.out.println("Вы уже загрузили отчёт, для повторной загрузки необходимо перезапустить программу!\n");
                            break;
                        }
                        for (int i = 0; i < 3; i++) {
                            mouths.add(mouth = new MonthlyReport());
                            mouths.get(i).findMonthlyReport(yearNum, i);
                        }
                        if (mouths.isEmpty()) {// Условие для исключения повторного выведения сообщения и сохранение пустого массива
                            mouths.remove(mouths.get(0));
                            break;
                        } else {
                            System.out.println("Месячные отчёты считаны!\n");
                        }
                        break;
                    case (2):
                        if (!year.isExpense.isEmpty()){ // Для отсутствия проблем
                            System.out.println("Вы уже загрузили отчёт, для повторной загрузки необходимо перезапустить программу!\n");
                            break;
                        }
                        year.findYearlyReport(yearNum);
                        if (year.month.isEmpty()) {
                            break;
                        } else {
                            System.out.println("Годовые отчёты считаны!\n");
                        }
                        break;
                    case (3):
                        dataVerification();
                        break;
                    case (4):
                        reportMouthInfo();
                        break;
                    case (5):
                        reportYearInfo(yearNum);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nВводить можно только цифры!\n");
                scanner.nextLine();
            }
        }

    }

    static void printMenu(String type) {
        switch (type) {
            case ("Меню1"):
                System.out.println("Выберите что хотите сделать:");
                System.out.println("1 - Считать все месячные отчёты");
                System.out.println("2 - Считать годовой отчёт");
                System.out.println("3 - Сверить отчёты");
                System.out.println("4 - Вывести информацию о всех месячных отчётах");
                System.out.println("5 - Вывести информацию о годовом отчёте");
                System.out.println("Для выхода напишите нынешний год");
                return;
            case("h"):
                System.out.println("///////////////////////////////////////////////////////////////////////////////////////");
                break;
            default:
                System.out.println("ВВЁЛ НЕПРАВИЛЬНОЕ МЕНЮ!!! =" + type);
        }
    }

    static void dataVerification() {
        if (year.month.isEmpty() || mouths.isEmpty()) { //Решил создать условие для проверки существования массивов необходимых файлов
            if (year.month.isEmpty()) System.out.println("Для сверки отсутствует годовой отчёт!");
            if (mouths.isEmpty()) System.out.println("Для сверки отсутствует месячный отчёт!");
            System.out.println();
            return;
        }
        int errors =0; // Значение для подсчёта ошибок
        for (int i = 0; i < year.isExpense.size(); i++){ //Масив который отталкивается от размера списка месячных трат
            if (year.isExpense.get(i)) {        // Проверка является ли тратой число i. (у меня в каждом объекте отфильтрованы arrayList"ы и чтобы обратиться, нужно лишь 1 число т.е. I)
                int sumTrueExpenses = 0;
                for (int o = 0; o < mouths.get(year.month.get(i) - 1).isExpense.size(); o++) { // Второй массив в котором мы будем плюсовать к сумме каждую трату, чтобы сверить.
                    if (mouths.get(year.month.get(i) - 1).isExpense.get(o)) {                  // Не стал делать все месяцы сразу, чтобы была возможность увидеть в каком именно месяце трата отличается
                        sumTrueExpenses += mouths.get(year.month.get(i) - 1).quantity.get(o) * mouths.get(year.month.get(i) - 1).sumOfOne.get(o);
                    }                       // ^-- всё это такое сложное из-за того что я не знал как в цикле создать 3 разных объекта, и запихал их в эррейлист, так тоже хорошо работает, главное привыкнуть
                }
                if (!(sumTrueExpenses == year.amount.get(i))) {               // Условие, которое делает сверку, со следующим циклом все значения так сказать обнуляются
                    System.out.println("Траты за " + year.month.get(i) + " месяц не совпадают с годовыми");
                    errors++;
                }
            } else {                            // Всё тоже самое но тут уже проверка заработка
                    int sumFalseExpenses = 0;
                    for (int o = 0; o < mouths.get(year.month.get(i) - 1).isExpense.size(); o++) {
                        if (!mouths.get(year.month.get(i) - 1).isExpense.get(o)) {
                            sumFalseExpenses += mouths.get(year.month.get(i) - 1).quantity.get(o) * mouths.get(year.month.get(i) - 1).sumOfOne.get(o);
                        }
                    }
                    if (!(sumFalseExpenses == year.amount.get(i))) {
                        System.out.println("Заработок за " + year.month.get(i) + " месяц не совпадают с годовыми");
                        errors++;
                    }
            }
        }
        if (errors!=0){
            System.out.println("\nСверка окончена, было допущено "+errors+" ошибок");
        } else {
            System.out.println("Сверка отчёта прошла успешно и без ошибок");
        }
    }

    static void reportMouthInfo(){
        if (mouths.isEmpty()) { //Решил создать условие для проверки существования массивов необходимых файлов
            System.out.println("Для сверки отсутствует месячный отчёт!\n");
            return;
        }
        String mostProfitableName ="";
        String mostExpensiveName ="";
        int mostProfitable = 0;
        int mostExpensive = 0;
        for (int i = 0;i<mouths.size();i++){//Создаем массив для вывода/проверки необходимого месяца
            mostProfitable = 0;
            mostExpensive = 0;
            printMenu("h"); // Линия чтобы было приятно смотреть в терминал
            switch (i+1){// Красивый вывод месяца, возможно пригодится в будущем
                case (1):
                    System.out.println("Январь");
                    break;
                case (2):
                    System.out.println("Февраль");
                    break;
                case (3):
                    System.out.println("Март");
                    break;
            }
            for (int y = 0;y < mouths.get(i).itemName.size();y++){
                if (mouths.get(i).isExpense.get(y) && mostExpensive<(mouths.get(i).quantity.get(y)*mouths.get(i).sumOfOne.get(y))){ // Проверка является ли это тратой, и проверка является ли сумма больше чем сейчас
                    mostExpensive = mouths.get(i).quantity.get(y)*mouths.get(i).sumOfOne.get(y);
                    mostExpensiveName = mouths.get(i).itemName.get(y);
                }
                if (!mouths.get(i).isExpense.get(y) && mostProfitable<(mouths.get(i).quantity.get(y)*mouths.get(i).sumOfOne.get(y))){ // такая же проверка только заработка
                    mostProfitable = mouths.get(i).quantity.get(y)*mouths.get(i).sumOfOne.get(y);
                    mostProfitableName = mouths.get(i).itemName.get(y);
                }
            }
            System.out.println("Самым прибыльным товаром был - "+mostProfitableName+" - "+mostProfitable+"р.");
            System.out.println("Самым затратным для покупки товаром был - "+mostExpensiveName+" - "+mostExpensive+"р.");
        }
        printMenu("h");
    }


    static void reportYearInfo(int yearNum){
        if (year.month.isEmpty()) { //Решил создать условие для проверки существования массивов необходимых файлов
            System.out.println("Для сверки отсутствует годовой отчёт!\n");
            return;
        }
        int sumExpenses =0;
        int sumProfit =0;
        printMenu("h");
        System.out.println("Информация за "+yearNum+" год.");
        for(Integer mouth: year.month){ // необходимо для получения номера месяца
            switch (mouth){
                case(1):
                    String m= "Январь";
                    calculateProfit(mouth,m);
                    break;
                case(2):
                    m= "Февраль";
                    calculateProfit(mouth,m);
                    break;
                case(3):
                    m= "Март";
                    calculateProfit(mouth,m);
                     break;
            }
        }
        for (int i=0;i<year.isExpense.size();i++){
            if (year.isExpense.get(i)){
                sumExpenses+=year.amount.get(i);
            } else {
                sumProfit+=year.amount.get(i);
            }
        }
        System.out.println();
        System.out.println("Средний расход за все месяцы в году ="+(sumExpenses/(year.isExpense.size()/2))+"р."); // Выводим данные среднего значения.
        System.out.println("Средний доход за все месяцы в году ="+(sumProfit/(year.isExpense.size()/2))+"р.");  // Мы заранее знаем что у каждого месяца есть 2 строчки, поэтому мы их делим
        printMenu("h");
        System.out.println();
    }
    static void calculateProfit(Integer mouth,String m){
        int profitable = 0;
        int profitableExpense = 0;
        for(int i=0;i<year.month.size();i++){ // Ищем подходящий year.mouth для того чтобы потом, найти внутри разные is_expense. Которые можно сверить
            boolean need = year.month.get(i).equals(mouth);
            if (year.isExpense.get(i) && need){
                profitableExpense=year.amount.get(i);}
            if (!year.isExpense.get(i) && need){
                profitable=year.amount.get(i);
            }
        }
        System.out.println("Прибыль за "+m+" = "+(profitable-profitableExpense)+"р.");
    }
}

