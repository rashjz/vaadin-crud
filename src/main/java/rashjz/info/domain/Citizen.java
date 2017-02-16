package rashjz.info.domain;

import javax.persistence.*;

/**
 * Created by Mobby on 2/13/2017.
 */
@Entity(name = "citizen")
public class Citizen {
    private int citizenId;
    private String name;
    private String surname;
    private String patronymic;
    private String pin;
    private String idNumber;
    private String idSeries;
    private String regAddress;
    private String note;
    private Byte status;
    private Accident accidentId;

    @Id
    @Column(name = "citizenId")
    public int getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(int citizenId) {
        this.citizenId = citizenId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "patronymic")
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Column(name = "pin")
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Column(name = "idNumber")
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Column(name = "idSeries")
    public String getIdSeries() {
        return idSeries;
    }

    public void setIdSeries(String idSeries) {
        this.idSeries = idSeries;
    }

    @Column(name = "regAddress")
    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accidentId", nullable = false)
    public Accident getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(Accident accidentId) {
        this.accidentId = accidentId;
    }
}
