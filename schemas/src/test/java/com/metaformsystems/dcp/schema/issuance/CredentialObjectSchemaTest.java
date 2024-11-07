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

public class CredentialObjectSchemaTest extends AbstractSchemaTest {

    public final static String CREDENTIAL_OBJECT = """
            {
                "@context": ["https://w3id.org/dspace-dcp/v0.8"],
                "@type": "CredentialObject",
                "credentialType": ["VerifiableCredential"],
                "offerReason": "reissue",
                "bindingMethods": [
                  "did:web"
                ],
                "cryptographicSuites": [
                  "JsonWebKey2020"
                ],
                "issuancePolicy": {
                  "permission": [
                    {
                      "action": "use",
                      "constraint": {
                        "and": [
                          {
                            "leftOperand": "iatp:CredentialPrereq",
                            "operator": "eq",
                            "rightOperand": "active"
                          }
                        ]
                      }
                    }
                  ]
                }
            }""";

    private final static String INVALID_CREDENTIAL_OBJECT = """
            {
              "@context": ["https://w3id.org/dspace-dcp/v0.8"],
              "@type": "CredentialObject"
            }""";

    private final static String INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_TYPE = """
             {
                "@context": ["https://w3id.org/dspace-dcp/v0.8"],
                "credentialType": ["VerifiableCredential"],
                "offerReason": "reissue",
                "bindingMethods": [
                  "did:web"
                ],
                "cryptographicSuites": [
                  "JsonWebKey2020"
                ],
                "issuancePolicy": {
                  "permission": [
                    {
                      "action": "use",
                      "constraint": {
                        "and": [
                          {
                            "leftOperand": "iatp:CredentialPrereq",
                            "operator": "eq",
                            "rightOperand": "active"
                          }
                        ]
                      }
                    }
                  ]
                }
            }""";

    @Test
    void verifySchema() {
        assertThat(schema.validate(CREDENTIAL_OBJECT, JSON)).isEmpty();
        assertThat(schema.validate(INVALID_CREDENTIAL_OBJECT, JSON)).hasSize(5);
        assertThat(schema.validate(INVALID_CREDENTIAL_REQUEST_MESSAGE_NO_TYPE, JSON)).hasSize(3);
    }

    @BeforeEach
    void setUp() {
        setUp("/issuance/credential-object-schema.json");
    }


}
