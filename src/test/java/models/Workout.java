package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Workout {
    public String date;
    public String timeOfDay;
    public String activityType;
    public String workoutName;
    public String workoutDescription;
    public String showPlannedDistanceDuration;
    public String distance;
    public String distanceUnit;
    public String duration;
    public String pace;
    public String paceUnit;
    public String howIFeltDropdown;
    public String moodRadioButton;
    public String perceivedEffort;
    public String notes;
    public String markAsRace;
    public String saveToLibrary;
    public String caloriesBurnt;
}
