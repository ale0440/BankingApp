package com.example.studentchestv1001;

public class AppState {
    private static com.example.studentchestv1001.LoginController loginController;
    private static com.example.studentchestv1001.MainController mainController;
    private static com.example.studentchestv1001.CardSettingsController cardSettingsController;

    public static com.example.studentchestv1001.LoginController getLoginController() {
        return loginController;
    }

    public static com.example.studentchestv1001.MainController getMainController() {
        return mainController;
    }

    public static com.example.studentchestv1001.CardSettingsController getCardSettingsController() {
        return cardSettingsController;
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
}
