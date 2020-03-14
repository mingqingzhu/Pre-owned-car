package sample;

import bean.ViewBean;
import com.google.gson.Gson;
import utils.CraiglistSearch;
import utils.SaveFile;
import utils.TimesSort;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class Menu {

    public Scanner reader = null;


    public void mainMenu() {

        while (true) {
            System.out.println("MAIN MENU");
            System.out.println("1. Search For Listings");
            System.out.println("2. Exit Car Search");
            System.out.print("Select an option:");
            reader = new Scanner(System.in);
            int option = reader.nextInt();
            switch (option) {
                case 1:
                    searchMenu();
                    break;
                case 2:
                    System.out.print("Bye! See you next time.");
                    System.exit(0);
                    break;
            }
        }
    }

    private void searchMenu() {
        System.out.println("");
        System.out.println("Search For Listings");
        System.out.println("What car do you want to search?");
        System.out.print("Enter car name(Example: benz):");
        reader = new Scanner(System.in);
        String title = reader.nextLine();
        System.out.print("Price range?(Example: 5000-8000): $");
        int minPrice = 0;
        int maxPrice = 0;
        try {
            String price = reader.nextLine();
            minPrice = Integer.parseInt(price.substring(0, price.indexOf("-")));
            maxPrice = Integer.parseInt(price.substring(price.indexOf("-") + 1, price.length()));
        } catch (StringIndexOutOfBoundsException e) {
        }
        System.out.print("Year range?(Example: 2016): ");
        int minAutoYear = 0;
        int maxAutoYear = 0;
        try {
            String year = reader.nextLine();
            minAutoYear = Integer.parseInt(year.substring(0, year.indexOf("-")));
            maxAutoYear = Integer.parseInt(year.substring(year.indexOf("-") + 1, year.length()));
        } catch (StringIndexOutOfBoundsException e) {
        }
        CraiglistSearch craiglistSearch = new CraiglistSearch();
        ArrayList<ViewBean> list = craiglistSearch.search(title, minPrice, maxPrice, minAutoYear, maxAutoYear);

        if (list != null) {
            listMenu(list);
        }
    }

    private void listMenu(ArrayList<ViewBean> list) {
        System.out.println("");
        System.out.println("Sorting Choose");
        System.out.println("1.Newest");
        System.out.println("2.Oldest");
        System.out.print("Enter your choose: ");
        reader = new Scanner(System.in);
        int choose = reader.nextInt();
        switch (choose) {
            case 1:
                try {
                    list = TimesSort.dataCompare(list, true);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                showMenu(list);
                break;
            case 2:
                try {
                    list = TimesSort.dataCompare(list, false);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                showMenu(list);
                break;
        }
    }

    private void showMenu(ArrayList<ViewBean> list) {
        boolean isloop = true;
        int lenth = 0;
        while (isloop) {
            for (int i = 1; i <= 5; i++) {
                if (lenth <= list.size()) {
                    ViewBean bean = list.get(lenth);
                    String str = String.format("%d.\nTitle: %s\nPrice: %s\nLink: %s\nDate: %s\n", i, bean.getTitle(), bean.getPrice(), bean.getLink(), bean.getData());
                    System.out.println(str);
                    lenth++;
                }
            }
            System.out.println("1. Save cars to location.");
            System.out.println("2. Next 5 cars ");
            System.out.println("3. Return MainMenu ");
            System.out.print("Enter your choose: ");
            reader = new Scanner(System.in);
            int choose = reader.nextInt();
            switch (choose) {
                case 1:
                    SaveCar(list);
                    System.out.println("1. Next 5 cars ");
                    System.out.println("2. Return MainMenu ");
                    System.out.print("Enter your choose: ");
                    reader = new Scanner(System.in);
                    int chooses = reader.nextInt();
                    switch (chooses) {
                        case 1:
                            break;
                        case 2:
                            return;
                    }
                    break;
                case 2:
                    break;
                case 3:
                    isloop = false;
                    System.out.println("");
                    return;
            }
        }
    }

    private void SaveCar(ArrayList<ViewBean> list) {
        System.out.println("");
        Gson gson = new Gson();
        new SaveFile().writeFile(gson.toJson(list));
        System.out.println("Save car list successed!!");
        System.out.println("");
    }

}
