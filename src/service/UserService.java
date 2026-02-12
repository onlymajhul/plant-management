package service;

import dao.GartenDAO;
import dao.PflanzeDAO;
import model.Pflanze;
import model.User;

import java.util.List;
import java.util.Scanner;

public class UserService {

    private PflanzeDAO pflanzeDAO = new PflanzeDAO();
    private GartenDAO gartenDAO = new GartenDAO();
    private Scanner scanner = new Scanner(System.in);

    public void start(User user) throws Exception {

        while (true) {
            System.out.println("""
                    
                    --- USER MENU ---
                    1. Alle Pflanzen anzeigen
                    2. Pflanze in meinen Garten hinzufügen
                    3. Mein Garten anzeigen
                    0. Beenden
                    """);

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> showAll();
                case 2 -> addToGarten(user);
                case 3 -> showMeinGarten(user);
                case 0 -> { return; }
            }
        }
    }

    private void showAll() throws Exception {
        List<Pflanze> list = pflanzeDAO.getAll();
        for (Pflanze p : list) {
            System.out.println(p.getId() + " - " + p.getName());
        }
    }

    private void addToGarten(User user) throws Exception {
        System.out.print("Pflanzen ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        gartenDAO.addToGarten(user.getId(), id);
        System.out.println("🌱 Pflanze hinzugefügt!");
    }

    private void showMeinGarten(User user) throws Exception {
        List<Pflanze> list = gartenDAO.getMeinGarten(user.getId());
        for (Pflanze p : list) {
            System.out.println("🌿 " + p.getName());
        }
    }
}