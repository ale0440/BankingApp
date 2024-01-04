package com.example.studentchestv1001;

public class AppState {
    private static com.example.studentchestv1001.LoginController loginController;
    private static com.example.studentchestv1001.MainController mainController;
    private static com.example.studentchestv1001.CardSettingsController cardSettingsController;
    private static com.example.studentchestv1001.TransferController transferController;
    private static com.example.studentchestv1001.SendMoneyController sendMoneyController;

    public static com.example.studentchestv1001.LoginController getLoginController() {
        return loginController;
    }

    public static com.example.studentchestv1001.MainController getMainController() {
        return mainController;
    }

    public static com.example.studentchestv1001.CardSettingsController getCardSettingsController() {
        return cardSettingsController;
    }

    public static com.example.studentchestv1001.TransferController getTransferController() {
        return transferController;
    }

    public static com.example.studentchestv1001.SendMoneyController getSendMoneyController() {
        return sendMoneyController;
    }

    public static void setLoginController(com.example.studentchestv1001.LoginController controller) {
        AppState.loginController = controller;
    }

    public static void setMainController(com.example.studentchestv1001.MainController controller) {
        AppState.mainController = controller;
    }

    public static void setCardSettingsController(com.example.studentchestv1001.CardSettingsController controller) {
        AppState.cardSettingsController = controller;
    }

    public static void setTransferController(com.example.studentchestv1001.TransferController transferController) {
        AppState.transferController = transferController;
    }

    public static void setSendMoneyController(com.example.studentchestv1001.SendMoneyController sendMoneyController) {
        AppState.sendMoneyController = sendMoneyController;
    }
}
