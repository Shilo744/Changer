import javax.swing.*;
import java.awt.*;

public class Scene extends JPanel {
    private String currencyToSell;
    private String currencyToBuy;
    private double currentPrice;
    private final JTextField CHOOSE_SELL;
    private final JTextField CHOOSE_BUY;
    private final JTextField AMOUNT;
    private final JTextField RESULT;
    private double input;
    private final Scrapping SCRAPER = new Scrapping();
    private final int SLEEP_TIME = 1000;
    //bottoms info
    private final int NUM_OF_COINS=10;
    private final JButton[] BOTTOMS_LIST_HAVE = new JButton[NUM_OF_COINS];
    private final JButton[] BOTTOMS_LIST_WANT = new JButton[NUM_OF_COINS];

    private boolean visibleBottoms = false;
    private final int BOTTOM_X_1 = 195;
    private final int BOTTOM_X_2 = 640;

    private int bottomY = 140;
    private final int START_BOTTOM_Y = 140;
    private final int BOTTOM_WIDTH = 180;
    private final int BOTTOM_HEIGHT = 35;
    private final String[] CURRENCIES_LIST = {"ILS", "USD", "EUR", "GBP", "AUD","CAD","CHF","FJD","GHS","JPY"};

    //menu bottom
    private final JButton MENU_BOTTOM_1 = new JButton("Chose currency to sell");
    private final JButton MENU_BOTTOM_2 = new JButton("Chose currency to buy");

    private final int START_RED = 80;
    private final int START_GREEN = 80;
    private final int START_BLUE = 200;

    private int blue=200;
    private int red=80;
    private int green = 60;
    private final Color MENU_BOTTOM_COLOR = new Color(START_RED, green, START_BLUE);
    private final int CHANGE_COLOR = 30;
    private final int MAX_COLOR=230;

    //little text fields
    private final int LITTLE_BOTTOMS_WIDTH = 55;
    private final int LITTLE_BOTTOMS_HEIGHT = 25;
    private final int LITTLE_BOTTOMS_TEXT_SIZE = 20;
    private final int LITTLE_TEXT_FIELD_RIGHT = BOTTOM_X_2 - LITTLE_BOTTOMS_WIDTH;
    private final int LITTLE_BOTTOMS_X_LEFT = BOTTOM_X_1 + BOTTOM_WIDTH;
    private final int LITTLE_BOTTOMS_Y_UP = 235;
    private final int LITTLE_BOTTOMS_Y_DOWN = 300;

    //text fields
    private final int TEXT_FIELD_X = 435;
    private final int TEXT_FIELD_WIDTH = 145;
    private final int TEXT_FIELD_HEIGHT = 35;
    private final int AMOUNT_Y = 230;
    private final int RESULT_Y = 295;
    //text field press enter to convent
    private final JTextField PRESS_ENTER_TO_CONVENT = new JTextField();
    private final int CORRECTION_FOR_ENTER = 25;
    //price per unit
    private final JTextField PRICE_PER_UNIT;
    final int CURRENT_PRICE_X = 455;
    final int CURRENT_PRICE_Y = 180;
    final int CURRENT_PRICE_WIDTH = 100;
    final int CURRENT_PRICE_HEIGHT = 35;
    final int NOTHING = 0;
    final int ONE_TO_ONE = 1;
    private final JLabel EXPLAIN_TEXT_PRICE_PER_UNIT = new JLabel("price per unit:");
    private final int CORRECTION = 8;
    private final int TINY_SIZE = 13;
    //title details
    private final int FONT_SIZE = 95;
    private final int TITLE_BOUNDS_X = Window.WINDOW_WIDTH / 6 - 10;
    private final int TITLE_BOUNDS_Y = Window.WINDOW_HEIGHT / 30 - 25;
    private final int TITLE_BOUNDS_WIDTH = 900;
    private final int TITLE_BOUNDS_HEIGHT = 120;
    private final Color TITLE_COLOR = new Color(220, 200, 0);


    public Scene(int x, int y, int width, int height) {
        //panel setup

        this.setBounds(x, y, width, height);
        this.setLayout(null);
        this.setBackground(null);
        this.setDoubleBuffered(true);
        //menu bottom setup
        setMenuBottom(BOTTOMS_LIST_HAVE, MENU_BOTTOM_1, BOTTOM_X_1);
        setMenuBottom(BOTTOMS_LIST_WANT, MENU_BOTTOM_2, BOTTOM_X_2);//MENU_BOTTOM_2,BOTTOM_X_2
        //bottom setup
        bottomsSetup(BOTTOMS_LIST_HAVE, BOTTOM_X_1);//BOTTOM_X_1
        bottomsSetup(BOTTOMS_LIST_WANT, BOTTOM_X_2);//BOTTOM_X_2

        //title setup
        JLabel TITLE = new JLabel("Currency Swap");
        TITLE.setFont(new Font("arial", Font.BOLD, FONT_SIZE));
        TITLE.setBounds(TITLE_BOUNDS_X, TITLE_BOUNDS_Y, TITLE_BOUNDS_WIDTH, TITLE_BOUNDS_HEIGHT);
        TITLE.setForeground(TITLE_COLOR);
        TITLE.setVisible(true);



        //JText setup
        CHOOSE_BUY = littleTextFieldSetup(LITTLE_BOTTOMS_X_LEFT, LITTLE_BOTTOMS_Y_DOWN);
        CHOOSE_SELL = littleTextFieldSetup(LITTLE_BOTTOMS_X_LEFT, LITTLE_BOTTOMS_Y_UP);
        JTextField sell = littleTextFieldSetup(LITTLE_TEXT_FIELD_RIGHT, LITTLE_BOTTOMS_Y_UP);
        sell.setText("SELL");
        sell.setBackground(Color.red);
        JTextField buy = littleTextFieldSetup(LITTLE_TEXT_FIELD_RIGHT, LITTLE_BOTTOMS_Y_DOWN);
        buy.setText("BUY");
        buy.setBackground(Color.green);

        AMOUNT = bigTextField(AMOUNT_Y);

        RESULT = bigTextField(RESULT_Y);
        RESULT.setEnabled(false);

        //current price
        PRICE_PER_UNIT = new JTextField();
        PRICE_PER_UNIT.setBounds(CURRENT_PRICE_X, CURRENT_PRICE_Y, CURRENT_PRICE_WIDTH, CURRENT_PRICE_HEIGHT);
        PRICE_PER_UNIT.setFont(new Font("arial", Font.BOLD, LITTLE_BOTTOMS_TEXT_SIZE));
        PRICE_PER_UNIT.setBackground(Color.white);
        PRICE_PER_UNIT.setDisabledTextColor(Color.BLACK);
        PRICE_PER_UNIT.setEnabled(false);
        //explain text
        EXPLAIN_TEXT_PRICE_PER_UNIT.setBounds(CURRENT_PRICE_X + CORRECTION, CURRENT_PRICE_Y - CURRENT_PRICE_HEIGHT + CORRECTION, CURRENT_PRICE_WIDTH, CURRENT_PRICE_HEIGHT);
        EXPLAIN_TEXT_PRICE_PER_UNIT.setFont(new Font("arial", Font.BOLD, TINY_SIZE));
        EXPLAIN_TEXT_PRICE_PER_UNIT.setForeground(Color.darkGray);
        //press enter convent
        PRESS_ENTER_TO_CONVENT.setBounds(RESULT.getBounds());
        PRESS_ENTER_TO_CONVENT.setLocation(RESULT.getX(), RESULT.getY() + RESULT.getHeight() + CORRECTION_FOR_ENTER);
        PRESS_ENTER_TO_CONVENT.setFont(new Font("arial", Font.BOLD, TINY_SIZE));
        PRESS_ENTER_TO_CONVENT.setDisabledTextColor(Color.BLACK);
        PRESS_ENTER_TO_CONVENT.setBackground(Color.GREEN);
        PRESS_ENTER_TO_CONVENT.setText("press enter to convent");
        PRESS_ENTER_TO_CONVENT.setEnabled(false);

        //backGround setup
        ImageIcon BACK_GROUND = new ImageIcon("C:\\Users\\Shilo\\Desktop\\תכנות\\Changer\\target\\images\\changer.jpg");
        JLabel BACK_GROUND_LABEL = new JLabel(BACK_GROUND);
        BACK_GROUND_LABEL.setSize(width, height);
        BACK_GROUND_LABEL.setBounds(x, y, width, height);
        ///
        ///all adds
        this.add(PRESS_ENTER_TO_CONVENT);
        this.add(EXPLAIN_TEXT_PRICE_PER_UNIT);
        this.add(PRICE_PER_UNIT);
        this.add(RESULT);
        this.add(sell);
        this.add(buy);
        this.add(AMOUNT);
        this.add(CHOOSE_BUY);
        this.add(CHOOSE_SELL);
        this.add(MENU_BOTTOM_1);
        this.add(MENU_BOTTOM_2);
        for (int i = 0; i < BOTTOMS_LIST_HAVE.length; i++) {
            this.add(BOTTOMS_LIST_HAVE[i]);
            this.add(BOTTOMS_LIST_WANT[i]);
        }
        this.add(TITLE);
        this.add(BACK_GROUND_LABEL);
        SCRAPER.start();
        updatePriceField();

    }

    private void bottomsSetup(JButton[] bottoms, int bottomX) {
        for (int i = 0; i < bottoms.length; i++) {
            bottoms[i] = new JButton("" + CURRENCIES_LIST[i]);
            bottoms[i].setBounds(bottomX, bottomY + BOTTOM_HEIGHT, BOTTOM_WIDTH, BOTTOM_HEIGHT);
            bottoms[i].setVisible(visibleBottoms);
            if(green<MAX_COLOR){
            green += CHANGE_COLOR;}
            else if(red<MAX_COLOR){
            red+=CHANGE_COLOR;
            blue-=CHANGE_COLOR;
            }
            bottoms[i].setBackground(new Color(red, green, blue));
            int finalI = i;
            int finalI1 = i;
            int finalI2 = i;
            int finalI3 = i;
            bottoms[i].addActionListener(event -> {
                if (bottomX == BOTTOM_X_1) {
                    currencyToSell = bottoms[finalI].getName();
                    CHOOSE_SELL.setText(currencyToSell);
                    CHOOSE_SELL.setVisible(true);
                    CHOOSE_SELL.setBackground(bottoms[finalI2].getBackground());
                } else {
                    currencyToBuy = bottoms[finalI1].getName();
                    CHOOSE_BUY.setText(currencyToBuy);
                    CHOOSE_BUY.setVisible(true);
                    CHOOSE_BUY.setBackground(bottoms[finalI3].getBackground());
                }
                for (JButton bottom : bottoms) {
                    bottom.setVisible(false);
                }
            });
            bottomY += BOTTOM_HEIGHT;
        }
        green = START_GREEN;
        red = START_RED;
        blue = START_BLUE;
        bottomY = START_BOTTOM_Y;
    }

    private void setMenuBottom(JButton[] bottoms, JButton button, int bottomX) {
        button.setBounds(bottomX, bottomY, BOTTOM_WIDTH, BOTTOM_HEIGHT);
        button.setBackground(MENU_BOTTOM_COLOR);
        button.addActionListener((e) -> {
            //press make bottoms visible/on/off
            visibleBottoms = !visibleBottoms;
            for (int i = 0; i < BOTTOMS_LIST_HAVE.length; i++) {
                bottoms[i].setName(CURRENCIES_LIST[i]);
                bottoms[i].setVisible(!visibleBottoms);
            }
        });
    }

    private JTextField littleTextFieldSetup(int x, int y) {
        JTextField textField = new JTextField();
        textField.setText(currencyToBuy);
        textField.setBounds(x, y, LITTLE_BOTTOMS_WIDTH, LITTLE_BOTTOMS_HEIGHT);
        textField.setFont(new Font("arial", Font.BOLD, LITTLE_BOTTOMS_TEXT_SIZE));
        textField.setForeground(Color.black);
        textField.setBackground(Color.lightGray);
        textField.setEnabled(false);
        textField.setDisabledTextColor(Color.BLACK);
        return textField;
    }

    private JTextField bigTextField(int y) {
        JTextField textField = new JTextField();
        textField.setBounds(TEXT_FIELD_X, y, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        textField.setFont(new Font("arial", Font.BOLD, LITTLE_BOTTOMS_TEXT_SIZE));
        textField.setForeground(Color.black);
        textField.setBackground(Color.white);
        textField.setDisabledTextColor(Color.BLACK);
        textField.addActionListener(e -> {
            try {
                input = Double.parseDouble(AMOUNT.getText());
            } catch (NumberFormatException ignored) {
            }
        });
        return textField;
    }

    private void updatePriceField() {
        new Thread(() -> {
            try {
                while (true) {
                    if (!(currencyToSell == (null) || currencyToBuy == (null))) {
                        if (currencyToBuy.equals(currencyToSell)) {
                            currentPrice = ONE_TO_ONE;
                        }

                        //conditions for sells
                        else if (currencyToSell.equals("USD")) {
                            currentlyBuying(ONE_TO_ONE);}
                        else if (currencyToSell.equals("ILS")) {
                            currentlyBuying(SCRAPER.getUsdToIls());
                        } else if (currencyToSell.equals("EUR")) {
                            currentlyBuying(SCRAPER.getUsdToEur());
                        } else if (currencyToSell.equals("GBP")) {
                            currentlyBuying(SCRAPER.getUsdToGbp());
                        } else if (currencyToSell.equals("AUD")) {
                            currentlyBuying(SCRAPER.getUsdToAud());
                    } else if (currencyToSell.equals("CAD")) {
                        currentlyBuying(SCRAPER.getUsdToCad());
                    } else if (currencyToSell.equals("CHF")) {
                        currentlyBuying(SCRAPER.getUsdToChf());
                    } else if (currencyToSell.equals("FJD")) {
                        currentlyBuying(SCRAPER.getUsdToFjd());
                    } else if (currencyToSell.equals("GHS")) {
                        currentlyBuying(SCRAPER.getUsdToGhs());
                    } else if (currencyToSell.equals("JPY")) {
                        currentlyBuying(SCRAPER.getUsdToJpy());
                        }
                    }
                    if (currentPrice > NOTHING) {
                        PRICE_PER_UNIT.setText(String.valueOf(currentPrice));
                    }
                    setResult();
                    Thread.sleep(SLEEP_TIME);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

    }

    private void currentlyBuying(double exchange) {
        switch (currencyToBuy) {
            case "ILS" -> currentPrice = ONE_TO_ONE / exchange * SCRAPER.getUsdToIls();
            case "GBP" -> currentPrice = ONE_TO_ONE / exchange * SCRAPER.getUsdToGbp();
            case "AUD" -> currentPrice = ONE_TO_ONE / exchange * SCRAPER.getUsdToAud();
            case "USD" -> currentPrice = ONE_TO_ONE / exchange;
            case "EUR" -> currentPrice = ONE_TO_ONE / exchange * SCRAPER.getUsdToEur();
            case "CAD" -> currentPrice = ONE_TO_ONE / exchange * SCRAPER.getUsdToCad();
            case "CHF" -> currentPrice = ONE_TO_ONE / exchange * SCRAPER.getUsdToChf();
            case "FJD" -> currentPrice = ONE_TO_ONE / exchange * SCRAPER.getUsdToFjd();
            case "GHS" -> currentPrice = ONE_TO_ONE / exchange * SCRAPER.getUsdToGhs();
            case "JPY" -> currentPrice = ONE_TO_ONE / exchange * SCRAPER.getUsdToJpy();

        }
    }

    private void setResult() {
        float finalResult = (float) (input / currentPrice);
        if (input > NOTHING && currentPrice > NOTHING) {
            RESULT.setText(String.valueOf(finalResult));
        }
        if (AMOUNT.getText().equals("")) {
            input = NOTHING;
            RESULT.setText(null);
        }
    }
}