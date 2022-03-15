import javax.security.auth.login.AccountLockedException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;


public class TransverAnother {

    private int numberOfTransfer;
    private int amount;
    private String date;
    private Person recipient;
    private Person sender;
    public static int counter;
    public static int pinCounter = 0;
    private static final long INPUT_BLOCKING_TIME_MILLIS = 5000L;

    public static TransverAnother get(Scanner scanner) throws DontCorrentPinCodeExeption, InterruptedException {

        TransverAnother obj = new TransverAnother(0, 0, "", new Person("", "", "", new HashMap<Integer, Double>(), 000), new Person("", "", "", new HashMap<Integer, Double>(), 000), 0);
        System.out.println("enter info Recipient ");
        obj.recipient = Person.get(scanner);
        System.out.println("enter info Sender ");
        obj.sender = Person.get(scanner);


        //
        InputBlocking blocking = new InputBlocking(INPUT_BLOCKING_TIME_MILLIS);

        System.out.println("ENTER PIN CODE");

        int tryCounter = 0;
        while (true) {
            try {
                int pin = scanner.nextInt();
                if (blocking.checkBlocking()) {
                    try {
                        showBlockingTimeLeft(blocking);
                    } catch (AccountLockedException accountLockedEx) {
                        System.out.println(accountLockedEx.getMessage());
                    }
                } else {
                    checkPin(pin);
                    break;
                }
            } catch (DontCorrentPinCodeExeption ex) {
                System.out.println(ex.getMessage());
                try {
                    checkPinInputTryCount(++tryCounter);
                } catch (BlockAccException blockAcException) {
                    System.out.println(blockAcException.getMessage());
                    blocking.start();
                    tryCounter = 0;
                }
            }


            System.out.println("test");

        }


while(true) {
    System.out.println("enter amount :");
    obj.amount = scanner.nextInt();

    if (obj.amount % 100 != 0) {
        System.out.println("Не кратно 100");
    }

    if (obj.amount % 100 == 0) {
        System.out.println("Кратно 100");
        break;
    }
}
        System.out.println(obj.amount);
        System.out.println(obj.sender.getAccs().get(1));
        System.out.println(obj.getSender().getAccs().get(1));

if(obj.sender.getAccs().get(1)>= obj.amount){
    Double buff = obj.sender.getAccs().get(1) - obj.amount;
    obj.sender.getAccs().put(1,buff);
    Double buff1 = obj.recipient.getAccs().get(1) + obj.amount;
    obj.recipient.getAccs().put(1,buff1);

} else{
    System.out.println("no money!");
}
        obj.numberOfTransfer = TransverAnother.counter;
        System.out.println("transaction number :" + obj.numberOfTransfer);
        TransverAnother.counter++;
        Date currentDate = new Date();
        SimpleDateFormat dateFormat =null;
        dateFormat = new SimpleDateFormat();

        obj.date = dateFormat.format(currentDate);

        System.out.println("date transaction :" + obj.date);


        return obj;

    }
//////////
private static void showBlockingTimeLeft(InputBlocking blocking) throws AccountLockedException {
    throw new AccountLockedException("CHALIT TEBE: " + blocking.getSecondsCountToEnd() + "s.");
}

    private static void checkPin(int pin) throws DontCorrentPinCodeExeption {
        if (pin != getCorrectPin()) {
            throw new DontCorrentPinCodeExeption("HUITU NE PISHI");
        } else {
            System.out.println("EBAT TI KRASAVA");
        }
    }

    private static void checkPinInputTryCount(int currentTry) {
        if (currentTry == 4) {
            throw new BlockAccException("TI V BAN");
        }
    }

    private static int getCorrectPin() {
        return Person.getPin();

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public TransverAnother(int numberOfTransfer, int amount, String date, Person recipient, Person sender, int i) {
        this.numberOfTransfer = numberOfTransfer;
        this.amount = amount;
        this.date = date;
        this.recipient = recipient;
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "TransverAnother{" +
            "numberOfTransfer=" + numberOfTransfer +
            ", amount=" + amount +
            ", date='" + date + '\'' +
            ", recipient=" + recipient +
            ", sender=" + sender +
            '}';
    }

    public int getNumberOfTransfer() {
        return numberOfTransfer;
    }

    public void setNumberOfTransfer(int numberOfTransfer) {
        this.numberOfTransfer = numberOfTransfer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Person getRecipient() {
        return recipient;
    }

    public void setRecipient(Person recipient) {
        this.recipient = recipient;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        TransverAnother.counter = counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransverAnother that = (TransverAnother) o;
        return numberOfTransfer == that.numberOfTransfer && amount == that.amount && Objects.equals(date, that.date) && Objects.equals(recipient, that.recipient) && Objects.equals(sender, that.sender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfTransfer, amount, date, recipient, sender);
    }
}


