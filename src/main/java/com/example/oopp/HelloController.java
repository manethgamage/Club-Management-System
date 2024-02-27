package com.example.oopp;
import com.example.oopp.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.sql.*;
import java.util.List;

import static com.example.oopp.ClubAdvisorController.getTeacherFromDatabase;


public class HelloController {

    @FXML
    private AnchorPane advisor_signin_form;

    @FXML
    private AnchorPane advisor_signup_form;

    @FXML
    private Button mainAdvisorButton;

    @FXML
    private Button mainStudentButton;

    @FXML
    private AnchorPane selection_MainMenu;

    @FXML
    private TextField signinStudentIdTextField;

    @FXML
    private PasswordField signinStudentPasswordField;

    @FXML
    private TextField signinTeacherIdTextField;

    @FXML
    private PasswordField signinTeacherPasswordField;

    @FXML
    private PasswordField signupStudentConfirmPasswordField;

    @FXML
    private TextField signupStudentIdTextField;

    @FXML
    private TextField signupStudentNameTextField;

    @FXML
    private PasswordField signupStudentPasswordField;

    @FXML
    private PasswordField signupTeacherConfirmPasswordField;

    @FXML
    private TextField signupTeacherIdTextField;

    @FXML
    private TextField signupTeacherNameTextField;

    @FXML
    private PasswordField signupTeacherPasswordField;

    @FXML
    private Button studentSigninButton;

    @FXML
    private Hyperlink studentSigninLink;

    @FXML
    private Button studentSignupButton;

    @FXML
    private Hyperlink studentSignupLink;

    @FXML
    private AnchorPane student_signin_form;

    @FXML
    private AnchorPane student_signup_form;

    @FXML
    private Button teacherSigninButton;

    @FXML
    private Hyperlink teacherSigninLink;

    @FXML
    private Button teacherSignupButton;

    @FXML
    private Hyperlink teacherSignupLink;

    @FXML
    private Label studentSigninMessageLabel;

    @FXML
    private Label teacherSigninMessageLabel;

    @FXML
    private Button studentSigninBackButton;

    @FXML
    private Button teacherSigninBackButton;

    @FXML
    private Button studentSignupBackButton;

    @FXML
    private Button teacherSignupBackButton;


    // method for showing and hiding anchor panes . also used in other classes
    public static void toggleVisibility(AnchorPane pane, boolean isVisible) {
        pane.setVisible(isVisible);
    }

    //show a desired default pane when launch the application
    public void showDefaultPane() {
        // Show default AnchorPane
        toggleVisibility(selection_MainMenu, true);
        // Hide other AnchorPanes
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, false);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, false);
    }

    //-------------------------------------pane switching---------------------------------------------------------------

    //start menu student section

    public void mainStudentButtonOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, false);
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, true);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, false);
    }

    //start menu club advisor section
    public void mainAdvisorButtonOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, false);
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, false);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, true);
    }

    public void studentSigninBackButtonOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, true);
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, false);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, false);
    }

    public void teacherSigninBackButtonOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, true);
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, false);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, false);
    }

    public void studentSignupBackButtonOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, false);
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, true);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, false);
    }

    public void teacherSignupBackButtonOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, false);
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, false);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, true);
    }

    public void studentSigninLinkOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, false);
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, true);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, false);
    }

    public void studentSignupLinkOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, false);
        toggleVisibility(student_signup_form, true);
        toggleVisibility(student_signin_form, false);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, false);
    }

    public void teacherSigninLinkOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, false);
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, false);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, true);
    }

    public void teacherSignupLinkOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, false);
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, false);
        toggleVisibility(advisor_signup_form, true);
        toggleVisibility(advisor_signin_form, false);
    }


    // ---------------------------------------user signin and registration ---------------------------------------------


    // alert dialog for student and club advisor signup

    private void showAlertError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    static void showAlertSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // -----------------------------------------------------------------------------------------------------------------

    // student signup
    StudentController studentController = new StudentController();

    public void studentSignupButtonOnAction(){
        //get user inputs from text fields and password fields

        String studentId = signupStudentIdTextField.getText();
        String studentName = signupStudentNameTextField.getText();
        String studentPassword = signupStudentPasswordField.getText();
        String studentConfirmPassword = signupStudentConfirmPasswordField.getText();

        // Validate student ID
        if (!InputValidations.validateId(studentId)) {
            showAlertError("Invalid student ID. Enter a valid one.");

            // Clear all text and password fields
            signupStudentIdTextField.clear();
            signupStudentNameTextField.clear();
            signupStudentPasswordField.clear();
            signupStudentConfirmPasswordField.clear();

            //Set focus back to student ID text field
            signupStudentIdTextField.requestFocus();
            return;
        }

        // Validate password and confirmPassword
        if (!InputValidations.arePasswordsEqual(studentPassword, studentConfirmPassword)) {
            showAlertError("Passwords do not match.Please enter same password");

            // Clear both password fields
            signupStudentPasswordField.clear();
            signupStudentConfirmPasswordField.clear();

            //set focus back to password field
            signupStudentPasswordField.requestFocus();
            return;
        }

        //get registered student ID
        studentController.signedInStudentId = studentId;
        // creating the object of the student
        Student student = new Student(studentId, studentName, studentPassword);

        //Insert user data into database
        StudentController.insertStudent(student);
    }

    // club advisor signup

    public void teacherSignupButtonOnAction(){
        //get user inputs from text fields and password fields

        String teacherId = signupTeacherIdTextField.getText();
        String teacherName = signupTeacherNameTextField.getText();
        String teacherPassword = signupTeacherPasswordField.getText();
        String teacherConfirmPassword = signupTeacherConfirmPasswordField.getText();

        // Validate teacher ID
        if (!InputValidations.validateId(teacherId)) {
            showAlertError("Invalid teacher ID. Enter a valid one.");

            // Clear all text and password fields
            signupTeacherIdTextField.clear();
            signupTeacherNameTextField.clear();
            signupTeacherPasswordField.clear();
            signupTeacherConfirmPasswordField.clear();

            //Set focus back to teacher ID text field
            signupTeacherIdTextField.requestFocus();
            return;
        }

        // Validate password and confirmPassword
        if (!InputValidations.arePasswordsEqual(teacherPassword, teacherConfirmPassword)) {
            showAlertError("Passwords do not match.Please enter same password");

            // Clear both password fields
            signupTeacherPasswordField.clear();
            signupTeacherConfirmPasswordField.clear();

            //set focus back to password field
            signupTeacherPasswordField.requestFocus();
            return;
        }

        //creating the object of the club advisor
        ClubAdvisor clubAdvisor = new ClubAdvisor(teacherId, teacherName, teacherPassword);

        //Insert user data into database
        ClubAdvisorController.insertTeacher(clubAdvisor);
    }

    //------------------------------------------------------------------------------------------------------------------

    // student signin
    static String signInStudentId;
    public void studentSigninButtonOnAction(ActionEvent event){
        // Get student inputs from text fields and password field
        signInStudentId = signinStudentIdTextField.getText();
        String signinPassword = signinStudentPasswordField.getText();

        // Validate student ID
        if (!InputValidations.validateId(signInStudentId)) {
            showAlertError("Invalid student ID. Enter a valid one.");
            signinStudentIdTextField.clear();
            signinStudentIdTextField.requestFocus();
            return;
        }

        // Retrieve data from the database using student ID
        Student student = studentController.getStudentFromDatabase(signInStudentId);

        // Check if the student is registered
        if (student == null) {
            showAlertError("Student with ID " + signInStudentId + " is not registered.");
            return;
        }

        // Validate the entered password
        if (!student.getStudentPassword().equals(signinPassword)) {
            showAlertError("Incorrect password. Please try again.");
            signinStudentPasswordField.clear();
            signinStudentPasswordField.requestFocus();
            return;
        }


        // After successful login, set the signed-in student ID
        //StudentController.signedInStudentId = signinStudentId; // Assuming student ID is entered in a text field
        // or retrieve it from your student object or wherever it is stored
        // ...
        // Successful login alert
        showAlertSuccess("Student logged in successfully!");

        // Fetch clubs the student has not joined
        //List<Club> clubsNotJoined = StudentController.fetchClubsNotJoined(StudentController.signedInStudentId);
        //System.out.println(clubsNotJoined);
        // Populate the student_send_club_request_table with clubsNotJoined data
        //student_send_club_request_table.getItems().setAll(clubsNotJoined);

        // Fetch clubs the student has joined
        ////List<Club> joinedClubs = StudentController.fetchJoinedClubs(StudentController.signedInStudentId);
        //System.out.println(joinedClubs);
        // Populate the student_joined_club_table with joinedClubs data
        //student_joined_club_table.getItems().setAll(joinedClubs);

        // Load student FXML after successful registration
        FXMLLoaderUtil.loadFXML("student.fxml", "Student");



    }



    // teacher signin
    static String signinTeacherId;
    public void teacherSigninButtonOnAction(ActionEvent event) {
        // Get teacher inputs from text fields and password field
        signinTeacherId = signinTeacherIdTextField.getText();
        String signinPassword = signinTeacherPasswordField.getText();

        // Validate teacher ID
        if (!InputValidations.validateId(signinTeacherId)) {
            showAlertError("Invalid teacher ID. Enter a valid one.");
            signinTeacherIdTextField.clear();
            signinTeacherIdTextField.requestFocus();
            return;
        }

        // Retrieve data from the database using teacher ID
        ClubAdvisor teacher = getTeacherFromDatabase(signinTeacherId);

        // Check if the teacher is registered
        if (teacher == null) {
            showAlertError("Teacher with ID " + signinTeacherId + " is not registered.");
            return;
        }

        // Validate the entered password
        if (!teacher.getTeacherPassword().equals(signinPassword)) {
            showAlertError("Incorrect password. Please try again.");
            signinTeacherPasswordField.clear();
            signinTeacherPasswordField.requestFocus();
            return;
        }

        // Successful login alert
        showAlertSuccess("Club advisor logged in successfully!");

        // Load student FXML after successful registration
        FXMLLoaderUtil.loadFXML("clubadvisor.fxml", "Club Advisor");

    }




}