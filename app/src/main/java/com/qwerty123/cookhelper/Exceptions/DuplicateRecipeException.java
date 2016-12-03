package com.qwerty123.cookhelper.Exceptions;

/**
 * Created by Ralph on 12/2/2016.
 */

public class DuplicateRecipeException extends Exception {

    /* This is thrown when a any two recipes have the same name
     * or the same details
     */
    public DuplicateRecipeException(){
        super();
    }
}
