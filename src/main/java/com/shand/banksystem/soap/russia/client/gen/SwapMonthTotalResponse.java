
package com.shand.banksystem.soap.russia.client.gen;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="SwapMonthTotalResult" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element ref="{http://www.w3.org/2001/XMLSchema}schema"/>
 *                   <any/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "swapMonthTotalResult"
})
@XmlRootElement(name = "SwapMonthTotalResponse")
public class SwapMonthTotalResponse {

    @XmlElement(name = "SwapMonthTotalResult")
    protected SwapMonthTotalResponse.SwapMonthTotalResult swapMonthTotalResult;

    /**
     * Gets the value of the swapMonthTotalResult property.
     * 
     * @return
     *     possible object is
     *     {@link SwapMonthTotalResponse.SwapMonthTotalResult }
     *     
     */
    public SwapMonthTotalResponse.SwapMonthTotalResult getSwapMonthTotalResult() {
        return swapMonthTotalResult;
    }

    /**
     * Sets the value of the swapMonthTotalResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SwapMonthTotalResponse.SwapMonthTotalResult }
     *     
     */
    public void setSwapMonthTotalResult(SwapMonthTotalResponse.SwapMonthTotalResult value) {
        this.swapMonthTotalResult = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element ref="{http://www.w3.org/2001/XMLSchema}schema"/>
     *         <any/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "schema",
        "any"
    })
    public static class SwapMonthTotalResult {

        @XmlElement(namespace = "http://www.w3.org/2001/XMLSchema", required = true)
        protected Schema schema;
        @XmlAnyElement(lax = true)
        protected Object any;

        /**
         * Gets the value of the schema property.
         * 
         * @return
         *     possible object is
         *     {@link Schema }
         *     
         */
        public Schema getSchema() {
            return schema;
        }

        /**
         * Sets the value of the schema property.
         * 
         * @param value
         *     allowed object is
         *     {@link Schema }
         *     
         */
        public void setSchema(Schema value) {
            this.schema = value;
        }

        /**
         * Gets the value of the any property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getAny() {
            return any;
        }

        /**
         * Sets the value of the any property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setAny(Object value) {
            this.any = value;
        }

    }

}
