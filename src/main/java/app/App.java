package app;

import base.Account;
import facade.BankFacade;
import facade.Facade;

import java.util.Scanner;

public class App {
    public void RunApp() {
        BankFacade app = new BankFacade();
        Scanner sc = new Scanner(System.in);

        Account gold = null;
        Account deposit = null;
        Account kids = null;
        Account invest = null;

        System.out.println("°❀⋆ Welcome to Kaspi Bank ❀⋆");

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1) Open Base Account");
            System.out.println("2) Open Deposit");
            System.out.println("3) Open card for Kids");
            System.out.println("4) Open Investment");
            System.out.println("5) Deposit to base account");
            System.out.println("6) Add money to deposit");
            System.out.println("7) Deposit to kids account");
            System.out.println("8) Deposit to invest account (invest)");
            System.out.println("9) Withdraw from base card");
            System.out.println("10) Withdraw from deposit card");
            System.out.println("11) Withdraw from kids card");
            System.out.println("12) Withdraw from invest card");
            System.out.println("13) Transfer between my accounts (any → any)");
            System.out.println("14) Pay utility from base card");
            System.out.println("15) Show balances");
            System.out.println("16) Close account");
            System.out.println("17) Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        gold = app.openBaseAccount();
                        break;
                    case "2":
                        deposit = app.openDeposit();
                        break;
                    case "3":
                        kids = app.openKidsAccount();
                        break;
                    case "4":
                        invest = app.openInvestmentAccount();
                        break;

                    case "5":
                        ensure(gold, "Open base account first");
                        app.depositTo(gold, readDouble(sc));
                        break;
                    case "6":
                        ensure(deposit, "Open deposit first");
                        app.depositTo(deposit, readDouble(sc));
                        break;
                    case "7":
                        ensure(kids, "Open kids card first");
                        app.depositTo(kids, readDouble(sc));
                        break;
                    case "8":
                        ensure(invest, "Open investment first");
                        app.investTo(invest, readDouble(sc));
                        break;

                    case "9":
                        ensure(gold, "Open base account first");
                        app.withdrawal(gold, readDouble(sc));
                        break;
                    case "10":
                        ensure(deposit, "Open deposit first");
                        app.withdrawal(deposit, readDouble(sc));
                        break;
                    case "11":
                        ensure(kids, "Open kids card first");
                        app.withdrawal(kids, readDouble(sc));
                        break;
                    case "12":
                        ensure(invest, "Open investment first");
                        app.withdrawal(invest, readDouble(sc));
                        break;

                    case "13":
                        doTransfer(sc, app, gold, deposit, kids, invest);
                        break;

                    case "14": {
                        ensure(gold, "Open base account first");
                        System.out.print("Utility (e.g., electricity/water/internet): ");
                        String util = sc.nextLine().trim();
                        double amt = readDouble(sc);
                        app.payUtility(gold, util, amt);
                        break;
                    }

                    case "15":
                        System.out.println("\n=== BALANCES ===");
                        if (gold != null) { System.out.print("Base: "); app.viewCurrentBalance(gold); }
                        if (deposit != null) { System.out.print("Deposit: "); app.viewCurrentBalance(deposit); }
                        if (kids != null) { System.out.print("Kids: "); app.viewCurrentBalance(kids); }
                        if (invest != null) { System.out.print("Investment: "); app.viewCurrentBalance(invest); }
                        if (gold == null && deposit == null && kids == null && invest == null)
                            System.out.println("No accounts.");
                        break;

                    case "16": {
                        System.out.println("Close which account? 1) Base  2) Deposit  3) Kids  4) Investment  0) Cancel");
                        String c = sc.nextLine().trim();
                        switch (c) {
                            case "1":
                                ensure(gold, "Base not opened");
                                app.closeAccount(gold);
                                gold = null;
                                System.out.println("Base account closed.");
                                break;
                            case "2":
                                ensure(deposit, "Deposit not opened");
                                app.closeAccount(deposit);
                                deposit = null;
                                System.out.println("Deposit closed.");
                                break;
                            case "3":
                                ensure(kids, "Kids not opened");
                                app.closeAccount(kids);
                                kids = null;
                                System.out.println("Kids card closed.");
                                break;
                            case "4":
                                ensure(invest, "Investment not opened");
                                app.closeAccount(invest);
                                invest = null;
                                System.out.println("Investment closed.");
                                break;
                            case "0":
                                System.out.println("Canceled.");
                                break;
                            default:
                                System.out.println("Unknown option.");
                        }
                        break;
                    }

                    case "17":
                        System.out.println("Bye!");
                        return;

                    default:
                        System.out.println("Unknown option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void ensure(Account acc, String msg) {
        if (acc == null) throw new IllegalStateException(msg);
    }

    private static double readDouble(Scanner sc) {
        System.out.print("Amount: ");
        String s = sc.nextLine().trim().replace(',', '.');
        return Double.parseDouble(s);
    }

    private static void doTransfer(Scanner sc, Facade app,
                                   Account gold, Account deposit, Account kids, Account invest) {
        System.out.println("\nChoose FROM which account:");
        Account from = chooseAccount(sc, gold, deposit, kids, invest, "from");

        System.out.println("Choose TO which account:");
        Account to = chooseAccount(sc, gold, deposit, kids, invest, "to");

        if (from == to) throw new IllegalStateException("Source and destination must be different");

        double amt = readDouble(sc);
        app.transfer(from, to, amt);
        System.out.println("Transferred successfully.");
    }

    private static Account chooseAccount(Scanner sc,
                                         Account gold, Account deposit, Account kids, Account invest,
                                         String role) {
        while (true) {
            System.out.println(
                    "1) Base" + (gold == null ? " (not opened)" : "") + "   " +
                            "2) Deposit" + (deposit == null ? " (not opened)" : "") + "   " +
                            "3) Kids" + (kids == null ? " (not opened)" : "") + "   " +
                            "4) Investment" + (invest == null ? " (not opened)" : "")
            );
            System.out.print("Choose account " + role + ": ");
            String c = sc.nextLine().trim();
            switch (c) {
                case "1": ensure(gold, "Base not opened"); return gold;
                case "2": ensure(deposit, "Deposit not opened"); return deposit;
                case "3": ensure(kids, "Kids not opened"); return kids;
                case "4": ensure(invest, "Investment not opened"); return invest;
                default: System.out.println("Unknown option, try again.");
            }
        }
    }
}