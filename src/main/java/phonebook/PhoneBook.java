/** PhoneBook realization on java. Contains data about people. *
 * More precisely it contains: First Name, Last Name, Phone Number.
 * Made by Mykyta, 14.02.2015                                     */

package phonebook;

public class PhoneBook {
    public static void main(String[] args) {
        PhoneBookLogic phoneBook = new PhoneBookLogic();
        phoneBook.menu();
    }
}