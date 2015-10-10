package com.targa.dev.fixcenter.business.entity;

import com.targa.dev.fixcenter.business.entity.enums.DeviceFamily;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "DEVICES")
@NamedQueries({
    @NamedQuery(name = "Device.findAll", query = "SELECT d FROM Device d"),
    @NamedQuery(name = "Device.findBySerial", query = "SELECT d FROM Device d WHERE d.serialNumber LIKE :serial")
})
public class Device implements Serializable {

    public static final String findAll = "Device.findAll";
    public static final String findBySerial = "Device.findBySerial";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @NotNull
    @Column(name = "deviceFamily")
    @Enumerated(EnumType.STRING)
    private DeviceFamily deviceFamily;
    @NotNull
    @Column(name = "depositDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date depositDate;
    @Column(name = "withdrawalDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date withdrawalDate;
    @Size(max = 50)
    @Column(name = "serialNumber", length = 50)
    private String serialNumber;
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

    public Device() {
        this("", DeviceFamily.PC, new Date(), "");
    }

    public Device(String name, DeviceFamily deviceFamily, Date depositDate, String serialNumber) {
        this(name, deviceFamily, depositDate, serialNumber, new Date(), true, false);
    }

    private Device(String name, DeviceFamily deviceFamily, Date depositDate, String serialNumber, Date creation, Boolean enabled, Boolean system) {
        this.name = name;
        this.deviceFamily = deviceFamily;
        this.depositDate = depositDate;
        this.serialNumber = serialNumber;
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
     * @return the deviceFamily
     */
    public DeviceFamily getDeviceFamily() {
        return deviceFamily;
    }

    /**
     * @param deviceFamily the deviceFamily to set
     */
    public void setDeviceFamily(DeviceFamily deviceFamily) {
        this.deviceFamily = deviceFamily;
    }

    /**
     * @return the depositDate
     */
    public Date getDepositDate() {
        return depositDate;
    }

    /**
     * @param depositDate the depositDate to set
     */
    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    /**
     * @return the withdrawalDate
     */
    public Date getWithdrawalDate() {
        return withdrawalDate;
    }

    /**
     * @param withdrawalDate the withdrawalDate to set
     */
    public void setWithdrawalDate(Date withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.serialNumber);
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
        final Device other = (Device) obj;
        if (!Objects.equals(this.serialNumber, other.serialNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Device{" + "name=" + name
                + ", deviceFamily=" + deviceFamily
                + ", depositDate=" + depositDate
                + ", withdrawalDate=" + withdrawalDate
                + ", serialNumber=" + serialNumber
                + ", description=" + description
                + '}';
    }

}
