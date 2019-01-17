public class TaskInfo {

    private String taskName;
    private String date;
    private String time;
    private String comment;
    private String priorty;


    public TaskInfo() {
    }

    public TaskInfo(String taskName, String date, String time, String comment, String priorty) {
        this.taskName = taskName;
        this.date = date;
        this.time = time;
        this.comment = comment;
        this.priorty = priorty;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPriorty() {
        return priorty;
    }

    public void setPriorty(String priorty) {
        this.priorty = priorty;
    }
}
