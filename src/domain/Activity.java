package domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by edwar on 9/18/2017.
 */
public class Activity {


    private final SimpleStringProperty colName;
    private final SimpleIntegerProperty colAgeRes;
    private final SimpleIntegerProperty colDuration;
    private final SimpleIntegerProperty colPrice;


    public Activity(String colName, int colAgeRes, int colDuration, int colPrice) {
        this.colName = new SimpleStringProperty(colName);
        this.colAgeRes = new SimpleIntegerProperty(colAgeRes);
        this.colDuration = new SimpleIntegerProperty(colDuration);
        this.colPrice = new SimpleIntegerProperty(colPrice);

    }


    public String getColName() {return colName.get();}
    public void setColName(String value) {colName.set(value);}

    public int getColAgeRes() {
        return colAgeRes.get();
    }
    public void setColAgeRes(int value) {
        colAgeRes.set(value);
    }

    public int getColDuration() {
        return colDuration.get();
    }
    public void setColDuration(int value) {
        colDuration.set(value);
    }

    public int getColPrice() {
        return colPrice.get();
    }
    public void setColPrice(int value) {
        colPrice.set(value);
    }


    /*
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
*/

}
