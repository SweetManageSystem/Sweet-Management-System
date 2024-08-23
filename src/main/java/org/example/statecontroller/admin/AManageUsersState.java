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
    public AManageUsersState(Context context) {
        this.context = context;
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
        String command = scanner.nextLine();
        context.filterState(command);
        switch (command) {
            case "1":
                logger.info("Enter the Email :");
                String email = scanner.nextLine();
                context.filterState(email);
                logger.info("Enter UserName :");
                String userName = scanner.nextLine();
                context.filterState(userName);
                for(Person p : UserDataBase.getDb()){
                    if(p.getEmail().equals(email) || p.getUsername().equals(userName)){
                        logger.info("Username or Email already exists");
                        context.handleInput();
                    }
                }
                logger.info("Enter Password :");
                String password = scanner.nextLine();
                context.filterState(password);
                logger.info("Enter Full Name :");
                String fullName = scanner.nextLine();
                context.filterState(fullName);
                Person p = new User(userName,email,password,fullName);
                logger.info(p.getUsername());
                UserDataBase.addPerson(p);
                logger.info("User created successfully");
                context.setCurrentState(new AdminState(context));
                break;
            case "2":
                logger.info("Enter the User Email :");
                String userEmail = scanner.nextLine();
                context.filterState(userEmail);
                Person person = UserDataBase.getPerson(userEmail);
                if (person != null) {
                    logger.info( "Username: " + person.getUsername() + "\n Role: " + person.getRole() + "\n Full Name : " + person.getFullname() + "\n Password: " + person.getPassword());
                }
                else {
                    logger.info("User not found");
                    context.handleInput();
                }
                context.setCurrentState(new AdminState(context));
                break;
            case "3":
                logger.info("Enter the Email :");
                String s = scanner.nextLine();
                context.filterState(s);
                editUser(s);
                logger.info("User Edited successfully");
                context.setCurrentState(new AdminState(context));
                break;
            case "4":
                logger.info("Enter the User Email :");
                command = scanner.nextLine();
                context.filterState(command);
                UserDataBase.removePersonByEmail(command);
                logger.info("User removed successfully");
                context.setCurrentState(new AdminState(context));
                break;
            default:
                logger.info("Invalid command");
                break;
        }
        context.handleInput();

    }



    private void editUser(String email){
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
        String command = scanner.nextLine();
        context.filterState(command);
        switch (command){
            case "1":
                logger.info("Enter new UserName :");
                command = scanner.nextLine();
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
                command = scanner.nextLine();
                context.filterState(command);
                p.setPassword(command);
                break;
            case "3":
                logger.info("Enter new Full Name :");
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
