package com.example.studentchestv1001;

import com.example.studentchestv1001.AppState;
import com.example.studentchestv1001.LoginController;
import com.example.studentchestv1001.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private Statement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    private MainController mainController;

    @Before
    public void setUp() throws Exception {
        mainController = new MainController();
        AppState.setMainController(mainController);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
    }

    @Test
    public void testInit() throws Exception {
        // Arrange
        LoginController mockLoginController = mock(LoginController.class);
        AppState.setLoginController(mockLoginController);
        when(mockLoginController.getId()).thenReturn(1);

        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString(1)).thenReturn("1234567890123456");
        when(mockResultSet.getDouble(2)).thenReturn(1000.0);
        when(mockResultSet.getString(3)).thenReturn("1234567890");

        // Act
        mainController.init();

        // Assert
        assertEquals("****3456", mainController.lblCardNumber.getText());
        assertEquals("1234567890", mainController.phone);
        verify(mockStatement, times(1)).close();
        verify(mockResultSet, times(1)).close();
    }

    @Test
    public void testChangeToAnotherScene() throws IOException {
        ActionEvent mockEvent = mock(ActionEvent.class);
        FXMLLoader mockLoader = mock(FXMLLoader.class);
        Parent mockRoot = mock(Parent.class);
        Stage mockStage = mock(Stage.class);

       // when(mockEvent.getSource()).thenReturn(new Node());
        when(mockLoader.load()).thenReturn(mockRoot);
        when(mockRoot.getScene()).thenReturn(new Scene(mockRoot));
        //when(mockStage.getScene()).thenReturn(new Scene(new Parent()));

        mainController.changeToAnotherScene(mockEvent, "login-view.fxml");

        verify(mockStage, times(1)).setScene(any(Scene.class));
        verify(mockStage, times(1)).show();
    }

}
