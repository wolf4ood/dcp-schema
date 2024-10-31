package com.metaformsystems.dcp.context.presentation;

import com.metaformsystems.dcp.context.fixtures.AbstractJsonLdTest;
import org.junit.jupiter.api.Test;

public class PresentationContextTest extends AbstractJsonLdTest {

    @Test
    void verifyPresentationQueryMessage() {
        verifyRoundTrip("/presentation/example/presentation-query-message.json", "/presentation/presentation-query-message-schema.json");
    }

    @Test
    void verifyPresentationResponseMessage() {
        verifyRoundTrip("/presentation/example/presentation-response-message.json", "/presentation/presentation-response-message-schema.json");
    }

    @Test
    void verifyCredentialMessage() {
        verifyRoundTrip("/presentation/example/credential-message.json", "/presentation/credential-message-schema.json");
    }
}
