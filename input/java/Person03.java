import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class Person03 {
    private String firstName;
    private String lastName;
    private Calendar birthday;
    private int favouriteNumber;

    public Person03()
    {
        this.firstName = "";
        this.lastName = "";
        this.birthday = new GregorianCalendar();

        Random randomObj = new Random();
        this.favouriteNumber = randomObj.nextInt(42);
    }
    public Person03(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = new GregorianCalendar();

        Random randomObj = new Random();
        this.favouriteNumber = randomObj.nextInt(42);
    }
    public Person03(String firstName, String lastName, Calendar birthday)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;

        Random randomObj = new Random();
        this.favouriteNumber = randomObj.nextInt(42);
    }

    public String fullName()
    {
        return firstName + " " + lastName;
    }

    public int age(Calendar today)
    {
        return today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
    }

    public int favNum() {
        return favouriteNumber;
    }
}