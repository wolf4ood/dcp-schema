/*
 *  Copyright (c) 2024 Metaform Systems, Inc.
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Metaform Systems, Inc. - initial API and implementation
 *
 */

package com.metaformsystems.dcp.schema.issuance;

import com.metaformsystems.dcp.schema.fixtures.AbstractSchemaTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.networknt.schema.InputFormat.JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class CredentialRequestMessageSchemaTest extends AbstractSchemaTest {

    private final static String CREDENTIAL_REQUEST_MESSAGE = """
            {
              "@context": ["https://w3id.org/dspace-dcp/v0.8"],
              "@type": "CredentialRequestMessage",
              "format": "jwt",
              "credentialType": ["VerifiableCredential"]
            }""";

    private final static String INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_FORMAT = """
            {
              "@context": ["https://w3id.org/dspace-dcp/v0.8"],
              "@type": "CredentialRequestMessage",
              "credentialType": ["VerifiableCredential"]
            }""";

    private final static String INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_CREDENTIAL_TYPE = """
            {
              "@context": ["https://w3id.org/dspace-dcp/v0.8"],
              "@type": "CredentialRequestMessage",
              "format": "jwt"
            }""";

    private final static String INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_TYPE = """
            {
              "@context": ["https://w3id.org/dspace-dcp/v0.8"],
              "format": "jwt",
              "credentialType": ["VerifiableCredential"]
            }""";

    private final static String INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_CONTEXT = """
            {
              "@type": "CredentialRequestMessage",
              "format": "jwt",
              "credentialType": ["VerifiableCredential"]
            }""";

    @Test
    void verifySchema() {
        assertThat(schema.validate(CREDENTIAL_REQUEST_MESSAGE, JSON)).isEmpty();
        assertThat(schema.validate(INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_FORMAT, JSON)).hasSize(1);
        assertThat(schema.validate(INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_TYPE, JSON)).hasSize(3);
        assertThat(schema.validate(INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_CREDENTIAL_TYPE, JSON)).hasSize(1);
        assertThat(schema.validate(INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_CONTEXT, JSON)).hasSize(1);
    }

    @BeforeEach
    void setUp() {
        setUp("/issuance/credential-request-message-schema.json");
    }


}
