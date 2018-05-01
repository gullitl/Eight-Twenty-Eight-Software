package com.cecilsystems.mistersoftfrontend.useful;

import javafx.geometry.Pos;
import javafx.util.Duration;

/**
 * @author Plamedi L. Lusembo
 */
public class NotifierPigeon {

    private Notification.Notifier notifier;
    private Notification notification;
    private static NotifierPigeon uniqueInstance;

    public NotifierPigeon() {
    }

    public static synchronized NotifierPigeon getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new NotifierPigeon();
        }
        return uniqueInstance;
    }

    public void notificaSucesso(String texto) {
        notification = NotificationBuilder.create().title("Sucesso")
                .message(texto).image(Notification.SUCCESS_ICON).build();
        notifica();
    }

    public void notificaInformacao(String mensagem) {
        notification = NotificationBuilder.create().title("informação")
                .message(mensagem).image(Notification.INFO_ICON).build();
        notifica();
    }

    public void notificaAdvertencia(String mensagem) {
        notification = NotificationBuilder.create().title("Advertência")
                .message(mensagem).image(Notification.WARNING_ICON).build();
        notifica();
    }

    public void notificaErro(String message) {
        notification = NotificationBuilder.create().title("Erro")
                .message(message).image(Notification.ERROR_ICON).build();
        notifica();
    }

    private void notifica() {
        notifier = NotifierBuilder.create()
                .popupLocation(Pos.CENTER)
                .popupLifeTime(Duration.millis(25))
                .build();

        notifier.notify(notification);
    }

}
