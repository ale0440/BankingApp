package com.example.studentchestv1001;

public class AppState {
    private static LoginController loginController;

    public static LoginController getLoginController(){
        return loginController;
    }

    public static void setLoginController(LoginController controller) {
        AppState.loginController = controller;
    }
}
