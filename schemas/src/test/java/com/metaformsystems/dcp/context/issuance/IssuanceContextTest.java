package com.metaformsystems.dcp.context.issuance;

import com.metaformsystems.dcp.context.fixtures.AbstractJsonLdTest;
import org.junit.jupiter.api.Test;

public class IssuanceContextTest extends AbstractJsonLdTest {

    @Test
    void verifyCredentialRequestMessage() {
        verifyRoundTrip("/issuance/example/credential-request-message.json", "/issuance/credential-request-message-schema.json");
    }

    @Test
    void verifyCredentialOfferMessage() {
        verifyRoundTrip("/issuance/example/credential-offer-message.json", "/issuance/credential-offer-message-schema.json");
    }

    @Test
    void verifyCredentialObject() {
        verifyRoundTrip("/issuance/example/credential-object.json", "/issuance/credential-object-schema.json");
    }

    @Test
    void verifyCredentialStatus() {
        verifyRoundTrip("/issuance/example/credential-status.json", "/issuance/credential-status-schema.json");
    }

    @Test
    void verifyIssuerMetadata() {
        verifyRoundTrip("/issuance/example/issuer-metadata.json", "/issuance/issuer-metadata-schema.json");
    }

}
