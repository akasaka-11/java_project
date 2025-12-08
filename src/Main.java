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
    }
}