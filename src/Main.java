import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
public class Main {
    public static void main(String[] args) {

    Patient p1 = new Patient("Aidos Nurgaliyev",23,"Appendicitis");
    Patient p2 = new Patient("Rasul Bekmuratov",27,"Gastritis");

    MedicalProfessional m1 = new MedicalProfessional("Zhanarbek Tulegenov","Surgeon");
    MedicalProfessional m2 = new MedicalProfessional("Aidar Nurmagambetov","Gastroenterologist");

    Hospital h1 = new Hospital("Astana Medical Center","Ahmet Baitursinuly street 49");

    p1.print();
    p2.print();

    m1.print();
    m2.print();

    h1.print();
    if(p1.getAge()> p2.getAge()) {
        System.out.println(p1.getName() + " is older than " + p2.getName());
    } else if (p1.getAge() < p2.getAge()) {
        System.out.println(p2.getName() + " is older than " + p1.getName());
    }
    //Filtering patients older than 25
        List<Patient> patients = new ArrayList<>();
        patients.add(p1);
        patients.add(p2);

        List<Patient> filteredPatients = patients.stream()
                .filter(p -> p.getAge() > 25)
                .collect(Collectors.toList());

        System.out.println("Filtered Patients (older than 25):");
        filteredPatients.forEach(patient -> patient.print());

        //Searching doctor by specialization

        String searchSpecialization = "Gastroenterologist";
        Optional<MedicalProfessional> foundDoctor = Optional.of(m1); // Search from a predefined list or any search logic
        if (m2.getSpecialization().equalsIgnoreCase(searchSpecialization)) {
            foundDoctor = Optional.of(m2);
        }
        foundDoctor.ifPresent(doctor -> System.out.println("Found Doctor: " + doctor));
        // sorting patients by age
        patients.sort(Comparator.comparingInt(Patient::getAge));

        System.out.println("Sorted Patients by Age:");
        patients.forEach(patient -> patient.print());
    }
}