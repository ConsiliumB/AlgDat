package modules;

/**
 * Created by Consilium on 19.09.2016.
 */
public class Student extends Person   // Student blir subklasse til Person
{
    private final Studium studium;      // studentens studium

    public Student(String fornavn, String etternavn, Studium studium)
    {
        super(fornavn, etternavn);
        this.studium = studium;
    }

    public String toString() { return super.toString() + " " + studium.name();}

    public Studium studium() { return studium; }

}  // class Student
