package project;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        String filePath = null;
        Gson gson = new Gson();
        ArrayList<Company> companies = new ArrayList<>();

        //fill the name of json
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the path to the file, please.");
            filePath = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //read json
        try {
            companies = gson.fromJson(new FileReader(new File(filePath)), new TypeToken<List<Company>>(){}.getType());
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        //task1
        printShortInfo(companies);
        System.out.println();
        //task2
        printExpiredSecurities(companies);
        System.out.println();
        //task3
        printCompaniesAfterDate(companies);
        //task4
        printTargetedSecurities(companies);

    }

    public static void printShortInfo(ArrayList<Company> companies) {
        if (!companies.isEmpty()) {
            System.out.println("                        ИФОРМАЦИЯ О КОМПАНИЯХ");
            long counter = 1;
            for (Company element : companies) {
                //System.out.println("«" + element.getShortName() + "»" + " - «Дата основания " + element.getValidDate().format(DateTimeFormatter.ofPattern("dd/MM/yy")) + "»");
                //The second type of output
                System.out.format("%5d.  %-35s  «Дата основания %-8s»\n", counter++, element.getShortName(), element.getValidDate().format(DateTimeFormatter.ofPattern("dd/MM/yy")));
            }
        }
        else {
            System.out.println("CПИСОК КОМПАНИЙ ПУСТ");
        }
    }

    public static void printExpiredSecurities(ArrayList<Company> companies) {
        String code;
        LocalDate date;
        String name;
        long counterOfAllExpiredSecurities = 0;

        System.out.println("                        ИФОРМАЦИЯ О ПРОСРОЧЕННЫХ БУМАГАХ");

        for (Company x : companies) {
            ArrayList<Security> temp = x.getSecurities();
            long counterOfCompanyExpiredSecurities = 0;
            System.out.println("КОМПАНИЯ: " + x.getShortName());

            for (Security s : temp) {
                code =  s.getCode();
                date = s.getValidDateTo();
                name = s.getFullName();

                if (date.isBefore(LocalDate.now())) {
                    counterOfAllExpiredSecurities++;
                    counterOfCompanyExpiredSecurities++;
                    System.out.format(counterOfCompanyExpiredSecurities + ". Code: %-20s Expiry date: %-20s Owner name: %s\n", code, date.format(DateTimeFormatter.ofPattern("dd/MM/yy")), name);
                }
            }

            if (counterOfCompanyExpiredSecurities == 1) {
                System.out.println(x.getShortName() + " на данный момент (" +
                        LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yy")) + ") имеет " + counterOfCompanyExpiredSecurities + " просроченную бумагу.\n");
            }
            else if (counterOfCompanyExpiredSecurities >= 2 && counterOfCompanyExpiredSecurities <= 4) {
                System.out.println(x.getShortName() + " на данный момент (" +
                        LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yy")) + ") имеет " + counterOfCompanyExpiredSecurities + " просроченные бумаги.\n");
            }
            else if (counterOfCompanyExpiredSecurities > 4) {
                System.out.println(x.getShortName() + " на данный момент (" +
                        LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yy")) + ") имеет " + counterOfCompanyExpiredSecurities + " просроченных бумаг.\n");
            }
            else {
                System.out.println("Просроченных бумаг нет.\n");
            }
        }

        if (counterOfAllExpiredSecurities > 0) {
            System.out.println("На данный момент (" +
                    LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yy")) + ") всего просроченно " + counterOfAllExpiredSecurities + " бумаг.\n");
        }
    }

    public static void printCompaniesAfterDate(ArrayList<Company> companies) {
        String temp;
        DateValidator dv1 = new DateValidator(DateTimeFormatter.ofPattern("dd.MM.yy"));
        DateValidator dv2= new DateValidator(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        DateValidator dv3 = new DateValidator(DateTimeFormatter.ofPattern("dd/MM/yy"));
        DateValidator dv4 = new DateValidator(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate date = null;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите дату либо \"exit\"");
            while (!(temp = reader.readLine()).equalsIgnoreCase("exit")) {
                if (dv1.isValid(temp)) {
                    date = LocalDate.parse(temp, DateTimeFormatter.ofPattern("dd.MM.yy"));
                    break;
                }
                else if (dv2.isValid(temp)) {
                    date = LocalDate.parse(temp, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    break;
                }
                else if (dv3.isValid(temp)) {
                    date = LocalDate.parse(temp, DateTimeFormatter.ofPattern("dd/MM/yy"));
                    System.out.println("WTF");
                    break;
                }
                else if (dv4.isValid(temp)) {
                    date = LocalDate.parse(temp, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    break;
                }
                else {
                    System.out.println("Неверный формат даты. Попробуйте снова.\n(Ожидаемый формат: дд.мм.гг ; дд.мм.гггг ; дд/мм/гг ; дд/мм/ггг)");
                }
            }
            System.out.println("Компании основанные после " + date + ":");
            for (Company c : companies) {
                if (isFoundedAfter(date, c)) {
                    System.out.println(c.getShortName() + " " + c.getValidDate());
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isFoundedAfter(LocalDate date, Company company) {
        return company.getValidDate().isAfter(date);
    }

    public static void printTargetedSecurities(ArrayList<Company> companies) {
        String temp;
        String code = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Введите код валюты либо \"exit\"");
            while (!(temp = reader.readLine()).equalsIgnoreCase("exit")) {
                if (temp.equalsIgnoreCase("RUB")) {
                    code = temp;
                    break;
                }
                else if (temp.equalsIgnoreCase("EUR") || temp.equalsIgnoreCase("EU")) {
                    code = temp;
                    break;
                }
                else if (temp.equalsIgnoreCase("USD")) {
                    code = temp;
                    break;
                }
                else {
                    System.out.println("Неверный формат валюты. Попробуйте снова.\n(Ожидаемый формат: RUB ; USD ; EUR ; EU)");
                }
            }

            System.out.println("Ценные бумаги использующие " + code.toUpperCase() + ":");
            for (Company c : companies) {
                ArrayList<Security> securities = c.getSecurities();
                for (Security s : securities) {
                    if (doUseCurrency(code, s)) {
                        System.out.format("ID: %-10d\tCODE: %-20s\n" ,s.getId(), s.getCode());
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean doUseCurrency(String code, Security security) {
        return security.getCurrency().getCode().equalsIgnoreCase(code);
    }
}

