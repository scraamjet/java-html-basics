import java.util.Comparator;

public class CourseComparator implements Comparator<Student> {
    //Здесь сортировал по курсам от большего к меньшему
    @Override
    public int compare(Student o1, Student o2) {
        if(o1.getCourse()<o2.getCourse()) return 1;
        else if(o1.getCourse()>o2.getCourse()) return -1;
        else return 0;
    }
}
