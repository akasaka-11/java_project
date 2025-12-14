public class MedicalProfessional {
    private String name;
    private String specialization;

    MedicalProfessional(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }
    public void print(){
        System.out.println("Doctor: " + name + ", Specialization" + specialization);
    }
    String getName(){
        return this.name;
    }
    String getSpecialization(){
        return this.specialization;
    }
    void setName(String name){
        this.name = name;
    }
    void setSpecialization(String specialization){
        this.specialization = specialization;
    }
}
