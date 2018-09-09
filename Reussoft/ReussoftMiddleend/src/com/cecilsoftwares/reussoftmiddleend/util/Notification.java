package com.cecilsoftwares.reussoftmiddleend.util;

/**
 * @author IBB
 */
public class Notification {

    /**
     * Dictionary of code variable case code = 10 : The operation of saving
     */
    private int code;
    private String title;
    private String message;
    private String cause;
    private boolean sucess;
    private boolean exitSystemRequest;

    private Notification() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public boolean isExitSystemRequest() {
        return exitSystemRequest;
    }

    public void setExitSystemRequest(boolean exitSystemRequest) {
        this.exitSystemRequest = exitSystemRequest;
    }

    @Override
    public String toString() {
        return "Notification{" + "message=" + message + ", \n\ncause=" + cause + '}';
    }

    public static class NotificationBuilder {

        private final int code;
        private String title;
        private String message;
        private String cause;
        private boolean sucess;
        private boolean exitSystemRequest;

        public NotificationBuilder(int code) {
            this.code = code;
        }

        public NotificationBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public NotificationBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public NotificationBuilder withCause(String cause) {
            this.cause = cause;
            return this;
        }

        public NotificationBuilder withSucess(boolean sucess) {
            this.sucess = sucess;
            return this;
        }

        public NotificationBuilder withExitSystemRequest(boolean exitSystemRequest) {
            this.exitSystemRequest = exitSystemRequest;
            return this;
        }

        public Notification create() {
            Notification notification = new Notification();

            notification.setCode(this.code);
            notification.setTitle(this.title);
            notification.setMessage(this.message);
            notification.setCause(this.cause);
            notification.setSucess(this.sucess);
            notification.setExitSystemRequest(this.exitSystemRequest);

            return notification;
        }

    }

}
