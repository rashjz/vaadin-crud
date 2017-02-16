package rashjz.info.domain;

import javax.persistence.*;

/**
 * Created by Mobby on 2/13/2017.
 */
@Entity(name = "vechile")
public class Vechile {
    private Accident accidentId;
    private String regNumber;
    private String marka;
    private String model;
    private int vechileId;


    @Id
    @Column(name = "vechile_id")
    public int getVechileId() {
        return vechileId;
    }

    public void setVechileId(int vechileId) {
        this.vechileId = vechileId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accidentId", nullable = false)
    public Accident getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(Accident accidentId) {
        this.accidentId = accidentId;
    }

    @Column(name = "regNumber")
    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    @Column(name = "marka")
    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


}
