package daif.aymane.elearningbackend.services;

import daif.aymane.elearningbackend.dto.AppUserDto;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Value("${realm-admin.server-url}")
    private String serverUrl;
    @Value("${realm-admin.username}")
    private String username;
    @Value("${realm-admin.password}")
    private String password;
    @Value("${realm-admin.realm}")
    private String realm;
    @Value("${realm-admin.group-students}")
    private String groupStudents;
    @Value("${realm-admin.group-instructors}")
    private String groupInstructors;
    @Value("${keycloak.resource}")
    private String clientId;

    public List<UserRepresentation> getAllStudents() {
        return getUsersByGroup(groupStudents);
    }
    public List<UserRepresentation> getAllInstructors() {
        return getUsersByGroup(groupInstructors);
    }

    public UserRepresentation getUserById(String userId) {
        Keycloak keycloak = getInstance();
        return keycloak.realm(realm).users().get(userId).toRepresentation();
    }

    public List<UserRepresentation> getUserByUsername(String username) {
        Keycloak keycloak = getInstance();
        // search for exact username
        return keycloak.realm(realm).users().search(username, true);
    }

    public void createUser(AppUserDto appUserDto) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(appUserDto.getPassword());
        credentialRepresentation.setTemporary(false);

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(appUserDto.getUsername());
        userRepresentation.setFirstName(appUserDto.getFirstName());
        userRepresentation.setLastName(appUserDto.getLastName());
        userRepresentation.setEmail(appUserDto.getEmail());
        userRepresentation.setCredentials(List.of(credentialRepresentation));
        userRepresentation.setEnabled(true);

        Keycloak keycloak = getInstance();
        keycloak.realm(realm).users().create(userRepresentation);
    }

    public void deleteUser(String userId) {
        Keycloak keycloak = getInstance();
        keycloak.realm(realm).users().get(userId).remove();
    }

    private Keycloak getInstance() {
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
        Keycloak keycloak = getInstance();
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

}
