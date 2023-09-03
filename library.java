import java.util.Scanner;
import java.util.ArrayList;
import java.time.Instant;
import java.time.Duration;
class lib {
    public void printMenu() {
        System.out.println("1.   Register a member");
        System.out.println("2.   Remove a member");
        System.out.println("3.   Add a book");
        System.out.println("4.   Remove a book");
        System.out.println("5.   View all members along with their books and fines to be paid");
        System.out.println("6.   View all books");
        System.out.println("7.   Back");
        System.out.println("--------------------------");
    }
    public void printMenu1(){
        System.out.println("1.   List Available Books");
        System.out.println("2.   List My Books");
        System.out.println("3.   Issue book");
        System.out.println("4.   Return book");
        System.out.println("5.   Pay Fine");
        System.out.println("6.   Back");
        System.out.println("--------------------------");
    }
}

class member {
    public ArrayList<String> names = new ArrayList<>();
    private ArrayList<Integer> ages = new ArrayList<>();
    private ArrayList<Long> phoneNumbers = new ArrayList<>();
    private ArrayList<Integer> memberIDs = new ArrayList<>();
    public String currentmember=new String();
    private int currentMemberID = 0;

    public void register() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter member's name: ");
        String name = scan.nextLine();
        System.out.print("Enter member's age: ");
        int age = scan.nextInt();
        System.out.print("Enter member's phone number: ");
        long phoneNumber = scan.nextLong();
        names.add(name);
        ages.add(age);
        phoneNumbers.add(phoneNumber);
        memberIDs.add(++currentMemberID);
        System.out.println("Member successfully registered with <Member ID> "+currentMemberID);
        System.out.println("--------------------------");
    }
    public void viewMembers() {
        System.out.println("\nRegistered Members:");
        for (int i = 0; i < names.size(); i++) {
            System.out.println("Name: " + names.get(i));
            System.out.println("Age: " + ages.get(i));
            System.out.println("Phone Number: " + phoneNumbers.get(i));
            System.out.println("--------------------------");
        }
    }
    public void removeMembers(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your phone number: ");
        long phoneNumber = scan.nextLong();
        for(int i = 0; i < phoneNumbers.size(); i++) {
            if(phoneNumber==phoneNumbers.get(i)){
                names.remove(i);
                ages.remove(i);
                phoneNumbers.remove(i);
            }
        }
        System.out.println("--------------------------");

    }
    public boolean check(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter member's name: ");
        String name = scan.nextLine();
        System.out.print("Enter phone number: ");
        Long phone = scan.nextLong();
        lib librarianObj = new lib();
        boolean found = false;
        for (int i = 0; i < phoneNumbers.size(); i++) {
            if (phone.equals(phoneNumbers.get(i)) && name.equals(names.get(i))) {
                System.out.print("Welcome " + name + " Member ID: "+(i+1)+"\n");
                found = true;
                currentmember=name;
                break;
            }
        }
        if (!found) {
            System.out.println("Member with Name: " + name + " and Phone No: " + phone + " doesn't exist.\n");
        }
        return found;
    }
    public String getNames() {
        return currentmember;
    }

}
class books {
    public ArrayList<String> booktitles = new ArrayList<>();
    private ArrayList<String> authors = new ArrayList<>();
    private ArrayList<Integer> copies = new ArrayList<>();
    private ArrayList<String> issued = new ArrayList<>();
    private ArrayList<String> issuedperson = new ArrayList<>();
    private ArrayList<String> issuedmember = new ArrayList<>();
    private ArrayList<String> issuedbook = new ArrayList<>();
    private ArrayList<Long> issuedfine = new ArrayList<>();
    private ArrayList<Long> issueTimes = new ArrayList<>();
    private ArrayList<Long> fineAmounts = new ArrayList<>();
    private member memberObj = new member();
    String cname = memberObj.getNames();
    private long totalFine = 0;
    public long finevalue = 0;
    public void p(){
        for (int i = 0; i < booktitles.size(); i++){
            issued.add("no");
            issuedperson.add("");
        }
    }

    public void addbook() {
        System.out.println("--------------------------");
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter book title: ");
        String booktitle = scan.nextLine();
        System.out.print("Enter author: ");
        String author = scan.nextLine();
        System.out.print("Enter no. of copies: ");
        int copy = scan.nextInt();
        for (int i = 1; i <= copy; i++) {
            booktitles.add(booktitle);
            authors.add(author);
        }
        System.out.println("Book successfully registered!");
    }

    public void viewbook() {
        System.out.println("\nRegistered Books:");
        for (int i = 0; i < booktitles.size(); i++) {
            System.out.println("BookID: " + (i + 1));
            System.out.println("Book Title: " + booktitles.get(i));
            System.out.println("Author: " + authors.get(i));
            System.out.println("--------------------------");
        }
    }

    public void removebook() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter bookID: ");
        int bookid = scan.nextInt();
        if (bookid >= 1 && bookid <= booktitles.size()) {
            booktitles.remove(bookid - 1);
            authors.remove(bookid - 1);
            System.out.println("Book successfully removed!");
        } else {
            System.out.println("Invalid bookID. Book not found.");
        }
        System.out.println("--------------------------");
    }

    public void availableBooks() {
        String boo = "no";
        for (int i = 0; i < booktitles.size(); i++){
            if (issued.get(i) == boo) {
                System.out.println("BookID: " + (i + 1));
                System.out.println("Book Title: " + booktitles.get(i));
                System.out.println("Author: " + authors.get(i));
            }
            System.out.println("--------------------------");
        }
    }

    public void issuebook(String memberName) {
        long totalFine = getTotalFineForMember(memberName);
        if (totalFine > 0) {
            System.out.println("You have a total fine of Rs." + totalFine + ". Please clear your fine to borrow a new book.");
        } else {
            Scanner san = new Scanner(System.in);
            System.out.print("Enter bookID: ");
            int bookid = san.nextInt();
            san.nextLine();
            System.out.print("Enter book title: ");
            String bookname = san.nextLine();
            issuedmember.add(memberName);

            if (bookid >= 1 && bookid <= booktitles.size()) {
                if (issued.get(bookid - 1).equals("no") && bookname.equals(booktitles.get(bookid - 1))) {
                    issued.set(bookid - 1, "yes");
                    issuedperson.set(bookid - 1, cname);
                    issuedbook.add(bookname);
                    System.out.println("Book Issued Successfully!");
                    issueTimes.add(Instant.now().getEpochSecond());
                } else {
                    System.out.println("Invalid bookID or the book is not available.");
                }
            } else {
                System.out.println("Invalid bookID or book not found");
            }
        }
    }
    public void payfine() {
        Scanner san = new Scanner(System.in);
        System.out.print("Enter member's name: ");
        String memberName = san.nextLine();
        long totalFineofMember = getTotalFineForMember(memberName);
        if (totalFineofMember > 0) {
            System.out.println("Total fine for " + memberName + " is Rs." + totalFineofMember);
            System.out.print("Enter the amount to pay: ");
            long amountPaid = san.nextLong();

            if (amountPaid >= totalFineofMember) {
                totalFineofMember = 0;
                System.out.println("Fine for " + memberName + " has been paid successfully.");
            } else {
                totalFineofMember -= amountPaid;
                System.out.println("Rs." + amountPaid + " has been paid for " + memberName + ". Remaining fine: Rs." + totalFineofMember);
            }
        } else {
            System.out.println("No fines to pay for " + memberName + ".");
        }
        updateTotalFineForMember(memberName, totalFineofMember);
    }
    private long getTotalFineForMember(String memberName) {
        long totalFine = 0;

        for (int i = 0; i < issuedmember.size(); i++) {
            if (issuedmember.get(i).equals(memberName) && fineAmounts.get(i) > 0) {
                totalFine += fineAmounts.get(i);
            }
        }
        return totalFine;
    }
    private void updateTotalFineForMember(String memberName, long totalFine) {
        for (int i = 0; i < issuedmember.size(); i++) {
            if (issuedmember.get(i).equals(memberName)) {
                fineAmounts.set(i, totalFine);
            }
        }
    }
    public void mybook(){
        for (int i = 0; i < booktitles.size(); i++) {
            if (issuedperson.get(i) == cname) {
                System.out.println("BookID: " + (i + 1));
                System.out.println("Book Title: " + booktitles.get(i));
                System.out.println("Author: " + authors.get(i));
            }
            System.out.println("--------------------------");
        }
    }
    public void returnbook(){
        Scanner san = new Scanner(System.in);
        System.out.print("Enter bookID: ");
        int bookid = san.nextInt();
        int s= issueTimes.size();

        if (bookid >= 1 ) {
            issued.set(bookid - 1, "no");
            issuedperson.set(bookid - 1, "");
            System.out.println("Book returned Successfully!");
            long issueTime = issueTimes.get(s- 1);
            long returnTime = Instant.now().getEpochSecond();
            long seconds = returnTime - issueTime;
            if (seconds > 10L) {
                long fine = (seconds - 10L) * 3L;
                finevalue=fine;
                fineAmounts.add(fine);
                System.out.println(fine + " Rupees has been charged for a delay of " + (seconds - 10L) + " seconds.");
            } else {
                fineAmounts.add(0L);
            }
        }else{
            System.out.println("Invalid bookID or the book is not available.");
        }
    }

    public void memberBOOKfine() {
        System.out.println("\nFine Details:");
        for (int i = 0; i < issuedmember.size(); i++) {
            System.out.println("Member: " + issuedmember.get(i));
            System.out.println("Book Title: " + issuedbook.get(i));
            System.out.println("Fine: " + fineAmounts.get(i));
            System.out.println("--------------------------");
        }
    }
    public long getvalue() {
        return finevalue;
    }
}
public class practice {
    public static void main(String[] args) {
        System.out.println("Library portal Initialized");
        System.out.println("--------------------------");
        System.out.println("1.    Enter as a librarian");
        System.out.println("2.    Enter as a member");
        System.out.println("3.    Exit");
        System.out.println("--------------------------");

        Scanner obj = new Scanner(System.in);
        int answer = obj.nextInt();

        member memberObj = new member();
        books bookObj = new books();
        lib librarianObj = new lib();

        while (answer != 3) {
            if (answer == 1) {
                int librarianChoice;

                do {
                    librarianObj.printMenu();
                    bookObj.p();
                    librarianChoice = obj.nextInt();
                    obj.nextLine();

                    if (librarianChoice == 1) {
                        memberObj.register();
                      //  memberObj.viewMembers();
                    }
                    if (librarianChoice == 2) {
                        memberObj.removeMembers();
                      //  memberObj.viewMembers();
                    }
                    if (librarianChoice == 3) {
                        bookObj.addbook();
                     //   bookObj.viewbook();
                    }
                    if (librarianChoice == 4) {
                        bookObj.removebook();
                       // bookObj.viewbook();
                    }
                    if (librarianChoice == 5) {
                        bookObj.memberBOOKfine();
                    }
                    if (librarianChoice == 6) {
                        bookObj.viewbook();
                    }
                    if (librarianChoice == 7) {
                        break;
                    }
                } while (true);
            }
            if (answer == 2) {
                if (!memberObj.check()) {
                    break;
                }
                System.out.println("--------------------------");
                do {
                    librarianObj.printMenu1();
                    Scanner sc = new Scanner(System.in);
                    int memberchoice = sc.nextInt();
                    if (memberchoice == 1) {
                        bookObj.availableBooks();
                    }
                    if (memberchoice==2){
                        bookObj.mybook();
                    }
                    if (memberchoice == 3) {
                        bookObj.issuebook(memberObj.getNames());
                    }
                    if (memberchoice == 4) {
                        bookObj.returnbook();
                    }
                    if (memberchoice==5){
                        bookObj.payfine();
                    }
                    if (memberchoice == 6) {
                        break;
                    }

                } while (true);
            }

            System.out.println("--------------------------");
            System.out.println("1.    Enter as a librarian");
            System.out.println("2.    Enter as a member");
            System.out.println("3.    Exit");
            System.out.println("--------------------------");
            answer = obj.nextInt();
        }
        System.out.println("--------------------------");
        System.out.println("Thanks for visiting!");
        System.out.println("--------------------------");
    }
}
