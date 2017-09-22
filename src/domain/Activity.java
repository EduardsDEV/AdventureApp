package domain;

/**
 * Created by edwar on 9/18/2017.
 */
public class Activity {

    private String name;
    private String description;
    private String duration;

    public Activity(String name, String description, String duration) {
        this.name = name;
        this.description = description;
        this.duration = duration;
    }

    public void setName(String name) {this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }


}
