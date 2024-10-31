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

package com.metaformsystems.dcp.schema.presentation;

import com.metaformsystems.dcp.schema.fixtures.AbstractSchemaTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.networknt.schema.InputFormat.JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class PresentationQueryMessageSchemaTest extends AbstractSchemaTest {

    private final static String PRESENTATION_QUERY_MESSAGE = """
            {
              "@context": ["https://w3id.org/dspace-dcp/v0.8"],
              "@type": "PresentationQueryMessage",
              "scope": ["scope1", "scope2"]
            }""";

    private final static String INVALID_PRESENTATION_QUERY_MESSAGE_NO_SCOPE = """
            {
              "@context": ["https://w3id.org/dspace-dcp/v0.8"],
              "@type": "PresentationQueryMessage"
            }""";

    private final static String INVALID_PRESENTATION_QUERY_MESSAGE_NO_TYPE = """
            {
              "@context": ["https://w3id.org/dspace-dcp/v0.8"],
              "scope": ["scope1", "scope2"]
            }""";

    private final static String INVALID_PRESENTATION_QUERY_MESSAGE_NO_CONTEXT = """
            {
              "@type": "PresentationQueryMessage",
              "scope": ["scope1", "scope2"]
            }""";

    @Test
    void verifySchema() {
        assertThat(schema.validate(PRESENTATION_QUERY_MESSAGE, JSON)).isEmpty();
        assertThat(schema.validate(INVALID_PRESENTATION_QUERY_MESSAGE_NO_SCOPE, JSON)).isNotEmpty();
        assertThat(schema.validate(INVALID_PRESENTATION_QUERY_MESSAGE_NO_TYPE, JSON)).isNotEmpty();
        assertThat(schema.validate(INVALID_PRESENTATION_QUERY_MESSAGE_NO_CONTEXT, JSON)).isNotEmpty();
    }

    @BeforeEach
    void setUp() {
        setUp("/presentation/presentation-query-message-schema.json");
    }


}
