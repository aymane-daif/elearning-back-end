package daif.aymane.elearningbackend.services;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Value("${realm-admin.server-url}")
    String serverUrl;
    @Value("${realm-admin.username}")
    String username;
    @Value("${realm-admin.password}")
    String password;
    @Value("${realm-admin.realm}")
    String realm;
    @Value("${realm-admin.group-students}")
    String groupStudents;

    @Value("${realm-admin.group-instructors}")
    String groupInstructors;
    @Value("${realm-admin.client-id}")
    String clientId;

    private Keycloak getKeyCloakRealmResource() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .grantType(OAuth2Constants.PASSWORD)
                .realm(realm)
                .clientId(clientId)
                .username(username)
                .password(password)
                .resteasyClient(new ResteasyClientBuilder()
                        .connectionPoolSize(10)
                        .build()).build();
    }

    private List<UserRepresentation> getUsersByGroup(String group){
        Keycloak keycloak = getKeyCloakRealmResource();
        GroupsResource groupsResource = keycloak.realm(realm).groups();
        // get group id
        String groupId = groupsResource.groups().stream()
                .filter(singleGroup -> singleGroup.getName().equals(group))
                .map(GroupRepresentation::getId)
                .findFirst()
                .orElse("");
        //get group users for given id
        return groupsResource.group(groupId).members();
    }
    public List<UserRepresentation> getAllStudents() {
        return getUsersByGroup(groupStudents);
    }
    public List<UserRepresentation> getAllInstructors() {
        return getUsersByGroup(groupInstructors);
    }

}
