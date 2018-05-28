/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.billar.models;

/**
 *
 * @author Usuari
 */
public class ModalitatException extends RuntimeException {

    public ModalitatException(String message) {
        super(message);
    }

    public ModalitatException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
