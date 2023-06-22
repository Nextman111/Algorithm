package Exceptions.Seminar3;

import java.io.*;
import java.nio.file.FileSystemException;
import java.util.Arrays;

/**
 * Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробелом:
 *         Фамилия Имя Отчество датарождения номертелефона пол
 *         Форматы данных:
 *         фамилия, имя, отчество - строки
 *
 *         дата_рождения - строка формата dd.mm.yyyy
 *
 *         номер_телефона - целое беззнаковое число без форматирования
 *
 *         пол - символ латиницей f или m.
 *
 *         Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.
 *
 *         Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.
 *
 *         Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны записаться полученные данные, вида
 *
 *         <Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
 *
 *         Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
 *
 *         Не забудьте закрыть соединение с файлом.
 *
 *         При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.*/
public class main {
    public static void main(String[] args) throws IOException {

        try {
            newData();
            System.out.println("Запись добавлена");
        }catch (FileSystemException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }

    }

    public static void newData() throws Exception{
        System.out.println("Введите по порядку через пробел: Фамилию Имя Отчество Дата рождения (dd.mm.yyyy) Номер телефона (только цыфры) Пол (m - муж. или f - жен.)");

        String text;
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            text = bf.readLine();
        }catch (IOException e){
            throw new Exception("Произошла ошибка при работе с консолью");
        }

        String[] dataArray = text.split(" ");
        if (dataArray.length != 6){
            throw new Exception("Количество введенных параметров не верно.");
        }

        Data data = new Data(dataArray[0], dataArray[1], dataArray[2], dataArray[3], dataArray[4], dataArray[5]);

        if (!Data.isName(data.surname)){
            throw new Exception("Неверный формат дня фамилии");
        }

        if (!Data.isName(data.name)){
            throw new Exception("Неверный формат дня имени");
        }

        if (!Data.isName(data.patronymic)){
            throw new Exception("Неверный формат дня отчества");
        }

        if (!Data.isBirhday(data.birthday)){
            throw new Exception("Неверный формат дня рождения");
        }

        if (!Data.isNumber(data.birthday)){
            throw new NumberFormatException("Неверный формат телефона");
        }

        if (!Data.isGender(data.gender)){
            throw new Exception("Неверный пол");
        }

        String fileName = "dataArray\\" + data.surname.toLowerCase() + ".txt";
        File file = new File(fileName);

        try (FileWriter fileWriter = new FileWriter(file, true)){
            if (file.length() > 0){
                fileWriter.write('\n');
            }
            fileWriter.write(String.format("%s %s %s %s %s %s", data.surname, data.name, data.patronymic, data.birthday, data.phone, data.gender));
        }catch (IOException e){
            throw new FileSystemException("Ошибка записи в файл");
        }

    }

    public static class Data{
        String surname;
        String name;
        String patronymic;
        String birthday;
        String phone;
        String gender;

        public Data(String surname, String name, String patronymic, String birthday, String phone, String gender) {
            this.surname = surname;
            this.name = name;
            this.patronymic = patronymic;
            this.birthday = birthday;
            this.phone = phone;
            this.gender = gender;
        }

        static public boolean isBirhday(String birthday){
            return birthday.matches("\\d{2}-\\d{2}-\\d{4}");
        }

        static public boolean isName(String name){
            return name.matches("\\[a-zA-Zа-яА-Я]+");
        }

        static public boolean isNumber(String number){
            return number.matches("\\d{11}");
        }

        static public boolean isGender(String gender){
            return gender.equals("m") || gender.equals("f");
        }

    }

}