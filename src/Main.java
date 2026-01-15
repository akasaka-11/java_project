public class Main {
    public static void main(String[] args) {
        try {
            HospitalDAO hospitalDAO = new HospitalDAO();
            PatientDAO patientDAO = new PatientDAO();
            MedicalProfessionalDAO medicalDAO = new MedicalProfessionalDAO();

            // CREATE hospital
            int hospitalId = hospitalDAO.createHospital(
                    "Astana Medical Center",
                    "Ahmet Baitursinuly street 49"
            );
            System.out.println("Created hospital id = " + hospitalId);

            // CREATE patients
            int p1Id = patientDAO.createPatient(
                    "Aidos Nurgaliyev", 23, "Appendicitis", hospitalId);
            int p2Id = patientDAO.createPatient(
                    "Rasul Bekmuratov", 27, "Gastritis", hospitalId);

            System.out.println("Created patients ids = " + p1Id + ", " + p2Id);

            // CREATE medical professionals
            int d1Id = medicalDAO.createMedicalProfessional(
                    "Dr. Kanat Saparov", 45, "Surgeon", hospitalId);
            int d2Id = medicalDAO.createMedicalProfessional(
                    "Dr. Ainur Tulegenova", 39, "Gastroenterologist", hospitalId);

            System.out.println("Created medical professionals ids = " + d1Id + ", " + d2Id);

            // READ
            System.out.println("\nAll hospitals:");
            hospitalDAO.getAllHospitals().forEach(System.out::println);

            System.out.println("\nAll patients:");
            patientDAO.getAllPatients().forEach(System.out::println);

            System.out.println("\nAll medical professionals:");
            medicalDAO.getAllMedicalProfessionals().forEach(System.out::println);

            // UPDATE
            System.out.println("\nUpdating diagnos
