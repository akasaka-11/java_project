public class Patient extends Person {
    private int age;
    private String diagnosis;

    Patient(String name, int age, String diagnosis){
        super(name);
        this.age = age;
        this.diagnosis = diagnosis;
    }
    public void print(){
        System.out.println("Patient: " + getName() + ", age: " + age + ", diagnosis: " + diagnosis);
    }
    int getAge(){
        return this.age;
    }
    String getDiagnosis(){
        return this.diagnosis;
    }
    void setAge(int age){
        this.age = age;
    }
    void setDiagnosis(String diagnosis){
        this.diagnosis = diagnosis;
    }
    @Override
    public String toString(){
        return "Name: " + getName() + ", age: " + getAge() + ", diagnosis: " + getDiagnosis();
    }
}
