package org.picketlink.identity.federation.newmodel.saml.v2.metadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.picketlink.identity.federation.newmodel.saml.v2.assertion.AttributeType; 


/**
 * <p>Java class for AttributeAuthorityDescriptorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AttributeAuthorityDescriptorType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SAML:2.0:metadata}RoleDescriptorType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:metadata}AttributeService" maxOccurs="unbounded"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:metadata}AssertionIDRequestService" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:metadata}NameIDFormat" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:metadata}AttributeProfile" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}Attribute" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

public class AttributeAuthorityDescriptorType
extends RoleDescriptorType
{ 
   protected List<EndpointType> attributeService = new ArrayList<EndpointType>();
   protected List<EndpointType> assertionIDRequestService = new ArrayList<EndpointType>();
   protected List<String> nameIDFormat = new ArrayList<String>();
   protected List<String> attributeProfile = new ArrayList<String>();
   protected List<AttributeType> attribute = new ArrayList<AttributeType>();
   
   public AttributeAuthorityDescriptorType(List<String> protocolSupport)
   {
      super(protocolSupport);
   }

   public void addAttributeService( EndpointType endpoint )
   {
      this.attributeService.add(endpoint);
   }

   public void addAssertionIDRequestService( EndpointType endpoint )
   {
      this.assertionIDRequestService.add(endpoint);
   }

   public void addNameIDFormat( String str )
   {
      this.nameIDFormat.add(str);
   }

   public void addAttributeProfile( String str )
   {
      this.attributeProfile.add(str);
   }

   public void addAttribute( AttributeType attribute )
   {
      this.attribute.add(attribute);
   }

   /**
    * Gets the value of the attributeService property. 
    * <p>
    * Objects of the following type(s) are allowed in the list
    * {@link EndpointType }
    * 
    * 
    */
   public List<EndpointType> getAttributeService() 
   { 
      return Collections.unmodifiableList( this.attributeService );
   }

   /**
    * Gets the value of the assertionIDRequestService property. 
    * 
    * <p>
    * Objects of the following type(s) are allowed in the list
    * {@link EndpointType }
    * 
    * 
    */
   public List<EndpointType> getAssertionIDRequestService() 
   {
      return Collections.unmodifiableList( this.assertionIDRequestService );
   }

   /**
    * Gets the value of the nameIDFormat property. 
    * <p>
    * Objects of the following type(s) are allowed in the list
    * {@link String } 
    */
   public List<String> getNameIDFormat() 
   { 
      return Collections.unmodifiableList( this.nameIDFormat );
   }

   /**
    * Gets the value of the attributeProfile property. 
    * <p>
    * Objects of the following type(s) are allowed in the list
    * {@link String }
    * 
    * 
    */
   public List<String> getAttributeProfile() 
   {
      return Collections.unmodifiableList( this.attributeProfile );
   }

   /**
    * Gets the value of the attribute property. 
    * <p>
    * Objects of the following type(s) are allowed in the list
    * {@link AttributeType }
    * 
    * 
    */
   public List<AttributeType> getAttribute() 
   { 
      return Collections.unmodifiableList( this.attribute );
   }
}