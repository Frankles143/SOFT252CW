package PatientManagementSystem.Model.Users;

public abstract class AbstractPerson {
    private String id;
    private String name;
    private String address;
    final String regex = "^[ADPS]\\d{4}$";  //This will only allow pattern of A0123

    public AbstractPerson(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this. address = address;
    }

    public String getId() {
        return id;
    }

    //Checks whether the ID matches a set regex pattern before setting
    public void setId(String id) {
        if (id.matches(regex)) {
            this.id = id;
        } else {
            System.out.println("ID does not match format");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
