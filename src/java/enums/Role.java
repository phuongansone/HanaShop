/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 * Enumerations for Role
 * @author andtpse62827
 */
public enum Role {
    ADMIN(1),
    USER(2);
    
    private final int roleId;

    private Role(int roleId) {
        this.roleId = roleId;
    }

    public int getValue() {
        return roleId;
    }
}
