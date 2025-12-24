public class MedicalProfessional extends Person {
    private String specialization;

    MedicalProfessional(String name, String specialization) {
        super(name);
        this.specialization = specialization;
    }
    public void print(){
        System.out.println("Doctor: " + getName() + ", Specialization: " + specialization);
    }
    @Override
    public String toString(){
        return "Doctor name: " + getName() + ", specialization: " + this.specialization;
    };
    public String getSpecialization(){
        return this.specialization;
    }
    void setSpecialization(String specialization){
        this.specialization = specialization;
    }
}
