package org.rvslab.chapter03;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BootSecurityApplicationTests {

    @Test
    public void testOAuthService() {
        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        resource.setUsername("user");
        resource.setPassword("user123");
        resource.setAccessTokenUri("http://localhost:305/oauth/token");
        resource.setClientId("trusted-client");
        resource.setClientSecret("trusted-client123");
        resource.setGrantType("password");
        resource.setScope(Arrays.asList("read", "write", "trust"));

        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource, clientContext);

        Greet greet = restTemplate.getForObject("http://localhost:305", Greet.class);
        Assert.assertEquals("Hello World!", greet.getMessage());
    }

}
