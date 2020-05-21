package project;

import com.google.gson.annotations.SerializedName;

public class CompanyType {
    private long id;
    @SerializedName("name_short")
    private String shortName;
    @SerializedName("name_full")
    private String fullName;

    public long getId() {
        return id;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }
}
