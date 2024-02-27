package com.example.oopp;

import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceTrackDatabaseConnection {

    private static int currentEventId;


    public static int getCurrentEventId() {
        return currentEventId;
    }

    public static void setCurrentEventId(int eventId) {
        currentEventId = eventId;
    }

    private void attendance_show_Alert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public AttendanceTrackDatabaseConnection() {
        this.connection = eventSheduleDatabaseConnection.getConnection();
    }

    private Connection connection;


    EventSheduleDatabaseConnection eventSheduleDatabaseConnection = new EventSheduleDatabaseConnection();

    // In your DatabaseConnection class

    // In your DatabaseConnection class


    public List<String> getClubNamesByAdvisorId(String advisorId) {
        List<String> clubNames = new ArrayList<>();
        String query = "SELECT DISTINCT clubName " +
                "FROM club " +
                "WHERE teacherId = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = eventSheduleDatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, advisorId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String clubName = resultSet.getString("clubName");
                clubNames.add(clubName);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }

        return clubNames;
    }

    public List<Integer> getEventIdsByClubId(int clubId) {
        List<Integer> eventIds = new ArrayList<>();
        Connection connection = eventSheduleDatabaseConnection.getConnection();


        try {
            // Retrieve eventIds from clubEvents
            String queryEvents = "SELECT eventId FROM clubEvents WHERE clubId = ?";
            try (PreparedStatement preparedStatementEvents = connection.prepareStatement(queryEvents)) {
                preparedStatementEvents.setInt(1, clubId);
                try (ResultSet resultSetEvents = preparedStatementEvents.executeQuery()) {
                    while (resultSetEvents.next()) {
                        int eventId = resultSetEvents.getInt("eventId");
                        eventIds.add(eventId);
                    }
                }
            }

            // Retrieve eventIds from clubMeetings
            String queryMeetings = "SELECT eventId FROM clubMeetings WHERE clubId = ?";
            try (PreparedStatement preparedStatementMeetings = connection.prepareStatement(queryMeetings)) {
                preparedStatementMeetings.setInt(1, clubId);
                try (ResultSet resultSetMeetings = preparedStatementMeetings.executeQuery()) {
                    while (resultSetMeetings.next()) {
                        int eventId = resultSetMeetings.getInt("eventId");
                        eventIds.add(eventId);
                    }
                }
            }

            // Retrieve eventIds from clubActivities
            String queryActivities = "SELECT eventId FROM clubActivities WHERE clubId = ?";
            try (PreparedStatement preparedStatementActivities = connection.prepareStatement(queryActivities)) {
                preparedStatementActivities.setInt(1, clubId);
                try (ResultSet resultSetActivities = preparedStatementActivities.executeQuery()) {
                    while (resultSetActivities.next()) {
                        int eventId = resultSetActivities.getInt("eventId");
                        eventIds.add(eventId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            // Perform other cleanup operations if needed, but do not close the connection
        }

        return eventIds;
    }


    public Map<String, String> getStudentsByClubId(int clubId) {
        Map<String, String> studentsMap = new HashMap<>();
        Connection connection = null;

        try {
            String query = "SELECT sc.studentId, s.studentName FROM student_club sc " +
                    "JOIN student s ON sc.studentId = s.studentId " +
                    "WHERE sc.clubId = ?";

            connection = eventSheduleDatabaseConnection.getConnection();

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, clubId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String studentId = resultSet.getString("studentId");
                        String studentName = resultSet.getString("studentName");
                        studentsMap.put(studentId, studentName);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        } finally {
            // Perform other cleanup operations if needed, but do not close the connection
        }

        return studentsMap;
    }


     public void updateStudentAttendanceTable(Attendance attendance, int eventId) {
        String query = "INSERT INTO StudentAttendance (eventId, studentId) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = eventSheduleDatabaseConnection.getConnection();
            connection.setAutoCommit(false); // Start transaction

            preparedStatement = connection.prepareStatement(query);

            // Insert a record for the attended student
            preparedStatement.setInt(1, eventId);
            preparedStatement.setString(2, attendance.getStudentId());
            preparedStatement.executeUpdate(); // Execute the insert

            connection.commit(); // Commit the transaction

            System.out.println("Data successfully inserted into the StudentAttendance table.");
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception

            try {
                if (connection != null) {
                    connection.rollback(); // Rollback the transaction in case of an exception
                    System.err.println("Rollback performed. Transaction failed.");
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace(); // Handle rollback exception
            }
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException closeException) {
                closeException.printStackTrace(); // Handle the exception appropriately
            }

            try {
                if (connection != null) {
                    connection.close(); // Close the connection
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Log the exception
            }
        }
    }



    public void updateStudentAttendanceMeetingsTable(Attendance attendance, int eventId) {
        String query = "INSERT INTO StudentAttendanceMeetings (eventId, studentId) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = eventSheduleDatabaseConnection.getConnection();
            connection.setAutoCommit(false); // Start transaction

            preparedStatement = connection.prepareStatement(query);

            // Insert a record for the attended student
            preparedStatement.setInt(1, eventId);
            preparedStatement.setString(2, attendance.getStudentId());
            preparedStatement.executeUpdate(); // Execute the insert

            connection.commit(); // Commit the transaction

            System.out.println("Data successfully inserted into the StudentAttendanceMeetings table.");

        } catch (SQLIntegrityConstraintViolationException duplicateException) {
            // Handle the exception for duplicate entry
            System.err.println("Duplicate entry: " + duplicateException.getMessage());

            attendance_show_Alert("Error", "Duplicate entry: A duplicate entry already exists.");

            // You can choose to log the error, show a message to the user, or take other appropriate actions.
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception

            try {
                if (connection != null) {
                    connection.rollback(); // Rollback the transaction in case of an exception
                    System.err.println("Rollback performed. Transaction failed.");
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace(); // Handle rollback exception
            }
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException closeException) {
                closeException.printStackTrace(); // Handle the exception appropriately
            }


        }
    }

    public void updateStudentAttendanceActivityTable(Attendance attendance, int eventId) {
        String query = "INSERT INTO StudentAttendanceActivity (eventId, studentId) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = eventSheduleDatabaseConnection.getConnection();
            connection.setAutoCommit(false); // Start transaction

            preparedStatement = connection.prepareStatement(query);

            // Insert a record for the attended student
            preparedStatement.setInt(1, eventId);
            preparedStatement.setString(2, attendance.getStudentId());
            preparedStatement.executeUpdate(); // Execute the insert

            connection.commit(); // Commit the transaction

            System.out.println("Data successfully inserted into the StudentAttendanceActivity table.");
        } catch (SQLIntegrityConstraintViolationException duplicateException) {
            // Handle the exception for duplicate entry
            System.err.println("Duplicate entry: " + duplicateException.getMessage());

            attendance_show_Alert("Error", "Duplicate entry: A duplicate entry already exists.");

            // You can choose to log the error, show a message to the user, or take other appropriate actions.
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception

            try {
                if (connection != null) {
                    connection.rollback(); // Rollback the transaction in case of an exception
                    System.err.println("Rollback performed. Transaction failed.");
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace(); // Handle rollback exception
            }
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException closeException) {
                closeException.printStackTrace(); // Handle the exception appropriately
            }


        }
    }

    public String getEventType(int eventId) {
        String eventType = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = eventSheduleDatabaseConnection.getConnection();

            // Check if the event exists in the clubevents table
            String queryEvents = "SELECT eventId FROM clubevents WHERE eventId = ?";
            preparedStatement = connection.prepareStatement(queryEvents);
            preparedStatement.setInt(1, eventId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                eventType = "Event";
            } else {
                // Check if the event exists in the clubmeetings table
                String queryMeetings = "SELECT eventId FROM clubmeetings WHERE eventId = ?";
                preparedStatement = connection.prepareStatement(queryMeetings);
                preparedStatement.setInt(1, eventId);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    eventType = "Meeting";
                } else {
                    // Check if the event exists in the clubactivities table
                    String queryActivities = "SELECT eventId FROM clubactivities WHERE eventId = ?";
                    preparedStatement = connection.prepareStatement(queryActivities);
                    preparedStatement.setInt(1, eventId);
                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        eventType = "Activity";
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace(); // Log the exception
            }
        }

        return eventType;
    }


    public Map<String, String> searchStudentsByStudentIdAndClub(String studentId, int clubId) {
        Map<String, String> searchResults = new HashMap<>();
        Connection connection = null;

        try {
            String query = "SELECT sc.studentId, s.studentName FROM student_club sc " +
                    "JOIN student s ON sc.studentId = s.studentId " +
                    "WHERE sc.clubId = ? AND s.studentId = ?";

            connection = eventSheduleDatabaseConnection.getConnection();

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, clubId);
                preparedStatement.setString(2, studentId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String resultStudentId = resultSet.getString("studentId");
                        String resultStudentName = resultSet.getString("studentName");
                        searchResults.put(resultStudentId, resultStudentName);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        } finally {
            // Perform other cleanup operations if needed, but do not close the connection
        }

        return searchResults;
    }


}
