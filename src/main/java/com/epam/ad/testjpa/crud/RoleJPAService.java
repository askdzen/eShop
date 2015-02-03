package com.epam.ad.testjpa.crud;


import com.epam.ad.testjpa.entity.Role;
import org.jboss.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Stateless
@Named
public class RoleJPAService extends JPAService<Role> {
    @Inject
    Logger logger;
    @Inject
    private Role role;
    @Inject
    private EntityManager entityManager;


    public RoleJPAService() {
        super(Role.class);
    }
    public Role getRoleByName(String roleName){
        return (Role) entityManager.createNamedQuery("Role.findByName").setParameter("rname",roleName).getSingleResult();
    }


}
