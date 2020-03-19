package models;

public enum Moods {
    GREAT("1"), GOOD("2"), NORMAL("3"), POOR("4"), TERRIBLE("5");
    private String moodValue;

    public String getMoodValue() {
        return moodValue;
    }

    public void setMoodValue(String moodValue) {
        this.moodValue = moodValue;
    }

    Moods(String i) {
        this.moodValue = i;
    }
}
