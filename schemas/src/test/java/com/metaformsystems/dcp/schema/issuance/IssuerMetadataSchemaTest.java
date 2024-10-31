
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

public class IssuerMetadataSchemaTest extends AbstractSchemaTest {

    public final static String ISSUER_METADATA = """
            {
                "@context": ["https://w3id.org/dspace-dcp/v0.8"],
                "@type": "CredentialObject",
                "@type": "IssuerMetadata",
                "credentialIssuer": "did:web:issuer-url",
                "credentialsSupported": [%s]
            }""";

    private final static String INVALID_ISSUER_METADATA = """
            {
              "@context": ["https://w3id.org/dspace-dcp/v0.8"],
              "@type": "IssuerMetadata"
            }""";

    private final static String INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_TYPE_AND_CONTEXT = """
            {
              "credentialIssuer": "did:web:issuer-url",
              "credentialsSupported": [%s]
            }""";

    @Test
    void verifySchema() {
        assertThat(schema.validate(ISSUER_METADATA.formatted(CREDENTIAL_OBJECT), JSON)).isEmpty();
        assertThat(schema.validate(INVALID_ISSUER_METADATA, JSON)).hasSize(2);
        assertThat(schema.validate(INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_TYPE_AND_CONTEXT.formatted(CREDENTIAL_OBJECT), JSON)).hasSize(2);
    }

    @BeforeEach
    void setUp() {
        setUp("/issuance/issuer-metadata-schema.json");
    }


}
