import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Scrapping extends Thread {
    public static double usdToIls;
    public static double usdToEur;
    public static double usdToGbp;
    public static double usdToAud;

    public static double usdToCad;
    public static double usdToChf;
    public static double usdToFjd;
    public static double usdToGhs;
    public static double usdToJpy;

    public static final int THREAD_SLEEP = 1000;

    public void run() {
        while (true) {
        try {
            final String url = "https://il.investing.com/currencies/single-currency-crosses";
                Document data = Jsoup.connect(url).get();
                checkValues(data);
                Thread.sleep(THREAD_SLEEP);
        } catch (Exception e) {
        }}
    }
    public static void checkValues(Document data) {
        try {
            Element board;
            String usdToIlsString;
            String usdToEurString;
            String usdToGbpString;
            String usdToAudString;
            String usdToCadString;
            String usdToChfString;
            String usdToFjdString;
            String usdToGhsString;
            String usdToJpyString;

            board = data.getElementById("leftColumn");
            usdToIlsString = board.getElementsByClass("pid-9723-ask").text();
            usdToEurString = board.getElementsByClass("pid-1-ask").text();
            usdToGbpString = board.getElementsByClass("pid-2-ask").text();
            usdToAudString = board.getElementsByClass("pid-5-high").text();

            usdToCadString = board.getElementsByClass("pid-1538-ask").text();
            usdToChfString = board.getElementsByClass("pid-1560-ask").text();
            usdToFjdString = board.getElementsByClass("pid-1726-ask").text();
            usdToGhsString = board.getElementsByClass("pid-1809-ask").text();
            usdToJpyString = board.getElementsByClass("pid-1910-ask").text();

            usdToIls = Double.parseDouble(usdToIlsString);
            usdToEur = Double.parseDouble(usdToEurString);
            usdToGbp = Double.parseDouble(usdToGbpString);
            usdToAud = Double.parseDouble(usdToAudString);

            usdToCad = Double.parseDouble(usdToCadString);
            usdToChf = Double.parseDouble(usdToChfString);
            usdToFjd = Double.parseDouble(usdToFjdString);
            usdToGhs = Double.parseDouble(usdToGhsString);
            usdToJpy = Double.parseDouble(usdToJpyString);

        } catch (Exception e) {
        }
    }

    public double getUsdToIls() {
        return usdToIls;
    }

    public double getUsdToEur() {
        return usdToEur;
    }

    public double getUsdToGbp() {
        return usdToGbp;
    }

    public double getUsdToAud() {
        return usdToAud;
    }

    public double getUsdToCad() {
        return usdToCad;
    }

    public double getUsdToChf() {
        return usdToChf;
    }

    public double getUsdToFjd() {
        return usdToFjd;
    }

    public double getUsdToGhs() {
        return usdToGhs;
    }

    public double getUsdToJpy() {
        return usdToJpy;
    }
}