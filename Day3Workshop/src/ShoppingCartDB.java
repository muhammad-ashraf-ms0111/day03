package Day3Workshop.src;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ShoppingCartDB {

    static final String LOGIN = "login";
    public static final String ADD = "add";
    protected static final String LIST = "list";
    private static final String SAVE = "save";
    private static final String EXIT = "exit";
    private static final String USERS = "users";

    public static final List<String> VALID_COMMANDS = Arrays.asList(
            LOGIN,
            SAVE,
            ADD,
            LIST,
            USERS,
            EXIT
    );

    private CartDBInMemory cartDatabase;
    private String currentUser;
    private String baseFolder;

    public ShoppingCartDB() {
        baseFolder = "db";
        setup();
        cartDatabase = new CartDBInMemory(baseFolder);
    }

    public ShoppingCartDB(String baseFolder) {
        this.baseFolder = baseFolder;
        setup();
        cartDatabase = new CartDBInMemory(baseFolder);
    }

    public void setup() {
        Path p = Paths.get(baseFolder);
        if (Files.isDirectory(p)) {
            // SKIP if directory already exits
        } else {
            try {
                Files.createDirectory(p);
            } catch (IOException e) {
                System.out.println("Error :" + e.getMessage());
            }
        }
    }

    public void startShell() {
        System.out.println("Welcome to MultiUser Shopping Cart >> ");
        Scanner sc = new Scanner(System.in);
        String line;
        boolean stop = false;

        while (!stop) {
            line = sc.nextLine();
            line = line.trim();
            System.out.println("=> " + line);
            if (line.equalsIgnoreCase("exit")) {
                System.out.println("Exiting !!!");
                stop = true;
            }
            if (isInputValid(line)) {
                System.out.println("Processing : " + line);
                processInput(line);
            } else {
                System.out.println("Invalid Input: ^^");
            }
        }
        sc.close();
    }

    public boolean isInputValid(String input) {
        String[] parts = input.split(" ");
        String command = parts[0].trim();
        // Scanner lsc = new Scanner(input);
        // String commad = lsc.next().trim()
        return VALID_COMMANDS.contains(command);
    }

    // Process command
    public void processInput(String input) {
        Scanner sc = new Scanner(input);
        String command = sc.next().trim();

        switch (command) {
            case LOGIN:
                String username = sc.nextLine().trim();
                loginAction(username);
                System.out.println("Print - current logged in user" + currentUser);
                break;

            case LIST:
                listAction();
                break;

            case ADD:
                String[] items = sc.nextLine().trim().split(",");
                addAction(items);
                break;

            case SAVE:
                saveAction();
                break;

            case USERS:
                listUsersAction();

            default:
                break;
        }
        sc.close();
    }

    public void loginAction(String username) {
        if (!cartDatabase.userMap.containsKey(username)) {
            cartDatabase.userMap.put(username, new ArrayList<String>());
        }
        currentUser = username;
    }

    public void addAction(String[] items) {
        for (String item : items) {
            cartDatabase.userMap.get(currentUser).add(item.trim());
        }
    }

    public void listAction() {
        for (String item : cartDatabase.userMap.get(currentUser)) {
            System.out.println("Item -> " + item);
        }
    }

    public void listUsersAction() {
        for (String key : cartDatabase.userMap.keySet()) {
            System.out.println("-> " + key);
        }
    }

    public void saveAction() {
        // Prepare the filePath = "db/<username>.txt"
        String outputFilename = String.format("%s/%s.txt", baseFolder, currentUser);

        try {
            FileWriter fw = new FileWriter(outputFilename);
            // Save the contents for this user in Map to a file.
            for (String item : cartDatabase.userMap.get(currentUser)) {
                fw.write(item + "\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}