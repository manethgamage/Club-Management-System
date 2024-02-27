package com.example.oopp;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import java.util.AbstractMap;
import java.util.Map;

public class Attendance {
    private SimpleStringProperty studentId;
    private SimpleStringProperty studentName;
    private SimpleBooleanProperty attendance;
    private CheckBox attendanceCheckBox;



    public Attendance(String studentId, String studentName, String uniqueIdentifier, boolean attendance) {
        this.studentId = new SimpleStringProperty(studentId);
        this.studentName = new SimpleStringProperty(studentName);
        this.attendance = new SimpleBooleanProperty(attendance);
        this.attendanceCheckBox = new CheckBox();
        this.attendanceCheckBox.selectedProperty().bindBidirectional(this.attendance);
    }
    public Map.Entry<String, String> toMapEntry() {
        return new AbstractMap.SimpleEntry<>(studentId.get(), studentName.get());
    }

    public boolean isSelected() {
        return attendanceCheckBox.isSelected();
    }

    public void setSelected(boolean selected) {
        attendanceCheckBox.setSelected(selected);
    }


    public CheckBox getAttendanceCheckBox() {
        return attendanceCheckBox;
    }

    public void setAttendanceCheckBox(CheckBox attendanceCheckBox) {
        this.attendanceCheckBox = attendanceCheckBox;
    }

    public void setAttendanceCheckbox(boolean isPresent) {
        this.attendanceCheckBox.setSelected(isPresent);
    }



    public String getStudentId() {
        return studentId.get();
    }

    public void setStudentId(String studentId) {
        this.studentId.set(studentId);
    }

    public SimpleStringProperty studentIdProperty() {
        return studentId;
    }

    public String getStudentName() {
        return studentName.get();
    }

    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }

    public SimpleStringProperty studentNameProperty() {
        return studentName;
    }

    public boolean getAttendance() {
        return attendance.get();
    }

    public void setAttendance(boolean attendance) {
        this.attendance.set(attendance);
    }

    public SimpleBooleanProperty attendanceProperty() {
        return attendance;
    }


}
