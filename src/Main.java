import model.User;
import service.AuthService;
import service.AdminService;
import service.UserService;

public class Main {

    public static void main(String[] args) throws Exception {

        AuthService auth = new AuthService();
        User user = auth.login();

        if (user == null) {
            System.out.println("Login fehlgeschlagen!");
            return;
        }

        if (user.getRole().equals("ADMIN")) {
            new AdminService().start();
        } else {
            new UserService().start(user);
        }
    }
}