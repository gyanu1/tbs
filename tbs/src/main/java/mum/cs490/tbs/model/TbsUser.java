/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author gyanu
 */
@Entity
public class TbsUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private String username;
    private String password;
    @ManyToOne
    @Cascade(CascadeType.ALL)
    private UserRole role;
    @OneToMany(mappedBy = "salesRep", fetch = FetchType.EAGER)
    private List<Customer> customerList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbsUser)) {
            return false;
        }
        TbsUser other = (TbsUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mum.cs490.tbs.model.TbsUser[ id=" + id + " ]";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return role;
    }

    public void setUserRole(UserRole userRole) {
        this.role = userRole;
    }

    public TbsUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public TbsUser(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public TbsUser() {
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

}
