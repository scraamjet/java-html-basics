import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> Students = new ArrayList<>();
    Student student  = new Student("Artem",19,2);
    Students.add(student);
    Student student1 = new Student("Bulat",19,2);
    Students.add(student1);
    Student student2 = new Student("Faiz",23,4);
    Students.add(student2);
    Student student3 = new Student("John",18,1);
    Students.add(student3);
    Student student4 = new Student("Paul",22,3);
    Students.add(student4);
    Student student5 = new Student("David",17,1);
    Students.add(student5);
    Student student6 = new Student("Peter", 22,3);
    Students.add(student6);
    Student student7 = new Student("Sean",21,3);
    Students.add(student7);
    System.out.println("Выберите Comparator на свой вкус и цвет из предложенных:\n1 - " +
            "NameComparator\n2 - AgeComparator\n3 - CourseComparator");
    byte a = scanner.nextByte();
    switch (a){
        case 1:{
            Collections.sort(Students,new NameComparator());
            print(Students);
            break;
        }
        case 2:{
            Collections.sort(Students,new AgeComparator());
            print(Students);
            break;
        }
        case 3:{
            Collections.sort(Students,new CourseComparator());
            print(Students);
            break;
        }
    }
    }
    public static void print(ArrayList list){
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            Student student = (Student)iterator.next();
            System.out.println("Name: " + student.getName() +  " ,Age: " + student.getAge()+ " ,Course:" + student.getCourse());
        }
    }
}
