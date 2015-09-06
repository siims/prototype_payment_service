package eu.onepay.payment;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
@Data
public class OurTransaction {
    private Long id = 0L;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Date date;
    private Long timeToWait;

    public Date timeSentOut() {
        return date;
    }
    public void setTimeSentOut(Date date) {
        this.date = date;
    }
}
