package tfg.front.domain;

import java.util.Date;

public class Ticket {
    int idAuto;
    int idTicket;
    String employee;
    Date dateBuy;
    int idArticle;
    int unit;
    int idTypePay;
    double amount;
    double pay;
    double payBack;

    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public Date getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(Date dateBuy) {
        this.dateBuy = dateBuy;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getIdTypePay() {
        return idTypePay;
    }

    public void setIdTypePay(int idTypePay) {
        this.idTypePay = idTypePay;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public double getPayBack() {
        return payBack;
    }

    public void setPayBack(double payBack) {
        this.payBack = payBack;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idAuto=" + idAuto +
                ", idTicket=" + idTicket +
                ", employee='" + employee + '\'' +
                ", dateBuy=" + dateBuy +
                ", idArticle=" + idArticle +
                ", unit=" + unit +
                ", idTypePay=" + idTypePay +
                ", amount=" + amount +
                ", pay=" + pay +
                ", payBack=" + payBack +
                '}';
    }
}
