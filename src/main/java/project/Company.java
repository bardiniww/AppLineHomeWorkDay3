package project;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Company {
    private long id;
    private String code;
    @SerializedName("name_full")
    private String fullName;
    @SerializedName("name_short")
    private String shortName;
    private String inn;
    @SerializedName("company_type")
    private CompanyType type;
    private String ogrn;
    @SerializedName("egrul_date")
    private String dateEgrul;
    private Country country;
    @SerializedName("fio_head")
    private String headName;
    private String address;
    private String phone;
    @SerializedName("e_mail")
    private String email;
    @SerializedName("www")
    private String website;
    @SerializedName("is_resident")
    private boolean isResident;
    private ArrayList<Security> securities;

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getInn() {
        return inn;
    }

    public CompanyType getType() {
        return type;
    }

    public String getOgrn() {
        return ogrn;
    }

    public String getDateEgrul() {
        return dateEgrul;
    }

    public Country getCountry() {
        return country;
    }

    public String getHeadName() {
        return headName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public boolean isResident() {
        return isResident;
    }

    public ArrayList<Security> getSecurities() {
        return securities;
    }

    public LocalDate getValidDate() { return LocalDate.parse(dateEgrul, DateTimeFormatter.ofPattern("yyyy-MM-dd")); }
}
