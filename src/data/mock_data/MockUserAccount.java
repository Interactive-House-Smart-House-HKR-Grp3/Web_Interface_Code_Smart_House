package data.mock_data;

public enum MockUserAccount {
    MOCK_USER_1("Test1", "pass1", "Name1", "Email1@web-Interface"),
    MOCK_USER_2("Test2", "pass2", "Name2", "Email2@web-Interface"),
    MOCK_USER_3("Test3", "pass3", "Name3", "Email3@web-Interface"),
    MOCK_USER_4("Test4", "pass4", "Name4", "Email4@web-Interface"),
    MOCK_USER_5("Test5", "pass5", "Name5", "Email5@web-Interface")
    ;

    private String ACCOUNT_NAME;
    private String PASSWORD;
    private String NAME;
    private String EMAIL;

    MockUserAccount(String ACCOUNT_NAME, String PASSWORD, String NAME, String EMAIL) {
        this.ACCOUNT_NAME = ACCOUNT_NAME;
        this.PASSWORD = PASSWORD;
        this.NAME = NAME;
        this.EMAIL = EMAIL;
    }

    public String getNAME() {
        return NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public String getACCOUNT_NAME() {
        return ACCOUNT_NAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public void setACCOUNT_NAME(String ACCOUNT_NAME) {
        this.ACCOUNT_NAME = ACCOUNT_NAME;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
}
