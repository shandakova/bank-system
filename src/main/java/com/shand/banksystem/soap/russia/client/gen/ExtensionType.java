
package com.shand.banksystem.soap.russia.client.gen;

import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for extensionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="extensionType">
 *   <complexContent>
 *     <extension base="{http://www.w3.org/2001/XMLSchema}annotated">
 *       <sequence>
 *         <group ref="{http://www.w3.org/2001/XMLSchema}typeDefParticle" minOccurs="0"/>
 *         <group ref="{http://www.w3.org/2001/XMLSchema}attrDecls"/>
 *       </sequence>
 *       <attribute name="base" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *       <anyAttribute processContents='lax' namespace='##other'/>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "extensionType", namespace = "http://www.w3.org/2001/XMLSchema", propOrder = {
    "group",
    "all",
    "choice",
    "sequence",
    "attributeOrAttributeGroup",
    "anyAttribute"
})
@XmlSeeAlso({
    SimpleExtensionType.class
})
public class ExtensionType
    extends Annotated
{

    protected GroupRef group;
    protected All all;
    protected ExplicitGroup choice;
    protected ExplicitGroup sequence;
    @XmlElements({
        @XmlElement(name = "attribute", type = Attribute.class),
        @XmlElement(name = "attributeGroup", type = AttributeGroupRef.class)
    })
    protected List<Annotated> attributeOrAttributeGroup;
    protected Wildcard anyAttribute;
    @XmlAttribute(name = "base", required = true)
    protected QName base;

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link GroupRef }
     *     
     */
    public GroupRef getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupRef }
     *     
     */
    public void setGroup(GroupRef value) {
        this.group = value;
    }

    /**
     * Gets the value of the all property.
     * 
     * @return
     *     possible object is
     *     {@link All }
     *     
     */
    public All getAll() {
        return all;
    }

    /**
     * Sets the value of the all property.
     * 
     * @param value
     *     allowed object is
     *     {@link All }
     *     
     */
    public void setAll(All value) {
        this.all = value;
    }

    /**
     * Gets the value of the choice property.
     * 
     * @return
     *     possible object is
     *     {@link ExplicitGroup }
     *     
     */
    public ExplicitGroup getChoice() {
        return choice;
    }

    /**
     * Sets the value of the choice property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExplicitGroup }
     *     
     */
    public void setChoice(ExplicitGroup value) {
        this.choice = value;
    }

    /**
     * Gets the value of the sequence property.
     * 
     * @return
     *     possible object is
     *     {@link ExplicitGroup }
     *     
     */
    public ExplicitGroup getSequence() {
        return sequence;
    }

    /**
     * Sets the value of the sequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExplicitGroup }
     *     
     */
    public void setSequence(ExplicitGroup value) {
        this.sequence = value;
    }

    /**
     * Gets the value of the attributeOrAttributeGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the attributeOrAttributeGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributeOrAttributeGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Attribute }
     * {@link AttributeGroupRef }
     * 
     * 
     * @return
     *     The value of the attributeOrAttributeGroup property.
     */
    public List<Annotated> getAttributeOrAttributeGroup() {
        if (attributeOrAttributeGroup == null) {
            attributeOrAttributeGroup = new ArrayList<>();
        }
        return this.attributeOrAttributeGroup;
    }

    /**
     * Gets the value of the anyAttribute property.
     * 
     * @return
     *     possible object is
     *     {@link Wildcard }
     *     
     */
    public Wildcard getAnyAttribute() {
        return anyAttribute;
    }

    /**
     * Sets the value of the anyAttribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link Wildcard }
     *     
     */
    public void setAnyAttribute(Wildcard value) {
        this.anyAttribute = value;
    }

    /**
     * Gets the value of the base property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getBase() {
        return base;
    }

    /**
     * Sets the value of the base property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setBase(QName value) {
        this.base = value;
    }

}
