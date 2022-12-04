import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MonthlyReport {
    ArrayList<String> itemName = new ArrayList<>();
    ArrayList<Boolean> isExpense = new ArrayList<>();
    ArrayList<Integer> quantity = new ArrayList<>();
    ArrayList<Integer> sumOfOne = new ArrayList<>();
    // Сорян, просто ctrl+c, ctrl+v из файлов чтобы было более понятно, не знал что так необходимо правильно инициализировать значения
    void findMonthlyReport(int year,int forCycleI){//Метод ищет файл, при нахождении сразу считывает и фильтрует по заданому примеру.
        try {
            List<String> files = Files.readAllLines(Path.of("m."+String.valueOf(year)+"0"+String.valueOf(forCycleI+1)+".csv"));// Собирает в массив List каждую линию с данными по типу blabla,blala,bla,
            for (int i=1;i<files.size();i++){// Такой цикл нужен чтобы пропустить первую строку
                String[] filteredFiles = files.get(i).split(","); // Делим строку по запятым и сохраняем в массив который сразу же фильтруем далее
                itemName.add(filteredFiles[0]);        //Тут я не стал париться с циклом, т.к. есть строгий пример, по которому они сохраняли данные
                isExpense.add(Boolean.parseBoolean(filteredFiles[1]));
                quantity.add(Integer.parseInt(filteredFiles[2]));
                sumOfOne.add(Integer.parseInt(filteredFiles[3]));
            }
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            Collections.emptyList();
        }

    }

    void showItem(int i){
        System.out.println(itemName.get(i)+" "+isExpense.get(i)+" "+quantity.get(i)+" "+sumOfOne.get(i));

    }
}
