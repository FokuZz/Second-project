import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YearlyReport {
    ArrayList<Integer> month = new ArrayList<>();
    ArrayList<Integer> amount = new ArrayList<>();
    ArrayList<Boolean> isExpense = new ArrayList<>();
    void findYearlyReport(int year){//Метод ищет фаил, при нахождении сразу считывает, фильтрует по заданому примеру.
        try {
            List<String> files = Files.readAllLines(Path.of("y."+String.valueOf(year)+".csv"));// Собирает в массив List каждую линию с данными по типу blabla,blala,bla,
            for (int i=1;i<files.size();i++){// Такой цикл нужен, чтобы пропустить первую строку
                String[] filteredFiles = files.get(i).split(","); // Делим строку по запятым и сохраняем в массив который сразу же фильтруем далее
                month.add(Integer.parseInt(filteredFiles[0]));
                amount.add(Integer.parseInt(filteredFiles[1]));
                isExpense.add(Boolean.parseBoolean(filteredFiles[2]));
            }
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно файл не находится в нужной директории.");
            return;
        }


    }
    void showItem(int i){
        System.out.println(month.get(i)+" "+amount.get(i)+" "+isExpense.get(i));
    }




    /*Годовой отчёт, содержащий ровно по 2 записи на каждый из 12 месяцев — общий доход
     и расход за этот месяц. Представлен классом YearlyReport.
     */
}
