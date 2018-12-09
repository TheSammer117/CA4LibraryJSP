/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

/**
 * Team: Hernel Provido, Sami Mahmoud, Haiyun Yu
 * @author Haiyun Yu d00188956 
 */
public class CommandFactory {

    public static Command createCommand(String action) {
        Command command = null;

        if (action != null) {
            switch (action) {
                //1. View books 
                case "viewAllBooks":
                    command = new ViewAllTitlesCommand();
                    break;
                //2. Search for a book (or books) 
                case "searchBook":
                    command = new SearchTitleCommand();
                    break;
                //3. Borrowing a book (or books) 
                case"borrowBook":
                    command = new BorrowTitleCommand();
                    break;
                //4. Returning a book (or books) 
                case "returnBook":
                    command = new ReturnTitleCommand();
                    break;
                //5. Log in to the system
                case "login":
                    command = new LoginCommand();
                    break;
                //6. Register with the system
                case "register":
                    command = new RegisterCommand();
                    break;
                //7. View my (the user’s) profile 
                case "viewProfile":
                    command = new ViewUserProfileCommand();
                    break;
                //8. Edit my (the user’s) details
                case "editDetails":
                    command = new EditUserDetailsCommand();
                    break;
                //9. View my (the user’s) current loans
                case "viewCurrentLoan":
                    command = new ViewCurrentLoanCommand();
                    break;
                //10. View my (the user’s) previous loans 
                case "viewPreviousLoan":
                    command = new ViewPreviousLoanCommand();
                    break;
                //11. Pay overdue fees 
                case "payOverdueFee":
                    command = new PayOverdueFeeCommand();
                    break;
                //12.change current language
                case "changeLanguage":
                    command = new ChangeLanguageCommand();
                    break;
                //13. view a title's information
                case "viewBookDetails":
                    command = new ViewTitleDetailCommand();
                    break;
                //14.edit book's details
                case "editBookDetails":
                    command = new EditTitleDetailCommand();
                    break;
                //15. view the user's all loans: 
                case "viewAllLoans":
                    command = new ViewAllLoansCommand();
                    break;
                //Programmer-defined: (HaiyunYu)
                //16. add a book
                case "addBook":
                    command = new AddTitleCommand();
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
