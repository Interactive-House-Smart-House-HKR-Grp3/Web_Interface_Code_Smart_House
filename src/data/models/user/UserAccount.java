package data.models.user;

import data.models.devices.Devices;
import data.models.statistics.StatisticsData;
import data.services.mqtt.MQTTConnectionHandler;
import org.eclipse.paho.client.mqttv3.MqttException;

public class UserAccount {

    public static UserAccount user;

    private String ACCOUNT_NAME;
    private String ACCOUNT_PASSWORD;
    private AccountData accountData;
    private StatisticsData statisticsData;
    private Devices devices;

    private MQTTConnectionHandler mqttConnectionHandler;

    public UserAccount() {
    }

    public UserAccount(String ACCOUNT_NAME, String ACCOUNT_PASSWORD) {
        this.ACCOUNT_NAME = ACCOUNT_NAME;
        this.ACCOUNT_PASSWORD = ACCOUNT_PASSWORD;

        try {
            // establish connection with the broker.
            // use it to subscribe to topics of interest, read what is
            // published, publish whe needed.
            this.mqttConnectionHandler= new MQTTConnectionHandler();
        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        }

        this.accountData = null;
        this.statisticsData = null;
    }

    public static UserAccount getInstance(){
        user = (user == null ? new UserAccount() : user);
        return user;
    }

    public MQTTConnectionHandler getMqttConnectionHandler() {
        return mqttConnectionHandler;
    }

    public void setMqttConnectionHandler(MQTTConnectionHandler mqttConnectionHandler) {
        this.mqttConnectionHandler = mqttConnectionHandler;
    }

    public AccountData getAccountData() {
        return accountData;
    }

    public void setAccountData(AccountData accountData) {
        this.accountData = accountData;
    }

    public StatisticsData getStatisticsData() {
        return statisticsData;
    }

    public void setStatisticsData(StatisticsData statisticsData) {
        this.statisticsData = statisticsData;
    }


    class AccountData{
        private String firstName;
        private String lastName;
        private String address;
        private String phoneNumber;
        private String emailAddress;
        private boolean shareDataAnonymously;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public boolean isShareDataAnonymously() {
            return shareDataAnonymously;
        }

        public void setShareDataAnonymously(boolean shareDataAnonymously) {
            this.shareDataAnonymously = shareDataAnonymously;
        }
    }
}
