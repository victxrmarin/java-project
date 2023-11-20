import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    menu(sc);
  }

  public static void menu(Scanner sc) {
    int entry;
    do {
      System.out.println("\nMenu\n");
      System.out.println("1 | Create new user");
      System.out.println("2 | Send a new message");
      System.out.println("3 | Read all messages send to an user");
      System.out.println("4 | Read all users");
      System.out.println("0 | Exit");
      entry = sc.nextInt();

      switch (entry) {
        case 1:
          newUser(sc);
          break;
        case 2:
          newMessage(sc);
          break;
        case 3:
          readAllMessagesToUser(sc);
          break;
        case 4:
          allUser();
          break;
        case 0:
          System.out.println("Exiting...");
          break;
        default:
          System.out.println("Try a correct option!");
          break;
      }
    } while (entry != 0);
  }

  public static void newUser(Scanner sc) {
    sc.nextLine();
    System.out.println("Type a new user's nickname:");
    String nickname = sc.nextLine();
    new User().createUser(nickname);
  }

  public static void newMessage(Scanner sc) {
    System.out.println("Enter a sender:");
    sc.nextLine();
    String sender = sc.nextLine();

    System.out.println("Enter a recipient:");
    String recipient = sc.nextLine();

    System.out.println("Type your text:");
    String text = sc.nextLine();

    new Message().sendMessage(sender, recipient, text);
  }

  public static void allUser() {
    new User().allUser();
  }

  public static void readAllMessagesToUser(Scanner sc) {
    sc.nextLine();
    System.out.println("Type a nickname to see all messages:");
    String nickname = sc.nextLine();
    new Message().readAllMessageToUser(nickname);
  }
}
