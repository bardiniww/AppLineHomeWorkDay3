package project;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Security {
    private long id;
    private String code;
    @SerializedName("name_full")
    private String fullName;
    private String cfi;
    @SerializedName("date_to")
    private String dateTo;
    @SerializedName("state_reg_date")
    private String stateRegDate;
    private State state;
    private Currency currency;

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCfi() {
        return cfi;
    }

    public String getDateTo() {
        return dateTo;
    }

    public String getStateRegDate() {
        return stateRegDate;
    }

    public State getState() {
        return state;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getValidDateTo() { return LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("yyyy-MM-dd")); }
}
