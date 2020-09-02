import java.util.Comparator;

public class NameComparator implements Comparator<Student> {
    //Здесь сортировал по имени по алфавиту
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
