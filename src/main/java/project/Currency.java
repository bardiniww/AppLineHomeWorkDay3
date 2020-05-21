package project;

import com.google.gson.annotations.SerializedName;

public class Currency {
    private long id;
    private String code;
    @SerializedName("name_short")
    private String nameShort;
    @SerializedName("name_full")
    private String nameFull;

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getNameShort() {
        return nameShort;
    }

    public String getNameFull() {
        return nameFull;
    }
}
