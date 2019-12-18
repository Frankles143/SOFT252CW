package PatientManagementSystem.Model.System;

/**
 * Medicine object to store relevant information
 * @author Josh Franklin
 */
public class Medicine {
    private String medicineName;
    private int stock;

    public Medicine(String medicineName) {
        this.medicineName = medicineName;
        this.stock = 0;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void OrderStock(int stockIncrease){
        this.stock += stockIncrease;
    }

    public void ReduceStock(int stockDecrease){
        if (this.getStock() > stockDecrease){
            this.stock -= stockDecrease;
        }
        else {
            System.out.println("Not enough medicine in stock for that!");
        }

    }
}
