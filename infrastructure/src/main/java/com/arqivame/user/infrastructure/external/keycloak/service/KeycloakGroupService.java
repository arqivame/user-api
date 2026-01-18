package com.arqivame.user.infrastructure.external.keycloak.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.ResponseSpec;

import com.arqivame.user.infrastructure.exception.BadRequestException;
import com.arqivame.user.infrastructure.exception.ConflictException;
import com.arqivame.user.infrastructure.exception.ForbiddenException;
import com.arqivame.user.infrastructure.exception.InternalServerError;
import com.arqivame.user.infrastructure.exception.NotFoundException;
import com.arqivame.user.infrastructure.exception.UnauthorizedException;
import com.arqivame.user.infrastructure.external.keycloak.model.Error;
import com.arqivame.user.infrastructure.external.keycloak.model.ErrorRepresentation;
import com.arqivame.user.infrastructure.external.keycloak.model.GroupRepresentation;
import com.arqivame.user.infrastructure.util.RestClientUtils;

public class KeycloakGroupService {

    private final RestClient client;
    private final String realm;

    public KeycloakGroupService(
            final RestClient client,
            final String realm) {
        this.client = client;
        this.realm = realm;
    }

    public GroupRepresentation getGroupByPath(final String path) {

        GroupRepresentation group = getGroups()
                .stream()
                .filter(g -> path.startsWith(g.path()))
                .findAny()
                .orElseThrow(() -> NotFoundException.from("Group not found for path: " + path));

        final StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append("/" + group.name());

        while (!pathBuilder.toString().equals(path)) {
            group = getGroupChildrens(group.id())
                    .stream()
                    .filter(g -> path.startsWith(g.path()))
                    .findAny()
                    .orElseThrow(() -> NotFoundException.from("Group not found for path: " + path));
            pathBuilder.append("/" + group.name());
        }

        return group;
    }

    public List<GroupRepresentation> getGroups() {
        return onStatus(client.get()
                .uri("/admin/realms/{realm}/groups", realm)
                .retrieve())
                .body(new ParameterizedTypeReference<List<GroupRepresentation>>() {
                });
    }

    public List<GroupRepresentation> getGroupChildrens(final String groupId) {
        return onStatus(client.get()
                .uri("/admin/realms/{realm}/groups/{groupId}/children", realm, groupId)
                .retrieve())
                .body(new ParameterizedTypeReference<List<GroupRepresentation>>() {
                });
    }

    private ResponseSpec onStatus(final ResponseSpec responseSpec) {
        return responseSpec

                .onStatus(
                        HttpStatus.BAD_REQUEST::isSameCodeAs,
                        (request, response) -> RestClientUtils.throwsException(
                                ErrorRepresentation.class,
                                request,
                                response,
                                error -> BadRequestException.from(error.errorMessage())))

                .onStatus(
                        HttpStatus.UNAUTHORIZED::isSameCodeAs,
                        (request, response) -> RestClientUtils.throwsException(
                                Error.class,
                                request,
                                response,
                                error -> UnauthorizedException.from(error.error())))

                .onStatus(
                        HttpStatus.FORBIDDEN::isSameCodeAs,
                        (request, response) -> RestClientUtils.throwsException(
                                Error.class,
                                request,
                                response,
                                error -> ForbiddenException.from(error.error())))

                .onStatus(
                        HttpStatus.CONFLICT::isSameCodeAs,
                        (request, response) -> RestClientUtils.throwsException(
                                ErrorRepresentation.class,
                                request,
                                response,
                                error -> ConflictException.from(error.errorMessage())))

                .onStatus(
                        HttpStatus.NOT_FOUND::isSameCodeAs,
                        (request, response) -> RestClientUtils.throwsException(
                                ErrorRepresentation.class,
                                request,
                                response,
                                error -> NotFoundException.from(error.errorMessage())))

                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        (request, response) -> RestClientUtils.throwsException(
                                String.class,
                                request,
                                response,
                                InternalServerError::from));

    }

}
