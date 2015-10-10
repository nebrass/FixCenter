package com.targa.dev.fixcenter.business.entity;

import com.targa.dev.fixcenter.business.entity.enums.RepairStatus;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.UuidGenerator;

/**
 *
 * @author nebrass
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "REPAIRS")
@NamedQueries({
    @NamedQuery(name = "Repair.findAll", query = "SELECT r FROM Repair r")
})
public class Repair implements Serializable {

    public static final String findAll = "Repair.findAll";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private RepairStatus status;
    @Column(name = "amount")
    private double amount;
    @OneToOne
    @JoinColumn(name = "deviceId")
    private Device device;
    @Size(max = 1000)
    @Column(name = "description", length = 1000)
    private String description;
    @NotNull
    @Past
    @Column(name = "creation", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
    @Column(name = "system", nullable = false)
    private Boolean system;
    @Version
    @Column(name = "version", nullable = false)
    private Timestamp version;

    public Repair() {
        this("", RepairStatus.DEPOSITED, "", new Date(), true, false);
    }

    public Repair(String name, RepairStatus status, String description, Date creation, Boolean enabled, Boolean system) {
        this.name = name;
        this.status = status;
        this.description = description;
        this.creation = creation;
        this.enabled = enabled;
        this.system = system;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the status
     */
    public RepairStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(RepairStatus status) {
        this.status = status;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the device
     */
    public Device getDevice() {
        return device;
    }

    /**
     * @param device the device to set
     */
    public void setDevice(Device device) {
        this.device = device;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean isSystem() {
        return system;
    }

    public void setSystem(Boolean system) {
        this.system = system;
    }

    public Timestamp getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.status);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Repair other = (Repair) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Repair{" + "name=" + name + ", status=" + status + ", amount=" + amount + ", device=" + device + ", description=" + description + '}';
    }

}
