package service;

import dao.UserDAO;
import model.User;

import java.util.Scanner;

public class AuthService {

    private UserDAO userDAO = new UserDAO();
    private Scanner scanner = new Scanner(System.in);

    public User login() throws Exception {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        return userDAO.findByUsernameAndPassword(username, password);
    }
}