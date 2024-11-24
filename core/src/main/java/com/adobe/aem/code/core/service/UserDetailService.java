package com.adobe.aem.code.core.service;



import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import javax.jcr.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.auth.core.spi.AuthenticationInfo;
import org.apache.sling.auth.core.spi.AuthenticationInfoPostProcessor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = AuthenticationInfoPostProcessor.class, immediate = true)
public class UserDetailService implements AuthenticationInfoPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailService.class);
    private static final String SERVICE_USER = "aem-guides-code-service";

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public void postProcess(AuthenticationInfo info, HttpServletRequest request, HttpServletResponse response) throws LoginException {
        if (info == null || info.getAuthType() == null) {
            LOGGER.debug("AuthenticationInfo is null. Skipping post processing.");
            return;
        }

        ResourceResolver resourceResolver = null;
        Session session = null;
        try {
            resourceResolver = resourceResolverFactory.getServiceResourceResolver(
                    Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, SERVICE_USER));
            session = resourceResolver.adaptTo(Session.class);
            UserManager userManager = resourceResolver.adaptTo(UserManager.class);
            Authorizable user = userManager.getAuthorizable(info.getUser());

            if (user != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                String lastLoggedIn = sdf.format(new Date());
                user.setProperty("profile/lastLoggedIn", session.getValueFactory().createValue(lastLoggedIn));
                session.save();
                LOGGER.info("Updated last logged in property for user: {}", info.getUser());
            } else {
                LOGGER.warn("User not found: {}", info.getUser());
            }
        } catch (Exception e) {
            LOGGER.error("Error updating last logged in property", e);
        } finally {
            if (session != null) {
                session.logout();
            }
            if (resourceResolver != null) {
                resourceResolver.close();
            }
        }
    }
}
