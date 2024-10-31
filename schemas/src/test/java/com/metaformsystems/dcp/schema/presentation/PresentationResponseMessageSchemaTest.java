
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

public class PresentationResponseMessageSchemaTest extends AbstractSchemaTest {

    private final static String PRESENTATION_RESPONSE_MESSAGE = """
            {
              "@context": ["https://w3id.org/dspace-dcp/v0.8"],
              "@type": "PresentationResponseMessage",
              "presentation": ["presentation1", "presentation2"]
            }""";

    private final static String PRESENTATION_RESPONSE_MESSAGE_WITH_OBJECT = """
            {
              "@context": ["https://w3id.org/dspace-dcp/v0.8"],
              "@type": "PresentationResponseMessage",
              "presentation": ["presentation1", {"@id": "presentation2"}]
            }""";

    private final static String INVALID_PRESENTATION_RESPONSE_MESSAGE_NO_PRESENTATION = """
            {
              "@context": ["https://w3id.org/dspace-dcp/v0.8"],
              "@type": "PresentationResponseMessage"
            }""";

    private final static String INVALID_PRESENTATION_RESPONSE_MESSAGE_NO_TYPE = """
            {
              "@context": ["https://w3id.org/dspace-dcp/v0.8"],
              "presentation": ["presentation1", "presentation2"]
            }""";

    private final static String INVALID_PRESENTATION_RESPONSE_MESSAGE_NO_CONTEXT = """
            {
              "@type": "PresentationResponseMessage",
              "presentation": ["presentation1", "presentation2"]
            }""";

    @Test
    void verifySchema() {
        assertThat(schema.validate(PRESENTATION_RESPONSE_MESSAGE, JSON)).isEmpty();
        assertThat(schema.validate(PRESENTATION_RESPONSE_MESSAGE_WITH_OBJECT, JSON)).isEmpty();
        assertThat(schema.validate(INVALID_PRESENTATION_RESPONSE_MESSAGE_NO_PRESENTATION, JSON)).isNotEmpty();
        assertThat(schema.validate(INVALID_PRESENTATION_RESPONSE_MESSAGE_NO_TYPE, JSON)).isNotEmpty();
        assertThat(schema.validate(INVALID_PRESENTATION_RESPONSE_MESSAGE_NO_CONTEXT, JSON)).isNotEmpty();
    }

    @BeforeEach
    void setUp() {
        setUp("/presentation/presentation-response-message-schema.json");
    }
    
}
