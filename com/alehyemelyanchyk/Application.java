package com.alehyemelyanchyk;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private List<User> userBase;

    private static final String FILE_NAME = "users";

    public Application() {
        this.userBase = new ArrayList<>();
    }

    public void writeToFile(User user) {
        PrintWriter printWriter;
        try (FileWriter fileWriter = new FileWriter(FILE_NAME, true)) {

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);

            printWriter.write(user.getLogin() + ";"
                    + user.getPassword() + ";"
                    + user.getEmail() + ";"
                    + user.getId() + "\n");
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFromFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
        String c;
        while ((c = bufferedReader.readLine()) != null) {
            String[] subStr = c.split(";");
            userBase.add(User.createUserNoHash(subStr[0], subStr[1], subStr[2]));
        }
        bufferedReader.close();
        return c;
    }

    public boolean register(User user) {
        if (findUser(user.getLogin(), user.getEmail())) {
            userBase.add(user);
            writeToFile(user);
            return true;
        }
        return false;
    }

    private boolean findUser(String login, String email) {
        for (User user : userBase) {
            if (user.getEmail().equalsIgnoreCase(email) || user.getLogin().equalsIgnoreCase(login)) {
                return false;
            }
        }
        return true;
    }

    public boolean login(String login, String password) {
        if (loginCheck(login, password)) {
            return true;
        }
        return false;
    }

    private boolean loginCheck(String login, String password) {
        for (User user : userBase) {
            if (user.getLogin().equalsIgnoreCase(login) && user.getPassword().equalsIgnoreCase(password)) {
                return true;
            }
        }
        return false;
    }



    public void showUsers() {
        for (User user : userBase) {
            System.out.println(user.toString());
        }
    }


}
