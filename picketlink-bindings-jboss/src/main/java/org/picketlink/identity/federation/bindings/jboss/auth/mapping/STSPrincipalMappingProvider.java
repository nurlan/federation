package org.picketlink.identity.federation.bindings.jboss.auth.mapping;

import java.security.Principal;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.mapping.MappingResult;
import org.jboss.security.mapping.providers.principal.AbstractPrincipalMappingProvider;
import org.picketlink.identity.federation.core.wstrust.auth.AbstractSTSLoginModule;
import org.picketlink.identity.federation.core.wstrust.plugins.saml.SAMLUtil;
import org.picketlink.identity.federation.saml.v2.assertion.AssertionType;
import org.picketlink.identity.federation.saml.v2.assertion.NameIDType;
import org.picketlink.identity.federation.saml.v2.assertion.SubjectType;
import org.w3c.dom.Element;

/**
 * <p>
 * This mapping provider looks at the NameID in the Assertion and
 *  returns a corresponding JBoss Principal for insertion into the Subject.
 * </p>
 * 
 * <h3>Configuration</h3>
 * <pre>{@code
 * <application-policy name="saml-issue-token">
 *   <authentication>
 *     <login-module code="org.picketlink.identity.federation.core.wstrust.auth.STSIssuingLoginModule" flag="required">
 *       <module-option name="configFile">/sts-client.properties</module-option>
 *       <module-option name="password-stacking">useFirstPass</module-option>
 *     </login-module>
 *   </authentication>
 *   <mapping>
 *     <mapping-module code="org.picketlink.identity.federation.bindings.jboss.auth.mapping.STSPrincipalMappingProvider" type="principal"/>
 *     <mapping-module code="org.picketlink.identity.federation.bindings.jboss.auth.mapping.STSGroupMappingProvider" type="role"/>
 *   </mapping>
 * </application-policy>
 * }
 * </pre>
 * 
 * @author <a href="mailto:Babak@redhat.com">Babak Mozaffari</a>
 */
public class STSPrincipalMappingProvider extends AbstractPrincipalMappingProvider
{
   private Logger log = Logger.getLogger(STSPrincipalMappingProvider.class);

   private MappingResult<Principal> result;

   @Override
   public void init(Map<String, Object> contextMap)
   {
      //No initialization needed
   }

   @Override
   public void performMapping(Map<String, Object> contextMap, Principal principal)
   {
      if (contextMap == null)
      {
         throw new IllegalArgumentException(
               "Empty context map. SAML Token must be provided in the context map to extract a Principal");
      }

      Object tokenObject = contextMap.get(AbstractSTSLoginModule.SHARED_TOKEN);
      if (!(tokenObject instanceof Element))
      {
         throw new IllegalArgumentException("Did not find a token " + Element.class.getClass().getName() + " under "
               + AbstractSTSLoginModule.SHARED_TOKEN + " in the map");
      }

      try
      {
         Element tokenElement = (Element) tokenObject;
         AssertionType assertion = SAMLUtil.fromElement(tokenElement);
         SubjectType subject = assertion.getSubject();
         if (subject != null)
         {
            for (JAXBElement<?> element : subject.getContent())
            {
               if (element.getDeclaredType().equals(NameIDType.class))
               {
                  NameIDType nameID = (NameIDType) element.getValue();
                  Principal mappedPrincipal = new SimplePrincipal(nameID.getValue());
                  result.setMappedObject(mappedPrincipal);
                  if (log.isDebugEnabled())
                  {
                     log.debug("Mapped principal to " + mappedPrincipal);
                  }
                  return;
               }
            }
         }
      }
      catch (JAXBException e)
      {
         throw new IllegalArgumentException(e);
      }
   }

   @Override
   public void setMappingResult(MappingResult<Principal> mappingResult)
   {
      this.result = mappingResult;
   }
}
