package dto;

import java.io.Serializable;

/**
 *
 * @author andtpse62827
 */
public class PriceRangeDTO implements Serializable {
    private int id;
    private String name;
    private int from;
    private int to;
    private boolean status;

    public PriceRangeDTO(int id, String name, int from, int to, boolean status) {
        this.id = id;
        this.name = name;
        this.from = from;
        this.to = to;
        this.status = status;
    }

    public PriceRangeDTO(int id, String name, int from, int to) {
        this(id, name, from, to, true);
    }
    
    

    public PriceRangeDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
