package com.targa.dev.fixcenter.business.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nebrass
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "CUSTOMERS")
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByName", query = "SELECT c FROM Customer c WHERE c.name = :name")
})
public class Customer extends Person {

    public static final String findAll = "Customer.findAll";
    public static final String findByName = "Customer.findByName";

    @OneToMany(cascade = CascadeType.ALL)
    private List<Device> devices;

    public Customer() {
        this("", "", null, "", new Date(), Boolean.TRUE, Boolean.FALSE, new ArrayList<Device>());
    }

    public Customer(String name, String familyName, Date birthDate, String description, Date creation, Boolean enabled, Boolean system, List<Device> devices) {
        super(name, familyName, birthDate, description, creation, enabled, system);
        this.devices = devices;
    }

    /**
     * @return the devices
     */
    public List<Device> getDevices() {
        return devices;
    }

    /**
     * @param devices the devices to set
     */
    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {

        return "Customer{ name=" + this.getName()
                + ", familyName=" + this.getFamilyName()
                + ", birthDate=" + this.getBirthDate()
                + ", description=" + this.getDescription()
                + ", devices{ "
                + this.getDevices()
                + "}"
                + "}";
    }

}
