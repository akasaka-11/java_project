public class Patient {
    private String name;
    private int age;
    private String diagnosis;

    Patient(String name, int age, String diagnosis){
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
    }
    public void print(){
        System.out.println("Patient: " + name + ", age: " + age + ", diagnosis: " + diagnosis);
    }
    String getName(){
        return this.name;
    }
    int getAge(){
        return this.age;
    }
    String getDiagnosis(){
        return this.diagnosis;
    }
    void setName(String name){
        this.name = name;
    }
    void setAge(int age){
        this.age = age;
    }
    void setDiagnosis(String diagnosis){
        this.diagnosis = diagnosis;
    }
}
