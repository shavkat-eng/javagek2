import java.io.*;
import java.util.*;
import java.text.*;

public class UserInfoApp {
    public static void main(String[] args) throws NumberFormatException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите данные (Фамилия Имя Отчество датарождения номертелефона пол):");

            String input = scanner.nextLine();
            String[] userData = input.split(" ");

            try {
                if (userData.length != 6) {
                    throw new IllegalArgumentException("Неверное количество данных.");
                }

                String lastName = userData[0];
                String firstName = userData[1];
                String middleName = userData[2];
                String birthDate = userData[3];
                String phoneNumber = userData[4];
                String gender = userData[5];

                if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                    throw new ParseException("Неверный формат даты рождения.", 0);
                }

                try {
                    Long.parseLong(phoneNumber);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Неверный формат номера телефона.");
                }

                if (!gender.equals("f") && !gender.equals("m")) {
                    throw new IllegalArgumentException("Неверный формат пола.");
                }

                String fileName = lastName + ".txt";
                String userInfo = String.join(" ", lastName, firstName, middleName, birthDate, phoneNumber, gender);

                writeFile(fileName, userInfo);

                System.out.println("Данные успешно записаны в файл " + fileName);

            } catch (IllegalArgumentException | ParseException e) {
                System.err.println("Ошибка: " + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeFile(String fileName, String userInfo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(userInfo);
            writer.newLine();
        }
    }
}
