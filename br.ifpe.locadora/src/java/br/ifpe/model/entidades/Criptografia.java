/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifpe.model.entidades;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Izack
 */
public class Criptografia {

    public String toEncrypt(String senha) {

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");

            m.update(senha.getBytes(), 0, senha.length());

            return new BigInteger(1, m.digest()).toString(16);

        } catch (NoSuchAlgorithmException ex) {

            Logger.getLogger(Object.class.getName()).log(Level.SEVERE, null, ex);

        }
        return "";
    }
}
