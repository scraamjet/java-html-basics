import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    System.out.println("Выберите Comparator на свой вкус и цвет из предложенных:\n1 - " +
            "NameComparator\n2 - AgeComparator\n3 - CourseComparator");
    byte a = scanner.nextByte();
    switch (a){
        case 1:{
            System.out.println("Сортируем студентов по имени по алфавита: ");
            PriorityQueue<Student> Students = new PriorityQueue<>(NameComparator);
            FillTheQueue(Students);
            print(Students);
            break;
        }
        case 2:{
            System.out.println("Сортируем студентов по возрасту: ");
            PriorityQueue<Student> Students = new PriorityQueue<>(AgeComparator);
            FillTheQueue(Students);
            print(Students);
            break;
        }
        case 3:{
            System.out.println("Сортируем студентов по курсу по убыванию: ");
            PriorityQueue<Student> Students = new PriorityQueue<>(CourseComparator);
            FillTheQueue(Students);
            print(Students);
            break;
        }
    }
    }
    public static void print(PriorityQueue<Student> queue){
        /*Iterator iterator = queue.iterator();
        while (iterator.hasNext()){
            Student student = (Student)iterator.next();
            System.out.println(student.toString());
        }

         */
        while (!queue.isEmpty()){
            System.out.println(queue.poll().toString());
        }
    }
    public static void FillTheQueue(PriorityQueue<Student> Students){
        Student student  = new Student("Artem",19,2);
        Students.offer(student);
        Student student1 = new Student("Bulat",19,2);
        Students.offer(student1);
        Student student2 = new Student("Faiz",23,4);
        Students.offer(student2);
        Student student3 = new Student("John",18,1);
        Students.offer(student3);
        Student student4 = new Student("Paul",22,3);
        Students.offer(student4);
        Student student5 = new Student("David",17,1);
        Students.offer(student5);
        Student student6 = new Student("Peter", 22,3);
        Students.offer(student6);
        Student student7 = new Student("Sean",21,3);
        Students.offer(student7);
        Student student8 = new Student("Denis",24,5);
        Students.offer(student8);
    }
    public static Comparator <Student> NameComparator = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public static Comparator<Student> AgeComparator = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            return Integer.compare(o1.getAge(), o2.getAge());
        }
    };
    public static Comparator<Student> CourseComparator = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            return Integer.compare(o2.getCourse(), o1.getCourse());
        }
    };
}
