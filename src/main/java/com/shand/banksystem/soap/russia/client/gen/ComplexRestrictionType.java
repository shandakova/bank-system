
package com.shand.banksystem.soap.russia.client.gen;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for complexRestrictionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="complexRestrictionType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}restrictionType">
 *       <sequence>
 *         <element ref="{http://www.w3.org/2001/XMLSchema}annotation" minOccurs="0"/>
 *         <choice minOccurs="0">
 *           <group ref="{http://www.w3.org/2001/XMLSchema}typeDefParticle"/>
 *         </choice>
 *         <group ref="{http://www.w3.org/2001/XMLSchema}attrDecls"/>
 *       </sequence>
 *       <anyAttribute processContents='lax' namespace='##other'/>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "complexRestrictionType", namespace = "http://www.w3.org/2001/XMLSchema")
public class ComplexRestrictionType
    extends RestrictionType
{


}
