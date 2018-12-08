/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

/**
 * Team: Hernel Provido, Sami Mahmoud, Haiyun Yu
 * @author Haiyun Yu d00188956 
 * 1. View books 
 * 2. Search for a book (or books) 
 * 3. Borrowing a book (or books) 
 * 4. Returning a book (or books) 
 * 5. Log in to the system 
 * 6. Register with the system 
 * 7. View my (the user’s) profile 
 * 8. Edit my (the user’s) details 
 * 9. View my (the user’s) current loans 
 * 10. View my (the user’s) previous loans 
 * 11. Pay overdue fees 
 * 12. Programmer-defined (Group member A) 
 * 13. Programmer-defined (Group member B) 
 * 14. Programmer-defined (Group member C)
 */
public class CommandFactory {

    public static Command createCommand(String action) {
        Command command = null;

        if (action != null) {
            switch (action) {
                case "viewAllBooks":
                    command = new ViewAllTitlesCommand();
                    break;
                case "searchBook":
                    command = new SearchTitleCommand();
                    break;
                case"borrowBook":
                    command = new BorrowBookCommand();
                    break;
                case "returnBook":
                    command = new ReturnBookCommand();
                    break;
                case "login":
                    command = new LoginCommand();
                    break;
                case "register":
                    command = new RegisterCommand();
                    break;
                case "viewProfile":
                    command = new ViewUserProfileCommand();
                    break;
                case "editDetails":
                    command = new EditUserDetailsCommand();
                    break;
                case "viewCurrentLoan":
                    
                    break;
                case "viewPriviousLoan":
                    
                    break;
                case "payOverdueFee":
                    
                    break;
                default:
                    command = new NoValidActionCommand();
                    break;
            }
        } else {
            command = new NoActionSuppliedCommand();
        }
        return command;
    }
}
