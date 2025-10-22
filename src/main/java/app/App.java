package app;

import base.Account;
import facade.BankFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public void RunApp() {
        BankFacade app = new BankFacade();
        Scanner sc = new Scanner(System.in);

        List<Account> base = new ArrayList<>();
        List<Account> deposits = new ArrayList<>();
        List<Account> kids = new ArrayList<>();
        List<Account> invests = new ArrayList<>();

        System.out.println("°❀⋆ Welcome to Bank ❀⋆");
        boolean menuVisible = true;
        printMenu();

        while (true) {
            if (!menuVisible) System.out.print("\nEnter choice (m = menu, 17 = exit): ");
            else System.out.print("Choose: ");

            String choice = sc.nextLine().trim().toLowerCase();

            try {
                if ("m".equals(choice)) {
                    printMenu();
                    menuVisible = true;
                    continue;
                }

                switch (choice) {
                    case "1": {
                        Account a = app.openBaseAccount();
                        base.add(a);
                        System.out.println("Opened Base account #" + indexOfLast(base));
                        break;
                    }
                    case "2": {
                        Account a = app.openDeposit();
                        deposits.add(a);
                        System.out.println("Opened Deposit #" + indexOfLast(deposits));
                        break;
                    }
                    case "3": {
                        Account a = app.openKidsAccount();
                        kids.add(a);
                        System.out.println("Opened Kids card #" + indexOfLast(kids));
                        break;
                    }
                    case "4": {
                        Account a = app.openInvestmentAccount();
                        invests.add(a);
                        System.out.println("Opened Investment #" + indexOfLast(invests));
                        break;
                    }
                    case "5": {
                        ensureNotEmpty(base, "Open at least one base account first");
                        Account a = chooseFromList(sc, base, "base");
                        app.depositTo(a, readDouble(sc));
                        break;
                    }
                    case "6": {
                        ensureNotEmpty(deposits, "Open at least one deposit first");
                        Account a = chooseFromList(sc, deposits, "deposit");
                        app.depositTo(a, readDouble(sc));
                        break;
                    }
                    case "7": {
                        ensureNotEmpty(kids, "Open at least one kids card first");
                        Account a = chooseFromList(sc, kids, "kids");
                        app.depositTo(a, readDouble(sc));
                        break;
                    }
                    case "8": {
                        ensureNotEmpty(invests, "Open at least one investment first");
                        Account a = chooseFromList(sc, invests, "investment");
                        app.investTo(a, readDouble(sc));
                        break;
                    }
                    case "9": {
                        ensureNotEmpty(base, "Open at least one base account first");
                        Account a = chooseFromList(sc, base, "base");
                        app.withdrawal(a, readDouble(sc));
                        break;
                    }
                    case "10": {
                        ensureNotEmpty(deposits, "Open at least one deposit first");
                        Account a = chooseFromList(sc, deposits, "deposit");
                        app.withdrawal(a, readDouble(sc));
                        break;
                    }
                    case "11": {
                        ensureNotEmpty(kids, "Open at least one kids card first");
                        Account a = chooseFromList(sc, kids, "kids");
                        app.withdrawal(a, readDouble(sc));
                        break;
                    }
                    case "12": {
                        ensureNotEmpty(invests, "Open at least one investment first");
                        Account a = chooseFromList(sc, invests, "investment");
                        app.withdrawal(a, readDouble(sc));
                        break;
                    }
                    case "13": {
                        doTransfer(sc, app, base, deposits, kids, invests);
                        break;
                    }
                    case "14": {
                        ensureNotEmpty(base, "Open at least one base account first");
                        Account a = chooseFromList(sc, base, "base");
                        System.out.print("Utility (e.g., electricity/water/internet): ");
                        String util = sc.nextLine().trim();
                        double amt = readDouble(sc);
                        app.payUtility(a, util, amt);
                        break;
                    }
                    case "15": {
                        System.out.println("\n=== BALANCES ===");
                        printBalances("Base", base, app);
                        printBalances("Deposit", deposits, app);
                        printBalances("Kids", kids, app);
                        printBalances("Investment", invests, app);
                        if (base.isEmpty() && deposits.isEmpty() && kids.isEmpty() && invests.isEmpty())
                            System.out.println("No accounts.");
                        break;
                    }
                    case "16": {
                        System.out.println("Close which type? 1) Base  2) Deposit  3) Kids  4) Investment  0) Cancel");
                        String t = sc.nextLine().trim();
                        switch (t) {
                            case "1": closeOne(sc, app, base, "base"); break;
                            case "2": closeOne(sc, app, deposits, "deposit"); break;
                            case "3": closeOne(sc, app, kids, "kids"); break;
                            case "4": closeOne(sc, app, invests, "investment"); break;
                            case "0": System.out.println("Canceled."); break;
                            default: System.out.println("Unknown option.");
                        }
                        break;
                    }
                    case "17":
                        System.out.println("Bye!");
                        return;
                    default:
                        System.out.println("Unknown option. Press 'm' to see menu.");
                }
                menuVisible = false;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                menuVisible = false;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1) Open Base Account");
        System.out.println("2) Open Deposit");
        System.out.println("3) Open card for Kids");
        System.out.println("4) Open Investment");
        System.out.println("5) Add money to base account");
        System.out.println("6) Add money to deposit");
        System.out.println("7) Add money to kids account");
        System.out.println("8) Add money to invest account");
        System.out.println("9) Withdraw from base card");
        System.out.println("10) Withdraw from deposit card");
        System.out.println("11) Withdraw from kids card");
        System.out.println("12) Withdraw from invest card");
        System.out.println("13) Transfer money between accounts");
        System.out.println("14) Pay utility from base card");
        System.out.println("15) Show balances");
        System.out.println("16) Close account");
        System.out.println("17) Exit");
        System.out.println("(press 'm' anytime to show this menu)");
    }

    private static int indexOfLast(List<Account> list) { return list.size() - 1; }
    private static void ensureNotEmpty(List<Account> list, String msg) {
        if (list.isEmpty()) throw new IllegalStateException(msg);
    }
    private static double readDouble(Scanner sc) {
        System.out.print("Amount: ");
        String s = sc.nextLine().trim().replace(',', '.');
        return Double.parseDouble(s);
    }
    private static Account chooseFromList(Scanner sc, List<Account> list, String label) {
        while (true) {
            System.out.println("\nChoose " + label + " account:");
            for (int i = 0; i < list.size(); i++) System.out.println(i + ") " + label + " #" + i);
            System.out.print("Index: ");
            String c = sc.nextLine().trim();
            try {
                int idx = Integer.parseInt(c);
                if (idx >= 0 && idx < list.size()) return list.get(idx);
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid index, try again.");
        }
    }
    private static void printBalances(String title, List<Account> list, BankFacade app) {
        if (list.isEmpty()) return;
        System.out.println(title + " accounts:");
        for (int i = 0; i < list.size(); i++) {
            double bal = app.viewCurrentBalance(list.get(i));
            System.out.println("  #" + i + " -> " + bal);
        }
    }
    private static void closeOne(Scanner sc, BankFacade app, List<Account> list, String label) {
        ensureNotEmpty(list, "No " + label + " accounts opened");
        Account a = chooseFromList(sc, list, label);
        app.closeAccount(a);
        int removedIdx = list.indexOf(a);
        list.remove(a);
        System.out.println(cap(label) + " account #" + removedIdx + " closed.");
    }
    private static String cap(String s) { return s.isEmpty() ? s : Character.toUpperCase(s.charAt(0)) + s.substring(1); }

    private static void doTransfer(Scanner sc, BankFacade app,
                                   List<Account> golds, List<Account> deposits,
                                   List<Account> kids, List<Account> invests) {
        List<Account> all = new ArrayList<>();
        List<String> names = new ArrayList<>();
        addLabeled(all, names, golds, "Base");
        addLabeled(all, names, deposits, "Deposit");
        addLabeled(all, names, kids, "Kids");
        addLabeled(all, names, invests, "Investment");
        if (all.isEmpty()) throw new IllegalStateException("You have no accounts to transfer.");

        System.out.println("\nChoose FROM which account:");
        int fromIdx = chooseAny(sc, names);
        System.out.println("Choose TO which account:");
        int toIdx = chooseAny(sc, names);
        if (fromIdx == toIdx) throw new IllegalStateException("Source and destination must be different");

        double amt = readDouble(sc);
        app.transfer(all.get(fromIdx), all.get(toIdx), amt);
        System.out.println("Transferred successfully.");
    }
    private static void addLabeled(List<Account> all, List<String> names, List<Account> src, String label) {
        for (int i = 0; i < src.size(); i++) { all.add(src.get(i)); names.add(label + " #" + i); }
    }
    private static int chooseAny(Scanner sc, List<String> names) {
        while (true) {
            for (int i = 0; i < names.size(); i++) System.out.println(i + ") " + names.get(i));
            System.out.print("Index: ");
            String c = sc.nextLine().trim();
            try {
                int idx = Integer.parseInt(c);
                if (idx >= 0 && idx < names.size()) return idx;
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid index, try again.");
        }
    }
}
