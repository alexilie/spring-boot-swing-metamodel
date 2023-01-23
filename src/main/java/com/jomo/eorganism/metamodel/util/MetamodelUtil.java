package com.jomo.eorganism.metamodel.util;

import java.util.UUID;

public class MetamodelUtil {
    private static UUID uuid;

    public static UUID getUuidInstance() {
        return uuid;
    }

    public static UUID getUuidRandon() {
        return uuid.randomUUID();
    }

    public static String getUuidRandomString() {
        return uuid.randomUUID().toString();
    }

    public static UUID getUuid() {
        return uuid;
    }

    public static void setUuid(UUID uuid) {
        MetamodelUtil.uuid = uuid;
    }

    public MetamodelUtil() {
    }

    @Override
    public String toString() {
        return "MetamodelUtil{}";
    }
}
