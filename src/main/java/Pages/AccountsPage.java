package Pages;

public class AccountsPage {
    String AccountName;
    String AccountID;

    public AccountsPage(String AID, String AName) {
        System.out.println("Hello, This is the first branch on merging startegy ");
        AccountID = AID;
        AccountName = AName;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getAccountID() {
        return AccountID;
    }

    public void setAccountID(String accountID) {
        AccountID = accountID;
    }


}
