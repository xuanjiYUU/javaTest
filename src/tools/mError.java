package tools;

public class mError extends Exception {
    private String message, type;

    public mError(String type, String message) {
        this.message = message;
        this.type = type;
        //此处写log日志
    }

    @Override
    public String getMessage() {
        return type + ":" + message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type+":"+message;
    }
}
