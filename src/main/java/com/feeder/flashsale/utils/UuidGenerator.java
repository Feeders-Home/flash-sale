package com.feeder.flashsale.utils;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

public interface UuidGenerator {

    Base64.Encoder encoder = Base64.getUrlEncoder();

    static String newBase64Uuid() {
        UUID uuid = UUID.randomUUID();
        byte[] src = ByteBuffer.wrap(new byte[16]).putLong(uuid.getMostSignificantBits())
                .putLong(uuid.getLeastSignificantBits()).array();
        return encoder.encodeToString(src).substring(0, 22);
    }

    static String newUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
