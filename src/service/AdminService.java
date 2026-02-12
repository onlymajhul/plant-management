package service;

import dao.PflanzeDAO;
import model.Pflanze;

import java.util.List;
import java.util.Scanner;

public class AdminService {

    private PflanzeDAO pflanzeDAO = new PflanzeDAO();
    private Scanner scanner = new Scanner(System.in);

    public void start() throws Exception {

        while (true) {
            System.out.println("""
                    
                    --- ADMIN MENU ---
                    1. Pflanze hinzufügen
                    2. Alle Pflanzen anzeigen
                    3. Pflanze löschen
                    0. Beenden
                    """);

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> add();
                case 2 -> showAll();
                case 3 -> delete();
                case 0 -> { return; }
            }
        }
    }

    private void add() throws Exception {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Ertrag: ");
        int ertrag = Integer.parseInt(scanner.nextLine());

        System.out.print("Dauer (Wochen): ");
        int dauer = Integer.parseInt(scanner.nextLine());

        System.out.print("Größe: ");
        double groesse = Double.parseDouble(scanner.nextLine());

        Pflanze p = new Pflanze(name, ertrag, dauer, groesse);
        pflanzeDAO.add(p);

        System.out.println("✅ Pflanze gespeichert!");
    }

    private void showAll() throws Exception {
        List<Pflanze> list = pflanzeDAO.getAll();

        for (Pflanze p : list) {
            System.out.println(p.getId() + " - " + p.getName());
        }
    }

    private void delete() throws Exception {
        System.out.print("ID der Pflanze: ");
        int id = Integer.parseInt(scanner.nextLine());
        pflanzeDAO.delete(id);
        System.out.println("❌ Pflanze gelöscht!");
    }
}