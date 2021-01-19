package Pages;

public class SettingPage
{
    String Language;
    String TimeZone;

    public SettingPage(String language, String timeZone) {
        Language = language;
        TimeZone = timeZone;
        System.out.println("Hello @ Setting Page");
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getTimeZone() {
        return TimeZone;
    }

    public void setTimeZone(String timeZone) {
        TimeZone = timeZone;
    }
}
