package Model.Users;

import PatientManagementSystem.Model.System.FeedbackData;
import PatientManagementSystem.Model.Gender;
import PatientManagementSystem.Model.System.ConsultationNote;
import PatientManagementSystem.Model.System.DoctorFeedback;

import java.io.Serializable;
import java.util.ArrayList;

public class Patient extends AbstractPerson implements Serializable {

    private Gender gender;
    private int age;
    private ArrayList<ConsultationNote> consultationNotes;

    public Patient(String id, String name, String address, Gender gender, int age) {
        super(id, name, address);
         this.gender = gender;
         this.age = age;
         this.consultationNotes = new ArrayList<>();
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Can be used by the controller to output patient history
     * @return ArrayList of this patients consultation notes
     */
    public ArrayList<ConsultationNote> getConsultationNotes() {
        return consultationNotes;
    }

    public void addConsultationNotes(ConsultationNote newNote){
        consultationNotes.add(newNote);
    }

    /**
     * Creates feedback object and stores it where the admins can check through it
     * @param doctor doctor you are creating feedback for
     * @param rating an int between 1-10
     * @param feedbackNotes the actual feedback string
     * @author Josh Franklin
     */
    public void CreateFeedback(Doctor doctor, int rating, String feedbackNotes){
        DoctorFeedback newFeedback = null;
        try {
            newFeedback = new DoctorFeedback(doctor, rating, feedbackNotes);
            FeedbackData.uncheckedFeedback.add(newFeedback);
        } catch (Exception e) {
            System.out.println("Cannot add new feedback");
        }
    }

    /**
     * Returns the checked feedback attached to that Doctor
     * @param doctor doctor that you want all feedback for
     * @return ArrayList of feedback objects
     * @author Josh Franklin
     */
    public ArrayList<DoctorFeedback> ViewDoctorFeedback(Doctor doctor){
        ArrayList<DoctorFeedback> feedback = null;
        try {
            feedback = doctor.getFeedback();
        }
        catch (Exception e) {
            System.out.println("Error getting feedback!");
        }
        return feedback;
    }
}
