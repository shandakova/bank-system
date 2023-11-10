
package com.shand.banksystem.soap.russia.client.gen;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <extension base="{http://www.w3.org/2001/XMLSchema}openAttrs">
 *       <sequence>
 *         <choice maxOccurs="unbounded" minOccurs="0">
 *           <element ref="{http://www.w3.org/2001/XMLSchema}include"/>
 *           <element ref="{http://www.w3.org/2001/XMLSchema}import"/>
 *           <element ref="{http://www.w3.org/2001/XMLSchema}redefine"/>
 *           <element ref="{http://www.w3.org/2001/XMLSchema}annotation"/>
 *         </choice>
 *         <sequence maxOccurs="unbounded" minOccurs="0">
 *           <group ref="{http://www.w3.org/2001/XMLSchema}schemaTop"/>
 *           <element ref="{http://www.w3.org/2001/XMLSchema}annotation" maxOccurs="unbounded" minOccurs="0"/>
 *         </sequence>
 *       </sequence>
 *       <attribute name="targetNamespace" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       <attribute name="version" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       <attribute name="finalDefault" type="{http://www.w3.org/2001/XMLSchema}fullDerivationSet" default="" />
 *       <attribute name="blockDefault" type="{http://www.w3.org/2001/XMLSchema}blockSet" default="" />
 *       <attribute name="attributeFormDefault" type="{http://www.w3.org/2001/XMLSchema}formChoice" default="unqualified" />
 *       <attribute name="elementFormDefault" type="{http://www.w3.org/2001/XMLSchema}formChoice" default="unqualified" />
 *       <attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       <attribute ref="{http://www.w3.org/XML/1998/namespace}lang"/>
 *       <anyAttribute processContents='lax' namespace='##other'/>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "includeOrImportOrRedefine",
    "simpleTypeOrComplexTypeOrGroup"
})
@XmlRootElement(name = "schema", namespace = "http://www.w3.org/2001/XMLSchema")
public class Schema
    extends OpenAttrs
{

    @XmlElements({
        @XmlElement(name = "include", namespace = "http://www.w3.org/2001/XMLSchema", type = Include.class),
        @XmlElement(name = "import", namespace = "http://www.w3.org/2001/XMLSchema", type = Import.class),
        @XmlElement(name = "redefine", namespace = "http://www.w3.org/2001/XMLSchema", type = Redefine.class),
        @XmlElement(name = "annotation", namespace = "http://www.w3.org/2001/XMLSchema", type = Annotation.class)
    })
    protected List<OpenAttrs> includeOrImportOrRedefine;
    @XmlElements({
        @XmlElement(name = "simpleType", namespace = "http://www.w3.org/2001/XMLSchema", type = TopLevelSimpleType.class),
        @XmlElement(name = "complexType", namespace = "http://www.w3.org/2001/XMLSchema", type = TopLevelComplexType.class),
        @XmlElement(name = "group", namespace = "http://www.w3.org/2001/XMLSchema", type = NamedGroup.class),
        @XmlElement(name = "attributeGroup", namespace = "http://www.w3.org/2001/XMLSchema", type = NamedAttributeGroup.class),
        @XmlElement(name = "element", namespace = "http://www.w3.org/2001/XMLSchema", type = TopLevelElement.class),
        @XmlElement(name = "attribute", namespace = "http://www.w3.org/2001/XMLSchema", type = TopLevelAttribute.class),
        @XmlElement(name = "notation", namespace = "http://www.w3.org/2001/XMLSchema", type = Notation.class),
        @XmlElement(name = "annotation", namespace = "http://www.w3.org/2001/XMLSchema", type = Annotation.class)
    })
    protected List<OpenAttrs> simpleTypeOrComplexTypeOrGroup;
    @XmlAttribute(name = "targetNamespace")
    @XmlSchemaType(name = "anyURI")
    protected String targetNamespace;
    @XmlAttribute(name = "version")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String version;
    @XmlAttribute(name = "finalDefault")
    @XmlSchemaType(name = "fullDerivationSet")
    protected List<String> finalDefault;
    @XmlAttribute(name = "blockDefault")
    @XmlSchemaType(name = "blockSet")
    protected List<String> blockDefault;
    @XmlAttribute(name = "attributeFormDefault")
    protected FormChoice attributeFormDefault;
    @XmlAttribute(name = "elementFormDefault")
    protected FormChoice elementFormDefault;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    protected String lang;

    /**
     * Gets the value of the includeOrImportOrRedefine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the includeOrImportOrRedefine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncludeOrImportOrRedefine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Annotation }
     * {@link Import }
     * {@link Include }
     * {@link Redefine }
     * 
     * 
     * @return
     *     The value of the includeOrImportOrRedefine property.
     */
    public List<OpenAttrs> getIncludeOrImportOrRedefine() {
        if (includeOrImportOrRedefine == null) {
            includeOrImportOrRedefine = new ArrayList<>();
        }
        return this.includeOrImportOrRedefine;
    }

    /**
     * Gets the value of the simpleTypeOrComplexTypeOrGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the simpleTypeOrComplexTypeOrGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSimpleTypeOrComplexTypeOrGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Annotation }
     * {@link NamedAttributeGroup }
     * {@link NamedGroup }
     * {@link Notation }
     * {@link TopLevelAttribute }
     * {@link TopLevelComplexType }
     * {@link TopLevelElement }
     * {@link TopLevelSimpleType }
     * 
     * 
     * @return
     *     The value of the simpleTypeOrComplexTypeOrGroup property.
     */
    public List<OpenAttrs> getSimpleTypeOrComplexTypeOrGroup() {
        if (simpleTypeOrComplexTypeOrGroup == null) {
            simpleTypeOrComplexTypeOrGroup = new ArrayList<>();
        }
        return this.simpleTypeOrComplexTypeOrGroup;
    }

    /**
     * Gets the value of the targetNamespace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetNamespace() {
        return targetNamespace;
    }

    /**
     * Sets the value of the targetNamespace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetNamespace(String value) {
        this.targetNamespace = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the finalDefault property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the finalDefault property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFinalDefault().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the finalDefault property.
     */
    public List<String> getFinalDefault() {
        if (finalDefault == null) {
            finalDefault = new ArrayList<>();
        }
        return this.finalDefault;
    }

    /**
     * Gets the value of the blockDefault property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the blockDefault property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBlockDefault().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the blockDefault property.
     */
    public List<String> getBlockDefault() {
        if (blockDefault == null) {
            blockDefault = new ArrayList<>();
        }
        return this.blockDefault;
    }

    /**
     * Gets the value of the attributeFormDefault property.
     * 
     * @return
     *     possible object is
     *     {@link FormChoice }
     *     
     */
    public FormChoice getAttributeFormDefault() {
        if (attributeFormDefault == null) {
            return FormChoice.UNQUALIFIED;
        } else {
            return attributeFormDefault;
        }
    }

    /**
     * Sets the value of the attributeFormDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormChoice }
     *     
     */
    public void setAttributeFormDefault(FormChoice value) {
        this.attributeFormDefault = value;
    }

    /**
     * Gets the value of the elementFormDefault property.
     * 
     * @return
     *     possible object is
     *     {@link FormChoice }
     *     
     */
    public FormChoice getElementFormDefault() {
        if (elementFormDefault == null) {
            return FormChoice.UNQUALIFIED;
        } else {
            return elementFormDefault;
        }
    }

    /**
     * Sets the value of the elementFormDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormChoice }
     *     
     */
    public void setElementFormDefault(FormChoice value) {
        this.elementFormDefault = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

}
