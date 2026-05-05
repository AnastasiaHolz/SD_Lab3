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

    public static int inputInt(Scanner sc, String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Error! Enter an intager.");
            }
        }
    }

    public static void printPatients(Patient[] patients) {
        System.out.printf("%-5s %-15s %-20s %-15s %-10s %-15s\n",
                "ID", "Surname", "Address", "Phone", "Card", "Diagnosis");
        System.out.println("-------------------------------------------------------------------------------");
        for (Patient p : patients) {
            p.print();
        }
    }

    public static void searchByDiagnosis(Patient[] patients, String diagnosis) {
        boolean found = false;

        System.out.println("\nResults:");
        for (Patient p : patients) {
            if (p.getDiagnosis().equalsIgnoreCase(diagnosis)) {
                p.print();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No patients with this diagnosis were found.");
        }
    }

    public static void searchByCardRange(Patient[] patients, int min, int max) {
        boolean found = false;

        System.out.println("\nResults:");
        for (Patient p : patients) {
            if (p.getMedicalCardNumber() >= min && p.getMedicalCardNumber() <= max) {
                p.print();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No patients found in this range.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = 5;
        Patient[] patients = new Patient[n];

        System.out.println("=== Patient data entry ===");

        for (int i = 0; i < n; i++) {
            System.out.println("\nPatient #" + (i + 1));

            int id = inputInt(sc, "ID: ");
            System.out.print("Surname: ");
            String surname = sc.nextLine();

            System.out.print("Adress: ");
            String address = sc.nextLine();

            System.out.print("Phone: ");
            String phone = sc.nextLine();

            int card = inputInt(sc, "Card: ");

            System.out.print("Diagnosis: ");
            String diagnosis = sc.nextLine();

            patients[i] = new Patient(id, surname, address, phone, card, diagnosis);
        }

        System.out.println("\n=== Patient list ===");
        printPatients(patients);

        System.out.print("\nEnter a diagnosis to search: ");
        String diag = sc.nextLine();
        searchByDiagnosis(patients, diag);

        int min = inputInt(sc, "\nMinimum card number: ");
        int max = inputInt(sc, "Maximum card number: ");
        searchByCardRange(patients, min, max);

        sc.close();
    }
}
