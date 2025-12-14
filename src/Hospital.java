public class Hospital {
    private String name;
    private String address;

    Hospital(String name, String address){
        this.name = name;
        this.address = address;
    }
    public void print(){
        System.out.println("Hospital: " + name + ", Address: " + address);
    }
    String getName(){
        return this.name;
    }
    String getAddress(){
        return this.address;
    }
    void setName(String name){
        this.name = name;
    }
    void setAddress(String address){
        this.address = address;
    }
}
