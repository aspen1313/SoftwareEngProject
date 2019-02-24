package com.example.project;

import java.io.Serializable;

/**
 * Essentially just a storage class for User data. Can be extended
 */
public abstract class AbstractUser implements Serializable {
    public String name;
    public String username;
    public String password;
}
