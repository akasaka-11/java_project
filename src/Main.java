public class Main {
    public static void main(String[] args) {
        try {
            HospitalDAO hospitalDAO = new HospitalDAO();
            PatientDAO patientDAO = new PatientDAO();

            // CREATE hospital
            int hospitalId = hospitalDAO.createHospital(
                    "Astana Medical Center",
                    "Ahmet Baitursinuly street 49"
            );
            System.out.println("Created hospital id = " + hospitalId);

            // CREATE patients
            int p1Id = patientDAO.createPatient("Aidos Nurgaliyev", 23, "Appendicitis", hospitalId);
            int p2Id = patientDAO.createPatient("Rasul Bekmuratov", 27, "Gastritis", hospitalId);
            System.out.println("Created patients ids = " + p1Id + ", " + p2Id);

            // READ
            System.out.println("\nAll hospitals:");
            hospitalDAO.getAllHospitals().forEach(System.out::println);

            System.out.println("\nAll patients:");
            patientDAO.getAllPatients().forEach(System.out::println);

            // UPDATE
            System.out.println("\nUpdating diagnosis for patient " + p2Id);
            patientDAO.updateDiagnosis(p2Id, "Chronic Gastritis");

            System.out.println("Updating address for hospital " + hospitalId);
            hospitalDAO.updateHospitalAddress(hospitalId, "New address 10");

            // READ after update
            System.out.println("\nPatients after update:");
            patientDAO.getAllPatients().forEach(System.out::println);

            System.out.println("\nHospitals after update:");
            hospitalDAO.getAllHospitals().forEach(System.out::println);

            // DELETE
            System.out.println("\nDeleting patient " + p1Id);
            patientDAO.deletePatient(p1Id);

            System.out.println("Deleting hospital " + hospitalId);
            hospitalDAO.deleteHospital(hospitalId);

            // READ after delete
            System.out.println("\nPatients after delete:");
            patientDAO.getAllPatients().forEach(System.out::println);

            System.out.println("\nHospitals after delete:");
            hospitalDAO.getAllHospitals().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
