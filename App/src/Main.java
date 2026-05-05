import java.util.*;

class Patient {
    private int id;
    private String surname;
    private String address;
    private String phone;
    private int medicalCardNumber;
    private String diagnosis;

    public Patient(int id, String surname, String address, String phone, int medicalCardNumber, String diagnosis) {
        this.id = id;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.medicalCardNumber = medicalCardNumber;
        this.diagnosis = diagnosis;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public int getMedicalCardNumber() {
        return medicalCardNumber;
    }

    public void print() {
        System.out.printf("%-5d %-15s %-20s %-15s %-10d %-15s\n",
                id, surname, address, phone, medicalCardNumber, diagnosis);
    }
}

public class Main {

    // 🔹 Метод безпечного введення числа
    public static int inputInt(Scanner sc, String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Помилка! Введіть ціле число.");
            }
        }
    }

    // 🔹 Вивід таблиці
    public static void printPatients(Patient[] patients) {
        System.out.printf("%-5s %-15s %-20s %-15s %-10s %-15s\n",
                "ID", "Прізвище", "Адреса", "Телефон", "Карта", "Діагноз");
        System.out.println("-------------------------------------------------------------------------------");
        for (Patient p : patients) {
            p.print();
        }
    }

    // 🔹 Пошук за діагнозом
    public static void searchByDiagnosis(Patient[] patients, String diagnosis) {
        boolean found = false;

        System.out.println("\nРезультати пошуку:");
        for (Patient p : patients) {
            if (p.getDiagnosis().equalsIgnoreCase(diagnosis)) {
                p.print();
                found = true;
            }
        }

        if (!found) {
            System.out.println("❗ Пацієнтів з таким діагнозом не знайдено.");
        }
    }

    // 🔹 Пошук за діапазоном картки
    public static void searchByCardRange(Patient[] patients, int min, int max) {
        boolean found = false;

        System.out.println("\nРезультати пошуку:");
        for (Patient p : patients) {
            if (p.getMedicalCardNumber() >= min && p.getMedicalCardNumber() <= max) {
                p.print();
                found = true;
            }
        }

        if (!found) {
            System.out.println("❗ Пацієнтів у цьому діапазоні не знайдено.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = 5;
        Patient[] patients = new Patient[n];

        System.out.println("=== Введення даних пацієнтів ===");

        for (int i = 0; i < n; i++) {
            System.out.println("\nПацієнт #" + (i + 1));

            int id = inputInt(sc, "ID: ");
            System.out.print("Прізвище: ");
            String surname = sc.nextLine();

            System.out.print("Адреса: ");
            String address = sc.nextLine();

            System.out.print("Телефон: ");
            String phone = sc.nextLine();

            int card = inputInt(sc, "Номер медичної картки: ");

            System.out.print("Діагноз: ");
            String diagnosis = sc.nextLine();

            patients[i] = new Patient(id, surname, address, phone, card, diagnosis);
        }

        // 🔹 Вивід таблиці
        System.out.println("\n=== Список пацієнтів ===");
        printPatients(patients);

        // 🔹 Пошук за діагнозом
        System.out.print("\nВведіть діагноз для пошуку: ");
        String diag = sc.nextLine();
        searchByDiagnosis(patients, diag);

        // 🔹 Пошук за діапазоном
        int min = inputInt(sc, "\nМінімальний номер картки: ");
        int max = inputInt(sc, "Максимальний номер картки: ");
        searchByCardRange(patients, min, max);

        sc.close();
    }
}