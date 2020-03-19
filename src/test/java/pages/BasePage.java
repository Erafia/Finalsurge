package pages;

public abstract class BasePage {
    protected final static String DASHBOARD_URL = "https://log.finalsurge.com/";
    /**
     * Method should not be used on forms opened within a page
     */
    abstract BasePage openPage();
    abstract void isPageOpened();

}
