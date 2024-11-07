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

import static com.metaformsystems.dcp.schema.issuance.CredentialObjectSchemaTest.CREDENTIAL_OBJECT;
import static com.networknt.schema.InputFormat.JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class CredentialOfferMessageSchemaTest extends AbstractSchemaTest {

    private final static String CREDENTIAL_OFFER_MESSAGE = """
            {
              "@context": ["https://w3id.org/dspace-dcp/v0.8"],
              "@type": "CredentialOfferMessage",
              "credentialIssuer": "issuer",
              "credentials": [%s]
            }""";

    private final static String INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_CREDENTIAL_ISSUER = """
            {
              "@context": ["https://w3id.org/dspace-dcp/v0.8"],
              "@type": "CredentialOfferMessage",
              "credentials": [%s]
            }""";

    private final static String INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_CREDENTIALS = """
            {
              "@context": ["https://w3id.org/dspace-dcp/v0.8"],
              "@type": "CredentialOfferMessage",
              "credentialIssuer": "issuer"
            }""";

    private final static String INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_TYPE = """
            {
              "@context": ["https://w3id.org/dspace-dcp/v0.8"],
              "credentialIssuer": "issuer",
              "credentials": [%s]
            }""";

    private final static String INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_CONTEXT = """
            {
              "@type": "CredentialOfferMessage",
              "credentialIssuer": "issuer",
              "credentials": [%s]
            }""";

    @Test
    void verifySchema() {
        assertThat(schema.validate(CREDENTIAL_OFFER_MESSAGE.formatted(CREDENTIAL_OBJECT), JSON)).isEmpty();
        assertThat(schema.validate(INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_CREDENTIAL_ISSUER.formatted(CREDENTIAL_OBJECT), JSON)).hasSize(1);
        assertThat(schema.validate(INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_TYPE.formatted(CREDENTIAL_OBJECT), JSON)).hasSize(3);
        assertThat(schema.validate(INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_CREDENTIALS, JSON)).hasSize(1);
        assertThat(schema.validate(INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_CONTEXT.formatted(CREDENTIAL_OBJECT), JSON)).hasSize(1);
    }

    @BeforeEach
    void setUp() {
        setUp("/issuance/credential-offer-message-schema.json");
    }


}
