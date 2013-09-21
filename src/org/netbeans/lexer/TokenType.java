/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.lexer;

/**
 *
 * @author constantin
 */
public enum TokenType {
    
    COMPLEX(58,"type"),
    FLOAT(57,"type"),
    ID(60,"identifier"),
    HEX(55,"type"),
    USER_OP(61,"operator"),
    NL(62,"keyword"),
    INT(56,"number"),
    WS(63,"whitespace"),
    STRING(59,"string"),
    T__18(36,"operator");
    
    public int id ; 
    public String category; 
    public String text ; 

    private TokenType(int id, String category) {
    
        this.id = id;
        this.category = category;
        
    }

  public static TokenType valueOf(int id) {
        TokenType[] values = values();
        for (TokenType value : values) {
            if (value.id == id) {
                return value;
            }
        }
        throw new IllegalArgumentException("The id " + id + " is not recognized");
    }
}
