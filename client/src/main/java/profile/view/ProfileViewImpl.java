package profile.view;

import javafx.scene.control.*;
import profile.presenter.ProfilePresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.TextFlow;

/**
 * Created by Slothan/Dennis Lindt on 25.06.2017.
 */
public class ProfileViewImpl extends GridPane implements ProfileView {
    private ProfilePresenter profilePresenter;
    private Label usernameLabel;
    private Label emailLabel;
    private Label newLabel;
    private Label repeatPWLabel;
    private TextField emailInput;
    private Label passwordLabel;
    private PasswordField passwordInput;
    private PasswordField passwordInput2;
    private Button confirm;
    private Label userFeedback;
    // Zur Unterscheidung ob E-Mail oder Passwort geändert werden soll
    private Integer credType = 0;


    public ProfileViewImpl(ProfilePresenter profilePresenter) {
        this.profilePresenter = profilePresenter;

        buildProfile();
    }

    public void buildProfile() {
        GridPane grid = this;
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(5));

        this.usernameLabel = new Label();
        this.emailLabel = new Label();
        this.newLabel= new Label();
        this.repeatPWLabel = new Label("Passwort wiederholen: ");
        this.emailInput = new TextField();
        this.passwordLabel = new Label("Passwort: ");
        this.passwordInput = new PasswordField();
        this.passwordInput2 = new PasswordField();
        this.userFeedback = new Label();
        this.confirm = new Button("Bestätigen");

        Button changeEmailButton = new Button("E-Mail ändern");
        changeEmailButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                passwordInput.setVisible(false);
                passwordInput2.setVisible(false);
                repeatPWLabel.setVisible(false);
                newLabel.setText("Neue E-Mail: ");
                newLabel.setVisible(true);
                emailInput.setVisible(true);
                credType = 1;
                confirm.setVisible(true);
            }
        });

        Button changePasswordButton = new Button("Passwort ändern");
        changePasswordButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                emailInput.setVisible(false);
                newLabel.setText("Neues Passwort: ");
                newLabel.setVisible(true);
                repeatPWLabel.setVisible(true);
                passwordInput.setVisible(true);
                passwordInput2.setVisible(true);
                credType = 2;
                confirm.setVisible(true);
            }
        });

        confirm.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(credType == 1) {
                    String email = emailInput.getText();
                    profilePresenter.sendChangeRequest(email, credType);
                } else {
                    String password = passwordInput.getText();
                    String confirmPassword = passwordInput2.getText();
                    if(password.length() < 8) {
                        userFeedback.setText("Passwort muss mindestens 8 Zeichen lang sein.");
                    } else {
                        if(password.equals(confirmPassword)) {
                            profilePresenter.sendChangeRequest(password, credType);
                        } else {
                            userFeedback.setText("Passwort stimmt nicht überein!");
                        }
                    }
                }
                passwordInput.setText("");
                passwordInput2.setText("");
                emailInput.setText("");
            }
        });

        //Labels
        grid.add(usernameLabel, 1, 1);
        grid.add(emailLabel, 1, 2);
        grid.add(newLabel, 1, 4);
        grid.add(repeatPWLabel, 1,5);
        grid.add(userFeedback, 1, 7);
        //Inputs
        grid.add(emailInput, 2,4);
        grid.add(passwordInput,2,4);
        grid.add(passwordInput2,2,5);
        //Buttons
        grid.add(changeEmailButton, 1, 3);
        grid.add(changePasswordButton,2,3);
        grid.add(confirm, 1, 8);



        //Eingabe-Felder sind anfangs unsichtbar
        newLabel.setVisible(false);
        repeatPWLabel.setVisible(false);
        emailInput.setVisible(false);
        passwordInput.setVisible(false);
        passwordInput2.setVisible(false);
        confirm.setVisible(false);
    }

    public Label getUsernameLabel() {
        return this.usernameLabel;
    }
    public Label getEmailLabel() {return this.emailLabel; }
    public TextField getEmailInputField() { return this.emailInput; }
    public void updateStatusLabel(String msg) { userFeedback.setText(msg);}

}
