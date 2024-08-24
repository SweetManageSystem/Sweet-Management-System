package org.example.statecontroller.admin;

import org.example.account.Person;
import org.example.account.User;
import org.example.database.UserDataBase;
import org.example.statecontroller.Context;
import org.example.statecontroller.State;

import java.util.Scanner;
import java.util.logging.Logger;

public class AManageUsersState implements State {
    private Context context;
    private Logger logger = Logger.getLogger(AManageUsersState.class.getName());
    private String command;
    private String email;
    private String userName;
    private String password;
    private String fullName;
    public AManageUsersState(Context context) {
        this.context = context;
    }


    public void setEmail(String email) {
        this.email = email;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        String textBlock = """
                     <html>
                          <body>
                                <tag>
                                   Manage Users<br>
                                   1.Create a new user account        2.Show user status<br>
                                   3.Edit user details                4.Delete a user account
                                </tag>
                          </body>
                    </html>""";
        logger.info(textBlock);
        if(!context.isTest())
         command = scanner.nextLine();
        context.filterState(command);
        AdminState adminState = new AdminState(context);
        if(context.isTest())
            adminState.setCommand("exit");
        switch (command) {
            case "1":
                logger.info("Enter the Email :");
                if(!context.isTest())
                    email = scanner.nextLine();
                context.filterState(email);
                logger.info("Enter UserName :");
                if(!context.isTest())
                     userName = scanner.nextLine();
                context.filterState(userName);
                for(Person p : UserDataBase.getDb()){
                    if(p.getEmail().equals(email) || p.getUsername().equals(userName)){
                        logger.info("Username or Email already exists");
                        context.handleInput();
                    }
                }
                logger.info("Enter Password :");
                if(!context.isTest())
                     password = scanner.nextLine();
                context.filterState(password);
                logger.info("Enter Full Name :");
                if(!context.isTest())
                    fullName = scanner.nextLine();
                context.filterState(fullName);
                Person p = new User(userName,email,password,fullName);
                logger.info(p.getUsername());
                UserDataBase.addPerson(p);
                logger.info("User created successfully");
                context.setCurrentState(adminState);
                break;
            case "2":
                logger.info("Enter the User Email :");
                if(!context.isTest())
                     email = scanner.nextLine();
                context.filterState(email);
                Person person = UserDataBase.getPerson(email);
                if (person != null) {
                    logger.info( "Username: " + person.getUsername() + "\n Role: " + person.getRole() + "\n Full Name : " + person.getFullname() + "\n Password: " + person.getPassword());
                }
                else {
                    logger.info("User not found");
                    context.handleInput();
                }
                context.setCurrentState(adminState);
                break;
            case "3":
                logger.info("Enter the Email :");
                if(!context.isTest())
                    email = scanner.nextLine();
                context.filterState(email);
                editUser(email);
                logger.info("User Edited successfully");
                context.setCurrentState(adminState);
                break;
            case "4":
                logger.info("Enter the User Email :");
                if(!context.isTest())
                    command = scanner.nextLine();
                context.filterState(command);
                UserDataBase.removePersonByEmail(command);
                logger.info("User removed successfully");
                context.setCurrentState(adminState);
                break;
            default:
                logger.info("Invalid command");
                break;
        }
        context.handleInput();

    }

    public void setCommand(String command) {
        this.command = command;
    }



    public void editUser(String email){
        if(email == null){
            logger.info("invalid email");
            context.handleInput();
            return;
        }
        Person p = UserDataBase.getPerson(email);
        if(p == null){
            logger.info("User not found");
            context.handleInput();
            return;
        }
        Scanner scanner = new Scanner(System.in);
        String textBlock =  """
                     <html>
                          <body>
                                <tag>
                                  Manage Account<br> 
                                  1.Edit UserName           2.Edit Password<br>
                                  3.Edit Full Name
                                </tag>
                          </body>
                    </html>""";
        logger.info(textBlock);
        if(!context.isTest())
            command = scanner.nextLine();
        context.filterState(command);
        switch (command){
            case "1":
                logger.info("Enter new UserName :");
                if(!context.isTest())
                    userName = scanner.nextLine();
                context.filterState(command);
                for (Person n : UserDataBase.getDb()) {
                    if(n.getUsername().equals(command)){
                        logger.info("Username already exists");
                        editUser(email);
                    }
                }
                p.setUsername(command);
                break;
            case "2":
                logger.info("Enter new Password :");
                if(!context.isTest())
                    password = scanner.nextLine();
                context.filterState(command);
                p.setPassword(command);
                break;
            case "3":
                logger.info("Enter new Full Name :");
                if(!context.isTest())
                    command = scanner.nextLine();
                context.filterState(command);
                p.setFullname(command);
                break;
            default:
                logger.info("Invalid command");
                editUser(email);

        }
    }
}
