
package com.shand.banksystem.soap.russia.client.gen;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for reducedDerivationControl.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>{@code
 * <simpleType name="reducedDerivationControl">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}derivationControl">
 *     <enumeration value="extension"/>
 *     <enumeration value="restriction"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "reducedDerivationControl", namespace = "http://www.w3.org/2001/XMLSchema")
@XmlEnum(DerivationControl.class)
public enum ReducedDerivationControl {

    @XmlEnumValue("extension")
    EXTENSION(DerivationControl.EXTENSION),
    @XmlEnumValue("restriction")
    RESTRICTION(DerivationControl.RESTRICTION);
    private final DerivationControl value;

    ReducedDerivationControl(DerivationControl v) {
        value = v;
    }

    public DerivationControl value() {
        return value;
    }

    public static ReducedDerivationControl fromValue(DerivationControl v) {
        for (ReducedDerivationControl c: ReducedDerivationControl.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
