import java.util.ArrayList;

public class DataBase {

    ArrayList<User> users;
    ArrayList<TaskInfo> taskInfo;


    //singelton
    private static final DataBase instance = new DataBase();
    public static DataBase getInstance() {
        return instance;
    }


    private DataBase() {
        this.users = new ArrayList<User>();
        this.taskInfo = new ArrayList<TaskInfo>();

    }


   // public void addTask()



}
