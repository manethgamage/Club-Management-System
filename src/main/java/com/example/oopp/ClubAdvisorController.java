package com.example.oopp;

import jasper.EventScheduleReport;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.DateTimeException;
import java.sql.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

import static com.example.oopp.Database.getDBConnection;
import static com.example.oopp.HelloController.showAlertSuccess;

public class ClubAdvisorController  implements Initializable{
    @FXML
    private AnchorPane AttendencePane;

    @FXML
    private AnchorPane JoinRequestsPane;

    @FXML
    private Button ManageClubButton;

    @FXML
    private Button SheduleEventsButton;

    @FXML
    private Button clubAttendenceButton;

    @FXML
    private Button clubJoinRequestButton;

    @FXML
    private AnchorPane createClubAnchorPane;
    @FXML
    private Button EventDeleteUpdateButton;
    @FXML
    private AnchorPane eventUpdatePane;
    @FXML
    private AnchorPane eventSchedulingPane;
    @FXML
    private AnchorPane eventSchedulingSecondPane;
    @FXML
    private TextField eventSchedulingEnterField;
    @FXML
    private ChoiceBox<Club> eventSchedulingChoiceBox;
    @FXML
    private Button EnterAdvisorButton;
    @FXML
    private ChoiceBox<String> EventSchedulingTypeChoiceBox;
    @FXML
    private TextField EventSchedulingEventId;
    @FXML
    private TextField EventSchedulingEventName;
    @FXML
    private DatePicker EventSchedulingDatePicker;
    @FXML
    private TextField EventSchedulingTime;
    @FXML
    private TextField EventSchedulingLocation;
    @FXML
    private TextField EventSchedulingTypeOrAgenda;
    @FXML
    private TextField EventSchedulingDescription;
    @FXML
    private TextField EventDeleteAdvisorId;
    @FXML
    private TableView<ScheduleActivity> EventSchedulingTable;
    @FXML
    private TableColumn<ScheduleActivity,Integer> EventIdColumn;
    @FXML
    private TableColumn<ScheduleActivity, String> EventClubName;
    @FXML
    private TableColumn<ScheduleActivity, String> EventName;
    @FXML
    private TableColumn<ScheduleActivity, String> eventType;
    @FXML
    private TableColumn<ScheduleActivity,String> eventScheduleDate;
    @FXML
    private TableColumn<ScheduleActivity,String> eventScheduleTime;
    @FXML
    private TableColumn<ScheduleActivity,String> EventScheduleLocation;
    @FXML
    private TableColumn<ScheduleActivity, String> EventScheduleDescription;
    @FXML
    private AnchorPane EventDeleteSecondPane;
    @FXML
    private TableView<ScheduleActivity> EventUpdateDeleteTable;
    @FXML
    private TableColumn<ScheduleActivity, Integer> EventDeleteId;
    @FXML
    private TableColumn<ScheduleActivity, String> EventDeleteName;
    @FXML
    private TableColumn<ScheduleActivity, String> EventDeleteActivityType;
    @FXML
    private TableColumn<ScheduleActivity , Date> EventDeleteDate;
    @FXML
    private TableColumn<ScheduleActivity, String> EventDeleteTime;
    @FXML
    private TableColumn<ScheduleActivity, String > EventDeleteLocation;
    @FXML
    private TableColumn<ScheduleActivity , String> EventDeleteTypeAgenda;
    @FXML
    private TableColumn<ScheduleActivity,String> EventDeleteDescription;
    @FXML
    private TextField EventUpdateName;
    @FXML
    private TextField EventUpdateTime;
    @FXML
    private DatePicker EventUpdateDate;
    @FXML
    private TextField EventUpdateLocation;
    @FXML
    private TextField EventUpdateDescription;
    @FXML
    private TextField EventUpdateTypeOrAgenda;
    @FXML
    private TextField EventUpdateDeleteEventId;
    @FXML
    private TableView<MembershipRequest> advisorJoinReqTable;
    @FXML
    private TableColumn<MembershipRequest, String> joinReqStudentName;
    @FXML
    private TableColumn<MembershipRequest, String> joinReqClubName;
    @FXML
    private TextField attendance_AdvisorID_search;
    @FXML
    private AnchorPane Attendance2pane;
    @FXML
    private TableColumn<Attendance, Boolean> attendance_Attendance_column;

    @FXML
    private ChoiceBox<String> attendance_Club_choice;
    @FXML
    private ChoiceBox<Integer> attendance_EventID_choice;

    @FXML
    private Button attendance_MarkAttendance_button;


    @FXML
    private TableColumn<Map.Entry<String, String>, String> attendance_StudentID_column;

    @FXML
    private TextField attendance_StudentID_search_bar;

    @FXML
    private TableColumn<Map.Entry<String, String>, String> attendance_StudentName_column;


    @FXML
    private TableView<Map.Entry<String, String>> attendance_table;
    @FXML
    private TextField createClubName;
    @FXML
    private TextArea createClubDescription;
    @FXML
    private TableColumn<Club,String> createClubNameCol;
    @FXML
    private TableView<Club> createClubsTable;
    @FXML
    private TableColumn<Club,String> createClubDescriptionCol;
    @FXML
    private TextField clubUpdateDeleteName;
    @FXML
    private TextArea ClubUpdateDeleteDescription;






    private static List<Event> eventList = new ArrayList<>();
    private static List<Meeting> meetingList = new ArrayList<>();
    private static List<Activity> activityList = new ArrayList<>();


    @FXML
    public void mainClick(ActionEvent event){
        if (event.getSource()==ManageClubButton){
            createClubAnchorPane.setVisible(true);
            AttendencePane.setVisible(false);
            eventSchedulingPane.setVisible(false);
            JoinRequestsPane.setVisible(false);
            eventSchedulingSecondPane.setVisible(false);
            eventUpdatePane.setVisible(false);
            EventDeleteSecondPane.setVisible(false);
        }
        if (event.getSource()==SheduleEventsButton){
            eventSchedulingPane.setVisible(true);
            createClubAnchorPane.setVisible(false);
            JoinRequestsPane.setVisible(false);
            AttendencePane.setVisible(false);
            eventSchedulingSecondPane.setVisible(false);
            eventUpdatePane.setVisible(false);
            EventDeleteSecondPane.setVisible(false);

        }
        if (event.getSource()==clubJoinRequestButton) {
            eventSchedulingPane.setVisible(false);
            createClubAnchorPane.setVisible(false);
            JoinRequestsPane.setVisible(true);
            AttendencePane.setVisible(false);
            eventSchedulingSecondPane.setVisible(false);
            eventUpdatePane.setVisible(false);
            EventDeleteSecondPane.setVisible(false);


        }

        if (event.getSource()==clubAttendenceButton){
            eventSchedulingPane.setVisible(false);
            createClubAnchorPane.setVisible(false);
            JoinRequestsPane.setVisible(false);
            AttendencePane.setVisible(true);
            eventSchedulingSecondPane.setVisible(false);
            eventUpdatePane.setVisible(false);
            EventDeleteSecondPane.setVisible(false);
        }
        if (event.getSource() == EventDeleteUpdateButton){
            eventUpdatePane.setVisible(true);
            eventSchedulingPane.setVisible(false);
            createClubAnchorPane.setVisible(false);
            JoinRequestsPane.setVisible(false);
            AttendencePane.setVisible(false);
            eventSchedulingSecondPane.setVisible(false);
            EventDeleteSecondPane.setVisible(false);

        }
    }
    List<Club> clubs = new ArrayList<>();


    EventSheduleDatabaseConnection dbConnection = new EventSheduleDatabaseConnection();
    public void createClubClick(ActionEvent event) {
        String clubName = createClubName.getText();
        String clubDescription = createClubDescription.getText();
        String advisorId = HelloController.signinTeacherId;

        if (dbConnection.isClubNameExists(clubName)) {
            showAlert("Error", "Club Name is already exists!!");
            createClubName.setStyle("-fx-border-color: red;");
        } else if (clubName.isEmpty()||clubDescription.isEmpty()){
            showAlert("Error", "Please fill the Fields");
            createClubName.setStyle("-fx-border-color: red;");
            createClubDescription.setStyle("-fx-border-color: red;");
        }else {
            createClub(clubName,clubDescription,advisorId);
            refreshTableClub();

            dbConnection.insertClubs(clubs);
            createClubName.setStyle(null);
            createClubDescription.setStyle(null);
            createClubName.clear();
            createClubDescription.clear();
        }

    }

    private void createClub(String clubName, String clubDescription,String advisorId){
        Club club  = new Club(clubName, clubDescription,advisorId);
        clubs.add(club);

    }



    @FXML
    public void enterAdvisorIdClick(ActionEvent event) {
        String advisorId = eventSchedulingEnterField.getText();

        // Check if advisor ID exists
        if (dbConnection.isAdvisorIdExists(advisorId)) {
            // If advisor ID exists, set the second pane visible
            eventSchedulingSecondPane.setVisible(true);

            // Retrieve and load all clubs to the ChoiceBox
            List<Club> clubs = dbConnection.getClubsByAdvisorId(advisorId);

            // Clear existing items in the ChoiceBox
            eventSchedulingChoiceBox.getItems().clear();


            for (Club club : clubs) {
                if (club != null) {
                    eventSchedulingChoiceBox.getItems().add(club);
                }
            }


            eventSchedulingChoiceBox.setConverter(new StringConverter<Club>() {
                @Override
                public String toString(Club club) {
                    return club == null ? "" : club.getClubName();
                }

                @Override
                public Club fromString(String string) {
                    return null;
                }
            });
            EventSchedulingTypeChoiceBox.getItems().setAll("Meeting", "Activity", "Event");
            populateEventSchedulingTable(advisorId);
        } else {
            // If advisor ID doesn't exist, show an alert
            showAlert("Advisor ID Not Found", "Provide a valid Advisor ID");
        }
    }

    @FXML
    public void eventScheduleClick(ActionEvent event) {
        try {
            // Validate other fields (non-empty check)
            String eventName = EventSchedulingEventName.getText();
            String time = EventSchedulingTime.getText();
            String description = EventSchedulingDescription.getText();
            String location = EventSchedulingLocation.getText();
            String TypeOrAgenda = EventSchedulingTypeOrAgenda.getText();
            String advisorId = eventSchedulingEnterField.getText();
            String selectedType = EventSchedulingTypeChoiceBox.getValue();

            boolean hasEmptyFields = false;

            // Set red border color for empty fields
            if (eventName.isEmpty()) {
                EventSchedulingEventName.setStyle("-fx-border-color: red;");
                hasEmptyFields = true;
            } else {
                EventSchedulingEventName.setStyle(null);
            }

            if (time.isEmpty()) {
                EventSchedulingTime.setStyle("-fx-border-color: red;");
                hasEmptyFields = true;
            } else {
                // Validate time format (e.g., 12am)
                if (!time.matches("^\\d{1,2}(am|pm)$")) {
                    showAlert("Invalid Time Format", "Please enter a valid time format (e.g., 12am).");
                    EventSchedulingTime.setStyle("-fx-border-color: red;");
                    return;
                } else {
                    EventSchedulingTime.setStyle(null);
                }
            }

            if (description.isEmpty()) {
                EventSchedulingDescription.setStyle("-fx-border-color: red;");
                hasEmptyFields = true;
            } else {
                EventSchedulingDescription.setStyle(null);
            }

            if (location.isEmpty()) {
                EventSchedulingLocation.setStyle("-fx-border-color: red;");
                hasEmptyFields = true;
            } else {
                EventSchedulingLocation.setStyle(null);
            }

            if (TypeOrAgenda.isEmpty()) {
                EventSchedulingTypeOrAgenda.setStyle("-fx-border-color: red;");
                hasEmptyFields = true;
            } else {
                EventSchedulingTypeOrAgenda.setStyle(null);
            }

            if (advisorId.isEmpty()) {
                eventSchedulingEnterField.setStyle("-fx-border-color: red;");
                hasEmptyFields = true;
            } else {
                eventSchedulingEnterField.setStyle(null);
            }

            if (selectedType == null) {
                EventSchedulingTypeChoiceBox.setStyle("-fx-border-color: red;");
                hasEmptyFields = true;
            } else {
                EventSchedulingTypeChoiceBox.setStyle(null);
            }

            // Check if ClubName is selected
            if (eventSchedulingChoiceBox.getValue() == null) {
                eventSchedulingChoiceBox.setStyle("-fx-border-color: red;");
                hasEmptyFields = true;
            } else {
                eventSchedulingChoiceBox.setStyle(null);
            }

            if (hasEmptyFields) {
                showAlert("Incomplete Form", "Please fill in all required fields.");
                return;
            }

            // Reset border color for non-empty fields
            EventSchedulingEventName.setStyle(null);
            EventSchedulingTime.setStyle(null);
            EventSchedulingDescription.setStyle(null);
            EventSchedulingLocation.setStyle(null);
            EventSchedulingTypeOrAgenda.setStyle(null);
            eventSchedulingEnterField.setStyle(null);
            EventSchedulingTypeChoiceBox.setStyle(null);
            eventSchedulingChoiceBox.setStyle(null);

            // Validate Event ID (if not empty)
            String eventIdText = EventSchedulingEventId.getText();
            int eventId = eventIdText.isEmpty() ? 0 : Integer.parseInt(eventIdText);

            // Validate Event Date (if not empty)
            LocalDate eventDateLocal = EventSchedulingDatePicker.getValue();
            if (eventDateLocal == null) {
                EventSchedulingDatePicker.setStyle("-fx-border-color: red;");
                showAlert("Invalid Input", "Please select a valid Event Date.");
                return;
            } else {

                EventSchedulingDatePicker.setStyle(null);
            }


            String eventDate = eventDateLocal.toString();


            if (isDuplicateEventId(eventId)) {
                // Set red border color for EventSchedulingEventId
                EventSchedulingEventId.setStyle("-fx-border-color: red;");
                showAlert("Duplicate Event ID", "Event ID already exists. Please choose a different one.");
                return;
            } else {
                // Reset border color for EventSchedulingEventId
                EventSchedulingEventId.setStyle(null);
            }

            int clubId = dbConnection.getClubIdByClubName(eventSchedulingChoiceBox.getValue().getClubName());

            switch (selectedType) {
                case "Meeting":
                    Meeting meeting = new Meeting(eventId, eventName, eventDate, time, location, description, clubId, advisorId, TypeOrAgenda);
                    meetingList.add(meeting);
                    dbConnection.insertMeetings(meetingList);
                    meetingList.clear();
                    break;
                case "Event":
                    Event events = new Event(eventId, eventName, eventDate, time, location, description, clubId, advisorId, TypeOrAgenda);
                    eventList.add(events);
                    dbConnection.insertEvents(eventList);
                    eventList.clear();
                    break;
                case "Activity":
                    Activity activity = new Activity(eventId, eventName, eventDate, time, location, description, clubId, advisorId, TypeOrAgenda);
                    activityList.add(activity);
                    dbConnection.insertActivities(activityList);
                    activityList.clear();
                    break;
                default:
                    showAlert("Please Choose a Type", "Please select a type (Meeting, Event, or Activity).");
                    break;
            }

            // Clear input fields
            clearInputFields();

            // Populate the table
            populateEventSchedulingTable(advisorId);

        } catch (NumberFormatException e) {
            EventSchedulingEventId.setStyle("-fx-border-color: red;");
            showAlert("Invalid Event ID", "Please enter a valid integer for Event ID.");
        }
    }



    private void clearInputFields() {

        EventSchedulingEventId.clear();
        EventSchedulingEventId.setStyle(null);
        EventSchedulingEventName.clear();
        EventSchedulingEventName.setStyle(null);
        EventSchedulingDatePicker.getEditor().clear();
        EventSchedulingDatePicker.setStyle(null);
        EventSchedulingDatePicker.setValue(null);
        EventSchedulingTime.clear();
        EventSchedulingTime.setStyle(null);
        EventSchedulingLocation.clear();
        EventSchedulingLocation.setStyle(null);
        EventSchedulingDescription.clear();
        EventSchedulingDescription.setStyle(null);
        EventSchedulingTypeOrAgenda.clear();
        EventSchedulingTypeOrAgenda.setStyle(null);
    }


    public boolean isDuplicateEventId(int eventId) {
        // Get all events, meetings, and activities
        List<Event> events = dbConnection.getAllEvents();
        List<Meeting> meetings = dbConnection.getAllMeetings();
        List<Activity> activities = dbConnection.getAllActivities();

        // Check for duplicate Event ID in events
        for (Event event : events) {
            if (event.getEventId() == eventId) {
                showAlert("Duplicate Event ID", "An event with the specified ID already exists.");
                return true;
            }
        }

        // Check for duplicate Event ID in meetings
        for (Meeting meeting : meetings) {
            if (meeting.getEventId() == eventId) {
                showAlert("Duplicate Event ID", "A meeting with the specified ID already exists.");
                return true;
            }
        }

        // Check for duplicate Event ID in activities
        for (Activity activity : activities) {
            if (activity.getEventId() == eventId) {
                showAlert("Duplicate Event ID", "An activity with the specified ID already exists.");
                return true;
            }
        }

        // If no duplicate Event ID is found
        return false;
    }




    private void populateEventSchedulingTable(String advisorId) {
        EventSchedulingTable.getItems().clear();

        // Fetch data from the database and add to the table
        List<ScheduleActivity> allActivities = new ArrayList<>();
        allActivities.addAll(dbConnection.getEventsByAdvisorId(advisorId));
        allActivities.addAll(dbConnection.getMeetingsByAdvisorId(advisorId));
        allActivities.addAll(dbConnection.getActivitiesByAdvisorId(advisorId));


        EventIdColumn.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        EventClubName.setCellValueFactory(param -> new SimpleStringProperty(getClubName(param.getValue().getClubId())));
        EventName.setCellValueFactory(cellData -> new SimpleStringProperty(getCombinedNames(cellData.getValue())));
        eventType.setCellValueFactory(cellData -> new SimpleStringProperty(getType(cellData.getValue())));
        eventScheduleDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        eventScheduleTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        EventScheduleLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        EventScheduleDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Add the data to the table
        EventSchedulingTable.getItems().addAll(allActivities);
    }

    private String getClubName(int clubId) {
        return dbConnection.getClubNameById(clubId);
    }

    private String getCombinedNames(ScheduleActivity scheduleActivity) {
        if (scheduleActivity instanceof Event) {
            return ((Event) scheduleActivity).getTitle();
        } else if (scheduleActivity instanceof Meeting) {
            return ((Meeting) scheduleActivity).getTitle();
        } else if (scheduleActivity instanceof Activity) {
            return ((Activity) scheduleActivity).getTitle();
        } else {
            return "";
        }
    }

    private String getType(ScheduleActivity scheduleActivity) {
        if (scheduleActivity instanceof Event) {
            return "Event";
        } else if (scheduleActivity instanceof Meeting) {
            return "Meeting";
        } else if (scheduleActivity instanceof Activity) {
            return "Activity";
        } else {
            return "";
        }
    }

    @FXML
    public void enterDeleteAdvisorId(ActionEvent event){
        String advisorId1 = EventDeleteAdvisorId.getText();

        if (dbConnection.isAdvisorIdExists(advisorId1)) {
            // If advisor ID exists, set the second pane visible
            EventDeleteSecondPane.setVisible(true);

            eventUpdateDeleteTable(advisorId1);


        } else {
            // If advisor ID doesn't exist, show an alert
            showAlert("Advisor ID Not Found", "Provide valid Advisor ID");
        }
    }

    public void eventUpdateDeleteTable(String advisorId){
        EventUpdateDeleteTable.getItems().clear();

        // Fetch data from the database and add to the table
        List<ScheduleActivity> allActivities = new ArrayList<>();
        allActivities.addAll(dbConnection.getEventsByAdvisorId(advisorId));
        allActivities.addAll(dbConnection.getMeetingsByAdvisorId(advisorId));
        allActivities.addAll(dbConnection.getActivitiesByAdvisorId(advisorId));

        EventDeleteId.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        EventDeleteName.setCellValueFactory(cellData -> new SimpleStringProperty(getCombinedNames(cellData.getValue())));
        EventDeleteActivityType.setCellValueFactory(cellData -> new SimpleStringProperty(getType(cellData.getValue())));
        EventDeleteDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        EventDeleteTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        EventDeleteLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        EventDeleteTypeAgenda.setCellValueFactory(cellData -> new SimpleStringProperty(getCombinedTypeAndAgenda(cellData.getValue())));
        EventDeleteDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        EventUpdateDeleteTable.getItems().addAll(allActivities);

        EventUpdateDeleteTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Populate the text fields with the details of the selected event
                EventUpdateDeleteEventId.setText(String.valueOf(newSelection.getEventId()));
                EventUpdateName.setText(newSelection.getTitle());
                EventUpdateTime.setText(newSelection.getTime());
                // Convert the date String to LocalDate and set it to the DatePicker
                EventUpdateDate.setValue(LocalDate.parse(newSelection.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                EventUpdateLocation.setText(newSelection.getLocation());
                EventUpdateDescription.setText(newSelection.getDescription());
                // Set type or agenda based on the event type
                if (newSelection instanceof Meeting) {
                    EventUpdateTypeOrAgenda.setText(((Meeting) newSelection).getAgenda());
                } else if (newSelection instanceof Event){
                    EventUpdateTypeOrAgenda.setText(((Event) newSelection).getEventType());
                }else if (newSelection instanceof Activity){
                    EventUpdateTypeOrAgenda.setText(((Activity)newSelection).getActivityType());
                }
            } else {
                // Clear the text fields if no event is selected
                EventUpdateDeleteEventId.clear();
                EventUpdateName.clear();
                EventUpdateTime.clear();
                EventUpdateDate.setValue(null);
                EventUpdateLocation.clear();
                EventUpdateDescription.clear();
                EventUpdateTypeOrAgenda.clear();
            }
        });


    }

    private String getCombinedTypeAndAgenda(ScheduleActivity scheduleActivity) {
        if (scheduleActivity instanceof Event) {
            return ((Event) scheduleActivity).getEventType();
        } else if (scheduleActivity instanceof Meeting) {
            return ((Meeting) scheduleActivity).getAgenda();
        } else if (scheduleActivity instanceof Activity) {
            return ((Activity) scheduleActivity).getActivityType();
        } else {
            return "";
        }
    }

    @FXML
    public void eventDeleteClick(ActionEvent event){
        try {
            int eventId2 = Integer.parseInt(EventUpdateDeleteEventId.getText());
            setEventFieldsByEventId(eventId2);
            deleteEventByEventId(eventId2);
            EventUpdateDeleteTable.refresh();
            EventUpdateDeleteEventId.clear();
            EventUpdateName.clear();
            EventUpdateTime.clear();
            EventUpdateDate.setValue(null);
            EventUpdateLocation.clear();
            EventUpdateDescription.clear();
            EventUpdateTypeOrAgenda.clear();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid integer for Event ID.");
        }


    }

    public void deleteEventByEventId(int eventId) {
        String advisorId = EventDeleteAdvisorId.getText();

        // Create lists to store events, meetings, and activities for the advisor
        List<Event> events = dbConnection.getEventsByAdvisorId(advisorId);
        List<Meeting> meetings = dbConnection.getMeetingsByAdvisorId(advisorId);
        List<Activity> activities = dbConnection.getActivitiesByAdvisorId(advisorId);

        boolean eventFound = false;

        // Iterate over the list using an iterator to avoid concurrent modification
        Iterator<Event> eventIterator = events.iterator();
        while (eventIterator.hasNext()) {
            Event event = eventIterator.next();
            if (event.getEventId() == eventId) {
                // Delete the event from the database
                dbConnection.deleteEvent(event);
                // Remove the event from the list
                eventIterator.remove();
                eventFound = true;
                eventUpdateDeleteTable(advisorId);
                break; // Exit the loop once the event is found and deleted
            }
        }

        boolean meetingFound = false;

        // Similar changes for meetings
        Iterator<Meeting> meetingIterator = meetings.iterator();
        while (meetingIterator.hasNext()) {
            Meeting meeting = meetingIterator.next();
            if (meeting.getEventId() == eventId) {
                // Delete the meeting from the database
                dbConnection.deleteMeeting(meeting);
                // Remove the meeting from the list
                meetingIterator.remove();
                meetingFound = true;
                eventUpdateDeleteTable(advisorId);
                break; // Exit the loop once the meeting is found and deleted
            }
        }

        boolean activityFound = false;

        // Similar changes for activities
        Iterator<Activity> activityIterator = activities.iterator();
        while (activityIterator.hasNext()) {
            Activity activity = activityIterator.next();
            if (activity.getEventId() == eventId) {
                // Delete the activity from the database
                dbConnection.deleteActivity(activity);
                // Remove the activity from the list
                activityIterator.remove();
                activityFound = true;
                eventUpdateDeleteTable(advisorId);
                break; // Exit the loop once the activity is found and deleted
            }
        }

        // Show the "Event Not Found" alert if the event is not found in any of the lists
        if (!eventFound && !meetingFound && !activityFound) {
            showAlert("Event Not Found", "The specified Event ID was not found for the advisor.");
        }
    }

    @FXML
    public void eventSearchClick(ActionEvent event) {
        try {
            int eventId = Integer.parseInt(EventUpdateDeleteEventId.getText());
            setEventFieldsByEventId(eventId);
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid integer for Event ID.");
        }
    }

    public void setEventFieldsByEventId(int eventId) {
        String advisorId = EventDeleteAdvisorId.getText();

        // Create lists to store events, meetings, and activities for the advisor
        List<Event> events = dbConnection.getEventsByAdvisorId(advisorId);
        List<Meeting> meetings = dbConnection.getMeetingsByAdvisorId(advisorId);
        List<Activity> activities = dbConnection.getActivitiesByAdvisorId(advisorId);

        boolean eventFound = false;

        // Iterate over the list using an iterator to avoid concurrent modification
        Iterator<Event> eventIterator = events.iterator();
        while (eventIterator.hasNext()) {
            Event event = eventIterator.next();
            if (event.getEventId() == eventId) {
                // Set the values to the text fields based on the retrieved event
                setEventDataFields(event);
                eventFound = true;
                break; // Exit the loop once the event is found
            }
        }

        boolean meetingFound = false;

        // Similar changes for meetings
        Iterator<Meeting> meetingIterator = meetings.iterator();
        while (meetingIterator.hasNext()) {
            Meeting meeting = meetingIterator.next();
            if (meeting.getEventId() == eventId) {
                // Set the values to the text fields based on the retrieved meeting
                setMeetingDataFields(meeting);
                meetingFound = true;
                break; // Exit the loop once the meeting is found
            }
        }

        boolean activityFound = false;

        // Similar changes for activities
        Iterator<Activity> activityIterator = activities.iterator();
        while (activityIterator.hasNext()) {
            Activity activity = activityIterator.next();
            if (activity.getEventId() == eventId) {
                // Set the values to the text fields based on the retrieved activity
                setActivityDataFields(activity);
                activityFound = true;
                break; // Exit the loop once the activity is found
            }
        }

        // Show the "Event Not Found" alert if the event is not found in any of the lists
        if (!eventFound && !meetingFound && !activityFound) {
            showAlert("Event Not Found", "The specified Event ID was not found for the advisor.");
        }
    }

    public void setEventDataFields(Event event) {
        EventUpdateName.setText(event.getTitle());
        EventUpdateTime.setText(event.getTime());

        // Assuming that 'eventDate' is a String; if it's a Date object, you may need to format it
        EventUpdateDate.setValue(LocalDate.parse(event.getDate()));

        EventUpdateLocation.setText(event.getLocation());
        EventUpdateDescription.setText(event.getDescription());
        EventUpdateTypeOrAgenda.setText(event.getEventType());
    }

    public void setMeetingDataFields(Meeting meeting) {
        // Assuming Meeting has similar properties as Event
        EventUpdateName.setText(meeting.getTitle());
        EventUpdateTime.setText(meeting.getTime());

        // Assuming that 'meetingDate' is a String; if it's a Date object, you may need to format it
        EventUpdateDate.setValue(LocalDate.parse(meeting.getDate()));

        EventUpdateLocation.setText(meeting.getLocation());
        EventUpdateDescription.setText(meeting.getDescription());
        EventUpdateTypeOrAgenda.setText(meeting.getAgenda());
    }

    public void setActivityDataFields(Activity activity) {
        // Assuming Activity has similar properties as Event
        EventUpdateName.setText(activity.getTitle());
        EventUpdateTime.setText(activity.getTime());

        // Assuming that 'activityDate' is a String; if it's a Date object, you may need to format it
        EventUpdateDate.setValue(LocalDate.parse(activity.getDate()));

        EventUpdateLocation.setText(activity.getLocation());
        EventUpdateDescription.setText(activity.getDescription());
        EventUpdateTypeOrAgenda.setText(activity.getActivityType());
    }

    @FXML
    public void updateEventsClick(ActionEvent event){
        try {
            int eventId1 = Integer.parseInt(EventUpdateDeleteEventId.getText());
            updateEvents(eventId1);
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid integer for Event ID.");
        }


    }

    public void updateEvents(int eventId) {
        String advisorId = EventDeleteAdvisorId.getText();
        String newEventName = EventUpdateName.getText();
        String newEventTime = EventUpdateTime.getText();
        String newEventLocation = EventUpdateLocation.getText();
        String newEventDescription = EventUpdateDescription.getText();
        String newEventAgendaOrType = EventUpdateTypeOrAgenda.getText();

        boolean hasEmptyFields = false;

        // Validate other fields (non-empty check)
        if (newEventName.isEmpty() || newEventLocation.isEmpty()
                || newEventDescription.isEmpty() || newEventAgendaOrType.isEmpty()) {
            hasEmptyFields = true;

            // Set red border color for empty fields
            if (newEventName.isEmpty()) {
                EventUpdateName.setStyle("-fx-border-color: red;");
            } else {
                EventUpdateName.setStyle(null);
            }

            if (newEventLocation.isEmpty()) {
                EventUpdateLocation.setStyle("-fx-border-color: red;");
            } else {
                EventUpdateLocation.setStyle(null);
            }

            if (newEventDescription.isEmpty()) {
                EventUpdateDescription.setStyle("-fx-border-color: red;");
            } else {
                EventUpdateDescription.setStyle(null);
            }

            if (newEventAgendaOrType.isEmpty()) {
                EventUpdateTypeOrAgenda.setStyle("-fx-border-color: red;");
            } else {
                EventUpdateTypeOrAgenda.setStyle(null);
            }
        } else {
            // Reset border color for non-empty fields
            EventUpdateName.setStyle(null);
            EventUpdateLocation.setStyle(null);
            EventUpdateDescription.setStyle(null);
            EventUpdateTypeOrAgenda.setStyle(null);
        }

        // Validate Date
        LocalDate newEventDateLocal = EventUpdateDate.getValue();
        if (newEventDateLocal == null) {
            EventUpdateDate.setStyle("-fx-border-color: red;");
            showAlert("Invalid Input", "Please select a valid Event Date.");
            return;
        } else {
            // Reset border color for EventSchedulingDatePicker
            EventUpdateDate.setStyle(null);
        }
        String newEventDate = newEventDateLocal.toString();

        // Validate Time
        if (newEventTime.isEmpty()) {
            EventUpdateTime.setStyle("-fx-border-color: red;");
            hasEmptyFields = true;
        } else {
            // Validate time format (e.g., 12am)
            if (!newEventTime.matches("^\\d{1,2}(am|pm)$")) {
                showAlert("Invalid Time Format", "Please enter a valid time format (e.g., 12am).");
                EventUpdateTime.setStyle("-fx-border-color: red;");
                return;
            } else {
                EventUpdateTime.setStyle(null);
            }
        }

        if (hasEmptyFields) {
            showAlert("Incomplete Form", "Please fill in all required fields.");
            return;
        }

        List<Event> events = dbConnection.getEventsByAdvisorId(advisorId);
        List<Meeting> meetings = dbConnection.getMeetingsByAdvisorId(advisorId);
        List<Activity> activities = dbConnection.getActivitiesByAdvisorId(advisorId);

        boolean eventFound = false;

        // Iterate over the list using an iterator to avoid concurrent modification
        Iterator<Event> eventIterator = events.iterator();
        while (eventIterator.hasNext()) {
            Event event = eventIterator.next();
            if (event.getEventId() == eventId) {
                // Delete the event from the database
                dbConnection.updateEventByEventId(eventId, newEventName, newEventDate, newEventTime, newEventLocation, newEventDescription, newEventAgendaOrType);
                // Remove the event from the list
                eventIterator.remove();
                eventFound = true;
                eventUpdateDeleteTable(advisorId);
                break; // Exit the loop once the event is found and deleted
            }
        }

        boolean meetingFound = false;

        // Similar changes for meetings
        Iterator<Meeting> meetingIterator = meetings.iterator();
        while (meetingIterator.hasNext()) {
            Meeting meeting = meetingIterator.next();
            if (meeting.getEventId() == eventId) {
                // Delete the meeting from the database
                dbConnection.updateMeetingByEventId(eventId, newEventName, newEventDate, newEventTime, newEventLocation, newEventDescription, newEventAgendaOrType);
                // Remove the meeting from the list
                meetingIterator.remove();
                eventFound = true;
                eventUpdateDeleteTable(advisorId);
                break; // Exit the loop once the meeting is found and deleted
            }
        }

        boolean activityFound = false;

        // Similar changes for activities
        Iterator<Activity> activityIterator = activities.iterator();
        while (activityIterator.hasNext()) {
            Activity activity = activityIterator.next();
            if (activity.getEventId() == eventId) {
                // Delete the activity from the database
                dbConnection.updateActivityByEventId(eventId, newEventName, newEventDate, newEventTime, newEventLocation, newEventDescription, newEventAgendaOrType);
                // Remove the activity from the list
                activityIterator.remove();
                eventFound = true;
                eventUpdateDeleteTable(advisorId);
                break; // Exit the loop once the activity is found and deleted
            }
        }

        // Show the "Event Not Found" alert if the event is not found in any of the lists
        if (!eventFound) {
            showAlert("Event Not Found", "The specified Event ID was not found for the advisor.");
        }
    }





    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

//---------------------------------save club advisor data to database----------------------------------------------------
    public static void insertTeacher(ClubAdvisor clubAdvisor) {
        // Check if the teacher already exists in the database
        if (isTeacherExists(clubAdvisor.getTeacherId())) {
            showAlertSuccess("Teacher with ID " + clubAdvisor.getTeacherId() + " already exists.");
            return;
        }

        String insertQuery = "INSERT INTO clubAdvisor (teacherId, teacherName, userPassword) VALUES (?, ?, ?)";

        try (Connection connection = getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, clubAdvisor.getTeacherId());
            preparedStatement.setString(2, clubAdvisor.getTeacherName());
            preparedStatement.setString(3, clubAdvisor.getTeacherPassword());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                showAlertSuccess("Teacher registered successfully!");

                // Load student FXML after successful registration
                FXMLLoaderUtil.loadFXML("clubadvisor.fxml", "Club Advisor");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlertSuccess("Error occurred while registering the teacher.");
        }
    }

    // Check if the teacher already exists in the database
    private static boolean isTeacherExists(String teacherId) {
        String query = "SELECT COUNT(*) FROM clubAdvisor WHERE teacherId = ?";
        try (Connection connection = getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, teacherId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//-------------------------------get teacher data from the database-----------------------------------------------------

    private static final String GET_TEACHER_QUERY = "SELECT teacherId, teacherName, userPassword FROM clubAdvisor WHERE teacherId = ?";

    static ClubAdvisor getTeacherFromDatabase(String teacherId) {
        try (Connection connection = Database.getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_TEACHER_QUERY)) {

            preparedStatement.setString(1, teacherId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String retrievedTeacherId = resultSet.getString("teacherId");
                    String retrievedTeacherName = resultSet.getString("teacherName");
                    String retrievedTeacherPassword = resultSet.getString("userPassword");

                    return new ClubAdvisor(retrievedTeacherId, retrievedTeacherName, retrievedTeacherPassword);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlertSuccess("Error retrieving teacher data from the database.");
        }

        return null;
    }

    AttendanceTrackDatabaseConnection attendanceTrackDatabaseConnection = new AttendanceTrackDatabaseConnection();

//    @FXML
//    public void initialize() {
//
//        String advisorId = HelloController.signinTeacherId;
//
//        // Fetch membership requests for the advisor
//        List<MembershipRequest> membershipRequests = dbConnection.getMembershipRequestsByAdvisorId(advisorId);
//
//        // Populate the table
//        populateTable(membershipRequests);
//        refreshTable();
//    }

    private void populateTable(List<MembershipRequest> membershipRequests) {
        ObservableList<MembershipRequest> data = FXCollections.observableArrayList(membershipRequests);

        joinReqStudentName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentId()));
        joinReqClubName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClubName()));
        advisorJoinReqTable.getItems().addAll(data);
    }

    @FXML
    public void attendanceAdvisorClick(ActionEvent event) {
        // Get the advisor ID from the search field
        String advisorId = attendance_AdvisorID_search.getText();

        // Check if the advisor ID is empty or null
        if (advisorId == null || advisorId.trim().isEmpty()) {
            showAlert("Null Advisor ID", "Please enter an Advisor ID.");
            return;
        }

        // Check if the advisor ID is a valid integer
        try {
            Integer.parseInt(advisorId);
            showAlert("Invalid Advisor ID", "Advisor ID should not be a full-numeric value.");
            return;
        } catch (NumberFormatException e) {
            // Advisor ID is not a valid integer, continue with the check
        }

        // Check if the advisor ID exists in the database
        if (dbConnection.isAdvisorIdExists(advisorId)) {
            Attendance2pane.setVisible(true);
            List<String> clubNames = attendanceTrackDatabaseConnection.getClubNamesByAdvisorId(advisorId);
            attendance_Club_choice.getItems().addAll(clubNames);
        } else {
            // If advisor ID does not exist, show an alert
            showAlert("Advisor ID Not Found", "No records found for the provided Advisor ID.");
        }
        Attendance2pane.setVisible(true);
    }





    @FXML
    public void handleAcceptButton(ActionEvent event) {
        // Get the selected membership request
        MembershipRequest selectedRequest = advisorJoinReqTable.getSelectionModel().getSelectedItem();

        // Check if a request is selected
        if (selectedRequest != null) {
            // Assuming you have a method to get the advisorId (replace it with your actual logic)
            String advisorId = HelloController.signinTeacherId;

            // Get the clubId based on clubName
            int clubId = dbConnection.getClubIdByClubName(selectedRequest.getClubName());

            // Accept the membership request
            dbConnection.acceptMembershipRequest(selectedRequest.getStudentId(), clubId);

            // Refresh the table
            refreshTable();
        }
    }

    // Add this method to refresh the table after accepting a request
    private void refreshTable() {
        advisorJoinReqTable.getItems().clear();
        // Fetch membership requests for the advisor
        List<MembershipRequest> membershipRequests = dbConnection.getMembershipRequestsByAdvisorId(HelloController.signinTeacherId);

        // Populate the table
        populateTable(membershipRequests);
    }
    @FXML
    public void handleDeclineButton(ActionEvent event) {
        // Get the selected membership request
        MembershipRequest selectedRequest = advisorJoinReqTable.getSelectionModel().getSelectedItem();

        // Check if a request is selected
        if (selectedRequest != null) {
            // Decline the membership request
            dbConnection.declineMembershipRequest(selectedRequest.getStudentId(), selectedRequest.getClubName());

            // Refresh the table after declining the request
            refreshTable();
        }
    }



    @FXML
    public void reportGenarateClick(ActionEvent event) {
        List<Event> allEvents = dbConnection.getAllClubEvents();
        EventScheduleReport eventScheduleReport = new EventScheduleReport();
        eventScheduleReport.generateAttendanceReport(allEvents);
    }

    public void attendance_MarkAttendance_click(ActionEvent event) {
        System.out.println("Attended Student : ");

        // Get the selected event from the ChoiceBox
        int selectedEvent = attendance_EventID_choice.getValue();

        // Check if an event is selected
        if (selectedEvent != 0) {
            // Set the current event ID
            AttendanceTrackDatabaseConnection.setCurrentEventId(selectedEvent);

            // Get the event type
            String eventType = attendanceTrackDatabaseConnection.getEventType(selectedEvent);
            ObservableList<Map.Entry<String, String>> selectedItems = attendance_table.getSelectionModel().getSelectedItems();

            // Debug: Print the event type
            System.out.println("Event Type: " + eventType);
            for (Map.Entry<String, String> selectedItem : selectedItems) {
                String studentId = selectedItem.getKey();

                // Print the details to the console
                System.out.println("Student ID: " + studentId);
                System.out.println("Event ID: " + AttendanceTrackDatabaseConnection.getCurrentEventId());
                System.out.println("----------------------");
                if ("Event".equals(eventType)) {
                    Attendance attendance = new Attendance(studentId, "Student Name", studentId, false);
                    attendanceTrackDatabaseConnection.updateStudentAttendanceTable(attendance, selectedEvent);
                } else if ("Meeting".equals(eventType)) {
                    Attendance attendance = new Attendance(studentId, "Student Name", studentId, false);
                    attendanceTrackDatabaseConnection.updateStudentAttendanceMeetingsTable(attendance, selectedEvent);
                } else if ("Activity".equals(eventType)) {
                    Attendance attendance = new Attendance(studentId, "Student Name", studentId, false);
                    attendanceTrackDatabaseConnection.updateStudentAttendanceActivityTable(attendance, selectedEvent);
                } else {
                    // Handle the case when no event is selected, e.g., show an alert
                    System.out.println("No event selected");
                }
            }
        }

    }

    @FXML
    public void enterClubNameClick(ActionEvent event) {
        String clubName = attendance_Club_choice.getValue();
        int clubId = dbConnection.getClubIdByClubName(clubName);
        List<Integer> eventIds = attendanceTrackDatabaseConnection.getEventIdsByClubId(clubId);
        attendance_EventID_choice.getItems().addAll(eventIds);
        populateAttendanceTable(clubId);

    }
    @FXML
    public void populateAttendanceTable(int clubId) {
        // Call the method to get student details by clubId
        Map<String, String> studentsMap = attendanceTrackDatabaseConnection.getStudentsByClubId(clubId);

        // Create an observable list for the table
        ObservableList<Map.Entry<String, String>> data = FXCollections.observableArrayList(studentsMap.entrySet());

        // Clear existing columns
        attendance_table.getColumns().clear();

        // Set the cell value factories for the existing columns
        attendance_StudentID_column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));
        attendance_StudentName_column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));

        // Create a new TableColumn for attendance checkbox
        TableColumn<Map.Entry<String, String>, Boolean> attendance_Column = new TableColumn<>("Attendance");
        attendance_Column.setCellValueFactory(cellData -> {
            String studentId = cellData.getValue().getKey();
            String studentName = cellData.getValue().getValue();


            // Initialize the Attendance object with a default attendance status of false
            Attendance attendance = new Attendance(studentId, studentName, studentId, false);


            // Set the checkbox value in the Attendance object when the property changes
            attendance.attendanceProperty().addListener((obs, oldValue, newValue) -> attendance.setAttendance(newValue));

            // Create a CheckBoxTableCell that displays the checkbox and allows manual interaction
            CheckBoxTableCell<Map.Entry<String, String>, Boolean> checkBoxTableCell = new CheckBoxTableCell<>();
            checkBoxTableCell.setSelectedStateCallback(index -> attendance.attendanceProperty());


            return attendance.attendanceProperty().asObject();

        });

        attendance_Column.setCellFactory(CheckBoxTableCell.forTableColumn(attendance_Column));

        // Add the columns in the desired order to the table
        attendance_table.getColumns().addAll(attendance_StudentID_column, attendance_StudentName_column, attendance_Column);

        attendance_Attendance_column.setEditable(true);

        attendance_table.setEditable(true);

        // Set the data to the table
        attendance_table.setItems(data);


    }



    @FXML
    public void attendance_SearchButtonClick(ActionEvent event) {
        // Get the entered student ID from the search bar
        String studentId = attendance_StudentID_search_bar.getText();

        // Get the selected club name from the ChoiceBox
        String selectedClub = attendance_Club_choice.getValue();

        // Check if both student ID and club are entered
        if (studentId == null || studentId.trim().isEmpty()) {
            showAlert("Null Student ID", "Please enter a Student ID.");
            return;
        }

        try {
            Integer.parseInt(studentId);
            showAlert("Invalid Student ID", "Student ID should not be a full-numeric value.");
            return;
        } catch (NumberFormatException e) {
            // Advisor ID is not a valid integer, continue with the check
        }

        if (selectedClub == null) {
            showAlert("Club not selected", "Please select a Club.");
            return;
        }

        int clubId = dbConnection.getClubIdByClubName(selectedClub);

        // Perform the search in the database
        Map<String, String> searchResults = attendanceTrackDatabaseConnection.searchStudentsByStudentIdAndClub(studentId, clubId);

        if (searchResults.isEmpty()) {
            showAlert("Student not found", "Student ID not found in the relevant club.");
            return;
        }

        // Update the GUI to display the search results
        updateSearchResultsOnGUI(searchResults);
    }

    private void updateSearchResultsOnGUI(Map<String, String> searchResults) {
        // Clear the existing data in the table
        attendance_table.getItems().clear();

        // Create an observable list for the table
        ObservableList<Map.Entry<String, String>> data = FXCollections.observableArrayList(searchResults.entrySet());

        // Set the cell value factories for the existing columns
        attendance_StudentID_column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));
        attendance_StudentName_column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));

        // Create a new TableColumn for attendance checkbox
        TableColumn<Map.Entry<String, String>, Boolean> attendance_Column = new TableColumn<>("Attendance");
        attendance_Column.setCellValueFactory(cellData -> {
            String studentId = cellData.getValue().getKey();
            String studentName = cellData.getValue().getValue();

            // Initialize the Attendance object with a default attendance status of false
            Attendance attendance = new Attendance(studentId, studentName, studentId, false);

            // Set the checkbox value in the Attendance object when the property changes
            attendance.attendanceProperty().addListener((obs, oldValue, newValue) -> attendance.setAttendance(newValue));

            // Create a CheckBoxTableCell that displays the checkbox and allows manual interaction
            CheckBoxTableCell<Map.Entry<String, String>, Boolean> checkBoxTableCell = new CheckBoxTableCell<>();
            checkBoxTableCell.setSelectedStateCallback(index -> attendance.attendanceProperty());

            return attendance.attendanceProperty().asObject();
        });

        attendance_Column.setCellFactory(CheckBoxTableCell.forTableColumn(attendance_Column));

        // Add the columns in the desired order to the table
        attendance_table.getColumns().clear();
        attendance_table.getColumns().addAll(attendance_StudentID_column, attendance_StudentName_column, attendance_Column);

        attendance_Attendance_column.setEditable(true);

        // Set the data to the table
        attendance_table.setItems(data);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createClubNameCol.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        createClubDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("clubDescription"));

        // Populate the TableView with data
        List<Club> clubs = dbConnection.getClubsByAdvisorId(HelloController.signinTeacherId);
        ObservableList<Club> clubObservableList = FXCollections.observableArrayList(clubs);
        createClubsTable.setItems(clubObservableList);

        // Listen for selection changes and update the text fields and text area
        createClubsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        // Set the selected data into the text fields and text area
                        clubUpdateDeleteName.setText(newValue.getClubName());
                        ClubUpdateDeleteDescription.setText(newValue.getClubDescription());
                    }
                }
        );

        refreshTableClub();

        String advisorId = HelloController.signinTeacherId;

        // Fetch membership requests for the advisor
        List<MembershipRequest> membershipRequests = dbConnection.getMembershipRequestsByAdvisorId(advisorId);

        // Populate the table
        populateTable(membershipRequests);



    }


    @FXML
    private void updateClubDescriptionClick(ActionEvent event) {
        String clubName = clubUpdateDeleteName.getText();
        String newDescription = ClubUpdateDeleteDescription.getText();

        if (clubName == null || clubName.isEmpty() || newDescription == null || newDescription.isEmpty()) {
            showAlert("Error", "Please fill in both Club Name and Description.");
        } else {
            dbConnection.updateClubDescription(clubName, newDescription);
            ClubUpdateDeleteDescription.clear();
            ClubUpdateDeleteDescription.setStyle(null);
            clubUpdateDeleteName.clear();
            clubUpdateDeleteName.setStyle(null);
            refreshTableClub();


            // Optionally, update the table or perform other actions after updating
        }
    }

    private void refreshTableClub() {
        List<Club> updatedClubs = dbConnection.getClubsByAdvisorId(HelloController.signinTeacherId);
        createClubsTable.getItems().clear();
        createClubsTable.getItems().addAll(updatedClubs);
    }

}
