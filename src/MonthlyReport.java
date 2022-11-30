import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MonthlyReport {
    ArrayList<String> item_name = new ArrayList<>();
    ArrayList<Boolean> is_expense = new ArrayList<>();
    ArrayList<Integer> quantity = new ArrayList<>();
    ArrayList<Integer> sum_of_one = new ArrayList<>();

    void findMonthlyReport(int year,int CycleI){//Метод ищет файл, при нахождении сразу считывает и фильтрует по заданому примеру.
        try {
            List<String> files = Files.readAllLines(Path.of("m."+String.valueOf(year)+"0"+String.valueOf(CycleI+1)+".csv"));// Собирает в массив List каждую линию с данными по типу blabla,blala,bla,
            for (int i=1;i<files.size();i++){// Такой цикл нужен чтобы пропустить первую строку
                String[] filteredFiles = files.get(i).split(","); // Делим строку по запятым и сохраняем в массив который сразу же фильтруем далее
                item_name.add(filteredFiles[0]);        //Тут я не стал париться с циклом, т.к. есть строгий пример, по которому они сохраняли данные
                is_expense.add(Boolean.parseBoolean(filteredFiles[1]));
                quantity.add(Integer.parseInt(filteredFiles[2]));
                sum_of_one.add(Integer.parseInt(filteredFiles[3]));
            }
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            Collections.emptyList();
        }

    }

    void showItem(int i){
        System.out.println(item_name.get(i)+" "+is_expense.get(i)+" "+quantity.get(i)+" "+sum_of_one.get(i));

    }
}
