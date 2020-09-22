import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Post {
    private static String txtFilename = "posts.txt";
    private String name;
    private String city;
    private String time;
    private String describe;
    private String id;

    protected Post() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getName() + ", " + getCity() + ", " + getTime() + "\n<br>" + getDescribe();
    }

    static void feed() throws IOException {
        LinkedList <Post> postList = new LinkedList<>();
        Pattern userName = Pattern.compile("Name:[a-zA-Z]+\\s[a-zA-Z]+");
        Pattern userCity = Pattern.compile("City:[a-zA-Z\\s]+");
        Pattern POST = Pattern.compile("POST!.+\\$\\*");
        Pattern ID = Pattern.compile("ID:\\d+");
        Pattern postTime = Pattern.compile("$*time:\\d+.+");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Post.class.getResource("/").getPath()+txtFilename));
        String information = "";
        String stringHelper = null;
        while ((stringHelper=bufferedReader.readLine())!=null){
            information+=stringHelper;
        }
        bufferedReader.close();
        String [] str = information.split("#");
        for(String b:str){
            Post post = new Post();
            Matcher matcher = ID.matcher(b);
            if(matcher.find()) {
                post.setId(matcher.group().substring(3));
            }
            matcher = POST.matcher(b);
            if(matcher.find()){
                post.setDescribe(matcher.group().substring(5,matcher.group().length()-2));
            }
            matcher = postTime.matcher(b);
            if(matcher.find()){
                post.setTime(matcher.group().substring(5));
            }
            postList.add(post);
        }
            str = User.usersReader();
            for(String b:str){
                    Matcher matcherID = ID.matcher(b);
                    if(matcherID.find()) {
                        for (int i = 0; i < postList.size(); i++) {
                            if(postList.get(i).getId().equals(matcherID.group().substring(3))){
                                Matcher matcher2 = userName.matcher(b);
                                if(matcher2.find()){
                                    postList.get(i).setName(matcher2.group().substring(5));
                                }
                                matcher2 = userCity.matcher(b);
                                if (matcher2.find()){
                                    postList.get(i).setCity(matcher2.group().substring(5));
                                }
                            }
                        }
                    }
            }
             information = "";
            Iterator iterator = postList.iterator();
            while (iterator.hasNext()) {
                information += (iterator.next().toString()) + "<br>";
            }
            Helper.makeHTMLFile(information, "feed.html");
            Helper.browserOpen("feed.html");
        }
    }
