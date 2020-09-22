import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private static String txtFilename = "users.txt";
   private String name;
   private int age;
   private String sex;
   private String country;
   private String city;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Name: "+getName()+ "\n<br>Age: " +getAge() + "\n<br>Sex: " + getSex() + "\n<br>Country: "+getCountry()+ "\n<br>City: "+ getCity()+"<br><br>";
    }
    static void userID(String ID) throws IOException {
        HashSet<User> usersSet = new HashSet<>();
        String []str = usersReader();
        Pattern Name = Pattern.compile("Name:[a-zA-Z]+\\s[a-zA-Z]+;");
        Pattern Age = Pattern.compile("Age:\\d+");
        Pattern Sex = Pattern.compile("Sex:F|Sex:M");
        Pattern Country = Pattern.compile("Country:[a-zA-Z]+;");
        Pattern City = Pattern.compile("City:[a-zA-Z\\s]+;");
        Pattern id = Pattern.compile("ID:\\d+");
        User need = new User();
        for(String b:str){
            User user = new User();
            Matcher matcher = Name.matcher(b);
            if(matcher.find()){
                user.name = matcher.group().substring(5,matcher.group().length()-1);
            }
            matcher = Age.matcher(b);
            if(matcher.find()){
                user.age = Integer.parseInt(matcher.group().substring(4));
            }
            matcher = Sex.matcher(b);
            if(matcher.find()){
                user.sex = matcher.group().substring(4);
            }
            matcher = Country.matcher(b);
            if(matcher.find()){
                user.country = matcher.group().substring(8,matcher.group().length()-1);
            }
            matcher = City.matcher(b);
            if (matcher.find()){
                user.city = matcher.group().substring(5,matcher.group().length()-1);
            }
            if(ID.equals("ALL")) {
                usersSet.add(user);
            }
            else{
                matcher = id.matcher(b);
                if(matcher.find()){
                    if(matcher.group().substring(3).equals(ID)){
                        need = user;
                    }
                }
            }
        }
        String information = "";
        if(ID.equals("ALL")) {
            Iterator iterator = usersSet.iterator();
            while (iterator.hasNext()) {
                information += iterator.next().toString() + "\n\n";
            }
        }
        else {
            information +=need.toString();
        }
        Helper.makeHTMLFile(information,"id.html");
        Helper.browserOpen("id.html");
    }
    static String[] usersReader() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(User.class.getResource("/").getPath()+txtFilename));
        String information = "";
        String stringHelper = null;
        while ((stringHelper = bufferedReader.readLine())!=null){
            information+=stringHelper;
        }
        String [] str= information.split("#");
        bufferedReader.close();
        return str;
    }
}
