/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAW;

/**
 *
 * @author Tema 3.4
 */
class Util {
    public static String getParam(String param, String defecto) {
        if (param!=null && !param.trim().equals(""))
            param=param.trim();
        else
            param=defecto;
        return param;
    }
}
